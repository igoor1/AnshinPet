package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.dto.input.InteresseAdocaoInput;
import fatec.sp.gov.br.anshinpet.domain.model.InteresseAdocao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InteresseInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public InteresseAdocao toDomainObject(InteresseAdocaoInput interesseAdocaoInput) {
        return modelMapper.map(interesseAdocaoInput, InteresseAdocao.class);
    }

    public void copyToDomainObject(InteresseAdocaoInput interesseAdocaoInput, InteresseAdocao interesseAdocao) {
        modelMapper.map(interesseAdocaoInput, interesseAdocao);
    }
}
