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
    public ResponseEntity<ProdutoEntity> buscarID(@PathVariable UUID id) {
        ProdutoEntity produto = produtoService.findById(id);
        return new ResponseEntity<ProdutoEntity>(produto, HttpStatus.OK);
    }
    @DeleteMapping("/{codigoID}")
    public ResponseEntity<Object> delete(@PathVariable(value = "codigoID") UUID codigoID){
        Optional<ProdutoEntity> usuariosOptional = Optional.ofNullable(produtoService.findById(codigoID));
        if (!usuariosOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
        }
        produtoService.delete(usuariosOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }

}
