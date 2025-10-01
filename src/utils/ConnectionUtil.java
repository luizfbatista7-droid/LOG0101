package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {

    // Método estático para conectar ao banco
    public static Connection conDB() {
        Connection conn = null;
        try {
            // Certifique-se de que você tem o MySQL Connector/J adicionado ao Build Path
            Class.forName("com.mysql.cj.jdbc.Driver");

            // URL do banco: jdbc:mysql://localhost:3306/nome_do_banco
            String url = "jdbc:mysql://localhost:3306/loginapp?useSSL=false&serverTimezone=UTC";
            //String url = "jdbc:mysql://localhost:3306/loginapp";
            String user = "root";        // seu usuário MySQL
            String password = "senai123";        // sua senha MySQL

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão bem-sucedida!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
        }
        return conn;
    }
}
