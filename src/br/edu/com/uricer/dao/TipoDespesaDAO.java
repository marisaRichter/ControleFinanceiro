package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.TipoDespesa;
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
public class TipoDespesaDAO {
    private Connection conexao = null;
    
    private static final String SQLList = "SELECT * FROM TipoDespesa";
    private static final String SQLFindById = "SELECT * FROM TipoDespesa WHERE id=?";
    private static final String SQLFindByNome = "SELECT * FROM TipoDespesa WHERE descricaoTipoDespesa=?";
    private static final String SQLUpdate = "UPDATE TipoDespesa SET descricaoTipoDespesa=? WHERE id=?";
    private static final String SQLDelete = "DELETE FROM TipoDespesa WHERE id=?";
    private static final String SQLCreate = "INSERT INTO TipoDespesa (id, descricaoTipoDespesa) VALUES (?,?)";

    public TipoDespesaDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public Integer createTipoDespesa(TipoDespesa tipoDespesa) throws SQLException{
        Integer idTipoDespesaCriado = 0;
        try(PreparedStatement stm = conexao.prepareStatement(SQLCreate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, tipoDespesa.getDescricaoTipoDespesa());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        
        return idTipoDespesaCriado;
    }
    
    public void deleteTipoDespesa(TipoDespesa tipoDespesa) throws SQLException{
        try(PreparedStatement stm = conexao.prepareStatement(SQLDelete, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, tipoDespesa.getIdTipoDespesa());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar delete: " + ex.getMessage());
            conexao.rollback();
        }     
    }
    
    public void updateTipoDespesa(TipoDespesa tipoDespesa) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, tipoDespesa.getDescricaoTipoDespesa());
            stm.setInt(2, tipoDespesa.getIdTipoDespesa());            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    public TipoDespesa findByIdTipoDespesa(Integer id) throws SQLException {
        TipoDespesa tipoDespesa = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindById, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, id);            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    tipoDespesa = new TipoDespesa();
                    tipoDespesa.setIdTipoDespesa(resultSet.getInt("id"));
                    tipoDespesa.setDescricaoTipoDespesa(resultSet.getString("descricaoTipoDespesa"));
                }
            }
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por id: " + ex.getMessage());
        }  
        
        return tipoDespesa;
    }
    
    public List<TipoDespesa> findByDescricaoTipoDespesa(String descricaoTipoDespesa) throws SQLException {
        List<TipoDespesa> tipoDespesas = new ArrayList<>();
        TipoDespesa tipoDespesa = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindByNome, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, "%" + descricaoTipoDespesa.toUpperCase() + "%");            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    tipoDespesa = new TipoDespesa();
                    tipoDespesa.setIdTipoDespesa(resultSet.getInt("id"));
                    tipoDespesa.setDescricaoTipoDespesa(resultSet.getString("descricaoTipoDespesa"));
                    tipoDespesas.add(tipoDespesa);
                }
            }
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por nome: " + ex.getMessage());
        } 
        return tipoDespesas;
    }
}
