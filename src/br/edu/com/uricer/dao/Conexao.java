package br.edu.com.uricer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author marisa.richter
 */
public class Conexao {
    private final static String BDDriver = "org.postgresql.Driver";
    private static String BDNome = "jdbc:postgresql://localhost:9080/controleFinanceiro";
    private String BDUsuario = "postgres";
    private String BDSenha = "C0rrea48";
    private Connection conn = null;
    private Statement stat = null;
    
    {
        try{
            Class.forName(BDDriver);            
        }catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Erro na localização do BD","Problema com Driver",JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    public Conexao(){
        try{
            conn = DriverManager.getConnection(BDNome,BDUsuario,BDSenha);
            conn.setAutoCommit(false);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Erro na conexão",JOptionPane.ERROR_MESSAGE);            
        }
    }
    
    public Connection getConexao(){
        return this.conn;
    }
}
