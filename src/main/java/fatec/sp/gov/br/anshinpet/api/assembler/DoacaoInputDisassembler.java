package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.dto.input.DoacaoInput;
import fatec.sp.gov.br.anshinpet.domain.model.Doacao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoacaoInputDisassembler {

    @Autowired
    private  ModelMapper modelMapper;

    public Doacao toDomainObject(DoacaoInput doacaoInput){
        return modelMapper.map(doacaoInput, Doacao.class);
    }

    public void copyToDomainObject(DoacaoInput doacaoInput, Doacao doacao){
        modelMapper.map(doacaoInput, doacao);
    }
}
