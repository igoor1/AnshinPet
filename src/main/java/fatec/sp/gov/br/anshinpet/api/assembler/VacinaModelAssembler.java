package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.model.VacinaModel;
import fatec.sp.gov.br.anshinpet.domain.model.Vacina;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VacinaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public VacinaModel toModel(Vacina vacina){
        return modelMapper.map(vacina, VacinaModel.class);
    }

    public List<VacinaModel> toCollectionModel(List<Vacina> vacinas){
        return vacinas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
