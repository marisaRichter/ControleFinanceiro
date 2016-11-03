package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.Despesa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marisa.richter
 */
public class DespesaDAO {
    private Connection conexao = null;
    
    private static final String SQLLista = "SELECT * FROM Despesa ORDER BY dataVencimento";
    private static final String SQLConsultaId = "SELECT * FROM Despesa WHERE id=?";
    private static final String SQLAtualiza = "UPDATE Despesa SET ?=? WHERE ?=?";
    private static final String SQLRemove = "DELETE FROM Despesa WHERE id=?";
    private static final String SQLInsere = "INSERT INTO Despesa (id, descricao, valor, parcelado, local, dataVencimento, cartao, tag, despesaPaga) VALUES (?,?,?,?,?,?,?,?)";

    public DespesaDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public Integer createDespesa(Despesa despesa) throws SQLException{
        Integer idDespesaCriado = 0;
        try(PreparedStatement stm = conexao.prepareStatement(SQLInsere, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, despesa.getDescricao());
            stm.setBigDecimal(2, despesa.getValor());
            stm.setBoolean(3, despesa.isParcelado());
            stm.setString(4, despesa.getLocal());
            stm.setDate(5, (Date) despesa.getDataVencimento());
            stm.setBoolean(6, despesa.isCartao());
//            stm.setString(7, despesa.getDescricao());
            stm.setBoolean(8, despesa.isDespesaPaga());
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        
        return idDespesaCriado;        
    }
}
