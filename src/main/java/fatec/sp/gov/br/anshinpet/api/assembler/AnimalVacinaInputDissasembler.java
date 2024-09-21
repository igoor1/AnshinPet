package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.model.AnimalVacinaModel;
import fatec.sp.gov.br.anshinpet.api.model.input.AnimalVacinaInput;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalVacina;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalVacinaInputDissasembler {

    @Autowired
    private ModelMapper modelMapper;

    public AnimalVacina toDomainObject(AnimalVacinaInput animalVacinaInput){
        return modelMapper.map(animalVacinaInput, AnimalVacina.class);
    }

    public void copyToDomainObject(AnimalVacinaInput animalVacinaInput, AnimalVacina animalVacina){
        modelMapper.map(animalVacinaInput, animalVacina);
    }
}
