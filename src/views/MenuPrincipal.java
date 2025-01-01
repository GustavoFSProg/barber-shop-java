/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import br.barbearia.modulo.ClientesDTO;
import br.barbearia.modulo.ModuloConexao;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author oem
 */
public class MenuPrincipal extends javax.swing.JFrame {
    
    
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        
         conexao = ModuloConexao.conector();
         
         listar_clientes();
    }
    
    
      public void pesquisar_cliente(){
          String sql = "select  *   from agenda  where  cliente  like ?";
          try{
               pst=conexao.prepareStatement(sql);
               
               pst.setString(1, ClienteField.getText() + "%");
               
                     rs= pst.executeQuery();
                     
                     Tabela.setModel(DbUtils.resultSetToTableModel(rs));
          
              }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
          
           
      }
    
        public void  listar_clientes(){
           String sql ="select * from clientes";
           
           ComboBoxClientes.removeAll();
           
           
               try{
                   
//                   ClientesDTO clientesOBJ = new ClientesDTO();
                   
                   
                
                
                pst = conexao.prepareStatement(sql);

                
                rs = pst.executeQuery();
                
                 while(rs.next()){
                     
                                        ComboBoxClientes.addItem(rs.getString(2));

                     
                 }
         
                
            }catch(Exception e){
                   JOptionPane.showMessageDialog(null, e);
                
            }
       }
        
        public void lista_agenda(){
//            date_format(data_os,  '%d/%m/%Y  -  %H:%i'
            
            String sql = "select *  from agenda order by cliente";
            
                  try{
               pst=conexao.prepareStatement(sql);
               
               
                     rs= pst.executeQuery();
                     
//                         if (rs.next()) {
                     
//                       Id.setText(rs.getString(1));  
                     
                     Tabela.setModel(DbUtils.resultSetToTableModel(rs));
//                         }
          
              }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
            
        }
        
        public void insere_agenda(){
                 String sql ="insert into agenda(cliente,  servico,   valor, data, hora, obs ) values(?,  ?, ? ,? ,? ,? )";
                 
           
               try{
                   pst=conexao.prepareStatement(sql);
           
                              pst.setString(1, ComboBoxClientes.getSelectedItem().toString());       
                              pst.setString(2, ComboBoxServico.getSelectedItem().toString()); 
                              pst.setString(3,  Valor.getText());             
                              pst.setString(4,  data.getText());
                              pst.setString(5,  hora.getText());           
                              pst.setString(6,  obs.getText());

                
//                rs = pst.executeQuery();
                
                          int adicionado =     pst.executeUpdate();
            
                          

            if(adicionado > 0){
                
               JOptionPane.showMessageDialog(null,"Agendado com sucesso!!");
//                    Tabela.setModel(DbUtils.resultSetToTableModel(rs));
                  lista_agenda();
               
//                     limpar_campos();

              
            }else{
                JOptionPane.showMessageDialog(null,"ERRO no agendamento!!");
                
            }
           
                
//                 while(rs.next()){
//                     
////                                        ComboBoxClientes.addItem(rs.getString(2));
//                     JOptionPane.showMessageDialog(null, "Agendado!!");
////                     
//
//                     
//                 }
         
                
            }catch(Exception e){
                   JOptionPane.showMessageDialog(null, e);
                
            }
        }
        
          public void setar_campos(){
            int setar = Tabela.getSelectedRow();
            
                Id.setText(Tabela.getModel().getValueAt(setar, 0).toString());
               Valor.setText(Tabela.getModel().getValueAt(setar, 3).toString());     
            
               data.setText(Tabela.getModel().getValueAt(setar, 4).toString());  
               hora.setText(Tabela.getModel().getValueAt(setar, 5).toString()); 
               
                ComboBoxClientes.setSelectedItem(Tabela.getModel().getValueAt(setar, 1).toString());
                
                ComboBoxServico.setSelectedItem(Tabela.getModel().getValueAt(setar, 2).toString());

//               FoneField.setText(TabelaClientes.getModel().getValueAt(setar, 3).toString());
//               EnderecoField.setText(TabelaClientes.getModel().getValueAt(setar, 4).toString());
//               
//               AddButton.setEnabled(false);

        }
          
              
       private void update(){
           
         String sql = "update agenda  set  servico =?, valor=? , data=? , hora=?, obs=?  where  id=?";
//             
//          
             try{
                 
            pst= conexao.prepareStatement(sql);
//            pst.setString(1, ComboBoxClientes.getSelectedItem().toString()); 
            pst.setString(1, ComboBoxServico.getSelectedItem().toString());   
            pst.setString(2, Valor.getText());       
            pst.setString(3, data.getText());        
            pst.setString(4, hora.getText());       
            pst.setString(5, obs.getText()); 
            pst.setString(6, Id.getText());
             
             

//      if(Equipamento.getText().isEmpty()){
//                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
//                
//                
//            }else{
            
             int adicionado =     pst.executeUpdate();
            
                          

            if(adicionado > 0){
                
               JOptionPane.showMessageDialog(null,"OS  Atualizado com sucesso!");
//              
//               
//               limpar_campos();
//                 
//                  AddButton.setEnabled(true);
//                     UpdateButton.setEnabled(false);
//                     deleteButton.setEnabled(false);
////                     Imprimir.setEnabled(false);
              
            }
//            }
             }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
//    
           
       }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        obs = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        hora = new javax.swing.JFormattedTextField();
        data = new javax.swing.JFormattedTextField();
        Valor = new javax.swing.JTextField();
        ComboBoxClientes = new javax.swing.JComboBox();
        ComboBoxServico = new javax.swing.JComboBox();
        Id = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        ClienteField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Cadastro = new javax.swing.JMenu();
        MenuCliente = new javax.swing.JMenuItem();
        MenuServico = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 1000));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0,0,190));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setForeground(new java.awt.Color(255, 250, 250));

        obs.setColumns(20);
        obs.setRows(10);
        obs.setPreferredSize(new java.awt.Dimension(220, 270));
        jScrollPane1.setViewportView(obs);

        jButton1.setBackground(new java.awt.Color(65, 158, 85));
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(239, 245, 241));
        jButton1.setText("Agendar");
        jButton1.setToolTipText("Agendar Serviço");
        jButton1.setAlignmentX(-1.0F);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Tabela.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Cliente", "Serviço", "Valor", "Data", "Hora", "Obs"
            }
        ));
        Tabela.setIntercellSpacing(new java.awt.Dimension(2, 2));
        Tabela.setRowHeight(25);
        Tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaMouseClicked(evt);
            }
        });
        Tabela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TabelaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(Tabela);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 247, 247));
        jLabel3.setText("Agenda");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(252, 240, 240));
        jLabel2.setText("Id");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(252, 240, 240));
        jLabel4.setText("Cliente");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(252, 240, 240));
        jLabel5.setText("Serviço");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(252, 240, 240));
        jLabel6.setText("R$ Valor");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(252, 240, 240));
        jLabel7.setText("Data");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(252, 240, 240));
        jLabel8.setText("Hora");

        try {
            hora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        hora.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        try {
            data.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        data.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        Valor.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N

        ComboBoxClientes.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        ComboBoxClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione o Cliente" }));
        ComboBoxClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxClientesActionPerformed(evt);
            }
        });
        ComboBoxClientes.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ComboBoxClientesAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        ComboBoxServico.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        ComboBoxServico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Corte de Cabelo", "Aparar a Barba", "Megahair", "Lavar o Cabelo", "Fazer as unhas" }));

        Id.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        Id.setEnabled(false);

        jButton2.setText("LISTAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ClienteField.setPreferredSize(new java.awt.Dimension(16, 34));
        ClienteField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ClienteFieldKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(254, 242, 242));
        jLabel10.setText("Nome do Cliente:");

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(252, 240, 240));
        jLabel11.setText("Observações");

        jButton3.setText("ATUALIZAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(367, 367, 367)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7)))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ComboBoxServico, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Valor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(99, 99, 99)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(ClienteField, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(ComboBoxClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(79, 79, 79)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jButton2))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ComboBoxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ComboBoxServico, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Valor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(28, 28, 28))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ClienteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jButton3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 1050, 740));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/imagens/fundo-menu-maior.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1500, 1500));
        jLabel1.setMinimumSize(new java.awt.Dimension(1500, 1500));
        jLabel1.setPreferredSize(new java.awt.Dimension(1190, 620));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 850));

        jMenuBar1.setPreferredSize(new java.awt.Dimension(130, 27));

        Cadastro.setText("Menu");

        MenuCliente.setText("Cliente");
        MenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuClienteActionPerformed(evt);
            }
        });
        Cadastro.add(MenuCliente);

        MenuServico.setText("Serviço");
        Cadastro.add(MenuServico);

        jMenuItem1.setText("Home");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Cadastro.add(jMenuItem1);

        jMenuBar1.add(Cadastro);

        jMenu1.setText("Operação");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1200, 910));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   
    
    
    private void ComboBoxClientesAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ComboBoxClientesAncestorAdded

        
    
            
        
    
// TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxClientesAncestorAdded

    public void run() {
          repaint(); //repaint pro Frame
         }
    
    private void ComboBoxClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxClientesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   insere_agenda();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      lista_agenda();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ClienteFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClienteFieldKeyReleased
      pesquisar_cliente();        // TODO add your handling code here:
    }//GEN-LAST:event_ClienteFieldKeyReleased

    private void MenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuClienteActionPerformed
        // TODO add your handling code here:
        
        Clientes clientes = new Clientes();
        clientes.setVisible(true);
    }//GEN-LAST:event_MenuClienteActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
   
  setVisible(false);
        
        Main main = new Main();
main.setVisible(true);

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void TabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabelaKeyReleased
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Pegou");
    }//GEN-LAST:event_TabelaKeyReleased

    private void TabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaMouseClicked
        // TODO add your handling code here:
        
        setar_campos();
    }//GEN-LAST:event_TabelaMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
   update();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Cadastro;
    private javax.swing.JTextField ClienteField;
    private javax.swing.JComboBox ComboBoxClientes;
    private javax.swing.JComboBox ComboBoxServico;
    private javax.swing.JTextField Id;
    private javax.swing.JMenuItem MenuCliente;
    private javax.swing.JMenuItem MenuServico;
    private javax.swing.JTable Tabela;
    private javax.swing.JTextField Valor;
    private javax.swing.JFormattedTextField data;
    private javax.swing.JFormattedTextField hora;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea obs;
    // End of variables declaration//GEN-END:variables
}
