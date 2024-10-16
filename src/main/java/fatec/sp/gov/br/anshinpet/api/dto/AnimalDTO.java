package fatec.sp.gov.br.anshinpet.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class AnimalDTO {

    private Long id;
    private String nome;
    private String sexo;
    private String tipo;
    private LocalDate data;
    private String cor;
    private String porte;
    private String castrado;
    private String adocao;
    private String raca;
}
