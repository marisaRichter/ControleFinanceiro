package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.Parcela;
import br.edu.com.uricer.model.StatusParcela;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author marisa.richter
 */
public class ParcelaDAO {
    private Connection conexao = null;
    
    private static final String SQLList = "SELECT * FROM \"Parcela\";";
    private static final String SQLListParcelas = "SELECT * FROM \"Parcela\" where despesa=?;";
    private static final String SQLFindById = "SELECT * FROM Parcela WHERE id=?";
    private static final String SQLRelatorio = "SELECT * " +
                                                "FROM \"Despesa\" d inner join \"Parcela\" p on p.despesa = d.id and status_parcela = ? " +
                                                "and tipo_despesa = ? and tag = ? and data_despesa >= ? and data_despesa <= ?;";
    private static final String SQLUpdate = "UPDATE \"Parcela\" SET valor_parcela=?, a_pagar=?, despesa=?, data_vencimento=?, juros_venc=?, status_parcela=? WHERE id_parcela=?";
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
            stm.setDate(4, new java.sql.Date(parcela.getDataVencimento().getTime()));
            stm.setBigDecimal(5, parcela.getJurosPorVencimento());
            stm.setInt(6, parcela.getStatusParcela().getIdStatus());
            
            stm.execute();
            
            conexao.commit();
            JOptionPane.showMessageDialog(null, "Criada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Erro ao criar Parcela",JOptionPane.ERROR_MESSAGE);
//            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        
        return idParcelaCriada;
    }
    
    public void deleteParcela(Parcela parcela) throws SQLException{
        try(PreparedStatement stm = conexao.prepareStatement(SQLDelete, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, parcela.getIdParcela());
            
            conexao.commit();
            JOptionPane.showMessageDialog(null, "Deletada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Erro ao tentar executar delete da Parcela",JOptionPane.ERROR_MESSAGE);
//            System.out.println("Erro ao tentar executar delete: " + ex.getMessage());
            conexao.rollback();
        }     
    }
    
    public void updateParcela(Parcela parcela) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdate, Statement.RETURN_GENERATED_KEYS)){
            stm.setBigDecimal(1, parcela.getValorParcela());
            stm.setBigDecimal(2, parcela.getSaldoAPagar());
            stm.setInt(3, parcela.getFkDespesa());
            stm.setDate(4, new java.sql.Date(parcela.getDataVencimento().getTime()));
            stm.setBigDecimal(5, parcela.getJurosPorVencimento());
            stm.setInt(6, parcela.getStatusParcela().getIdStatus());
            stm.setInt(7, parcela.getIdParcela());
            stm.executeUpdate();
            conexao.commit();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Erro ao tentar executar aualização",JOptionPane.ERROR_MESSAGE);
//            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    
    public List<Parcela> findParcelas(Integer id_despesa) throws SQLException {
        List<Parcela> parcelas = new ArrayList();
        Parcela parcela = null;
        try (PreparedStatement stm = conexao.prepareStatement(SQLListParcelas)) {
            stm.setInt(1, id_despesa);
            stm.execute();
            try (ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    int id = (resultSet.getInt("id_parcela"));
                    BigDecimal valorParcela = resultSet.getBigDecimal("valor_parcela");
                    BigDecimal aPagar = resultSet.getBigDecimal("a_pagar");
                    int idS = resultSet.getInt("status_parcela");
                    int idD = resultSet.getInt("despesa");
                    Date data = resultSet.getDate("data_vencimento");
                    StatusParcela status = StatusDAO.findByIdStatus(idS);
                    parcela = new Parcela(id, valorParcela, aPagar, status, idD, data);
                    parcelas.add(parcela);
                
                }
                resultSet.close();
            }
            stm.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Erro ao tentar busca por id da Parcela",JOptionPane.ERROR_MESSAGE);
//            System.out.println("Erro ao tentar executar busca por id parcela table: " + ex.getMessage());
        }

        return parcelas;
    }
    
    public List<Parcela> findParcelasRelatorio(int idTipoDespesa, int idTag, int idStatus, int mes, int ano) throws SQLException {
        // status_parcela, tipo_despesa, tag, e datas
       List<Parcela> parcelas = new ArrayList<>();
       Parcela parcela = null;
       Calendar dataInicio = new GregorianCalendar(ano, mes-1, 01);
       Calendar dataFim = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLRelatorio)){
            stm.setInt(1, idStatus);
            stm.setInt(2, idTipoDespesa);
            stm.setInt(3, idTag);
            stm.setDate(4, new java.sql.Date (dataInicio.getTime().getTime()));
            if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
                dataFim = new GregorianCalendar(ano, mes-1, 31);
            } else if(mes == 2){
                
            } else{
                dataFim = new GregorianCalendar(ano, mes-1, 30);
            }
            stm.setDate(5, new java.sql.Date (dataFim.getTime().getTime()));
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {    
                    int id = (resultSet.getInt("id_parcela"));
                    BigDecimal valorParcela = resultSet.getBigDecimal("valor_parcela");
                    BigDecimal aPagar = resultSet.getBigDecimal("a_pagar");
                    int idS = resultSet.getInt("status_parcela");
                    int idD = resultSet.getInt("despesa");
                    Date data = resultSet.getDate("data_vencimento");
                    StatusParcela status = StatusDAO.findByIdStatus(idS);
                    parcela = new Parcela(id, valorParcela, aPagar, status, idD, data);
                    parcelas.add(parcela);
                }
                resultSet.close();
            }
            stm.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Erro ao tentar buscar parcelas para relatório",JOptionPane.ERROR_MESSAGE);
//            System.out.println("Erro ao tentar executar busca por parcela: " + ex.getMessage());
        }  
        return parcelas;
    }    
}
