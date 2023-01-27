package com.shadow.produto.controllers;


import com.shadow.produto.entities.ProdutoEntity;
import com.shadow.produto.services.ProdutoService;
import com.shadow.produto.services.exceptionhandler.MessageExceptionHandler;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Data
public class ProdutoController {
    final ProdutoService produtoService;
    @PostMapping(value = "salvar") /* Cadastrar novos produtos */
    public ResponseEntity<Object> cadastrarProduto(@RequestBody @Valid ProdutoEntity produtoEntity,
                                                   @RequestHeader(HttpHeaders.AUTHORIZATION)String token) {
        if (!produtoService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Token inválido"));
        }
        if (produtoService.getTypeUser(token).equals("CLIENTE")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Usuário sem permição"));
        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoEntity));
        }
        catch (Error e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }

    }
    @GetMapping(value = "listartodos")
    public ResponseEntity<Object> listarTodos(@PageableDefault(page = 0, size = 5, direction = Sort.Direction.ASC) Pageable pageable,
                                              @RequestHeader(HttpHeaders.AUTHORIZATION)String token){

        if (!produtoService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Token inválido"));
        }

        return new ResponseEntity<>(produtoService.findAll(pageable),HttpStatus.OK);
    }

    @GetMapping("/{id}") // Buscar por id
    public ResponseEntity<Object> buscarID(@PathVariable(value = "id") String id,
                                           @RequestHeader(HttpHeaders.AUTHORIZATION)String token) {
        if (!produtoService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Token inválido"));
        }
        return new ResponseEntity<>(produtoService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<Object> buscarPorNome(@RequestParam(name = "nomeProduto") String nomeProduto,
                                                @RequestHeader(HttpHeaders.AUTHORIZATION)String token) {

        if (!produtoService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Token inválido"));
        }
        List<ProdutoEntity> produto = produtoService.findByNome(nomeProduto.trim()); /* trim aceita espaço depois do nome */
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findByNome(nomeProduto.trim()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") String id,
                                         @RequestHeader(HttpHeaders.AUTHORIZATION)String token) {
        if (!produtoService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Token inválido"));
        }
        if (produtoService.getTypeUser(token).equals("CLIENTE")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Usuário sem permição"));
        }
        ProdutoEntity produtoEntity = produtoService.findById(id);
        produtoService.delete(produtoEntity);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageExceptionHandler(new Date(), HttpStatus.OK.value(),"Produto deletado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id")String id) {
        ProdutoEntity produtoEntity = produtoService.findById(id);
        produtoEntity.setNomeProduto(produtoEntity.getNomeProduto());
        produtoEntity.setPreco(produtoEntity.getPreco());
        produtoEntity.setQuantidade(produtoEntity.getQuantidade());
        produtoEntity.setCategoria(produtoEntity.getCategoria());

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoEntity));

    }












}
