package fatec.sp.gov.br.anshinpet.api.dto.input;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnimalInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String sexo;

    @NotBlank
    private String tipo;

    @PastOrPresent
    private LocalDate data;

    @NotBlank
    private String cor;

    @Size(max = 10)
    private String porte;

    @NotBlank
    private String castrado;

    @NotBlank
    private String adocao;

    @Size(max =30)
    private String raca;
}
