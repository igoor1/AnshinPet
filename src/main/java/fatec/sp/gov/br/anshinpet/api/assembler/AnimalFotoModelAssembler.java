package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.dto.AnimalFotoDTO;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalFoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalFotoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public AnimalFotoDTO toModel(AnimalFoto animalFoto){
        return modelMapper.map(animalFoto, AnimalFotoDTO.class);
    }
}
