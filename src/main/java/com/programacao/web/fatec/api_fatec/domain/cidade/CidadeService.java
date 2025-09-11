package com.programacao.web.fatec.api_fatec.domain.cidade;

import java.util.List;
import java.util.Optional;

import javax.print.DocFlavor.STRING;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.programacao.web.fatec.api_fatec.entities.Cidade;

@Service //Classe de regra de negócios
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;
    //CRUD

    //@GetMapping
    public List<Cidade> listarCidadeH2(){
        var cidade = cidadeRepository.findAll();
        return cidade;
    }

    //@PostMapping
    public Cidade createCidadeH2(Cidade cidade){
        cidade.setId(null);
        Cidade cidadeCreated = cidadeRepository.save(cidade);
        return cidadeCreated;
    }

    //@PutMapping
    public String atualizarCidadeH2(Long id, Cidade entity){
        Optional<Cidade> cidadeEncontrada = cidadeRepository.findById(id);
        if (!cidadeEncontrada.isPresent()) {
            return String.format("Não encontrado ID: %s", id);
        }
        entity.setId(id);
        cidadeRepository.save(entity);
        return "Cidade atualizada com sucesso";
    }
    //@DeleteMapping
    public String deletarCidadeH2(Long id){
        cidadeRepository.deleteById(id);
        return "Cidade apagada com sucesso";
    }
}
