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
    public ResponseEntity<Object> cadastrarProduto(@RequestBody @Valid ProdutoDto produtoDto) {
        var produtoEntity = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDto, produtoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoEntity));
    }
    @GetMapping(value = "listartodos")
    public ResponseEntity<List<ProdutoEntity>> listarTodos(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(produtoService.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoEntity> buscarID2(@PathVariable UUID id) {
        ProdutoEntity produto = produtoService.findById(id);
        return new ResponseEntity<ProdutoEntity>(produto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProdutoDto produtoDto){
        Optional<ProdutoEntity> produtoOptional = Optional.ofNullable(produtoService.findById(id));
        if (!produtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        var produtoEntity = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDto, produtoEntity); /* conversão de Dto para entity*/
        produtoEntity.setId(produtoOptional.get().getId()); /* Setando Id para permanecer o mesmo */
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoEntity));
    }

    @DeleteMapping("/{codigoID}")
    public ResponseEntity<Object> delete2(@PathVariable(value = "codigoID") UUID codigoID){
        Optional<ProdutoEntity> usuariosOptional = Optional.ofNullable(produtoService.findById(codigoID));
        if (!usuariosOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
        }
        produtoService.delete(usuariosOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
    }



}
