package br.edu.com.uricer.model;

import java.math.BigDecimal;
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
    private int fkCartaoUsado;
    private Tag tag;
    private BigDecimal numeroParcela;
    private BigDecimal valorTotalDespesa;

    public Despesa(String descricaoDespesa, String localDespesa, TipoDespesa tipoDespesa, int fkCartaoUsado, Tag tag, BigDecimal numeroParcela, BigDecimal valorTotalDespesa) {
        this.descricaoDespesa = descricaoDespesa;
        this.localDespesa = localDespesa;
        this.tipoDespesa = tipoDespesa;
        this.fkCartaoUsado = fkCartaoUsado;
        this.tag = tag;
        this.numeroParcela = numeroParcela;
        this.valorTotalDespesa = valorTotalDespesa;
    }

    public int getTipoDespesa() {
        return tipoDespesa.getIdTipoDespesa();
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public int getTag() {
        return tag.getIdTag();
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

    public int getFkCartaoUsado() {
        return fkCartaoUsado;
    }

    public void setFkCartaoUsado(int fkCartaoUsado) {
        this.fkCartaoUsado = fkCartaoUsado;
    }

    public BigDecimal getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(BigDecimal numeroParcela) {
        this.numeroParcela = numeroParcela;
        List<Parcela> parcelas = new ArrayList();
        BigDecimal valorParcela = getValorTotalDespesa().divide(numeroParcela);
        int numeroParcelas = numeroParcela.intValue();
        for(int i = 0; i < numeroParcelas; i++){
             parcelas.set(i, new Parcela(valorParcela, getIdDespesa()));
        }
    }

    public BigDecimal getValorTotalDespesa() {
        return valorTotalDespesa;
    }

    public void setValorTotalDespesa(BigDecimal valorTotalDespesa) {
        this.valorTotalDespesa = valorTotalDespesa;
    }
}
