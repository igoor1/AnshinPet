package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.model.input.AnimalFotoInput;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalFoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalFotoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public AnimalFoto toDomainObject(AnimalFotoInput animalFotoInput){
        return modelMapper.map(animalFotoInput, AnimalFoto.class);
    }

    public void copyToDomainObject(AnimalFotoInput animalFotoInput, AnimalFoto animalFoto){
        modelMapper.map(animalFotoInput, animalFoto);
    }
}
