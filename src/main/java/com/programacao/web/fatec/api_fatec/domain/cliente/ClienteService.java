package com.programacao.web.fatec.api_fatec.domain.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.programacao.web.fatec.api_fatec.domain.cidade.CidadeRepository;
import com.programacao.web.fatec.api_fatec.entities.Cidade;
import com.programacao.web.fatec.api_fatec.entities.Cliente;

@Service //Classe de regra de negócios
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;
    //@GetMapping("") Retorna o cliente service para fazer a lista de clientes
    public List<Cliente> listarClientesH2() {
        //return listaDeCliente -usando o array;
        var clientes = clienteRepository.findAll();
        return clientes;
    }

    //Criar um método para buscar a cidade por id e usar nas classes que buscam cidade e filtram o nulo

    //@GetMapping("/buscarPorIdOuNome/{search}")
    public List<Cliente> buscarPorIdOuNomeGenerico(String search) {
        Long id = null;
        try {
            id = Long.parseLong(search);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return clienteRepository.buscarPorIdOuNome(id, search);
    }
    //@PostMapping("/buscarPorIdeNome")
    public List<Cliente> buscarPorIdeNomeH2(Cliente cliente) {
        return clienteRepository.buscarPorIdeNome(cliente.getId(), cliente.getNome());
    }    
    //@PostMapping("")
    public Cliente createClienteH2(Cliente cliente) {
        cliente.setId(null);
        // Verifica se o cliente tem uma cidade associada
        if (cliente.getCidade() != null && cliente.getCidade().getId() != null) {
            // Busca a cidade pelo ID
            Optional<Cidade> cidadeOpt = cidadeRepository.findById(cliente.getCidade().getId());

            // Se a cidade existir, associa ao cliente
            if (cidadeOpt.isPresent()) {
                cliente.setCidade(cidadeOpt.get());
            } else {
                // Se a cidade não existir, remove a associação
                cliente.setCidade(null);
            }
        }

        Cliente clienteCreated = clienteRepository.save(cliente);
        return clienteCreated;
    }
    //@PutMapping("/{id}")
    public String AtualizarClienteH2(Long id, Cliente entity) {
        //return clienteRepository.save(cliente);
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);
        if (!clienteEncontrado.isPresent()) {
            return String.format("Não encontrado ID: %s", id);
        }
        entity.setId(id);
        if (entity.getCidade() != null && entity.getCidade().getId() != null) {
            // Busca a cidade pelo ID
            Optional<Cidade> cidadeOpt = cidadeRepository.findById(entity.getCidade().getId());

            // Se a cidade existir, associa ao cliente
            if (cidadeOpt.isPresent()) {
                entity.setCidade(cidadeOpt.get());
            } else {
                // Se a cidade não existir, remove a associação
                entity.setCidade(null);
            }
        }
        clienteRepository.save(entity);
        return "Cliente atualizado com sucesso";
    }
    //@DeleteMapping("/{id}")
    public String deletarClienteH2(Long id){
        clienteRepository.deleteById(id);
        /*Método didático que usa um return do tipo String
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);
        if (clienteEncontrado.isPresent()) {
            clienteRepository.deleteById(id);
            return "Cliente Deletado";
        }
        return "Não encontrado ID: "+id;*/
        return "Cliente apagado com sucesso";
    }
}
