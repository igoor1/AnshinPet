package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.model.AnimalDoencaModel;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalDoenca;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnimalDoencaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public AnimalDoencaModel toModel(AnimalDoenca animalDoenca){
        return modelMapper.map(animalDoenca, AnimalDoencaModel.class);
    }

    public List<AnimalDoencaModel> toCollectionModel(List<AnimalDoenca> animalDoencas){
        return animalDoencas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
