/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.com.uricer.view;

import br.edu.com.uricer.model.Tag;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Correa
 */
public class TagTableModel extends AbstractTableModel{
    
    private List<Tag> tags;
    
    public TagTableModel(List<Tag> tags) {
        this.tags = tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public int getRowCount() {
        return tags.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tag tag = tags.get(rowIndex);
        switch (columnIndex) {
            case 0: return tag.getIdTag();
            case 1: return tag.getCategoria();   
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Id";
            case 1: return "Categoria";
            default:
                throw new AssertionError();
        }
    }
    
}
