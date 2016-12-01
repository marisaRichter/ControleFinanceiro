/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.com.uricer.view;

import br.edu.com.uricer.dao.Conexao;
import br.edu.com.uricer.model.Parcela;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Correa
 */
public class ParcelasTableModel extends AbstractTableModel{
    private List<Parcela> parcelas;
    
    public ParcelasTableModel(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    @Override
    public int getRowCount() {
        return parcelas.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Parcela parcela = parcelas.get(rowIndex);
        DecimalFormat decFormat = new DecimalFormat("'R$ ' 0.##");        
        switch (columnIndex) {
            case 0: return parcela.getIdParcela();
            case 1: return parcela.getValorParcela();
            case 2: return parcela.getSaldoAPagar();    
            case 3: return parcela.getDataVencimento();
            case 4: return parcela.getJurosPorVencimento();    
            case 5: return parcela.getStatusParcela().getStatusParcela();
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Id";
            case 1: return "Valor da Parcela";
            case 2: return "Saldo a pagar";   
            case 3: return "Data Vencimento";  
            case 4: return "Juros";  
            case 5: return "Status";
            default:
                throw new AssertionError();
        }
    }
}
