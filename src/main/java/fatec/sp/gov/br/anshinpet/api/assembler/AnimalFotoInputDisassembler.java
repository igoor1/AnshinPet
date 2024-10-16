package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.dto.input.FotoInput;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalFoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalFotoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public AnimalFoto toDomainObject(FotoInput fotoInput){
        return modelMapper.map(fotoInput, AnimalFoto.class);
    }

    public void copyToDomainObject(FotoInput fotoInput, AnimalFoto animalFoto){
        modelMapper.map(fotoInput, animalFoto);
    }
}
