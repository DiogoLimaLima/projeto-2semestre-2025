package com.programacao.web.fatec.api_fatec.domain.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.programacao.web.fatec.api_fatec.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome(String nome);

    @Query("""
        Select c from Cliente c 
        where (c.id = :id) OR 
        (lower(c.nome) like lower(concat('%', :nome, '%')))
        """)
    List<Cliente> buscarPorIdOuNome(@Param("id") Long id, @Param("nome") String nome);
    
    @Query("""
        Select c from Cliente c 
        where (:id is null or c.id = :id) AND 
        (:nome is null or lower(c.nome) like lower(concat('%', :nome, '%')))
        """)
    List<Cliente> buscarPorIdeNome(@Param("id") Long id, @Param("nome") String nome);
}
