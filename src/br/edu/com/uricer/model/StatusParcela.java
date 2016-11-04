package br.edu.com.uricer.model;

/**
 *
 * @author marisa.richter
 */
public class StatusParcela {
    private int idStatus;
    private String statusParcela;

    public StatusParcela(String statusParcela) {
        this.statusParcela = statusParcela;
    }

    public String getStatusParcela() {
        return statusParcela;
    }

    public void setStatusParcela(String statusParcela) {
        this.statusParcela = statusParcela;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
    
    
}
