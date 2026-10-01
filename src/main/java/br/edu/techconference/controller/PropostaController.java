package br.edu.techconference.controller;

import br.edu.techconference.model.Categoria;
import br.edu.techconference.model.Nivel;
import br.edu.techconference.model.Proposta;
import br.edu.techconference.model.StatusProposta;
import br.edu.techconference.repository.PropostaRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
//endereço principal
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
        // Cria uma Proposta vazia
        Proposta proposta = new Proposta();

        // Envia a Proposta pra View
        model.addAttribute("proposta", proposta);

        //Aqui enviamos para a View os niveis e categorias
        model.addAttribute("niveis", Nivel.values());
        model.addAttribute("categorias", Categoria.values());

        return "propostas/formulario";
    }

    @PostMapping
    public String salvar(
            @Valid @ModelAttribute("proposta") Proposta proposta,
            BindingResult result,
            Model model) {

        // Verifica se existe algum erro de validação
        if (result.hasErrors()){

            // Envia tudo novamente
            // porque o formulário será aberto novamente
            model.addAttribute("niveis", Nivel.values());
            model.addAttribute("categorias", Categoria.values());

            // Volta para o formulário
            return "propostas/formulario";
        }
        // Salva a proposta no repository
        repository.salvar(proposta);

        return "redirect:/propostas";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        // Procura a proposta pelo ID
        Proposta proposta = repository.buscarPorId(id).orElse(null);

        // Envia a proposta encontrada para a View
        model.addAttribute("proposta", proposta);

        return "propostas/detalhes";
    }

    //Rota para aprovar a proposta
    @PostMapping("/{id}/aprovar")
    public String aprovar(@PathVariable Long id){

        // Procura a proposta pelo ID
        Proposta proposta = repository.buscarPorId(id).orElse(null);

        // Aprova a proposta com nosso metodo aprovar
        proposta.aprovar();

        // Volta para propostas
        return "redirect:/propostas";
    }

    //Rota para rejeitar a proposta
    @PostMapping("/{id}/rejeitar")
    public String rejeitar(@PathVariable Long id){

        // Procura a proposta pelo ID
        Proposta proposta = repository.buscarPorId(id).orElse(null);

        // Rejeita a proposta com nosso metodo rejeitar
        proposta.rejeitar();

        // Volta para propostas
        return "redirect:/propostas";
    }


}
