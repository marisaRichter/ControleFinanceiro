package br.edu.com.uricer.model;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author marisa.richter
 */
public class Parcela {
    private int idParcela;
    private BigDecimal valorParcela;
    private BigDecimal saldoAPagar;
    private int fkDespesa;
    private Date dataVencimento;
    private BigDecimal jurosPorVencimento;
    private StatusParcela statusParcela;

    public Parcela(BigDecimal valorParcela, int fkDespesa, Date data) throws SQLException {
        this.valorParcela = valorParcela;
        this.saldoAPagar = valorParcela;
        this.statusParcela = new StatusParcela("Para Pagar");
        this.statusParcela.setIdStatus(3);
        this.fkDespesa = fkDespesa;
        this.dataVencimento = data;
        this.jurosPorVencimento = new BigDecimal(0.0);
    }
    
    public Parcela(int id, BigDecimal valorParcela, BigDecimal aPagar, StatusParcela status, int fkDespesa, Date data){
        this.idParcela = id;
        this.valorParcela = valorParcela;
        this.saldoAPagar = aPagar;
        this.statusParcela = status;
        this.fkDespesa = fkDespesa;
        this.dataVencimento = data;
        this.jurosPorVencimento = new BigDecimal(0.0);
    }

    public Parcela() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getFkDespesa() {
        return fkDespesa;
    }

    public void setFkDespesa(int fkDespesa) {
        this.fkDespesa = fkDespesa;
    }

    public int getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public BigDecimal getSaldoAPagar() {
        return saldoAPagar;
    }

    public void setSaldoAPagar(BigDecimal saldoAPagar) {
        this.saldoAPagar = saldoAPagar;
    }

    public StatusParcela getStatusParcela() {
        return statusParcela;
    }

    public void setStatusParcela(StatusParcela statusParcela) {
        this.statusParcela = statusParcela;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getJurosPorVencimento() {
        return jurosPorVencimento;
    }

    public void setJurosPorVencimento(BigDecimal jurosPorVencimento) {
        this.jurosPorVencimento = jurosPorVencimento;
    }
    
}
