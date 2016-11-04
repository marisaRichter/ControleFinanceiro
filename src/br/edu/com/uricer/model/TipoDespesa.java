package br.edu.com.uricer.model;

/**
 *
 * @author marisa.richter
 */
public class TipoDespesa {
    private int idTipoDespesa;
    private String descricaoTipoDespesa;

    public TipoDespesa(String descricaoTipoDespesa) {
        this.descricaoTipoDespesa = descricaoTipoDespesa;
    }

    public int getIdTipoDespesa() {
        return idTipoDespesa;
    }

    public void setIdTipoDespesa(int idTipoDespesa) {
        this.idTipoDespesa = idTipoDespesa;
    }

    public String getDescricaoTipoDespesa() {
        return descricaoTipoDespesa;
    }

    public void setDescricaoTipoDespesa(String descricaoTipoDespesa) {
        this.descricaoTipoDespesa = descricaoTipoDespesa;
    }
    
    
}
