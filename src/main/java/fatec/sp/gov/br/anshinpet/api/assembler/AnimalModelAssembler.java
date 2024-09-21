package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.model.AnimalModel;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnimalModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public AnimalModel toModel(Animal animal){
        return modelMapper.map(animal, AnimalModel.class);
    }

    public List<AnimalModel> toCollectionModel(List<Animal> animais){
        return animais.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
