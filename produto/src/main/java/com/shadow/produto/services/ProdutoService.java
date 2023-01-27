package com.shadow.produto.services;

import com.shadow.produto.entities.ProdutoEntity;
import com.shadow.produto.repositories.ProdutoRepository;

import com.shadow.produto.repositories.feign.AuthFeign;
import com.shadow.produto.services.exceptionhandler.ProductNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    AuthFeign authFeign;


    public ProdutoEntity save(ProdutoEntity produtoEntity) {
        return produtoRepository.save(produtoEntity);

    }
    public Page<ProdutoEntity> findAll(Pageable pageable) { /* Consultar paginado */
        return produtoRepository.findAll(pageable);
    }

    public List<ProdutoEntity> findByNome(String nomeProduto) { /* Buscar por nome */
        return produtoRepository.buscarPorNome(nomeProduto);
    }


    public ProdutoEntity findById(String id) {

        Optional<ProdutoEntity> produto = produtoRepository.findById(id);
        return produto.orElseThrow(() -> new ProductNotFoundException());
    }
    public void delete(ProdutoEntity produtoEntity) {
        findById(produtoEntity.getId());
        produtoRepository.delete(produtoEntity);
    }

    public boolean validateToken(String token) {
        return authFeign.validateToken(token);
    }

    public String getTypeUser(String token) {
        return authFeign.getTypeUser(token);
    }

//    public boolean produtoExistente(String nomeProduto) {
//        return produtoRepository.produtoExistente(nomeProduto);
//    }
}
