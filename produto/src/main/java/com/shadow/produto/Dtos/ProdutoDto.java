package com.shadow.produto.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProdutoDto {

    @NotBlank
    private String nomeProduto;
    @NotBlank
    private double preco;
    @NotBlank
    private int quantidade;

}
