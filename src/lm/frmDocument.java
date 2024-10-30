/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package lm;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class frmDocument extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmNhanvien1
     */
    public frmDocument() {
        initComponents();
        getDocument();
    }

    public void getDocument() {

        try {
            // Lấy mô hình (model) của jTable1 và xóa tất cả các hàng hiện tại
            DefaultTableModel dt = (DefaultTableModel) tbDocument.getModel();
            dt.setRowCount(0);

            // Tạo kết nối với cơ sở dữ liệu
            DatabaseHelper cn = new DatabaseHelper();
            System.out.println("Connected SQL server success");
            Object[] argv = new Object[0];

            // Thực hiện truy vấn và lấy dữ liệu từ bảng Document
            try (ResultSet resultSet = cn.selectQuery("SELECT * FROM Document", argv)) {
                System.out.println("Kết nối OK" + resultSet);
                while (resultSet.next()) {
                    // Tạo một hàng dữ liệu để thêm vào bảng
                    Vector v = new Vector();
                    v.add(resultSet.getString("document_id")); 
                    v.add(resultSet.getString("title"));  
                    v.add(resultSet.getString("author"));   
                    v.add(resultSet.getString("publication_year"));  
                    v.add(resultSet.getString("category_id")); 
                    v.add(resultSet.getString("publisher_id"));  

                    // Thêm hàng vào mô hình của jTable1
                    dt.addRow(v);
                }

                // Đóng kết nối sau khi hoàn thành
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi trong quá trình lấy dữ liệu: " + e.getMessage());
        }
    }

    public int insertDocument() {
        // ID is Auto inc
        String DoID = txtDocument_id.getText();
        String Ti = txtTitle.getText().trim();
        String Au = txtAuthor.getText().trim();
        String puby = txtPublication_year.getText().trim();
        String CaID = txtCategory_id.getText();
        String plishID = txtPublisher_id.getText();

        // Kiểm tra nếu các trường không được để trống
        if (Ti.isEmpty() || Au.isEmpty() || puby.isEmpty()  ) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
            return 0;
        }

        Object[] argv = new Object[5];
        // Số lượng tham số đúng
        argv[0] = Ti;
        argv[1] = Au;
        argv[2] = puby;
        argv[3] = CaID;
        argv[4] = plishID;

        try {
            // Tạo kết nối tới cơ sở dữ liệu
            DatabaseHelper cn = new DatabaseHelper();
            // Thực hiện câu truy vấn chèn dữ liệu vào bảng Document
            int rs = cn.executeQuery("INSERT INTO Document ( title, author, publication_year, category_id, publisher_id) VALUES ( ?, ?, ?, ?, ?)", argv);

            // Kiểm tra xem dữ liệu đã được chèn thành công chưa
            if (rs > 0) {
                JOptionPane.showMessageDialog(null, "Thêm mới thành công!");
                clearText(); // Xóa các trường nhập liệu sau khi thêm thành công
            }
            return rs; // Trả về số lượng hàng đã chèn

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm mới thất bại: " + e.getMessage());
            return 0; // Trả về 0 nếu có lỗi
        }
    }

    private String[] getDataFromUI() {

        String DoID = txtDocument_id.getText().trim();  
        String Ti = txtTitle.getText().trim();
        String Au = txtAuthor.getText().trim();
        String puby = txtPublication_year.getText().trim();
        String CaID = txtCategory_id.getText().trim();
        String plishID = txtPublisher_id.getText().trim();

        // Trả về mảng với các giá trị lấy từ giao diện
        return new String[]{DoID, Ti, Au, puby, CaID, plishID};
    }
   public void updateDocument() {
       try {
            String[] data = getDataFromUI();

            if (data.length < 6) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không đủ để cập nhật!");
                return;
            }

            if (data[0].isEmpty()) {
                JOptionPane.showMessageDialog(null, "ID không được để trống!");
                return;
            }
            String DoID = data[0];
            String Ti = data[1];
            String Au = data[2];
            String puby = data[3];
            String CaID = data[4];
            String plishID = data[5];

            DatabaseHelper cn = new DatabaseHelper();
            Object[] params = {Ti, Au, puby, CaID, plishID, DoID};

            int rs = cn.executeQuery("UPDATE Document SET title = ?, author = ?, publication_year = ?, category_id = ?, publisher_id = ? WHERE document_id = ?", params);
            if (rs > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công " + DoID);
                clearText();
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy phòng với ID: " + DoID);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID không hợp lệ!");
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi trong quá trình cập nhật: " + e.getMessage());
            e.printStackTrace();
        }
    }

// Phương thức clearText để xóa các trường nhập liệu
    private void clearText() {
        txtDocument_id.setText("");       
        txtTitle.setText("");      
        txtAuthor.setText("");     
        txtPublication_year.setText("");       
        txtCategory_id.setText("");       
        txtPublisher_id.setText("");      
    }

    frmDocument(frmMain aThis) {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jscro = new javax.swing.JScrollPane();
        tbDocument = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtDocument_id = new javax.swing.JTextField();
        txtTitle = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAuthor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPublication_year = new javax.swing.JTextField();
        txtCategory_id = new javax.swing.JTextField();
        btThem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtPublisher_id = new javax.swing.JTextField();
        btUpdate = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        tbDocument.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Document ID", "Title", "Author", "Publication year", "Category ID", "Publisher ID"
            }
        ));
        tbDocument.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDocumentMouseClicked(evt);
            }
        });
        jscro.setViewportView(tbDocument);

        jLabel4.setText("Title");

        txtDocument_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocument_idActionPerformed(evt);
            }
        });

        jLabel5.setText("Author");

        jLabel7.setText("Category ID");

        jLabel8.setText("Publisher ID");

        txtCategory_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategory_idActionPerformed(evt);
            }
        });

        btThem.setText("Thêm");
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        jLabel1.setText("Publication year");

        btUpdate.setText("Cập nhật");
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });

        jLabel3.setText("Document ID");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txtDocument_id, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(54, 54, 54)
                        .addComponent(txtAuthor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtPublisher_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(txtCategory_id, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPublication_year))
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btUpdate)
                .addGap(212, 212, 212))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDocument_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txtPublication_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtCategory_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtPublisher_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btThem)
                    .addComponent(btUpdate))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jscro, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscro, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCategory_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategory_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategory_idActionPerformed

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        // TODO add your handling code here:
        insertDocument();
        getDocument();
    }//GEN-LAST:event_btThemActionPerformed

    private void txtDocument_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocument_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocument_idActionPerformed

    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed
        // TODO add your handling code here:
        updateDocument();
        getDocument();
    }//GEN-LAST:event_btUpdateActionPerformed

    private void tbDocumentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDocumentMouseClicked
        // TODO add your handling code here:
        int i = tbDocument.getSelectedRow();
        if (i >= 0 && tbDocument.getValueAt(i, 0) != null) {
            String id = tbDocument.getValueAt(i, 0).toString();
            String Ti = tbDocument.getValueAt(i, 1).toString();
            txtDocument_id.setText(id);
            txtTitle.setText(Ti);
        }
    }//GEN-LAST:event_tbDocumentMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btThem;
    private javax.swing.JButton btUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jscro;
    private javax.swing.JTable tbDocument;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtCategory_id;
    private javax.swing.JTextField txtDocument_id;
    private javax.swing.JTextField txtPublication_year;
    private javax.swing.JTextField txtPublisher_id;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
