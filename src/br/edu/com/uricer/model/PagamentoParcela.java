package br.edu.com.uricer.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author marisa.richter
 */
public class PagamentoParcela {
    private int idPagamentoParcela;
    private BigDecimal valorPago;
    private Date dataPagamento;
    private int fkIdParcela;

    public PagamentoParcela(BigDecimal valorPago, Date dataPagamento, int fkIdParcela) {
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.fkIdParcela = fkIdParcela;
        setSaldoParcela();
    }

    public PagamentoParcela() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setSaldoParcela(){
        Parcela parcela = null;
        parcela.setSaldoAPagar(valorPago);
        parcela.setIdParcela(fkIdParcela);
    }
    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getIdPagamentoParcela() {
        return idPagamentoParcela;
    }

    public void setIdPagamentoParcela(int idPagamentoParcela) {
        this.idPagamentoParcela = idPagamentoParcela;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public int getFkIdParcela() {
        return fkIdParcela;
    }

    public void setFkIdParcela(int fkIdParcela) {
        this.fkIdParcela = fkIdParcela;
    }
    
}
