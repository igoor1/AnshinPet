package fatec.sp.gov.br.anshinpet.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DoacaoModel {

    private Long id;
    private String tipo;
    private BigDecimal valor;
    private Integer quantidade;
    private LocalDate data;
    private String descricao;
}
