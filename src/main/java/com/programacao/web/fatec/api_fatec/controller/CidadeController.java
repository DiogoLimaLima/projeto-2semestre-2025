package com.programacao.web.fatec.api_fatec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.programacao.web.fatec.api_fatec.domain.cidade.CidadeRepository;
import com.programacao.web.fatec.api_fatec.domain.cidade.CidadeService;
import com.programacao.web.fatec.api_fatec.entities.Cidade;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CidadeService cidadeService;
    //CRUD

    @GetMapping("cidade")
    public List<Cidade> listarCidadeH2(){
        return cidadeService.listarCidadeH2();
    }
    
    @PostMapping(value = "", consumes = "application/json")
    public Cidade createCidadeH2(@RequestBody Cidade cidade){
        return cidadeService.createCidadeH2(cidade);
    }
    @PutMapping("cidade/{id}")
    public String atualizarCidadeH2(@PathVariable Long id, @RequestBody Cidade entity){
        return cidadeService.atualizarCidadeH2(id, entity);
    }

    @DeleteMapping("cidade/{id}")
    public String deletarCidadeH2(@PathVariable Long id){
        return cidadeService.deletarCidadeH2(id);
    }

}
