package com.example.Dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class VendaDTO {
    private long id;
    private List<ProdutoDTO> produtos;
    private double precoTotal;

    public VendaDTO(long id, List<ProdutoDTO> produtos) {
        this.id = id;
        this.produtos = produtos;
        CalculaPrecoTotal();
    }
    
    
    
    public void CalculaPrecoTotal(){
        precoTotal = 0;
        produtos.stream().forEach((produto) -> {
            precoTotal += produto.getPreco() * produto.getQtd();
        });
    }
    
    
}
