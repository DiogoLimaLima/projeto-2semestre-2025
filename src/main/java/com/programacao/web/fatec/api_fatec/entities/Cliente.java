package com.programacao.web.fatec.api_fatec.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 60)
    private String nome;
    @Column(nullable = false, length = 100)
    private String Endereco;
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    @JsonIgnoreProperties("clientes")
    // Descomente a linha abaixo para obter o FORMATO 1 (sem o objeto cidade)
    //@JsonIgnore
    private Cidade cidade;

    public Cliente() {}

    public Cliente(Long id, String nome, String Endereco) {
        this.id = id;
        this.nome = nome;
        this.Endereco = Endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

}
