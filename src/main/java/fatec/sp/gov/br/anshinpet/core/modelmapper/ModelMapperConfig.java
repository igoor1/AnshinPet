package fatec.sp.gov.br.anshinpet.core.modelmapper;

import fatec.sp.gov.br.anshinpet.api.dto.AnimalDTO;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Animal.class, AnimalDTO.class)
                .addMapping(Animal::getNome, AnimalDTO::setNome);
        return modelMapper;
    }
}
