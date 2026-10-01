package br.edu.techconference.repository;

import br.edu.techconference.model.Proposta;
import br.edu.techconference.model.StatusProposta;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PropostaRepository {

    private final List<Proposta> propostas = new ArrayList<>();
    private Long contador = 1L;

    public List<Proposta> listarTodas() {
        return propostas;
    }

    public void salvar(Proposta proposta) {
        proposta.setId(contador++);
        propostas.add(proposta);
    }

    public Optional<Proposta> buscarPorId(Long id) {
        return propostas.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Proposta> listarAprovadas() {

        //Aqui usamos a mesma logica do de cima, ao inves de buscar por ID, aqui buscamos todos que estão aprovados
        return propostas.stream()
                .filter(proposta -> proposta.getStatus() == StatusProposta.APROVADA)
                .toList();
    }
}
