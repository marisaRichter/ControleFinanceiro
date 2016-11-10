package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.Tag;
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
public class TagDAO {
    private Connection conexao = null;
    
    private static final String SQLList = "SELECT * FROM Tag";
    private static final String SQLFindById = "SELECT * FROM Tag WHERE id=?";
    private static final String SQLFindByNome = "SELECT * FROM Tag WHERE categoria=?";
    private static final String SQLUpdate = "UPDATE Tag SET categoria=? WHERE id=?";
    private static final String SQLDelete = "DELETE FROM Tag WHERE id=?";
    private static final String SQLCreate = "INSERT INTO Tag (id, categoria) VALUES (?,?)";
    
    public TagDAO(Connection conexao) {
        this.conexao = conexao;
    }
    public Integer createTag(Tag tag) throws SQLException{
        Integer idTagCriada = 0;
        
        try(PreparedStatement stm = conexao.prepareStatement(SQLCreate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(2, tag.getCategoria());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        return idTagCriada;        
    }
    public void deleteTag(Tag tag) throws SQLException{
        try(PreparedStatement stm = conexao.prepareStatement(SQLDelete, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, tag.getIdTag());
            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar delete: " + ex.getMessage());
            conexao.rollback();
        }     
    }
    public void updateTag(Tag tag) throws SQLException {
        try(PreparedStatement stm = conexao.prepareStatement(SQLUpdate, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, tag.getCategoria());
            stm.setInt(2, tag.getIdTag());            
            conexao.commit();
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }  
    }
    public Tag findByIdTag(Integer id) throws SQLException {
        Tag tag = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindById, Statement.RETURN_GENERATED_KEYS)){
            stm.setInt(1, id);            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    tag = new Tag();
                    tag.setIdTag(resultSet.getInt("id"));
                    tag.setCategoria(resultSet.getString("nome"));
                }
            }
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por id: " + ex.getMessage());
        }  
        
        return tag;
    }
    
    public List<Tag> findByNomeCartao(String nome) throws SQLException {
        String sql = "Select * from Pessoas p where upper(p.nome) like ?";
        List<Tag> tags = new ArrayList<>();
        Tag tag = null;
        try(PreparedStatement stm = conexao.prepareStatement(SQLFindByNome, Statement.RETURN_GENERATED_KEYS)){
            stm.setString(1, "%" + nome.toUpperCase() + "%");            
            stm.execute();
            try(ResultSet resultSet = stm.getResultSet()) {
                while(resultSet.next()) {
                    tag = new Tag();
                    tag.setIdTag(resultSet.getInt("id"));
                    tag.setCategoria(resultSet.getString("nome"));
                    tags.add(tag);
                }
            }
        }catch(Exception ex){
            System.out.println("Erro ao tentar executar busca por nome: " + ex.getMessage());
        } 
        return tags;
    }   
}
