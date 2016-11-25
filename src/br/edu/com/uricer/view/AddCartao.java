/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.com.uricer.view;

import br.edu.com.uricer.dao.CartaoDAO;
import br.edu.com.uricer.dao.Conexao;
import br.edu.com.uricer.model.Cartao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Correa
 */
public class AddCartao extends javax.swing.JInternalFrame {
    
    public AddCartao() { 
        this.setTitle("CARTÃO");
        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        initComponents();
        inicializar();
    }
    private void inicializar(){
        Conexao con = new Conexao();
        cartaoDAO = new CartaoDAO(con.getConexao());        
        
        cartoes = new ArrayList<>();
        cartaoTableModel = new CartaoTableModel(cartoes);
        tableCartoes.setModel(cartaoTableModel);
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
        PanelPesquisar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCartoes = new javax.swing.JTable();
        LabelNomePesquisa = new javax.swing.JLabel();
        CampoPesquisar = new javax.swing.JTextField();
        BotaoPesquisar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PanelAdicionar = new javax.swing.JPanel();
        LabelId = new javax.swing.JLabel();
        LabelNome = new javax.swing.JLabel();
        LabelBanco = new javax.swing.JLabel();
        BotaoAddEditar = new javax.swing.JButton();
        CampoIdAdd = new javax.swing.JTextField();
        CampoNomeAdd = new javax.swing.JTextField();
        CampoBancoAdd = new javax.swing.JFormattedTextField();
        BotaoExcluir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        tableCartoes.setModel(new javax.swing.table.DefaultTableModel(
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
        tableCartoes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableCartoes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableCartoes.setUpdateSelectionOnSort(false);
        tableCartoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCartoesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCartoes);

        LabelNomePesquisa.setText("Nome:");

        BotaoPesquisar.setText("Pesquisar");
        BotaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoPesquisarActionPerformed(evt);
            }
        });

        jLabel1.setText("Para fazer uma edição/eliminação de algum cartão,");

        jLabel2.setText(" de um duplo click na linha referente ao mesmo.");

        javax.swing.GroupLayout PanelPesquisarLayout = new javax.swing.GroupLayout(PanelPesquisar);
        PanelPesquisar.setLayout(PanelPesquisarLayout);
        PanelPesquisarLayout.setHorizontalGroup(
            PanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelPesquisarLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(PanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelPesquisarLayout.createSequentialGroup()
                        .addGroup(PanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelPesquisarLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))
                            .addComponent(jLabel1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelPesquisarLayout.createSequentialGroup()
                        .addComponent(LabelNomePesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CampoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(BotaoPesquisar)
                        .addGap(98, 98, 98))))
        );
        PanelPesquisarLayout.setVerticalGroup(
            PanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNomePesquisa)
                    .addComponent(CampoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addContainerGap())
        );

        PainelPrincipal.addTab("Pesquisar", PanelPesquisar);

        LabelId.setText("Id:");

        LabelNome.setText("Nome: ");

        LabelBanco.setText("Banco: ");

        BotaoAddEditar.setText("Adicionar/Editar Cartão");
        BotaoAddEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAddEditarActionPerformed(evt);
            }
        });

        CampoIdAdd.setEnabled(false);

        CampoNomeAdd.setNextFocusableComponent(CampoBancoAdd);

        CampoBancoAdd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        CampoBancoAdd.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        BotaoExcluir.setText("Excluir");
        BotaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelAdicionarLayout = new javax.swing.GroupLayout(PanelAdicionar);
        PanelAdicionar.setLayout(PanelAdicionarLayout);
        PanelAdicionarLayout.setHorizontalGroup(
            PanelAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAdicionarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAdicionarLayout.createSequentialGroup()
                        .addGroup(PanelAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LabelBanco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LabelId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CampoIdAdd)
                            .addComponent(CampoBancoAdd)
                            .addGroup(PanelAdicionarLayout.createSequentialGroup()
                                .addComponent(CampoNomeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 66, Short.MAX_VALUE)))
                        .addGap(210, 210, 210))
                    .addGroup(PanelAdicionarLayout.createSequentialGroup()
                        .addComponent(BotaoAddEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BotaoExcluir)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PanelAdicionarLayout.setVerticalGroup(
            PanelAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAdicionarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelId)
                    .addComponent(CampoIdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNome)
                    .addComponent(CampoNomeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelBanco)
                    .addComponent(CampoBancoAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoAddEditar)
                    .addComponent(BotaoExcluir))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        PainelPrincipal.addTab("Adicionar/Editar", PanelAdicionar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PainelPrincipal)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PainelPrincipal)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotaoAddEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAddEditarActionPerformed
        // TODO add your handling code here:
        
        String nome = CampoNomeAdd.getText();
        String banco = CampoBancoAdd.getText();
        System.out.println("campo: " + nome + "-" + banco);
        cartao = new Cartao(nome, banco);
        if(CampoIdAdd.getText() == null || CampoIdAdd.getText().equals("")) {
                Integer idCriado = 0;
            try {
                
                idCriado = cartaoDAO.createCartao(cartao);
            } catch (SQLException ex) {
                Logger.getLogger(AddCartao.class.getName()).log(Level.SEVERE, null, ex);
            }
                cartao.setIdCartao(idCriado);
                CampoIdAdd.setText(idCriado.toString());

            } else {
                try {
                    int id = Integer.parseInt(CampoIdAdd.getText());
                    cartao.setIdCartao(id);
                    cartaoDAO.updateCartao(cartao);
                } catch (SQLException ex) {
                    Logger.getLogger(AddCartao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_BotaoAddEditarActionPerformed

    private void BotaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoPesquisarActionPerformed
        // TODO add your handling code here:
        try {
            cartoes = cartaoDAO.findByNomeCartao(CampoPesquisar.getText(), "");
            cartaoTableModel.setCartoes(cartoes);
            cartaoTableModel.fireTableDataChanged();
        } catch (SQLException ex) {
            Logger.getLogger(AddCartao.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_BotaoPesquisarActionPerformed

    private void tableCartoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCartoesMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2) {
            cartao = cartoes.get(tableCartoes.getSelectedRow());
            CampoIdAdd.setText(String.valueOf(cartao.getIdCartao()));
            CampoNomeAdd.setText(cartao.getNomeCartao());
            CampoBancoAdd.setText(cartao.getBanco());
            PainelPrincipal.setSelectedIndex(1);
            CampoNomeAdd.setEnabled(true);
//            btNovo.setEnabled(false);
//            btGravar.setEnabled(true);
//            btCancelar.setEnabled(true);
//            btExcluir.setEnabled(true);
        }else{
            System.out.println("MARISA");
        }
    }//GEN-LAST:event_tableCartoesMouseClicked

    private void BotaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoExcluirActionPerformed
        // TODO add your handling code here:
        if(CampoIdAdd.getText() != null || !CampoIdAdd.getText().equals("")) {
            int resultado = JOptionPane.showConfirmDialog(this, "Confirma exclusão", "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
            if(resultado == 0) {
                try {
                    String nome = CampoNomeAdd.getText();
                    String banco = CampoBancoAdd.getText();
                    int id = Integer.parseInt(CampoIdAdd.getText());
                    cartao = new Cartao(nome, banco);
                    cartao.setIdCartao(id);
                    cartaoDAO.deleteCartao(cartao);
                    limparEdits();
                } catch (SQLException ex) {
                    Logger.getLogger(AddCartao.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Erro ao excluir", "Erro", JOptionPane.ERROR);
                }
            }
        }
    }//GEN-LAST:event_BotaoExcluirActionPerformed
    private void limparEdits(){
        CampoIdAdd.setText("");
        CampoNomeAdd.setText("");
        CampoBancoAdd.setText("");
        CampoPesquisar.setText("");
    }
    private void cartaoParaEdit() {
        
    }
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
            java.util.logging.Logger.getLogger(AddCartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCartao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoAddEditar;
    private javax.swing.JButton BotaoExcluir;
    private javax.swing.JButton BotaoPesquisar;
    private javax.swing.JFormattedTextField CampoBancoAdd;
    private javax.swing.JTextField CampoIdAdd;
    private javax.swing.JTextField CampoNomeAdd;
    private javax.swing.JTextField CampoPesquisar;
    private javax.swing.JLabel LabelBanco;
    private javax.swing.JLabel LabelId;
    private javax.swing.JLabel LabelNome;
    private javax.swing.JLabel LabelNomePesquisa;
    private javax.swing.JTabbedPane PainelPrincipal;
    private javax.swing.JPanel PanelAdicionar;
    private javax.swing.JPanel PanelPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCartoes;
    // End of variables declaration//GEN-END:variables
    private Cartao cartao;
    private CartaoDAO cartaoDAO;
    private List<Cartao> cartoes;
    private CartaoTableModel cartaoTableModel;
}
