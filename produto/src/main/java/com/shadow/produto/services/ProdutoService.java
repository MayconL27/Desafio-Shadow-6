package com.shadow.produto.services;

import com.shadow.produto.entities.ProdutoEntity;
import com.shadow.produto.repositories.ProdutoRepository;
import com.shadow.produto.services.exceptions.EntityNotFoundException;
import com.shadow.produto.services.response.Handler;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    Handler handler;

    @Transactional
    public ResponseEntity save(ProdutoEntity produtoEntity) {
        Optional<ProdutoEntity> optionalProdutoEntity = Optional.ofNullable(produtoEntity);
        if (produtoEntity.getNomeProduto().isBlank() || produtoEntity.getPreco()<=0 || produtoEntity.getQuantidade()<=0) {
            handler = new Handler("insira um valor valido", Optional.of(produtoEntity));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handler);
        }
        else {
            produtoRepository.save(produtoEntity);
            handler = new Handler("Cadastrado", Optional.of(produtoEntity));
            return ResponseEntity.status(HttpStatus.CREATED).body(handler);
        }

    }
    public List<ProdutoEntity> findAll() { /* Exibir todos */
        return produtoRepository.findAll();
    }
    public Page<ProdutoEntity> findAll(Pageable pageable) { /* Consultar paginado */
        return produtoRepository.findAll(pageable);
    }
    public ProdutoEntity findById(UUID id) { /* Exibir por ID */
        return produtoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(" Id not found " + id));
    }
    public void delete(ProdutoEntity produtoEntity) { /* Deletar */
        produtoRepository.delete(produtoEntity);
    }
    public List<ProdutoEntity> findByNome(String nomeProduto) { /* Buscar por nome */
        return produtoRepository.buscarPorNome(nomeProduto);
    }

}
