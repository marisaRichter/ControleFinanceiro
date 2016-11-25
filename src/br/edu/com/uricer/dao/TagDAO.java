package br.edu.com.uricer.dao;

import br.edu.com.uricer.model.Tag;
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
public class TagDAO {

    private Connection conexao = null;

    private static final String SQLList = "SELECT * FROM \"Tag\";";
    private static final String SQLFindById = "SELECT * FROM \"Tag\" WHERE id=?;";
    private static final String SQLFindByNome = "SELECT * FROM \"Tag\" WHERE categoria LIKE ?;";
    private static final String SQLFind = "SELECT * FROM \"Tag\" WHERE categoria = ?;";
    private static final String SQLUpdate = "UPDATE \"Tag\" SET categoria=? WHERE id=?;";
    private static final String SQLDelete = "DELETE FROM \"Tag\" WHERE id=?;";
    private static final String SQLCreate = "INSERT INTO \"Tag\" (categoria) VALUES (?);";

    public TagDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Integer createTag(Tag tag) throws SQLException {
        Integer idTagCriada = 0;

        try (PreparedStatement stm = conexao.prepareStatement(SQLCreate, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, tag.getCategoria());
            stm.execute();

            try (ResultSet resultSet = stm.getGeneratedKeys()) {
                while (resultSet.next()) {
                    idTagCriada = resultSet.getInt(1);
                }
                resultSet.close();
            }
            conexao.commit();
            JOptionPane.showMessageDialog(null, "Tag adicionada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            stm.close();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar executar insercao: " + ex.getMessage());
            conexao.rollback();
        }
        return idTagCriada;
    }

    public void deleteTag(Tag tag) throws SQLException {
        try (PreparedStatement stm = conexao.prepareStatement(SQLDelete, Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1, tag.getIdTag());
            stm.executeUpdate();
            conexao.commit();
            stm.close();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar executar delete: " + ex.getMessage());
            conexao.rollback();
        }
    }

    public void updateTag(Tag tag) throws SQLException {
        try (PreparedStatement stm = conexao.prepareStatement(SQLUpdate, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, tag.getCategoria());
            stm.setInt(2, tag.getIdTag());
            stm.executeUpdate();
            conexao.commit();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            stm.close();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar executar atualização: " + ex.getMessage());
            conexao.rollback();
        }
    }

    public Tag findByIdTag(Integer id) throws SQLException {
        Tag tag = null;
        try (PreparedStatement stm = conexao.prepareStatement(SQLFindById)) {
            stm.setInt(1, id);
            stm.execute();
            try (ResultSet resultSet = stm.getResultSet()) {
                resultSet.next();
                System.out.println("try tag");
                    int idT = (resultSet.getInt("id"));
                    String categoria = (resultSet.getString("categoria"));
                    tag = new Tag(categoria);
                    tag.setIdTag(idT);
                    System.out.println("teste tag: " + tag.getCategoria());
                
                resultSet.close();
            }
            stm.close();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar executar busca por id tag: " + ex.getMessage());
        }

        return tag;
    }

    public List<Tag> findByCategoria(String nome) throws SQLException {
        List<Tag> tags = new ArrayList<>();
        Tag tag = null;
        try (PreparedStatement stm = conexao.prepareStatement(SQLFindByNome)) {
            stm.setString(1, "%" + nome + "%");
            stm.execute();
            try (ResultSet resultSet = stm.getResultSet()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String categoria = resultSet.getString("categoria");
                    tag = new Tag(categoria);
                    tag.setIdTag(id);
                    tags.add(tag);
                }
                resultSet.close();
            }
            stm.close();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar executar busca por nome: " + ex.getMessage());
        }
        return tags;
    }
    
    public Tag findCategoria(String nome) throws SQLException {
        Tag tag = null;
        try (PreparedStatement stm = conexao.prepareStatement(SQLFind)) {
            stm.setString(1, nome);
            stm.execute();
            try (ResultSet resultSet = stm.getResultSet()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String categoria = resultSet.getString("categoria");
                    tag = new Tag(categoria);
                    tag.setIdTag(id);
                }
                resultSet.close();
            }
            stm.close();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar executar busca por nome: " + ex.getMessage());
        }
        return tag;
    }

    public List<Tag> listar() throws SQLException {
        List<Tag> tags = new ArrayList<>();
        try (ResultSet resultSet = conexao.createStatement().executeQuery(SQLList)) {
            while (resultSet.next()) {
                String categoria = resultSet.getString("categoria");
                int id = resultSet.getInt("id");
                Tag tag = new Tag(categoria);
                tag.setIdTag(id);
                tags.add(tag);
            }
            resultSet.close();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar executar list: " + ex.getMessage());
        }

        return tags;
    }
}
