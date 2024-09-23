package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.model.input.DoencaInput;
import fatec.sp.gov.br.anshinpet.domain.model.Doenca;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoencaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Doenca toDomainObject(DoencaInput doencaInput){
        return modelMapper.map(doencaInput, Doenca.class);
    }

    public void copyToDomainObject(DoencaInput doencaInput, Doenca doenca){
        modelMapper.map(doencaInput, doenca);
    }
}
