package com.programacao.web.fatec.api_fatec.controller;

import org.springframework.web.bind.annotation.RestController;

import com.programacao.web.fatec.api_fatec.entities.Cliente;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @GetMapping("/testeCliente1") //-> /api/clientes/testeCliente1
    public String TesteClient(){
        return "Teste Cliente";
    }
    @GetMapping("/testeCliente2/{nome}") //-> /api/clientes/testeCliente2
    public String Teste(@PathVariable String nome) {
        return nome;
    }

    @GetMapping("/testeCliente3/{Idade}")
    public String Teste_idade(@PathVariable int Idade) {
        if (Idade >= 18) {
            return "Maior de idade";
        } else {
            return "Menor de idade";
        }
    }

    @PostMapping("")
    public String createCliente(@RequestBody Cliente cliente) {
        return cliente;
    }
    
}
