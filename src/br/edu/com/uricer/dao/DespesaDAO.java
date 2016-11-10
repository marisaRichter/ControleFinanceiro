package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.Despesa;
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
public class DespesaDAO {
    private Connection conexao = null;
    
    private static final String SQLLista = "SELECT * FROM Despesa ORDER BY dataVencimento";
    private static final String SQLFindById = "SELECT * FROM Despesa WHERE id=?";
    private static final String SQLUpdate = "UPDATE Despesa SET ?=? WHERE ?=?";
    private static final String SQLDelete = "DELETE FROM Despesa WHERE id=?";
    private static final String SQLInsere = "INSERT INTO Despesa (id, descricao, valor, parcelado, local, dataVencimento, cartao, tag, despesaPaga) VALUES (?,?,?,?,?,?,?)";
    private final TipoDespesaDAO TipoDespesaDAO;
    private final CartaoDAO CartaoDAO;
    private final TagDAO TagDAO;

    public DespesaDAO(Connection conexao) {
        this.conexao = conexao;
        TipoDespesaDAO = new TipoDespesaDAO(conexao);
        CartaoDAO = new CartaoDAO(conexao);
        TagDAO = new TagDAO(conexao);
    }
    
    public Integer createDespesa(Despesa despesa) throws SQLException{
        Integer idDespesaCriado = 0;
        try(PreparedStatement stm = conexao.prepareStatement(SQLInsere, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, despesa.getDescricaoDespesa());
            stm.setString(2, despesa.getLocalDespesa());
            stm.setInt(3, despesa.getTipoDespesa());
            stm.setInt(4, despesa.getFkCartaoUsado());
            stm.setInt(5, despesa.getTag());
            stm.setBigDecimal(6, despesa.getNumeroParcela());
            stm.setBigDecimal(7, despesa.getValorTotalDespesa());
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        
        return idDespesaCriado;        
    }
    public void deleteDespesa(Despesa despesa) throws SQLException{
        try(PreparedStatement stm = conexao.prepareStatement(SQLDelete, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, despesa.getIdDespesa());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar delete: " + ex.getMessage());
            conexao.rollback();
        }     
    }
    
    public void updateDespesa(Despesa despesa) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, despesa.getDescricaoDespesa());
            stm.setString(2, despesa.getLocalDespesa());
            stm.setInt(3, despesa.getTipoDespesa());
            stm.setInt(4, despesa.getFkCartaoUsado());
            stm.setInt(5, despesa.getTag());
            stm.setBigDecimal(6, despesa.getNumeroParcela());
            stm.setBigDecimal(7, despesa.getValorTotalDespesa());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    public Despesa findByIdDespesa(Integer id) throws SQLException {
        Despesa despesa = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindById, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, id);            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    despesa = new Despesa();
                    despesa.setIdDespesa(resultSet.getInt("id"));
                    despesa.setDescricaoDespesa(resultSet.getString("descricaoTipoDespesa"));
                    despesa.setLocalDespesa(resultSet.getString("localDespesa"));
                    despesa.setTipoDespesa(TipoDespesaDAO. findByIdTipoDespesa(resultSet.getInt("tipoDespesa")));
                    despesa.setFkCartaoUsado(CartaoDAO.findByIdCartao(resultSet.getInt("fkCartaoUsado")));
                    despesa.setTag(TagDAO.findByIdTag(resultSet.getInt("fkTag")));
                    despesa.setNumeroParcela(resultSet.getBigDecimal("numeroParcela"));
                    despesa.setValorTotalDespesa(resultSet.getBigDecimal("valorTotalDespesa"));
                }
            }
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por id: " + ex.getMessage());
        }  
        
        return despesa;
    }
    
}
