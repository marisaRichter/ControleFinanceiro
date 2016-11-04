package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.Cartao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marisa.richter
 */
public class CartaoDAO {
    private Connection conexao = null;
    
    private static final String SQLList = "SELECT * FROM Cartao";
    private static final String SQLFindById = "SELECT * FROM Cartao WHERE id=?";
    private static final String SQLFindByNome = "SELECT * FROM Cartao WHERE nomeCartao=?";
    private static final String SQLUpdate = "UPDATE Cartao SET nome=?, banco=? WHERE id=?";
    private static final String SQLDelete = "DELETE FROM Cartao WHERE id=?";
    private static final String SQLCreate = "INSERT INTO Cartao (id, nome, banco) VALUES (?,?,?,?,?,?,?)";

    public CartaoDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public Integer createCartao(Cartao cartao) throws SQLException{
        Integer idCartaoCriado = 0;
        try(PreparedStatement stm = conexao.prepareStatement(SQLCreate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, cartao.getNomeCartao());
            stm.setString(2, cartao.getBanco());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        
        return idCartaoCriado;        
    }
    
    public void deleteCartao(Cartao cartao) throws SQLException{
        try(PreparedStatement stm = conexao.prepareStatement(SQLDelete, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, cartao.getIdCartao());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar delete: " + ex.getMessage());
            conexao.rollback();
        }     
    }
    
    public void updateCartao(Cartao cartao) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(3, cartao.getNomeCartao());
            stm.setString(3, cartao.getBanco());
            stm.setInt(3, cartao.getIdCartao());            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    public Cartao findByIdCartao(Integer id) throws SQLException {
        Cartao cartao = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindById, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, id);            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    cartao = new Cartao();
                    cartao.setIdCartao(resultSet.getInt("id"));
                    cartao.setNomeCartao(resultSet.getString("nome"));
                    cartao.setBanco(resultSet.getString("banco"));
                }
            }
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por id: " + ex.getMessage());
        }  
        
        return cartao;
    }
    
    public List<Cartao> findByNomeCartao(String nome) throws SQLException {
        String sql = "Select * from Pessoas p where upper(p.nome) like ?";
        List<Cartao> cartoes = new ArrayList<>();
        Cartao cartao = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindByNome, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, "%" + nome.toUpperCase() + "%");            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    cartao = new Cartao();
                    cartao.setIdCartao(resultSet.getInt("id"));
                    cartao.setNomeCartao(resultSet.getString("nome"));
                    cartao.setBanco(resultSet.getString("banco"));
                    cartoes.add(cartao);
                }
            }
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por nome: " + ex.getMessage());
        } 
        return cartoes;
    }   
}
