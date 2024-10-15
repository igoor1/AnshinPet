package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.dto.AnimalVacinaDTO;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalVacina;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnimalVacinaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public AnimalVacinaDTO toModel(AnimalVacina animalVacina){
        return modelMapper.map(animalVacina, AnimalVacinaDTO.class);
    }

    public List<AnimalVacinaDTO> toCollectionModel(List<AnimalVacina> animalVacinas){
        return animalVacinas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}





