package com.example.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoDTO {
    private long id;
    private String descricao;
    private int qtd;
    private double preco;
}
