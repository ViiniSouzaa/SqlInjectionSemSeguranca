package com.example.Dao;

import com.example.Configurations.ConfigurationsH2;
import com.example.Database.DataBaseGeneric;
import com.example.Dto.ProdutoDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO extends DataBaseGeneric implements AbstractDAO<ProdutoDTO>{

    public ProdutoDAO() {
        super(new ConfigurationsH2(),"Produto");
    }
    
    List<ProdutoDTO> produtos;
    
    @Override
    public void insert(ProdutoDTO entidade) {
        Map<Object, Object> mapObj =  new HashMap<>();
        mapObj.put("descricao", entidade.getDescricao());
        mapObj.put("preco", entidade.getPreco());
        mapObj.put("qtd", entidade.getQtd());
        this.genericInsert(mapObj);
    }

    @Override
    public void update(ProdutoDTO entidade) {
        Map<Object, Object> mapObj =  new HashMap<>();
        Map<Object, Object> mapConditions = new HashMap<>();
        mapObj.put("descricao", entidade.getDescricao());
        mapObj.put("preco", entidade.getPreco());
        mapObj.put("qtd", entidade.getQtd());
        mapConditions.put("id", entidade.getId());
        this.genericUpdate(mapObj,mapConditions);
    }

    @Override
    public void delete(long id) {
        Map<Object, Object> mapObj = new HashMap<>();
        mapObj.put("id", id);
        this.genericDelete(mapObj);
    }

    @Override
    public List<ProdutoDTO> getAlls() {
        produtos = new ArrayList<>();
        try{
            ResultSet rs = this.getAll();
            while(rs.next()){
                produtos.add(ProdutoDTO.builder()
                        .id(rs.getLong("id"))
                        .descricao(rs.getString("descricao"))
                        .preco(rs.getFloat("preco"))
                        .qtd(rs.getInt("qtd"))
                        .build());
            }
            return produtos;
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public List<ProdutoDTO> getByID(long id) {
        produtos = new ArrayList<>();
        try{
            ResultSet rs = this.getOne("id", id);
            while(rs.next()){
                produtos.add(ProdutoDTO.builder()
                        .id(rs.getLong("id"))
                        .descricao(rs.getString("descricao"))
                        .preco(rs.getFloat("preco"))
                        .qtd(rs.getInt("qtd"))
                        .build());
            }
            return produtos;
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
