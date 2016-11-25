/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.com.uricer.view;

import br.edu.com.uricer.model.Cartao;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Correa
 */
public class CartaoTableModel extends AbstractTableModel{
    
    private List<Cartao> cartoes;
    
    public CartaoTableModel(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    @Override
    public int getRowCount() {
        return cartoes.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cartao cartao = cartoes.get(rowIndex);
        System.out.println("teste");
        switch (columnIndex) {
            case 0: return cartao.getIdCartao();
            case 1: return cartao.getNomeCartao();
            case 2: return cartao.getBanco();    
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Id";
            case 1: return "Nome";
            case 2: return "Banco";
            default:
                throw new AssertionError();
        }
    }
    
}
