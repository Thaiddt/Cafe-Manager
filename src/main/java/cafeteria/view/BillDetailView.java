package cafeteria.view;

import cafeteria.dao.BillDAO;
import cafeteria.dao.EmployeeDAO;
import cafeteria.dao.StatisticDAO;
import cafeteria.entity.Bill;
import cafeteria.entity.Employee;
import cafeteria.utils.LocalVietNam;
import cafeteria.utils.MsgBox;

import javax.swing.table.DefaultTableModel;

public class BillDetailView extends javax.swing.JInternalFrame {

    private StatisticDAO thongKeDAO;
    private BillDAO hoaDonDAO;
    private EmployeeDAO nhanVienDAO;
    DefaultTableModel tableSanPham;

    public BillDetailView(int maHoaDon) {
        initComponents();
        init();
        fillTable(maHoaDon);
        setForm(maHoaDon);
    }

    private void initComponents() {
        mahoadon = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        lbKhachHang = new javax.swing.JLabel();
        khachhang = new javax.swing.JLabel();
        lbNgayIn = new javax.swing.JLabel();
        ngayin = new javax.swing.JLabel();
        thungan = new javax.swing.JLabel();
        lbThuNgan = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        lblTong = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblChungNhan = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Receipt");

        mahoadon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mahoadon.setText("Mã hoá đơn:");

        lblMaHoaDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMaHoaDon.setText("HD001");

        lbKhachHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbKhachHang.setText("Nước ngoài");

        khachhang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        khachhang.setText("Khách hàng:");

        lbNgayIn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNgayIn.setText("16/11/2020");

        ngayin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ngayin.setText("Ngày in:");

        thungan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        thungan.setText("Thu nhân:");

        lbThuNgan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbThuNgan.setText("Hà Văn Minh");

        tblSanPham.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Sản Phẩm", "Số Lượng", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setResizable(false);
            tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(250);
            tblSanPham.getColumnModel().getColumn(1).setResizable(false);
            tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblSanPham.getColumnModel().getColumn(2).setResizable(false);
            tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setText("Tổng: ");

        lblTong.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblTong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTong.setText("000000");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Hóa Đơn Bán Hàng");

        lblChungNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/system/pay.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTong, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(mahoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(khachhang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thungan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ngayin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbThuNgan, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(lbNgayIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblChungNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mahoadon)
                            .addComponent(lblMaHoaDon))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(khachhang)
                            .addComponent(lbKhachHang))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(thungan)
                            .addComponent(lbThuNgan))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ngayin)
                            .addComponent(lbNgayIn)))
                    .addComponent(lblChungNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(lblTong))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel khachhang;
    private javax.swing.JLabel lbKhachHang;
    private javax.swing.JLabel lbNgayIn;
    private javax.swing.JLabel lbThuNgan;
    private javax.swing.JLabel lblChungNhan;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblTong;
    private javax.swing.JLabel mahoadon;
    private javax.swing.JLabel ngayin;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JLabel thungan;
    // End of variables declaration//GEN-END:variables

    private void init() {
        thongKeDAO = new StatisticDAO();
        hoaDonDAO = new BillDAO();
        nhanVienDAO = new EmployeeDAO();

        tableSanPham = (DefaultTableModel) tblSanPham.getModel();
    }

    private void fillTable(int maHoaDon) {
        tableSanPham.setRowCount(0);
        int tongTien = 0;
        try {
            for (Object[] x : thongKeDAO.getHoaDonChiTiet(maHoaDon)) {
                int soLuong = Integer.parseInt(x[1].toString());
                int tien = Integer.parseInt(x[2].toString());
                tongTien += (soLuong * tien);
                tableSanPham.addRow(new Object[]{
                    x[0], soLuong, LocalVietNam.getCurrency(soLuong * tien)
                });
            }
            lblTong.setText(LocalVietNam.getCurrency(tongTien));
        } catch (Exception e) {
            MsgBox.notify(e.getMessage(), this);
        }

    }

    private void setForm(int maHoaDon) {
        Bill hd = hoaDonDAO.selectByID(maHoaDon);
        Employee nv = nhanVienDAO.selectByID(hd.getMaNhanVien());

        lblMaHoaDon.setText(hd.getMaHoaDon() + "");
        lbNgayIn.setText(LocalVietNam.getDate(hd.getNgayXuatHoaDon()));
        lbThuNgan.setText(nv.getTenNhanVien());
        lbKhachHang.setText(hd.isLoaiKhachHang() ? "Nước ngoài" : "Trong Nước");
    }
}
