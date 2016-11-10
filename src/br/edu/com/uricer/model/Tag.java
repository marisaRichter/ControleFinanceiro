package br.edu.com.uricer.model;

/**
 *
 * @author marisa.richter
 */
public class Tag {
    private int idTag;
    private String Categoria;

    public Tag(String Categoria) {
        this.Categoria = Categoria;
    }

    public Tag() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }
}
