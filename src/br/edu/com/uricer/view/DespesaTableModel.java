/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.com.uricer.view;

import br.edu.com.uricer.dao.CartaoDAO;
import br.edu.com.uricer.dao.Conexao;
import br.edu.com.uricer.dao.TagDAO;
import br.edu.com.uricer.dao.TipoDespesaDAO;
import br.edu.com.uricer.model.Cartao;
import br.edu.com.uricer.model.Despesa;
import br.edu.com.uricer.model.Tag;
import br.edu.com.uricer.model.TipoDespesa;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Correa
 */
public class DespesaTableModel extends AbstractTableModel{
    private List<Despesa> despesas;
    private TipoDespesaDAO tipoDespesaDAO;
    private TagDAO tagDAO;
    private CartaoDAO cartaoDAO;
    
    public DespesaTableModel(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    @Override
    public int getRowCount() {
        return despesas.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Despesa despesa = despesas.get(rowIndex);
        DecimalFormat decFormat = new DecimalFormat("'R$ ' 0.##");        
        switch (columnIndex) {
            case 0: return despesa.getIdDespesa();
            case 1: return despesa.getDescricaoDespesa();
            case 2: return despesa.getLocalDespesa();    
            case 3: return despesa.getTipoDespesa().getDescricaoTipoDespesa();    
            case 4: return despesa.getFkCartaoUsado().getNomeCartao();    
            case 5: return despesa.getTag().getCategoria();    
            case 6: return despesa.getNumeroParcela();  
            case 7: return decFormat.format(despesa.getValorTotalDespesa());  
            case 8: return despesa.getDataDespesa(); 
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Id";
            case 1: return "Descrição";
            case 2: return "Local";    
            case 3: return "Pagemento feito em";  
            case 4: return "Cartão Usado";  
            case 5: return "Categoria";  
            case 6: return "Número de parcela(s)";  
            case 7: return "Total";  
            case 8: return "Data"; 
            default:
                throw new AssertionError();
        }
    }
}
