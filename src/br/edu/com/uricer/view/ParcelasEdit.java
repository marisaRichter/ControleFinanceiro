/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.com.uricer.view;

import br.edu.com.uricer.dao.Conexao;
import br.edu.com.uricer.dao.ParcelaDAO;
import br.edu.com.uricer.dao.StatusDAO;
import br.edu.com.uricer.model.Parcela;
import br.edu.com.uricer.model.StatusParcela;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Correa
 */
public class ParcelasEdit extends javax.swing.JFrame {

    /**
     * Creates new form ParcelasEdit
     */
    public ParcelasEdit(String idDespesa) throws SQLException {
        initComponents();
        id_despesa = Integer.parseInt(idDespesa);
        inicializar(id_despesa);
    }

    private ParcelasEdit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void inicializar(int id_despesa) throws SQLException{
        Conexao con = new Conexao();
        parcelaDAO = new ParcelaDAO(con.getConexao());
        statusDAO = new StatusDAO(con.getConexao());
        
        parcelas = new ArrayList();
        
        parcelas = parcelaDAO.findParcelas(id_despesa);
        parcelasTableModel = new ParcelasTableModel(parcelas);
        TableParcelas.setModel(parcelasTableModel);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelPrincipal = new javax.swing.JTabbedPane();
        PainelLista = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableParcelas = new javax.swing.JTable();
        PainelEdit = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        CampoSaldo = new javax.swing.JTextField();
        BotaoSalvar = new javax.swing.JButton();
        CampoId = new javax.swing.JTextField();
        CampoTotal = new javax.swing.JTextField();
        CampoData = new javax.swing.JTextField();
        CampoJuros = new javax.swing.JTextField();
        CheckBoxPago = new javax.swing.JCheckBox();

        TableParcelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableParcelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableParcelasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableParcelas);

        javax.swing.GroupLayout PainelListaLayout = new javax.swing.GroupLayout(PainelLista);
        PainelLista.setLayout(PainelListaLayout);
        PainelListaLayout.setHorizontalGroup(
            PainelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
            .addGroup(PainelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelListaLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        PainelListaLayout.setVerticalGroup(
            PainelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
            .addGroup(PainelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelListaLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        PainelPrincipal.addTab("Lista", PainelLista);

        jLabel1.setText("Id:");

        jLabel2.setText("Valor Total:");

        jLabel3.setText("Saldo a Pagar:");

        jLabel4.setText("Data Vencimento: ");

        jLabel5.setText("Juros: ");

        jLabel6.setText("Status:");

        CampoSaldo.setEnabled(false);

        BotaoSalvar.setText("Salvar");
        BotaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarActionPerformed(evt);
            }
        });

        CampoId.setEnabled(false);

        CampoTotal.setEnabled(false);

        CheckBoxPago.setText("Pago");

        javax.swing.GroupLayout PainelEditLayout = new javax.swing.GroupLayout(PainelEdit);
        PainelEdit.setLayout(PainelEditLayout);
        PainelEditLayout.setHorizontalGroup(
            PainelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelEditLayout.createSequentialGroup()
                        .addComponent(BotaoSalvar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelEditLayout.createSequentialGroup()
                        .addGroup(PainelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CampoSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(CampoTotal)
                            .addComponent(CampoId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelEditLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(55, 55, 55))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CampoData)
                            .addComponent(CampoJuros)
                            .addComponent(CheckBoxPago, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addGap(34, 34, 34))))
        );
        PainelEditLayout.setVerticalGroup(
            PainelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(CampoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(CampoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoJuros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(CampoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckBoxPago))
                .addGap(27, 27, 27)
                .addComponent(BotaoSalvar)
                .addContainerGap(138, Short.MAX_VALUE))
        );

        PainelPrincipal.addTab("Editar", PainelEdit);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PainelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 395, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PainelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableParcelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableParcelasMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2) {
            parcela = parcelas.get(TableParcelas.getSelectedRow());
            CampoId.setText(String.valueOf(parcela.getIdParcela()));
            CampoTotal.setText(String.valueOf(parcela.getValorParcela()));
            CampoSaldo.setText(String.valueOf(parcela.getSaldoAPagar()));
            SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
            Date dataFormatada = parcela.getDataVencimento();
            CampoData.setText(sdf.format(dataFormatada));
            CampoJuros.setText(String.valueOf(parcela.getJurosPorVencimento()));
            if(parcela.getStatusParcela().getIdStatus() == 1){
                CheckBoxPago.setSelected(true);
            } else{
                CheckBoxPago.setSelected(false);
            }
            
            PainelPrincipal.setSelectedIndex(1);
//            btNovo.setEnabled(false);
//            btGravar.setEnabled(true);
//            btCancelar.setEnabled(true);
//            btExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_TableParcelasMouseClicked

    private void BotaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarActionPerformed
        // TODO add your handling code here:
        int id_status = 0;
        BigDecimal saldo = null;
        int id = Integer.parseInt(CampoId.getText());
        BigDecimal total = new BigDecimal(CampoTotal.getText());
        if(CheckBoxPago.getSelectedObjects().length == 1){
           saldo = new BigDecimal(0.0); 
           id_status = 1;
        } else{
            saldo = new BigDecimal(CampoSaldo.getText());
            id_status = 3;
        }
        try {
            status = statusDAO.findByIdStatus(id_status);
        } catch (SQLException ex) {
            Logger.getLogger(ParcelasEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date data = null;
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = CampoData.getText();
        try {
            data = sdf.parse(dataFormatada);
        } catch (ParseException ex) {
            Logger.getLogger(AddReceita.class.getName()).log(Level.SEVERE, null, ex);
        }
        BigDecimal juros = new BigDecimal(CampoJuros.getText());
        parcela = new Parcela(id, total, saldo, status, id_despesa, data);
        try {
            parcelaDAO.updateParcela(parcela);
        } catch (SQLException ex) {
            Logger.getLogger(ParcelasEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotaoSalvarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParcelasEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParcelasEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParcelasEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParcelasEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParcelasEdit().setVisible(true);                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoSalvar;
    private javax.swing.JTextField CampoData;
    private javax.swing.JTextField CampoId;
    private javax.swing.JTextField CampoJuros;
    private javax.swing.JTextField CampoSaldo;
    private javax.swing.JTextField CampoTotal;
    private javax.swing.JCheckBox CheckBoxPago;
    private javax.swing.JPanel PainelEdit;
    private javax.swing.JPanel PainelLista;
    private javax.swing.JTabbedPane PainelPrincipal;
    private javax.swing.JTable TableParcelas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    private Parcela parcela;
    private ParcelaDAO parcelaDAO;
    private List<Parcela> parcelas;
    private ParcelasTableModel parcelasTableModel;
    private StatusParcela status;
    private StatusDAO statusDAO;
    private int id_despesa;
}