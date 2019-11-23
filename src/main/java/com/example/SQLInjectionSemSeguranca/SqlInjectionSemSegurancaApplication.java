package com.example.SQLInjectionSemSeguranca;

import com.example.Database.DataBaseGeneric;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlInjectionSemSegurancaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqlInjectionSemSegurancaApplication.class, args);
                DataBaseGeneric dataBaseGeneric = new DataBaseGeneric();
                
                dataBaseGeneric.query(
                                    "Create table if NOT EXISTS 'produto' ( " +
                                    "id int not null auto_increment, " +
                                    "descricao varchar(255) not null, " +
                                    "qtd int not null, " + 
                                    "preco float not null, " +
                                    "PRIMARY KEY(id)"
                                );
                dataBaseGeneric.query(
                                    "Create table if NOT EXISTS 'usuario' ( " +
                                    "user varchar(255) not null, " +
                                    "senha varchar(255) not null, " + 
                                    "token varchar(255), " +
                                    "PRIMARY KEY(user)"
                                );
                dataBaseGeneric.query(
                                    "Create table if NOT EXISTS 'venda' ( " +
                                    "id int not null, " +
                                    "idProduto int not null, " +
                                    "PRIMARY KEY(id,idProduto)"
                                );
	}

}
