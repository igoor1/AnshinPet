package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.dto.DoencaDTO;
import fatec.sp.gov.br.anshinpet.domain.model.Doenca;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoencaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public DoencaDTO toModel(Doenca doenca){
        return modelMapper.map(doenca, DoencaDTO.class);
    }

    public List<DoencaDTO> toCollectionModel(List<Doenca> doencas){
        return doencas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
