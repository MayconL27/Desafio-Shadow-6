package com.shadow.produto.repositories;

import com.shadow.produto.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository /* Não é precisa se jpa for extends por Jpa */
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
    @Query(value = " select u from ProdutoEntity u where trim(u.nomeProduto) like ?1%")
    List<ProdutoEntity> buscarPorNome(String nomeProduto);
}
