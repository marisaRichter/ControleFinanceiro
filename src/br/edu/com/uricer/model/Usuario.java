package br.edu.com.uricer.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author marisa.richter
 */
public class Usuario {
    private int id;
    private String nome;
    private String senha;

    public Usuario(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }
    public Despesa adicionarDespesa(String descricaoDespesa, String localDespesa, TipoDespesa tipoDespesa, Cartao fkCartaoUsado, Tag tag, BigDecimal numeroParcela, BigDecimal valorTotalDespesa){
        Despesa despesa = new Despesa(descricaoDespesa, localDespesa, tipoDespesa, fkCartaoUsado, tag, numeroParcela, valorTotalDespesa);
        return despesa;
    }
    
    public Receita adicionarReceita(String descricaoReceita, BigDecimal valorReceita, Date dataReceita){
        Receita receita = new Receita(descricaoReceita, valorReceita, dataReceita);
        return receita;
    }
    
    public void emitirFaturaCartao(Date periodo){
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return nome;
    }

    public void setLogin(String login) {
        this.nome = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
