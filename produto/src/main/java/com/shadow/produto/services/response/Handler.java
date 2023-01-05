package com.shadow.produto.services.response;

import com.shadow.produto.entities.ProdutoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Handler {
    private String message;

    private Optional<ProdutoEntity> produtoEntityList;
}
