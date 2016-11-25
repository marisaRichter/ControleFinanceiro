package br.edu.com.uricer.model;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marisa.richter
 */
public class Despesa {
    private int idDespesa;
    private String descricaoDespesa;
    private String localDespesa;
    private TipoDespesa tipoDespesa;
    private Cartao fkCartaoUsado;
    private Tag tag;
    private BigDecimal numeroParcela;
    private BigDecimal valorTotalDespesa;
    private Date dataDespesa;

    public Despesa(String descricaoDespesa, String localDespesa, TipoDespesa tipoDespesa, Cartao fkCartaoUsado, Tag tag, BigDecimal numeroParcela, BigDecimal valorTotalDespesa, Date dataDespesa){
        this.descricaoDespesa = descricaoDespesa;
        this.localDespesa = localDespesa;
        this.tipoDespesa = tipoDespesa;
        this.fkCartaoUsado = fkCartaoUsado;
        this.tag = tag;
        this.numeroParcela = numeroParcela;
        this.valorTotalDespesa = valorTotalDespesa;
        this.dataDespesa = dataDespesa;
    }

    public Despesa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public String getDescricaoDespesa() {
        return descricaoDespesa;
    }

    public void setDescricaoDespesa(String descricaoDespesa) {
        this.descricaoDespesa = descricaoDespesa;
    }

    public String getLocalDespesa() {
        return localDespesa;
    }

    public void setLocalDespesa(String localDespesa) {
        this.localDespesa = localDespesa;
    }

    public Cartao getFkCartaoUsado() {
        return fkCartaoUsado;
    }

    public void setFkCartaoUsado(Cartao fkCartaoUsado) {
        this.fkCartaoUsado = fkCartaoUsado;
    }

    public BigDecimal getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(BigDecimal numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public BigDecimal getValorTotalDespesa() {
        return valorTotalDespesa;
    }

    public void setValorTotalDespesa(BigDecimal valorTotalDespesa) {
        this.valorTotalDespesa = valorTotalDespesa;
    }

    public Date getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(Date dataDespesa) {
        this.dataDespesa = dataDespesa;
    }
}
