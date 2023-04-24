package br.com.letscode.teste.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @GetMapping("/detalhe")
    public Map<String, String> detalhe(@RequestParam String nome) {
        return new HashMap<>() {{
            put("nome", nome);
            put("idade", "100");
        }};
    }
    @PostMapping
    public String criar(@RequestBody Map<String, String> dados) {
        System.out.println("Nome do animal: "+ dados.get("nome"));
        return "Animal "+ dados.get("nome") + " criado com sucesso...";
    }

    @PutMapping
    public String editar(@RequestBody Map<String , String> dados) {
        System.out.println("Nome do animal: "+ dados.get("nome"));
        return "Animal "+ dados.get("nome") + " editado com sucesso...";
    }
    @DeleteMapping
    public String deletar(@RequestParam String nome) {
        return "Animal "+ nome +" deletado com sucesso...";
    }
}