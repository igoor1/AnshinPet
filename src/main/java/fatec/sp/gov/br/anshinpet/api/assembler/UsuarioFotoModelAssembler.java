package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.dto.UsuarioFotoDTO;
import fatec.sp.gov.br.anshinpet.domain.model.UsuarioFoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioFotoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioFotoDTO toModel(UsuarioFoto usuarioFoto){
        return modelMapper.map(usuarioFoto, UsuarioFotoDTO.class);
    }
}
