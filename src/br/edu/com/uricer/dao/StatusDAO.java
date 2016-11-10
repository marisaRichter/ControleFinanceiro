package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.StatusParcela;
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
public class StatusDAO {
    private Connection conexao = null;
    
    private static final String SQLList = "SELECT * FROM StatusParcela";
    private static final String SQLFindById = "SELECT * FROM StatusParcela WHERE id=?";
    private static final String SQLFindByNome = "SELECT * FROM StatusParcela WHERE status=?";
    private static final String SQLUpdate = "UPDATE StatusParcela SET status=? WHERE id=?";
    private static final String SQLDelete = "DELETE FROM StatusParcela WHERE id=?";
    private static final String SQLCreate = "INSERT INTO StatusParcela (status) VALUES (?)";

    public StatusDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public Integer createStatus(StatusParcela status) throws SQLException{
        Integer idStatusCriado = 0;
        try(PreparedStatement stm = conexao.prepareStatement(SQLCreate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, status.getStatusParcela());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        
        return idStatusCriado;        
    }
    
    public void deleteStatus(StatusParcela status) throws SQLException{
        try(PreparedStatement stm = conexao.prepareStatement(SQLDelete, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, status.getIdStatus());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar delete: " + ex.getMessage());
            conexao.rollback();
        }     
    }
    
    public void updateStatus(StatusParcela status) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, status.getStatusParcela());
            stm.setInt(2, status.getIdStatus());            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    public StatusParcela findByIdStatus(Integer id) throws SQLException {
        StatusParcela parcela = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindById, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, id);            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    parcela = new StatusParcela();
                    parcela.setIdStatus(resultSet.getInt("id"));
                    parcela.setStatusParcela(resultSet.getString("status"));
                }
            }
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por id: " + ex.getMessage());
        }  
        
        return parcela;
    }
    
    public List<StatusParcela> findByStatus(String nome) throws SQLException {
        List<StatusParcela> statusParcelas = new ArrayList<>();
        StatusParcela statusParcela = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindByNome, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, "%" + nome.toUpperCase() + "%");            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    statusParcela = new StatusParcela();
                    statusParcela.setIdStatus(resultSet.getInt("id"));
                    statusParcela.setStatusParcela(resultSet.getString("nome"));
                    statusParcelas.add(statusParcela);
                }
            }
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por nome: " + ex.getMessage());
        } 
        return statusParcelas;
    }
}
