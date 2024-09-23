package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.model.input.AnimalDoencaInput;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalDoenca;
import fatec.sp.gov.br.anshinpet.domain.model.Doenca;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalDoencaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public AnimalDoenca toDomainObject(AnimalDoencaInput animalDoencaInput){
        return modelMapper.map(animalDoencaInput, AnimalDoenca.class);
    }

    public void copyToDomainObject(AnimalDoencaInput animalDoencaInput, AnimalDoenca animalDoenca){


        modelMapper.map(animalDoencaInput, animalDoenca);
    }
}
