package com.shadow.produto.Dtos;

import com.shadow.produto.entities.Categoria;

public record ProdutoDto(String nomeProduto, double preco, int quantidade, Categoria categoria) {
}
