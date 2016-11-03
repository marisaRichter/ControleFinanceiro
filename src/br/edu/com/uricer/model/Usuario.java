package br.edu.com.uricer.model;

/**
 *
 * @author marisa.richter
 */
public class Usuario {
    private int idPessoa;
    private String login;
    private String senha;

    public Usuario(int idPessoa, String login, String senha) {
        this.idPessoa = idPessoa;
        this.login = login;
        this.senha = senha;
    }
    
    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
