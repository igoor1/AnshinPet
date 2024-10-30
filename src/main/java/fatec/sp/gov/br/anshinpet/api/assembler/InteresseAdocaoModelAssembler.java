package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.dto.InteresseAdocaoDTO;
import fatec.sp.gov.br.anshinpet.domain.model.InteresseAdocao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InteresseAdocaoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public InteresseAdocaoDTO toModel(InteresseAdocao interesseAdocao) {
        return modelMapper.map(interesseAdocao, InteresseAdocaoDTO.class);
    }

    public List<InteresseAdocaoDTO> toCollectionModel(List<InteresseAdocao> interesses) {
        return interesses.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
