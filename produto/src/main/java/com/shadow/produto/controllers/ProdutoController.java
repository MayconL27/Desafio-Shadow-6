package com.shadow.produto.controllers;

import com.shadow.produto.Dtos.ProdutoDto;
import com.shadow.produto.entities.ProdutoEntity;
import com.shadow.produto.services.ProdutoService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Data
public class ProdutoController {
    final ProdutoService produtoService;

    @PostMapping(value = "salvar")
    public ResponseEntity<?> cadastrarProduto(@RequestBody ProdutoDto produtoDto) {
        var produtoEntity = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDto, produtoEntity); /* conversão de (Dto, entity); */
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoEntity));
    }
    @GetMapping(value = "listartodos")
    public ResponseEntity<List<ProdutoEntity>> listarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> buscarID(@PathVariable(value = "id") UUID id) {
        Optional<ProdutoEntity> produtoEntityOptional = produtoService.findById(id);
        if (!produtoEntityOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoEntityOptional.get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        Optional<ProdutoEntity> produtoOptional = produtoService.findById(id);
        if (!produtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
        }
        produtoService.delete(produtoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }

}
