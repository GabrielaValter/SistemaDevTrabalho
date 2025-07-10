package br.csi.sistemadev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBancoDados {

    public static Connection conectarBancoPostgres()
        throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        System.out.println("Conectando com o banco de dados");

        String url = "jdbc:postgresql://localhost:5432/sistemadev";
        String user = "postgres";
        String senha = "1234";

        return DriverManager.getConnection(url, user, senha);
    }
}
