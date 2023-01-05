package com.shadow.produto.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoDto {


    private String nomeProduto;

    private double preco;

    private int quantidade;

}
