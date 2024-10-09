package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.model.DoacaoModel;
import fatec.sp.gov.br.anshinpet.domain.model.Doacao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoacaoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public DoacaoModel toModel(Doacao doacao){
        return modelMapper.map(doacao, DoacaoModel.class);
    }

    public List<DoacaoModel> toCollectionModel(List<Doacao> doacoes){
        return doacoes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
