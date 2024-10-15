package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.dto.VacinaDTO;
import fatec.sp.gov.br.anshinpet.domain.model.Vacina;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VacinaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public VacinaDTO toModel(Vacina vacina){
        return modelMapper.map(vacina, VacinaDTO.class);
    }

    public List<VacinaDTO> toCollectionModel(List<Vacina> vacinas){
        return vacinas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
