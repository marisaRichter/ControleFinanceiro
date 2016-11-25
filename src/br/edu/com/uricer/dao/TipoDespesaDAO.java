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
    
    private static final String SQLList = "SELECT * FROM \"TipoDespesa\";";
    private static final String SQLFindById = "SELECT * FROM \"TipoDespesa\" WHERE id=?;";
    private static final String SQLFindByNome = "SELECT * FROM \"TipoDespesa\" WHERE descricao LIKE ?;";

    public TipoDespesaDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public TipoDespesa findByIdTipoDespesa(Integer id) throws SQLException {
        TipoDespesa tipoDespesa = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindById, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, id);            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                resultSet.next();
                    int idT = (resultSet.getInt("id"));
                    String descricao = (resultSet.getString("descricao"));
                    tipoDespesa = new TipoDespesa(descricao);
                    tipoDespesa.setIdTipoDespesa(idT);
                
                resultSet.close();
            }
            stm.close();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por id tipo: " + ex.getMessage());
        }  
        
        return tipoDespesa;
    }
    
    public List<TipoDespesa> findByDescricaoTipoDespesa(String descricaoTipoDespesa) throws SQLException {
        List<TipoDespesa> tipos = new ArrayList<>();
        TipoDespesa tipoDespesa = null;
        try (PreparedStatement stm = conexao.prepareStatement(SQLFindByNome)) {
            stm.setString(1, "%" + descricaoTipoDespesa + "%");
            stm.execute();
            try (ResultSet resultSet = stm.getResultSet()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String categoria = resultSet.getString("descricao");
                    tipoDespesa = new TipoDespesa(categoria);
                    tipoDespesa.setIdTipoDespesa(id);
                    tipos.add(tipoDespesa);
                }
                resultSet.close();
            }
            stm.close();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar executar busca por topo: " + ex.getMessage());
        }
        return tipos;
    }
    
    public List<TipoDespesa> listar() throws SQLException {
        List<TipoDespesa> tipos = new ArrayList<>();
        try (ResultSet resultSet = conexao.createStatement().executeQuery(SQLList)) {
            while (resultSet.next()) {
                String descricao = resultSet.getString("descricao");
                int id = resultSet.getInt("id");
                TipoDespesa tipoDespesa = new TipoDespesa(descricao);
                tipoDespesa.setIdTipoDespesa(id);
                tipos.add(tipoDespesa);
            }
            resultSet.close();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar executar list: " + ex.getMessage());
        }
        
        return tipos;
    }
}
