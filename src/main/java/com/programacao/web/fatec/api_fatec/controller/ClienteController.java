package com.programacao.web.fatec.api_fatec.controller;

import org.springframework.web.bind.annotation.RestController;

import com.programacao.web.fatec.api_fatec.domain.cidade.CidadeRepository;
import com.programacao.web.fatec.api_fatec.domain.cliente.ClienteRepository;
import com.programacao.web.fatec.api_fatec.domain.cliente.ClienteService;
import com.programacao.web.fatec.api_fatec.entities.Cidade;
import com.programacao.web.fatec.api_fatec.entities.Cliente;
import com.programacao.web.fatec.api_fatec.entities.Estado;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteService clienteService;

     @PostConstruct()
    public void dadosIniciais() {
        // Criando 5 cidades de exemplo com diferentes estados
        Cidade saoPaulo = cidadeRepository.save(new Cidade(null, "São Paulo", Estado.SP));
        Cidade rioDeJaneiro = cidadeRepository.save(new Cidade(null, "Rio de Janeiro", Estado.RJ));
        Cidade beloHorizonte = cidadeRepository.save(new Cidade(null, "Belo Horizonte", Estado.MG));
        Cidade salvador = cidadeRepository.save(new Cidade(null, "Salvador", Estado.BA));
        Cidade fortaleza = cidadeRepository.save(new Cidade(null, "Fortaleza", Estado.CE));

        // Criando clientes associados às cidades
        // Observe como o relacionamento é estabelecido passando a cidade como parâmetro
        clienteRepository.save(new Cliente(null, "Danilo", "Av. Paulista, 1000", saoPaulo));
        clienteRepository.save(new Cliente(null, "Maria", "Rua Copacabana, 500", rioDeJaneiro));
        clienteRepository.save(new Cliente(null, "João", "Av. Afonso Pena, 123", beloHorizonte));
        clienteRepository.save(new Cliente(null, "Ana", "Rua Chile, 45", salvador));
        clienteRepository.save(new Cliente(null, "Pedro", "Av. Beira Mar, 789", fortaleza));
    }
    
    /*
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
    */
    //Uso de cliente Repository para comunicação o H2
    @GetMapping("") // Retorna o cliente service para fazer a lista de clientes
    public List<Cliente> listarClientesH2() {
        //return listaDeCliente -usando o array;
        return clienteService.listarClientesH2();
    }

    @GetMapping("/buscarPorIdOuNome/{search}")
    public List<Cliente> buscarPorIdOuNomeGenerico(@PathVariable String search) {
        return clienteService.buscarPorIdOuNomeGenerico(search);
    }

    @PostMapping("/buscarPorIdeNome")
    public List<Cliente> buscarPorIdeNomeH2(@RequestBody Cliente cliente) {
        return clienteService.buscarPorIdeNomeH2(cliente);
    }    

    @PostMapping("")
    public Cliente createClienteH2(@RequestBody Cliente cliente) {
        return clienteService.createClienteH2(cliente);
    }
    
    @PutMapping("/{id}")
    public String AtualizarClienteH2(@PathVariable Long id, @RequestBody Cliente entity) {
        return clienteService.AtualizarClienteH2(id, entity);
    }

    @DeleteMapping("/{id}")
    public String deletarClienteH2(@PathVariable Long id){
        return clienteService.deletarClienteH2(id);
    }
    
}