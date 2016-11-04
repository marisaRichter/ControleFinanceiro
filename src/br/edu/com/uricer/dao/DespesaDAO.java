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
    private static final String SQLInsere = "INSERT INTO Despesa (id, descricao, valor, parcelado, local, dataVencimento, cartao, tag, despesaPaga) VALUES (?,?,?,?,?,?,?)";

    public DespesaDAO(Connection conexao) {
        this.conexao = conexao;
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
}
