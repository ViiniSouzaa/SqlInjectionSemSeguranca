/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Configurations;

/**
 *
 * @author Vinicius
 */
public class ConfigurationsH2 extends Configurations{

    public ConfigurationsH2() {
        super.JDBC_DRIVER = "org.h2.Driver";
        super.DB_URL = "jdbc:h2:~/test";  
        super.USER = "admin"; 
        super.PASS = ""; 
    }
    
}
