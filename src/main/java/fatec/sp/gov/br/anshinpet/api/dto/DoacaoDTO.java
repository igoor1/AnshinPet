package fatec.sp.gov.br.anshinpet.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DoacaoDTO {

    private Long id;
    private String tipo;
    private BigDecimal valor;
    private Integer quantidade;
    private LocalDate data;
    private String descricao;
}
