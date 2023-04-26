package br.com.letscode.teste.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @GetMapping("/detalhe")
    public Map<String, String> detalhe(@RequestParam String nome) {
        return new HashMap<>() {{
            put("nome", nome);
            put("cor", "prata");
        }};
    }
    @PostMapping
    public String criar(@RequestBody Map<String, String> dados) {
        System.out.println("Nome do carro: "+ dados.get("nome"));
        return "Carro "+ dados.get("nome") + " criado com sucesso...";
    }

    @PutMapping
    public String editar(@RequestBody Map<String , String> dados) {
        System.out.println("Nome do carro: "+ dados.get("nome"));
        return "Carro "+ dados.get("nome") + " editado com sucesso...";
    }
    @DeleteMapping
    public String deletar(@RequestParam String nome) {

        return "Carro "+ nome +" deletado com sucesso...";
    }
}