package com.example.Dao;

import com.example.Configurations.ConfigurationsH2;
import com.example.Database.DataBaseGeneric;
import com.example.Dto.ProdutoDTO;
import com.example.Dto.VendaDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinicius
 */
public class VendaDAO extends DataBaseGeneric implements AbstractDAO<VendaDTO>{

    public VendaDAO() {
        super(new ConfigurationsH2(), "venda"); 
    }
   
    
    private List<VendaDTO> vendas;

    @Override
    public void insert(VendaDTO entidade) {
        Map<Object, Object> mapObj;
        long tamanho = this.getAlls().size();
        
        for(ProdutoDTO produto : entidade.getProdutos()){
            mapObj =  new HashMap<>();
            mapObj.put("id", tamanho+1);
            mapObj.put("idProduto", produto.getId());
            this.genericInsert(mapObj);
        }
    }

    @Override
    public void update(VendaDTO entidade) {
        Map<Object, Object> mapObj;
        long tamanho = entidade.getId();
        delete(entidade.getId());
        for(ProdutoDTO produto : entidade.getProdutos()){
            mapObj =  new HashMap<>();
            mapObj.put("id", tamanho);
            mapObj.put("idProduto", produto.getId());
            this.genericInsert(mapObj);
        }
    }

    @Override
    public void delete(long id) {
        Map<Object, Object> mapObj = new HashMap<>();
        mapObj.put("id", id);
        this.genericDelete(mapObj);
    }

    @Override
    public List<VendaDTO> getAlls() {
        vendas = new ArrayList<>();
        try{
            ResultSet rs = this.getAll();
            while(rs.next()){
                vendas.add(new VendaDTO(rs.getLong("id"), GetLista(rs, rs.getLong("id"))));
            }
            return vendas;
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public List<VendaDTO> getByID(long id) {
        vendas = new ArrayList<>();
        try{
            ResultSet rs = this.getOne("id", id);
            while(rs.next()){
                vendas.add(new VendaDTO(id, GetLista(rs, id)));
            }
            return vendas;
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    private List<ProdutoDTO> GetLista(ResultSet rs, long aLong) throws SQLException {
        List<ProdutoDTO> produtos = new ArrayList<>();
        while(rs.next()){
            if(rs.getLong("id") == aLong){
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtos.add(produtoDAO.getByID(rs.getLong("idProduto")).get(0));
            }
        }
        return produtos;
    }
}
