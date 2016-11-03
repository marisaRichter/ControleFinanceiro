package br.edu.com.uricer.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author marisa.richter
 */
public class Despesa {
    private int idDespesa;
    private String descricao;
    private BigDecimal valor;
    private boolean parcelado;
    private String local;
    private Date dataVencimento;
    private boolean cartao;
    Tag tag;
    private boolean despesaPaga;

    public Despesa(String descricao, BigDecimal valor, boolean parcelado, String local, Date dataVencimento, boolean cartao) {
        this.descricao = descricao;
        this.valor = valor;
        this.parcelado = parcelado;
        this.local = local;
        this.dataVencimento = dataVencimento;
        this.cartao = cartao;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String Descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isParcelado() {
        return parcelado;
    }

    public void setParcelado(boolean parcelado) {
        this.parcelado = parcelado;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public boolean isCartao() {
        return cartao;
    }

    public void setCartao(boolean cartao) {
        this.cartao = cartao;
    }

    public boolean isDespesaPaga() {
        return despesaPaga;
    }

    public void setDespesaPaga(boolean despesaPaga) {
        this.despesaPaga = despesaPaga;
    }
}
