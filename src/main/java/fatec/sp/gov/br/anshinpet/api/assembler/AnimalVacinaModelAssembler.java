package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.model.AnimalVacinaModel;
import fatec.sp.gov.br.anshinpet.api.model.VacinaModel;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalVacina;
import fatec.sp.gov.br.anshinpet.domain.model.Vacina;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnimalVacinaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public AnimalVacinaModel toModel(AnimalVacina animalVacina){
        return modelMapper.map(animalVacina, AnimalVacinaModel.class);
    }

    public List<AnimalVacinaModel> toCollectionModel(List<AnimalVacina> animalVacinas){
        return animalVacinas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}





