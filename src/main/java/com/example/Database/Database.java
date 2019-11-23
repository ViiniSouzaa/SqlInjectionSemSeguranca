/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Database;

import com.example.Configurations.Configurations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vinicius
 */
public class Database {
    private String JDBC_DRIVER;
    private String DB_URL;
    private String USER;
    private String PASS;
    
    Connection dbConnection = null; 
    Statement dbStatement = null; 
    
    Boolean connected;
    Configurations config;

    public Database() {
    }

    public Database(Configurations config) {
        this.config = config;
        this.connect();
    }
    
    public void connect(){
        this.JDBC_DRIVER =  this.config.JDBC_DRIVER;
        this.DB_URL =       this.config.DB_URL;
        this.USER =         this.config.USER;
        this.PASS =         this.config.PASS;
        
        try{
            Class.forName(JDBC_DRIVER);
            this.dbConnection = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            this.dbStatement = dbConnection.createStatement();
        }catch(ClassNotFoundException ex){
            System.out.println("Houve um erro ao carregar o driver: " + ex.getMessage());
        }
        catch(SQLException ex){
            System.out.println("Houve um erro ao tentar relizar uma conexão: " + ex.getMessage());
        }
    }
    
    public ResultSet query(String sql){
        try {
            this.dbStatement = dbConnection.createStatement();
            return this.dbStatement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Houve um erro ao tentar executar um sql (consulta) : " + ex.getMessage());
            System.out.println("[SQL] " + sql);
        }
        return null;
    }
    
//    public ResultSet query(String sql, Object... params){
//        try {
//            this.dbPreparedStm = this.dbConnection.prepareStatement(sql);
//            int i = 1;
//            for (Object o:params)
//                this.dbPreparedStm.setObject(i++, o);
//            return this.dbPreparedStm.executeQuery();
//        } catch (SQLException ex) {
//            System.out.println("Houve um erro ao tentar executar um sql (consulta) : " + ex.getMessage());
//            System.out.println("[SQL] " + sql);
//        }
//        return null;
//    }
//    
//    public void execute(String sql, Object... params){
//        try {
//            this.dbPreparedStm = this.dbConnection.prepareStatement(sql);
//            int i = 1;
//            for (Object o:params)
//                this.dbPreparedStm.setObject(i++, o);
//            this.dbPreparedStm.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("Houve um erro ao tentar executar um sql (execução) : " + ex.getMessage());
//            System.out.println("[SQL] " + sql);
//        }
//    }
//    
//    public void execute(String sql, ArrayList<Object> params){
//        try {
//            this.dbPreparedStm = this.dbConnection.prepareStatement(sql);
//            int i = 1;
//            for (Object o:params)
//                this.dbPreparedStm.setObject(i++, o);
//            this.dbPreparedStm.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("Houve um erro ao tentar executar um sql (execução) : " + ex.getMessage());
//            System.out.println("[SQL] " + sql);
//        }
//    }
    
    public void disconnect() throws SQLException{
        this.dbConnection.close();
        this.connected = false;
    }
}
