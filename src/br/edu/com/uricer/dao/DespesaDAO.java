package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.Cartao;
import br.edu.com.uricer.model.Despesa;
import br.edu.com.uricer.model.Tag;
import br.edu.com.uricer.model.TipoDespesa;
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
public class DespesaDAO {
    private Connection conexao = null;
    
    private static final String SQLLista = "SELECT * FROM Despesa ORDER BY dataVencimento";
    private static final String SQLFindById = "SELECT * FROM Despesa WHERE id=?";
    private static final String SQLFindByDescricao = "SELECT * FROM \"Despesa\" WHERE descricao LIKE ?";
    private static final String SQLFindByIdTag = "SELECT * FROM \"Despesa\" WHERE tag=?";
    private static final String SQLUpdate = "UPDATE Despesa SET ?=? WHERE ?=?";
    private static final String SQLDelete = "DELETE FROM Despesa WHERE id=?";
    private static final String SQLInsere = "INSERT INTO \"Despesa\" (descricao, local, tipo_despesa, cartao_usado, tag, numero_parcelas, valor_total, data_despesa) VALUES (?,?,?,?,?,?,?,?);";
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
            stm.setInt(3, despesa.getTipoDespesa().getIdTipoDespesa());
            stm.setInt(4, despesa.getFkCartaoUsado().getIdCartao());
            stm.setInt(5, despesa.getTag().getIdTag());
            stm.setBigDecimal(6, despesa.getNumeroParcela());
            stm.setBigDecimal(7, despesa.getValorTotalDespesa());
            stm.setDate(8, new java.sql.Date(despesa.getDataDespesa().getTime()));
            stm.execute();
            try (ResultSet resultSet = stm.getGeneratedKeys()) {
                while (resultSet.next()) {
                    idDespesaCriado = resultSet.getInt(1);
                }
                resultSet.close();
            }
            conexao.commit();
            JOptionPane.showMessageDialog(null, "Despesa adicionada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            stm.close();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        
        return idDespesaCriado;        
    }
    public void deleteDespesa(int id) throws SQLException{
        try(PreparedStatement stm = conexao.prepareStatement(SQLDelete, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, id);
            
            conexao.commit();
            stm.close();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar delete: " + ex.getMessage());
            conexao.rollback();
        }     
    }
    
    public void updateDespesa(Despesa despesa) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, despesa.getDescricaoDespesa());
            stm.setString(2, despesa.getLocalDespesa());
            stm.setInt(3, despesa.getTipoDespesa().getIdTipoDespesa());
            stm.setInt(4, despesa.getFkCartaoUsado().getIdCartao());
            stm.setInt(5, despesa.getTag().getIdTag());
            stm.setBigDecimal(6, despesa.getNumeroParcela());
            stm.setBigDecimal(7, despesa.getValorTotalDespesa());
            
            conexao.commit();
            stm.close();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
//    public Despesa findByIdDespesa(Integer id) throws SQLException {
//        Despesa despesa = null;
//        try(PreparedStatement stm = conexao.prepareStatement(SQLFindById, Statement.RETURN_GENERATED_KEYS)){
//            stm.setInt(1, id);            
//            stm.execute();
//            try(ResultSet resultSet = stm.getResultSet()) {
//                while(resultSet.next()) {
//                    despesa = new Despesa();
//                    despesa.setIdDespesa(resultSet.getInt("id"));
//                    despesa.setDescricaoDespesa(resultSet.getString("descricao"));
//                    despesa.setLocalDespesa(resultSet.getString("local"));
//                    despesa.setTipoDespesa(resultSet.getInt("tipo_despesa"));
//                    despesa.setFkCartaoUsado(resultSet.getInt("cartao_usado"));
//                    despesa.setTag(resultSet.getInt("tag"));
//                    despesa.setNumeroParcela(resultSet.getBigDecimal("numero_parcelas"));
//                    despesa.setValorTotalDespesa(resultSet.getBigDecimal("valor_total"));
//                }
//                resultSet.close();
//            }
//            stm.close();
//        }catch(Exception ex){
//            System.out.println("Erro ao tentar executar busca por id: " + ex.getMessage());
//        }  
//        
//        return despesa;
//    }
    
    public List<Despesa> findByTag(int idTag) throws SQLException {
        System.out.println("find tag");
       List<Despesa> despesas = new ArrayList<>();
       Despesa despesa = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindByIdTag)){
            stm.setInt(1, idTag);            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    int id = (resultSet.getInt("id"));
                    String descricaoD = (resultSet.getString("descricao"));
                    String local = (resultSet.getString("local"));
                    TipoDespesa idTipo = TipoDespesaDAO.findByIdTipoDespesa(resultSet.getInt("tipo_despesa"));
                    Cartao cartao = CartaoDAO.findByIdCartao(resultSet.getInt("cartao_usado"));
                    Tag tag = TagDAO.findByIdTag(resultSet.getInt("tag"));
                    BigDecimal parcelas = (resultSet.getBigDecimal("numero_parcelas"));
                    BigDecimal total = (resultSet.getBigDecimal("valor_total"));
                    Date data = (resultSet.getDate("data_despesa"));
                    despesa = new Despesa(descricaoD, local, idTipo, cartao, tag, parcelas, total, data);
                    despesa.setIdDespesa(id);
                    despesas.add(despesa);
                }
                resultSet.close();
            }
            stm.close();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por tag: " + ex.getMessage());
        }  
        
        return despesas;
    }
    
    public List<Despesa> findByDescricao(String descricao) throws SQLException {
       List<Despesa> despesas = new ArrayList<>();
       Despesa despesa = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindByDescricao)){
            stm.setString(1, "%" + descricao + "%");
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    int id = (resultSet.getInt("id"));
                    String descricaoD = (resultSet.getString("descricao"));
                    String local = (resultSet.getString("local"));
                    TipoDespesa idTipo = TipoDespesaDAO.findByIdTipoDespesa(resultSet.getInt("tipo_despesa"));
                    Cartao cartao = CartaoDAO.findByIdCartao(resultSet.getInt("cartao_usado"));
                    Tag tag = TagDAO.findByIdTag(resultSet.getInt("tag"));
                    BigDecimal parcelas = (resultSet.getBigDecimal("numero_parcelas"));
                    BigDecimal total = (resultSet.getBigDecimal("valor_total"));
                    Date data = (resultSet.getDate("data_despesa"));
                    despesa = new Despesa(descricaoD, local, idTipo, cartao, tag, parcelas, total, data);
                    despesa.setIdDespesa(id);
                    despesas.add(despesa);
                }
                resultSet.close();
            }
            stm.close();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por descrição: " + ex.getMessage());
        }  
        return despesas;
    }
    
}
