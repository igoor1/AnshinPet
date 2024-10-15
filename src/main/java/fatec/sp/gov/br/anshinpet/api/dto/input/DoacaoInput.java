package fatec.sp.gov.br.anshinpet.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DoacaoInput {

    @NotBlank
    private String tipo;


    private BigDecimal valor;


    private Integer quantidade;


    private LocalDate data;

    @NotBlank
    private String descricao;

}
