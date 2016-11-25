package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.Receita;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author marisa.richter
 */
public class ReceitaDAO {
    private Connection conexao = null;
    
    private static final String SQLList = "SELECT * FROM \"Receita\"";
    private static final String SQLFindById = "SELECT * FROM Receita WHERE id=?";
    private static final String SQLFindByNome = "SELECT * FROM \"Receita\" WHERE descricao LIKE ?;";
    private static final String SQLUpdateAll = "UPDATE \"Receita\" SET descricao=?, valor=?, data=? WHERE id=?;";
    private static final String SQLDelete = "DELETE FROM \"Receita\" WHERE id=?";
    private static final String SQLCreate = "INSERT INTO \"Receita\" (descricao, valor, data) VALUES (?,?,?)";
    
    public ReceitaDAO(Connection conexao) {
        this.conexao = conexao;
    }
    public Integer createReceita(Receita receita) throws SQLException{
        Integer idReceitaCriada = 0;
        
        try(PreparedStatement stm = conexao.prepareStatement(SQLCreate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, receita.getDescricaoReceita());
            stm.setBigDecimal(2, receita.getValorReceita());
            stm.setDate(3, new java.sql.Date(receita.getDataReceita().getTime()));
            stm.execute();

            try (ResultSet resultSet = stm.getGeneratedKeys()) {
                while (resultSet.next()) {
                    idReceitaCriada = resultSet.getInt(1);
                }
            }
            conexao.commit();
            JOptionPane.showMessageDialog(null, "Receita adicionada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
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
    
    public void updateAllReceita(Receita receita) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdateAll, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, receita.getDescricaoReceita());
            stm.setBigDecimal(2, receita.getValorReceita());            
            stm.setDate(3, new java.sql.Date(receita.getDataReceita().getTime()));
            stm.setInt(4, receita.getIdReceita()); 
            stm.executeUpdate();
            
            conexao.commit();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    public Receita findByIdReceita(Integer id) throws SQLException {
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
        List<Receita> receitas = new ArrayList<>();
        Receita receita = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindByNome)){
            stm.setString(1, "%" + nome + "%");            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    
                    int id = (resultSet.getInt("id"));
                    String descricao = (resultSet.getString("descricao"));
                    BigDecimal valor = (resultSet.getBigDecimal("valor"));
                    Date data = (resultSet.getDate("data"));
                    receita = new Receita(descricao, valor, data);
                    receita.setIdReceita(id);
                    receitas.add(receita);
                }
            }
            System.out.println("teste: " + nome);
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por nome: " + ex.getMessage());
        } 
        return receitas;
    }   
}
