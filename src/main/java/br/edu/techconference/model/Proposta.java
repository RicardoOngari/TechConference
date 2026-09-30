package br.edu.techconference.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proposta {

    private Long id;

    @NotBlank(message = "Informe o titulo")
    @Size(min = 5, max = 100, message = "O titulo deve possuir entre 5 e 100 caracteres")
    private String titulo;

    // TODO: adicionar as validacoes solicitadas no laboratorio
    private String descricao;
    private String palestrante;
    private String emailPalestrante;
    private Categoria categoria;
    private Integer duracaoMinutos;
    private Nivel nivel;

    private StatusProposta status;

    public Proposta() {
        this.status = StatusProposta.EM_ANALISE;
    }

    public void aprovar() {
        // TODO: implementar regra de negocio
    }

    public void rejeitar() {
        // TODO: implementar regra de negocio
    }
}
