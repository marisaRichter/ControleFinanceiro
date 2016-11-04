package br.edu.com.uricer.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marisa.richter
 */
public class FaturaCartao {
    private int idFaturaCartao;
    private Date mes;
    private BigDecimal valorTotal;
    private List<Despesa> listaDespesas = new ArrayList();
    private int idCartao;

    public FaturaCartao(Date mes, int idCartao) {
        this.mes = mes;
        this.idCartao = idCartao;
    }

    public int getIdFaturaCartao() {
        return idFaturaCartao;
    }

    public void setIdFaturaCartao(int idFaturaCartao) {
        this.idFaturaCartao = idFaturaCartao;
    }

    public Date getMes() {
        return mes;
    }

    public void setMes(Date mes) {
        this.mes = mes;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Despesa> getListaDespesas() {
        return listaDespesas;
    }

    public void setListaDespesas(List<Despesa> listaDespesas) {
        this.listaDespesas = listaDespesas;
    }

    public int getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(int idCartao) {
        this.idCartao = idCartao;
    }
 
}
