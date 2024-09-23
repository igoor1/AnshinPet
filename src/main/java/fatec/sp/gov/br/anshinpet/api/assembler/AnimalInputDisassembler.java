package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.model.input.AnimalInput;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Animal toDomainObject(AnimalInput animalInput){
        return modelMapper.map(animalInput, Animal.class);
    }

    public void copyToDomainObject(AnimalInput animalInput, Animal animal){
        modelMapper.map(animalInput, animal);
    }
}
