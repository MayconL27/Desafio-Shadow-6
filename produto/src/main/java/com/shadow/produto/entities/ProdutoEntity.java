package com.shadow.produto.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "PRODUTOS")
@Data
public class ProdutoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; /* Codigo do produto */
    @Column(nullable = false)
    private String nomeProduto;
    @Column(nullable = false)
    private double preco;
    @Column(nullable = false)
    private int quantidade;

}
