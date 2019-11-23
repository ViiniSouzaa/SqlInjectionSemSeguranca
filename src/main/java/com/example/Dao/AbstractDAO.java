/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Dao;

import java.util.List;

/**
 *
 * @author Vinicius
 * @param <T>
 */
public interface AbstractDAO<T>{
    public void insert(T entidade);
    
    public void update(T entidade);
    
    public void delete(long id);
    
    public List<T> getAlls();
    
    public List<T> getByID(long id);
}
