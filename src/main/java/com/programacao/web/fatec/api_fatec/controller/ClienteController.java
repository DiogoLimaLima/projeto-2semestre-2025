package com.programacao.web.fatec.api_fatec.controller;

import org.springframework.web.bind.annotation.RestController;

import com.programacao.web.fatec.api_fatec.domain.cliente.ClienteRepository;
import com.programacao.web.fatec.api_fatec.entities.Cliente;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final List<Cliente> listaDeCliente = new ArrayList<>();

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteController(){
        listaDeCliente.add(new Cliente(1L, "Betera", "Rua Jaraguará"));
        listaDeCliente.add(new Cliente(2L, "Jerine", "Av. dos Lagos Secos"));
        //Exemplo set
        //Cliente cliente2 = new Cliente();
        //Cliente2.setId(2L);
        //Cliente2.setNome("Jerine");
        //listaDeCliente.add(Cliente2);
    } 

    @PostConstruct
    public void dadosiniciais(){
        clienteRepository.save(new Cliente(null, "Betera", "Rua Jaraguará"));
        clienteRepository.save(new Cliente(null, "Jerine", "Av. dos Lagos Secos"));
    }

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
    public String createCliente(@RequestBody String cliente) {
        return cliente;
    }

    @PostMapping("/criarClientes")
    public Cliente criarCliente(@RequestBody Cliente cliente) {
            listaDeCliente.add(cliente);
        return cliente;
    }
    
    @DeleteMapping("/deletarCliente/{id}")
    public String deletarCliente(@PathVariable Long id){
        for(Cliente cliente: listaDeCliente){
            if (cliente.getId() == id) {
                listaDeCliente.remove(cliente);
                return "Apagado";
            }
        }
        return "Não encontrado ID: "+id;
    }

    @PutMapping("alterarCliente/{id}")
    public String alterarCliente(@PathVariable Long id, @RequestBody Cliente entity) {
        for(Cliente cliente: listaDeCliente){
            if (cliente.getId() == id) {
                entity.setId(id);
                cliente.setNome(entity.getNome());
                return "Atualizado";
            }
        }
        return "Não encontrado ID: "+id;
    }
    //Uso de cliente Repository para comunicação o H2
    @GetMapping("/listarClientes") // Já usa a variável clienteRepository para realizar a comunicação com banco
    public List<Cliente> listarClientesH2() {
        //return listaDeCliente -usando o array;
        var clientes = clienteRepository.findAll();

        return clientes;
    }
}