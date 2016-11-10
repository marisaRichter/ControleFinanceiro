package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.PagamentoParcela;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marisa.richter
 */
public class PagamentoParcelaDAO {
    private Connection conexao = null;
    
    private static final String SQLList = "SELECT * FROM PagamentoParcela";
    private static final String SQLFindById = "SELECT * FROM PagamentoParcela WHERE id=?";
    private static final String SQLUpdate = "UPDATE PagamentoParcela SET valorPago=?, dataPagamento=?, fkParcela=? WHERE id=?";
    private static final String SQLDelete = "DELETE FROM PagamentoParcela WHERE id=?";
    private static final String SQLCreate = "INSERT INTO PagamentoParcela (valorPago, dataPagamento, fkParcela) VALUES (?,?,?)";

    public PagamentoParcelaDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public Integer createPagamentoParcela(PagamentoParcela pagamentoParcela) throws SQLException{
        Integer idPagamentoParcelaCriada = 0;
        try(PreparedStatement stm = conexao.prepareStatement(SQLCreate, Statement.RETURN_GENERATED_KEYS)){
            stm.setBigDecimal(1, pagamentoParcela.getValorPago());
            stm.setDate(2, (Date) pagamentoParcela.getDataPagamento());
            stm.setInt(3, pagamentoParcela.getFkIdParcela());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        
        return idPagamentoParcelaCriada;
    }
    
    public void deletePagamentoParcela(PagamentoParcela pagamentoParcela) throws SQLException{
        try(PreparedStatement stm = conexao.prepareStatement(SQLDelete, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, pagamentoParcela.getIdPagamentoParcela());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar delete: " + ex.getMessage());
            conexao.rollback();
        }     
    }
    
    public void updatePagamentoParcela(PagamentoParcela pagamentoParcela) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdate, Statement.RETURN_GENERATED_KEYS)){
            stm.setBigDecimal(1, pagamentoParcela.getValorPago());
            stm.setDate(2, (Date) pagamentoParcela.getDataPagamento());
            stm.setInt(3, pagamentoParcela.getFkIdParcela());
            stm.setInt(4, pagamentoParcela.getIdPagamentoParcela());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    public PagamentoParcela findByIdPagamentoParcela(Integer id) throws SQLException {
        PagamentoParcela pagamentoParcela = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindById, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, id);            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    pagamentoParcela = new PagamentoParcela();
                    pagamentoParcela.setIdPagamentoParcela(resultSet.getInt("id"));
                    pagamentoParcela.setValorPago(resultSet.getBigDecimal("valorPago"));
                    pagamentoParcela.setDataPagamento(resultSet.getDate("dataPagamento"));
                    pagamentoParcela.setFkIdParcela(resultSet.getInt("fkParcela"));
                    
                }
            }
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por id: " + ex.getMessage());
        }  
        
        return pagamentoParcela;
    }
}
