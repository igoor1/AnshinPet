package fatec.sp.gov.br.anshinpet.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageDTO {
    private List<?> conteudo;
    private long totalItens;
    private int totalPaginas;
    private int paginaAtual;
    private Integer proximo;
    private Integer anterior;
}
