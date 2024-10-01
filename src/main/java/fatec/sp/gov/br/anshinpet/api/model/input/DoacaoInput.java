package fatec.sp.gov.br.anshinpet.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DoacaoInput {

    @NotBlank
    private String tipo;

    @Null
    private BigDecimal valor;

    @Null
    private Integer quantidade;

    @NotBlank
    private LocalDate data;

    @NotBlank
    private String descricao;

}
