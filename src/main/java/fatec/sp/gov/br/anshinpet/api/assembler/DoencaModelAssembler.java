package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.model.DoencaModel;
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

    public DoencaModel toModel(Doenca doenca){
        return modelMapper.map(doenca, DoencaModel.class);
    }

    public List<DoencaModel> toCollectionModel(List<Doenca> doencas){
        return doencas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
