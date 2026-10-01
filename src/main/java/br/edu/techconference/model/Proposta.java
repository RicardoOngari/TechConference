package br.edu.techconference.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Proposta {

    private Long id;

    @NotBlank(message = "Informe o titulo")
    @Size(min = 5, max = 100, message = "O titulo deve possuir entre 5 e 100 caracteres")
    private String titulo;

    //Validação descrição
    @NotBlank(message = "Descrição obrigatoria")
    @Size(min = 20, max = 500, message = "A descrição deve possuir entre 20 a 500 caracteres")
    private String descricao;

    //Validação palestrante
    @NotBlank(message = "Palestrante obrigatorio")
    @Size(min = 3, message = "O palestrante deve possuir no minimo 3 caracteres")
    private String palestrante;

    //Validação emailPalestrante
    @NotBlank(message = "Email obrigatorio")
    @Email(message = "Formato valido")
    private String emailPalestrante;

    //Validação categoria
    @NotNull(message = "Categoria obrigatorio")
    private Categoria categoria;

    //Validação duracaoMinutos
    @NotNull(message = "Duração obrigatoria")
    @Min(value = 20, message = "No minimo 20 minutos")
    @Max(value = 120, message = "No maximo 120 minutos")
    private Integer duracaoMinutos;

    //Validação Nivel
    @NotNull(message = "Nivel obrigatorio")
    private Nivel nivel;


    private StatusProposta status;

    public Proposta() {
        this.status = StatusProposta.EM_ANALISE;
    }

    //Aqui criamos uma condição que só e possivel aprovar se o Status estiver em analise
    public void aprovar() {

        if (status == StatusProposta.EM_ANALISE){
            status = StatusProposta.APROVADA;
        }
    }

    //Aqui criamos uma condição que só e possivel rejeitar se o Status estiver em analise
    public void rejeitar() {

        if (status == StatusProposta.EM_ANALISE){
            status = StatusProposta.REJEITADA;
        }
    }
}
