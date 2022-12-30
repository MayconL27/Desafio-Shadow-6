package com.shadow.produto.services;

import com.shadow.produto.entities.ProdutoEntity;
import com.shadow.produto.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ProdutoEntity> findAll() {
        return produtoRepository.findAll();
    }

    public void delete(ProdutoEntity produtoEntity) {
        produtoRepository.delete(produtoEntity);
    }

    public Optional<ProdutoEntity> findById(UUID id) {
        return produtoRepository.findById(id);
    }
}
