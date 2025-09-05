package com.programacao.web.fatec.api_fatec.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="clientes")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 60)
    private String nome;
    @Column(nullable = false, length = 100)
    private String endereco;
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    @JsonIgnoreProperties("clientes")
    // Descomente a linha abaixo para obter o FORMATO 1 (sem o objeto cidade)
    //@JsonIgnore
    private Cidade cidade;
    
    public Cliente(Long id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Cliente(Long id, String nome, String endereco, Cidade cidade) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
    }

    @JsonProperty("cidade_nome")
    // Descomente a linha abaixo para obter o FORMATO 2 (sem o campo cidade_nome)
    //@JsonIgnore
    public String getCidadeNome() {
        return cidade != null ? cidade.getNome() : null;
    }

    @JsonProperty("cidade_estado")
    // Descomente a linha abaixo para obter o FORMATO 2 (sem o campo cidade_estado)
    //@JsonIgnore
    public Estado getCidadeEstado() {
        return cidade != null ? cidade.getEstado() : null;
    }

    @JsonProperty("cidade_id")
    // Descomente a linha abaixo para obter o FORMATO 2 (sem o campo cidade_id)
    // @JsonIgnore
    public Long getCidadeId() {
        return cidade != null ? cidade.getId() : null;
    }

}
