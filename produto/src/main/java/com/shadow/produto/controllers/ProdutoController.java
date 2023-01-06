package com.shadow.produto.controllers;

import com.shadow.produto.Dtos.ProdutoDto;
import com.shadow.produto.entities.Categoria;
import com.shadow.produto.entities.ProdutoEntity;
import com.shadow.produto.services.ProdutoService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Data
public class ProdutoController {
    final ProdutoService produtoService;
    @PostMapping(value = "salvar") /* Cadastrar novos produtos */
    public ResponseEntity<?> cadastrarProduto(@RequestBody ProdutoDto produtoDto) {
        //var categoriaTratada = Categoria.valueOfName(produtoDto.categoria());
        var produtoEntity = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDto, produtoEntity); /* conversão de (Dto, entity); */
        return produtoService.save(produtoEntity);
    }
    @GetMapping(value = "listartodos")
    public ResponseEntity<Page<ProdutoEntity>> listarTodos(@PageableDefault( /* Retorna um Page */
            page = 0, size = 5, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll(pageable));
    }
    @GetMapping(value = "/{id}") /* Consultar por id */
    public ResponseEntity<ProdutoEntity> buscarID(@PathVariable UUID id) {
        ProdutoEntity produto = produtoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }
    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<ProdutoEntity>> buscarPorNome(@RequestParam(name = "nomeProduto") String nomeProduto) { /* Recebe os dados para consultar */
        List<ProdutoEntity> produto = produtoService.findByNome(nomeProduto.trim()); /* trim aceita espaço depois do nome */
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findByNome(nomeProduto.trim()));
    }
    @GetMapping(value = "listartodospage") /* Paginação */
    public ResponseEntity<Page<ProdutoEntity>> getAllParkingSpots(@PageableDefault( /* Retorna um Page */
            page = 0, size = 5, sort = "nomeProduto", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll(pageable));
    }
    @DeleteMapping("/{id}") /* Excluir */
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id){
        ProdutoEntity produto = produtoService.findById(id);
        produtoService.delete(produto);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }
    @PutMapping("/{id}") /* Atualizar */
    public ResponseEntity<ResponseEntity> atualizar(@PathVariable(value = "id") UUID id , @RequestBody ProdutoDto produtoDto) {
        ProdutoEntity produto = produtoService.findById(id);
        var produtoEntity = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDto, produtoEntity);
        produtoEntity.setId(produto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoEntity));
    }
}
