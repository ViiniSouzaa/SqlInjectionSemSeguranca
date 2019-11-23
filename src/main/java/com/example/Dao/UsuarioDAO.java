package com.example.Dao;

import com.example.Configurations.ConfigurationsH2;
import com.example.Database.DataBaseGeneric;
import com.example.Dto.UsuarioDTO;
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
public class UsuarioDAO extends DataBaseGeneric implements AbstractDAO<UsuarioDTO>{

    public UsuarioDAO() {
        super(new ConfigurationsH2(),"usuario");
    }
    
    List<UsuarioDTO> usuarios;
    
    @Override
    public void insert(UsuarioDTO entidade) {
        Map<Object, Object> mapObj =  new HashMap<>();
        mapObj.put("user", entidade.getUser());
        mapObj.put("senha", entidade.getSenha());
        this.genericInsert(mapObj);
    }

    @Override
    public void update(UsuarioDTO entidade) {
        Map<Object, Object> mapObj =  new HashMap<>();
        Map<Object, Object> mapCondicions = new HashMap<>();
        mapObj.put("senha", entidade.getSenha());
        mapCondicions.put("user", entidade.getUser());
        this.genericUpdate(mapObj,mapCondicions);
    }

    @Override
    public void delete(long id) {
        Map<Object, Object> mapObj = new HashMap<>();
        mapObj.put("id", id);
        this.genericDelete(mapObj);
    }

    @Override
    public List<UsuarioDTO> getAlls() {
        usuarios = new ArrayList<>();
        try{
            ResultSet rs = this.getAll();
            while(rs.next()){
                usuarios.add(new UsuarioDTO(rs.getString("user"), rs.getString("senha"),""));
            }
            return usuarios;
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

   
    
    public List<UsuarioDTO> getByUser(String user) {
        usuarios = new ArrayList<>();
        try{
            ResultSet rs = this.getLike("user", user);
            while(rs.next()){
                usuarios.add(new UsuarioDTO(rs.getString("user"), rs.getString("senha"),""));
            }
            return usuarios;
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<UsuarioDTO> getByID(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
