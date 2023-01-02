package com.shadow.produto.services;

import com.shadow.produto.entities.ProdutoEntity;
import com.shadow.produto.repositories.ProdutoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
@Data
public class ProdutoService {
    @Autowired
    final ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoEntity save(ProdutoEntity produtoEntity) {
        return produtoRepository.save(produtoEntity);
    }
    public List<ProdutoEntity> findAll() { /* Exibir todos */
        return produtoRepository.findAll();
    }
    public Page<ProdutoEntity> findAll(Pageable pageable) { /* Consultar paginado */
        return produtoRepository.findAll(pageable);
    }
    public void delete(ProdutoEntity produtoEntity) {
        produtoRepository.delete(produtoEntity);
    }

    public Optional<ProdutoEntity> findById(UUID id) { /* Exibir por ID */
        return produtoRepository.findById(id);
    }
    public List<ProdutoEntity> findByNome(String descricao) { /* Buscar por nome */
        return produtoRepository.buscarPorNome(descricao);
    }
}
