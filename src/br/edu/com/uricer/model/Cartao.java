package br.edu.com.uricer.model;

/**
 *
 * @author marisa.richter
 */
public class Cartao {
    private int idCartao;
    private String nomeCartao;
    private String banco;
//    ver sobre ter todos os cartões cadastrados, para ter o escolhido aqui (CardFlag
    
    public Cartao(String nomeCartao, String banco) {
        this.nomeCartao = nomeCartao;
        this.banco = banco;
    }

    public Cartao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(int idCartao) {
        this.idCartao = idCartao;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
    
    @Override
    public String toString() {
        return "Pessoa[id: " + idCartao + " nome: " + nomeCartao + "," + "banco: " + banco + "]";
    }
    
}
