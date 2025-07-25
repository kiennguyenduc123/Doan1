/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.ChiTietHoaDonDao;
import DAO.HoadonDao;
import DAO.KhuyenmaiDao;
import DAO.LichdatDao;
import DAO.UserDao;
import DTO.Chitiethoadon;
import DTO.Chitietlichdat;
import DTO.Hoadon;
import DTO.Lichdat;
import DTO.Session;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import com.itextpdf.text.Font; // Font của iText dùng cho PDF
//import java.awt.Font;  
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;


public class HoaDonFrame extends javax.swing.JFrame {

    /**
     * Creates new form HoaDonFrame
     */

    private JTextArea textArea;
    private String noiDungHoaDon;
    
    private String maHD;
    private int idLich;
    private String tenNhanVien;
    private long tongCong;
    private long khuyenMai;
    private long thanhTien;
    private long tienKhachDua;
    private long tienThua;
    private int magg;
        private String makh;
        private int diemDaDung;
    
    private quanlyHoaDonPanel hoaDonPanel;

    private quanlyLichPanel lichPanel;
    
    private KhuyenMaiPanel khuyenmaiPanel;
    
    private LichdatDao lichdatDao;
    private UserDao userDao;
    private KhuyenmaiDao khuyenmaiDao;

    List<Lichdat> lichdatList = new ArrayList<>();
    List<Chitietlichdat> chiTietList = new ArrayList<>();
     public ChiTietHoaDonDao chiTietHoaDonDao;
    
    public HoaDonFrame(){}
    
    public HoaDonFrame(String maHD, String tenNhanVien, List<Chitietlichdat> chiTietList,
                        long tongCong, long khuyenMai, long thanhTien,
                        long tienKhachDua, long tienThua, int idLich,int magg,String makh, int diemDaDung  ,  quanlyHoaDonPanel hoaDonPanel, quanlyLichPanel lichPanel) {
        
        this.maHD = maHD;
        this.idLich = idLich;
        this.tenNhanVien = tenNhanVien;
        this.tongCong = tongCong;
        this.khuyenMai = khuyenMai;
        this.thanhTien = thanhTien;
        this.tienKhachDua = tienKhachDua;
        this.tienThua = tienThua;
        this.magg = magg;
        this.hoaDonPanel = hoaDonPanel;
        this.lichPanel = lichPanel;
//        this.khuyenmaiPanel = khuyenmaiPanel;
        this.makh = makh;
        this.diemDaDung = diemDaDung;
        this.chiTietHoaDonDao = new ChiTietHoaDonDao();
        setTitle("Hóa Đơn Thanh Toán");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        noiDungHoaDon = buildContent(maHD, tenNhanVien, chiTietList, tongCong, khuyenMai, thanhTien, tienKhachDua, tienThua);
        textArea = new JTextArea(noiDungHoaDon);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 14));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(420, 450));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton printBtn = new JButton("In Hoá Đơn");
        JButton pdfBtn = new JButton("Xuất HĐ (PDF)");
        JButton cancelBtn = new JButton("Cancel");

        printBtn.addActionListener(e -> {
            try {
                thanhToan();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDonFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        pdfBtn.addActionListener(e -> xuatPDF());
        cancelBtn.addActionListener(e -> dispose());

        buttonPanel.add(printBtn);
        buttonPanel.add(pdfBtn);
        buttonPanel.add(cancelBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private String buildContent(String maHD, String tenNhanVien, List<Chitietlichdat> chiTietList,
                                long tongCong, long khuyenMai, long thanhTien,
                                long tienKhachDua, long tienThua) {


        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        sb.append("              Cửa Hàng Thuê Dịch Vụ Cưới\n");
        sb.append("    355 xxxx, Quận xx, TP Hồ Chí Minh\n");
        sb.append("     SDT Cửa Hàng: 09765432\n\n");
        sb.append("         Hóa Đơn Thanh Toán\n");
        sb.append("--------------------------------------\n");
        sb.append("Số Phiếu: ").append(maHD).append("\n");
        sb.append("Ngày: ").append(sdf.format(new Date())).append("\n");
        sb.append("Thu Ngân: ").append(tenNhanVien).append("\n");
        sb.append("--------------------------------------\n");
        sb.append(String.format("%-10s %-3s %-10s %-10s\n", "Tên SP", "SL", "Giá", "Thành Tiền"));

        for (Chitietlichdat ct : chiTietList) {
            long donGia = (long) ct.getDonGia();
            long thanhTienSP = ct.getSoLuong() * donGia;
            sb.append(String.format("%-10s %-3d %,10d %,10d\n",
                    ct.getTenDV(), ct.getSoLuong(), donGia, thanhTienSP));
        }

        sb.append("\n");
        sb.append(String.format("Tổng cộng:       %,10d\n", tongCong));
        sb.append(String.format("Khuyến Mãi:      %,10d\n", khuyenMai));
        sb.append(String.format("Thành Tiền:      %,10d\n", thanhTien));
        sb.append(String.format("Tiền Khách Đưa:  %,10d\n", tienKhachDua));
        sb.append(String.format("Tiền Thừa:       %,10d\n", tienThua));
        sb.append("\n");
        sb.append("  Xin Cảm Ơn Quý Khách\n");
        sb.append("       Và Hẹn Gặp Lại!\n");

        return sb.toString();
    }

    private void thanhToan() throws ClassNotFoundException {
        // Tạo đối tượng hóa đơn mới
        HoadonDao hoadonDao = new HoadonDao();

        // Tùy vào dữ liệu đầu vào bạn có thể lấy lại các thông tin cần thiết từ các biến
        Hoadon hd = new Hoadon();
        hd.setMaHd(maHD); // truyền từ constructor
        hd.setNgayttoan(new java.sql.Date(System.currentTimeMillis())); // ngày hiện tại
        hd.setTongTien(thanhTien);
        hd.setVaiTro(Session.vaiTro);
        hd.setIddonHang(idLich);  // <- cái này là quan trọng nhất
        hd.setIdUser(Session.id);
        hd.setIdMagg(magg);
        hd.setMaKH(makh); // từ tfMaKH.getText()
        hd.setDiemDadung(diemDaDung); // đã tính toán trong thanh toán
        // Bạn cần xử lý thêm: idUser, id_don_hang, id_km... nếu cần
        boolean inserted = hoadonDao.insertHoaDon(hd); // viết phương thức này trong DAO
        if (inserted) {
            int idHoaDon = ChiTietHoaDonDao.getIdHoaDonByMa(maHD); // Lấy lại ID
            List<Chitiethoadon> chiTietList = chiTietHoaDonDao.getChiTietHoaDonFromLich(idLich);
            boolean chiTietInserted = chiTietHoaDonDao.insertChiTietHoaDon(idHoaDon, chiTietList);

            if (!chiTietInserted) {
                JOptionPane.showMessageDialog(this, "Thêm chi tiết hóa đơn thất bại!");
                return;
            }
            JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
            dispose(); // đóng frame

            if (hoaDonPanel != null) {
                hoaDonPanel.reloadData(); // load lại danh sách hoá đơn
            }
            if (lichPanel != null) {
                lichPanel.loadLichThue(); // hoặc loadLich() tùy bạn đặt tên
            }
            if(this.khuyenmaiPanel != null) {
                khuyenmaiPanel.loadTable();
                khuyenmaiPanel.revalidate();
                khuyenmaiPanel.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Thanh toán thất bại!");
        }
    }

  private void xuatPDF() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu hóa đơn PDF");
    fileChooser.setSelectedFile(new File("hoa_don_" + System.currentTimeMillis() + ".pdf"));

    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();

        try {
            Document document = new Document(PageSize.A6, 20, 20, 20, 20); // Lề trái, phải, trên, dưới
            PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
            document.open();

            // Font hỗ trợ Unicode
            BaseFont bf = BaseFont.createFont("c:/windows/fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            com.itextpdf.text.Font fontNormal = new com.itextpdf.text.Font(bf, 10);
            com.itextpdf.text.Font fontBold = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD);

            // Header
            Paragraph header = new Paragraph("Cửa Hàng Thuê Dịch Vụ Cưới", fontBold);
            header.setAlignment(Element.ALIGN_CENTER);
            header.setSpacingAfter(5);
            document.add(header);

            document.add(new Paragraph("355 xxxx, Quận xx, TP Hồ Chí Minh", fontNormal));
            document.add(new Paragraph("SDT Cửa Hàng: 09765432", fontNormal));

            document.add(Chunk.NEWLINE);
            document.add(new LineSeparator());

            // Tiêu đề hóa đơn
            Paragraph title = new Paragraph("HÓA ĐƠN THANH TOÁN", fontBold);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(10);
            title.setSpacingAfter(10);
            document.add(title);

            document.add(new Paragraph("Số Phiếu: " + maHD, fontNormal));
            document.add(new Paragraph("Ngày: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()), fontNormal));
            document.add(new Paragraph("Thu Ngân: " + tenNhanVien, fontNormal));

            document.add(Chunk.NEWLINE);
            document.add(new LineSeparator());
            document.add(Chunk.NEWLINE);

            // Bảng sản phẩm
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{3, 1, 2, 3});
            table.setSpacingBefore(5f);
            table.setSpacingAfter(5f);

            table.addCell(getCell("Tên SP", fontBold));
            table.addCell(getCell("SL", fontBold));
            table.addCell(getCell("Giá", fontBold));
            table.addCell(getCell("Thành Tiền", fontBold));

            for (Chitietlichdat ct : chiTietList) {
                long donGia = (long) ct.getDonGia();
                long thanhTienSP = ct.getSoLuong() * donGia;

                table.addCell(getCell(ct.getTenDV(), fontNormal));
                table.addCell(getCell(String.valueOf(ct.getSoLuong()), fontNormal));
                table.addCell(getCell(String.format("%,d", donGia), fontNormal));
                table.addCell(getCell(String.format("%,d", thanhTienSP), fontNormal));
            }

            document.add(table);

            document.add(new LineSeparator());

            // Tổng kết
            document.add(getRightAligned("Tổng cộng: " + String.format("%,d", tongCong), fontNormal));
            document.add(getRightAligned("Khuyến mãi: " + String.format("%,d", khuyenMai), fontNormal));
            document.add(getRightAligned("Thành tiền: " + String.format("%,d", thanhTien), fontBold));
            document.add(getRightAligned("Tiền khách đưa: " + String.format("%,d", tienKhachDua), fontNormal));
            document.add(getRightAligned("Tiền thừa: " + String.format("%,d", tienThua), fontNormal));

            document.add(Chunk.NEWLINE);
            Paragraph thank = new Paragraph("Xin cảm ơn quý khách!\nHẹn gặp lại!", fontNormal);
            thank.setAlignment(Element.ALIGN_CENTER);
            thank.setSpacingBefore(10);
            document.add(thank);

            document.close();
            JOptionPane.showMessageDialog(this, "Đã xuất PDF thành công:\n" + fileToSave.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất PDF:\n" + e.getMessage());
        }
    }
}

private PdfPCell getCell(String text, Font font) {
    PdfPCell cell = new PdfPCell(new Phrase(text, font));
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setPadding(3f); // Thêm padding cho đẹp
    return cell;
}

private Paragraph getRightAligned(String text, Font font) {
    Paragraph p = new Paragraph(text, font);
    p.setAlignment(Element.ALIGN_RIGHT);
    return p;
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(HoaDonFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(HoaDonFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(HoaDonFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(HoaDonFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new HoaDonFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
