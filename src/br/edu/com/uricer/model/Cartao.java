package br.edu.com.uricer.model;

/**
 *
 * @author marisa.richter
 */
public class Cartao {
    private int idCartao;
    private String nomeCartao;
//    ver sobre ter todos os cartões cadastrados, para ter o escolhido aqui (CardFlag
    
    public Cartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
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
    
}
