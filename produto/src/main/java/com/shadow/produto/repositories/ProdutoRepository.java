package com.shadow.produto.repositories;

import com.shadow.produto.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository /* Não é precisa se ja for extends por Jpa */
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> { /* <Entity , identificador >*/

}
