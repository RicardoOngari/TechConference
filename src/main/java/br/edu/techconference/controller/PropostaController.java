package br.edu.techconference.controller;

import br.edu.techconference.model.Categoria;
import br.edu.techconference.model.Nivel;
import br.edu.techconference.model.Proposta;
import br.edu.techconference.repository.PropostaRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/propostas")
public class PropostaController {

    private final PropostaRepository repository;

    public PropostaController(PropostaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("propostas", repository.listarTodas());
        return "propostas/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        // TODO: disponibilizar proposta, categorias e niveis
        return "propostas/formulario";
    }

    @PostMapping
    public String salvar(
            @Valid @ModelAttribute("proposta") Proposta proposta,
            BindingResult result,
            Model model) {

        // TODO: tratar os erros de validacao e salvar quando valido
        return "redirect:/propostas";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        // TODO: buscar proposta e enviar para a view
        return "propostas/detalhes";
    }

    // TODO: criar as rotas POST /{id}/aprovar e /{id}/rejeitar
}
