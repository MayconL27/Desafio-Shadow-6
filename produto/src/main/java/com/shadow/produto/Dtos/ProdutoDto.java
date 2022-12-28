package com.shadow.produto.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProdutoDto {

    private String descricao;

    private double preco;

    private int quantidade;

}
