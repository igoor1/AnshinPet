package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.model.input.VacinaInput;
import fatec.sp.gov.br.anshinpet.domain.model.Vacina;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VacinaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Vacina toDomainObject(VacinaInput vacinaInput){
        return modelMapper.map(vacinaInput, Vacina.class);
    }

    public void copyToDomainObject(VacinaInput vacinaInput, Vacina vacina){
        modelMapper.map(vacinaInput, vacina);
    }
}
