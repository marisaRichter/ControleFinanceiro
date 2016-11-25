package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.Parcela;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marisa.richter
 */
public class ParcelaDAO {
    private Connection conexao = null;
    
    private static final String SQLList = "SELECT * FROM Parcela";
    private static final String SQLFindById = "SELECT * FROM Parcela WHERE id=?";
    private static final String SQLUpdate = "UPDATE Parcela SET valorParcela=?, saldoParcela=?, fkDespesa=?, dataVencimento=?, jurosPorVencimento=?, statusParcela=? WHERE id=?";
    private static final String SQLDelete = "DELETE FROM Parcela WHERE id=?";
    private static final String SQLCreate = "INSERT INTO \"Parcela\" (valor_parcela, a_pagar, despesa, data_vencimento, juros_venc, status_parcela) VALUES (?,?,?,?,?,?);";
    private final StatusDAO StatusDAO;

    public ParcelaDAO(Connection conexao) {
        this.conexao = conexao;
        StatusDAO = new StatusDAO(conexao);
    }
    
    public Integer createParcela(Parcela parcela) throws SQLException{
        Integer idParcelaCriada = 0;
        try(PreparedStatement stm = conexao.prepareStatement(SQLCreate, Statement.RETURN_GENERATED_KEYS)){
            stm.setBigDecimal(1, parcela.getValorParcela());
            stm.setBigDecimal(2, parcela.getSaldoAPagar());
            stm.setInt(3, parcela.getFkDespesa());
            stm.setDate(4, (java.sql.Date) parcela.getDataVencimento());
            stm.setBigDecimal(5, parcela.getJurosPorVencimento());
            stm.setInt(6, parcela.getStatusParcela());
            stm.execute();
            
            conexao.commit();
            System.out.println("parcela criada");
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        
        return idParcelaCriada;
    }
    
    public void deleteParcela(Parcela parcela) throws SQLException{
        try(PreparedStatement stm = conexao.prepareStatement(SQLDelete, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, parcela.getIdParcela());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar delete: " + ex.getMessage());
            conexao.rollback();
        }     
    }
    
    public void updateParcela(Parcela parcela) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdate, Statement.RETURN_GENERATED_KEYS)){
            stm.setBigDecimal(1, parcela.getValorParcela());
            stm.setBigDecimal(2, parcela.getSaldoAPagar());
            stm.setInt(3, parcela.getFkDespesa());
            stm.setDate(4, (java.sql.Date) parcela.getDataVencimento());
            stm.setBigDecimal(5, parcela.getJurosPorVencimento());
            stm.setInt(6, parcela.getStatusParcela());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
//    public Parcela findByIdParcela(Integer id) throws SQLException {
//        Parcela parcela = null;
//        try(PreparedStatement stm = conexao.prepareStatement(SQLFindById, Statement.RETURN_GENERATED_KEYS)){
//            stm.setInt(1, id);            
//            stm.execute();
//            try(ResultSet resultSet = stm.getResultSet()) {
//                while(resultSet.next()) {
//                    parcela = new Parcela();
//                    parcela.setIdParcela(resultSet.getInt("id"));
//                    parcela.setValorParcela(resultSet.getBigDecimal("valorParcela"));
//                    parcela.setSaldoAPagar(resultSet.getBigDecimal("saldoParcela"));
//                    parcela.setFkDespesa(resultSet.getInt("fkDespesa"));
//                    parcela.setDataVencimento(resultSet.getDate("dataVencimento"));
//                    parcela.setJurosPorVencimento(resultSet.getBigDecimal("jurosPorVencimento"));
//                    parcela.setStatusParcela(StatusDAO.findByIdStatus(resultSet.getInt("statusParcela")));
//                    
//                }
//            }
//        }catch(Exception ex){
//            System.out.println("Erro ao tentar executar busca por id: " + ex.getMessage());
//        }  
//        
//        return parcela;
//    }
    
}
