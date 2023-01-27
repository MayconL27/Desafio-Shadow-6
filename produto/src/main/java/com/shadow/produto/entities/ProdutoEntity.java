package com.shadow.produto.entities;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "PRODUTOS")
@Data
public class ProdutoEntity{

    @Id
    @GenericGenerator(name="UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String id; /* Codigo do produto */
    @Column(nullable = false)
    @NotBlank
    private String nomeProduto;
    @Column(nullable = false)
    private double preco;
    @Column(nullable = false)
    private int quantidade;
    private Categoria categoria;


}
