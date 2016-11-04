package br.edu.com.uricer.model;

import java.math.BigDecimal;

/**
 *
 * @author marisa.richter
 */
public class Parcela {
    private int idParcela;
    private BigDecimal valorParcela;
    private BigDecimal saldoAPagar;
    private StatusParcela statusParcela;
    private int fkDespesa;

    public Parcela(BigDecimal valorParcela, int fkDespesa) {
        this.valorParcela = valorParcela;
        this.saldoAPagar = valorParcela;
        this.statusParcela.setStatusParcela("PAGAR");
        this.fkDespesa = fkDespesa;
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
    
}
