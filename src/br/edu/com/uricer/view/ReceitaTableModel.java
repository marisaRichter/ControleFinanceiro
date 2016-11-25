package br.edu.com.uricer.view;

import br.edu.com.uricer.model.Receita;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author marisa.richter
 */
public class ReceitaTableModel extends AbstractTableModel{
    private List<Receita> receitas;
    
    public ReceitaTableModel(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        System.out.println("table");
        this.receitas = receitas;
    }

    @Override
    public int getRowCount() {
        return receitas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Receita receita = receitas.get(rowIndex);
        switch (columnIndex) {
            case 0: return receita.getIdReceita();
            case 1: return receita.getDescricaoReceita();
            case 2: DecimalFormat decFormat = new DecimalFormat("'R$ ' 0.##");
                    return decFormat.format(receita.getValorReceita());
            case 3: return receita.getDataReceita();
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Id";
            case 1: return "Descrição";
            case 2: return "Valor";
            case 3: return "Data";
            default:
                throw new AssertionError();
        }
    }
}
