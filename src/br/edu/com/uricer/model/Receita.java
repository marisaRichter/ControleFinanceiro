package br.edu.com.uricer.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author marisa.richter
 */
public class Receita {
    private int idReceita;
    private String descricaoReceita;
    private BigDecimal valorReceita;
    private Date dataReceita;

    public Receita(String descricaoReceita, BigDecimal valorReceita, Date dataReceita) {
        this.descricaoReceita = descricaoReceita;
        this.valorReceita = valorReceita;
        this.dataReceita = dataReceita;
    }

    public Receita() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(int idReceita) {
        this.idReceita = idReceita;
    }
    
    public String getDescricaoReceita() {
        return descricaoReceita;
    }

    public void setDescricaoReceita(String descricaoReceita) {
        this.descricaoReceita = descricaoReceita;
    }

    public BigDecimal getValorReceita() {
        return valorReceita;
    }

    public void setValorReceita(BigDecimal valorReceita) {
        this.valorReceita = valorReceita;
    }

    public Date getDataReceita() {
        return dataReceita;
    }

    public void setDataReceita(Date dataReceita) {
        this.dataReceita = dataReceita;
    }
    
}
