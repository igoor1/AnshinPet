package fatec.sp.gov.br.anshinpet.api.assembler;

import fatec.sp.gov.br.anshinpet.api.dto.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageModelAssembler {

    public PageDTO toModel(Page<?> pages){
        PageDTO pageDTO = new PageDTO();
        pageDTO.setConteudo(pages.getContent());
        pageDTO.setTotalPaginas(pages.getTotalPages());
        pageDTO.setTotalItens(pages.getTotalElements());
        pageDTO.setPaginaAtual(pages.getNumber());
        pageDTO.setProximo(pages.hasNext() ? pages.getNumber()+1 : null);
        pageDTO.setAnterior(pages.hasPrevious() ? pages.getNumber()-1 : null);
        return pageDTO;
    }
}
