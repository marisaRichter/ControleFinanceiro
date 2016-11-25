package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.Cartao;
import java.sql.Connection;
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
public class CartaoDAO {
    private Connection conexao = null;
    
    private static final String SQLList = "SELECT * FROM \"Cartao\"";
    private static final String SQLFindById = "SELECT * FROM \"Cartao\" WHERE id=?;";
    private static final String SQLFindByNomeBanco = "SELECT * FROM \"Cartao\" WHERE nome LIKE ? and banco = ?;";
    private static final String SQLFindByNome = "SELECT * FROM \"Cartao\" WHERE nome LIKE ?;";
    private static final String SQLUpdate = "UPDATE \"Cartao\" SET nome=?, banco=? WHERE id=?;";
    private static final String SQLDelete = "DELETE FROM \"Cartao\" WHERE id=?;";
    private static final String SQLCreate = "INSERT INTO \"Cartao\" (nome, banco) VALUES (?,?)";

    public CartaoDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public Integer createCartao(Cartao cartao) throws SQLException{
        Integer idCartaoCriado = 0;
        
        try(PreparedStatement stm = conexao.prepareStatement(SQLCreate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, cartao.getNomeCartao());
            stm.setString(2, cartao.getBanco());
            stm.execute();

            try (ResultSet resultSet = stm.getGeneratedKeys()) {
                while (resultSet.next()) {
                    idCartaoCriado = resultSet.getInt(1);
                }
            }
            conexao.commit();
            JOptionPane.showMessageDialog(null, "Cartão adicionado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            stm.close();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        
        return idCartaoCriado;        
    }
    
    public void deleteCartao(Cartao cartao) throws SQLException{
        try(PreparedStatement stm = conexao.prepareStatement(SQLDelete)){
            stm.setInt(1, cartao.getIdCartao());
            stm.executeUpdate();
            
            conexao.commit();
            stm.close();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar delete: " + ex.getMessage());
            conexao.rollback();
        }     
    }
    
    public void updateCartao(Cartao cartao) throws SQLException {
        
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdate)){
            stm.setString(1, cartao.getNomeCartao());
            stm.setString(2, cartao.getBanco());
            stm.setInt(3, cartao.getIdCartao());            
            stm.executeUpdate();
            conexao.commit();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            stm.close();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    public Cartao findByIdCartao(Integer id) throws SQLException {
        Cartao cartao = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindById, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, id);            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                resultSet.next();
                    int idC = (resultSet.getInt("id"));
                    
                    String nome = (resultSet.getString("nome"));
                    String banco = (resultSet.getString("banco"));
                    cartao = new Cartao(nome, banco);
                    cartao.setIdCartao(idC);
                
                resultSet.close();
            }
            stm.close();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por id cartao: " + ex.getMessage());
        }  
        
        return cartao;
    }
    
    public List<Cartao> findByNomeCartao(String nome, String banco) throws SQLException {
        List<Cartao> cartoes = new ArrayList<>();
        Cartao cartao = null;
        String sql;
        if(!banco.equals("")){
            sql = SQLFindByNomeBanco;
        } else{
            sql = SQLFindByNome;
        }
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, "%" + nome + "%");
            if(!banco.equals("")){
                stm.setString(2, banco);
            }             
            stm.execute();
            try (ResultSet resultSet = stm.getResultSet()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String cartaoNome = resultSet.getString("nome");
                    String cartaoBanco = resultSet.getString("banco");
                    cartao = new Cartao(cartaoNome, cartaoBanco);
                    cartao.setIdCartao(id);
                    cartoes.add(cartao);
                }
            }
            stm.close();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar executar busca por cartao: " + ex.getMessage());
        }
        return cartoes;
    }   
    
    public List<Cartao> listar() throws SQLException {
        List<Cartao> cartoes = new ArrayList<>();
        try(ResultSet resultSet = conexao.createStatement().executeQuery(SQLList)){
           while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String nomeCartao = resultSet.getString("nome");
                String banco = resultSet.getString("banco");
                Cartao cartao = new Cartao(nomeCartao, banco);
                cartao.setIdCartao(id);
                cartoes.add(cartao);           
            }
           resultSet.close();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar list: " + ex.getMessage());
        }       
        
        return cartoes;
    }
}
