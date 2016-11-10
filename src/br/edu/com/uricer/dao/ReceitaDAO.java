package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.Receita;
import java.sql.Connection;
import java.sql.Date;
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
public class ReceitaDAO {
    private Connection conexao = null;
    
    private static final String SQLList = "SELECT * FROM Receita";
    private static final String SQLFindById = "SELECT * FROM Receita WHERE id=?";
    private static final String SQLFindByNome = "SELECT * FROM Receita WHERE descricao=?";
    private static final String SQLUpdateAll = "UPDATE Receita SET descricao=?, valor=?, dataReceita=? WHERE id=?";
    private static final String SQLUpdateDescricao = "UPDATE Receita SET descricao=? WHERE id=?";
    private static final String SQLUpdateValor = "UPDATE Receita SET valor=? WHERE id=?";
    private static final String SQLUpdateDataReceita = "UPDATE Receita SET dataReceita=? WHERE id=?";
    private static final String SQLDelete = "DELETE FROM Receita WHERE id=?";
    private static final String SQLCreate = "INSERT INTO Receita (id, descricao, valor, dataReceita) VALUES (?,?,?,?)";
    
    public ReceitaDAO(Connection conexao) {
        this.conexao = conexao;
    }
    public Integer createReceita(Receita receita) throws SQLException{
        Integer idReceitaCriada = 0;
        
        try(PreparedStatement stm = conexao.prepareStatement(SQLCreate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(2, receita.getDescricaoReceita());
            stm.setBigDecimal(3, receita.getValorReceita());
            stm.setDate(4, (Date) receita.getDataReceita());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        return idReceitaCriada;        
    }
    public void deleteReceita(Receita receita) throws SQLException{
        try(PreparedStatement stm = conexao.prepareStatement(SQLDelete, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, receita.getIdReceita());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar delete: " + ex.getMessage());
            conexao.rollback();
        }     
    }
    public void updateDataReceita(Receita receita) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdateAll, Statement.RETURN_GENERATED_KEYS)){
            stm.setDate(1, (Date) receita.getDataReceita());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    public void updateValorReceita(Receita receita) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdateAll, Statement.RETURN_GENERATED_KEYS)){
            stm.setBigDecimal(1, receita.getValorReceita()); 
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    public void updateDescricaoReceita(Receita receita) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdateAll, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, receita.getDescricaoReceita());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    public void updateAllReceita(Receita receita) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdateAll, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, receita.getDescricaoReceita());
            stm.setBigDecimal(2, receita.getValorReceita());            
            stm.setDate(3, (Date) receita.getDataReceita());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    public Receita findByIdReceira(Integer id) throws SQLException {
        Receita receita = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindById, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, id);            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    receita = new Receita();
                    receita.setIdReceita(resultSet.getInt("id"));
                    receita.setDescricaoReceita(resultSet.getString("descricao"));
                    receita.setValorReceita(resultSet.getBigDecimal("valor"));
                    receita.setDataReceita(resultSet.getDate("dataReceita"));
                }
            }
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por id: " + ex.getMessage());
        }  
        
        return receita;
    }
    
    public List<Receita> findByDescricao(String nome) throws SQLException {
        String sql = "Select * from Pessoas p where upper(p.nome) like ?";
        List<Receita> receitas = new ArrayList<>();
        Receita receita = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindByNome, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, "%" + nome.toUpperCase() + "%");            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    receita = new Receita();
                    receita.setIdReceita(resultSet.getInt("id"));
                    receita.setDescricaoReceita(resultSet.getString("descrica"));
                    receita.setValorReceita(resultSet.getBigDecimal("valor"));
                    receita.setDataReceita(resultSet.getDate("dataReceita"));
                    receitas.add(receita);
                }
            }
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por nome: " + ex.getMessage());
        } 
        return receitas;
    }   
}
