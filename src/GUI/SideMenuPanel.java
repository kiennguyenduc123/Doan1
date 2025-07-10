/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class SideMenuPanel extends javax.swing.JPanel {
    private final AdminPanels mainFrame;
    private final int menuWidth = 260;
    
    public SideMenuPanel(AdminPanels mainFrame) {   
        int menuWidth = 260; 
        this.mainFrame = mainFrame; // ✅ Gán chính xác

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(45, 45, 45));
        setPreferredSize(new Dimension(menuWidth, getHeight()));

        add(Box.createVerticalStrut(15));

        addMenuItem("Icon/icons8-home-48.png", "Trang Chủ", mainFrame);
//        addDropdownMenu("Icon/icons8-ring-48.png", "Quản lý dịch vụ", new String[][]{
//                {"Icon/icons8-ring-48.png", "Gói cưới hỏi"},
//                {"Icon/icons8-car-48.png", "Thuê xe hoa"},
//                {"Icon/icons8-camera-intelligence-48.png", "Chụp ảnh "},
//                {"Icon/icons8-wedding-dress-50.png", "Thuê váy cưới"}
//        },mainFrame );
        addMenuItem("Icon/icons8-ring-48.png", "Quản Lý Dịch Vụ", mainFrame);

        addMenuItem("Icon/icons8-user-50.png", "Quản Lý Người Dùng", mainFrame);
        addMenuItem("Icon/shopping_cart_24px.png", "Khuyễn Mãi", mainFrame);
        addMenuItem("Icon/icons8-date-48.png", "Lịch đặt", mainFrame);
        addMenuItem("Icon/icons8-report-48.png", "Thống kê & báo cáo", mainFrame);
        addMenuItem("Icon/icons8-setting-48.png", "Cài đặt hệ thống", mainFrame);
        addMenuItem("Icon/icons8-logout-50.png", "Logout", mainFrame);

        add(Box.createVerticalGlue());
    }

    private void addMenuItem(String iconPath, String title, AdminPanels frame) {
        ImageIcon icon = loadIcon(iconPath, 22, 22);

        JButton button = new JButton(title, icon);
        int menuWidth = 260; 

        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(new Color(45, 45, 45));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        button.setBorderPainted(false);
        button.setIconTextGap(15);
        button.setMargin(new Insets(12, 20, 12, 10));

        Dimension fullSize = new Dimension(menuWidth, 45);
        button.setMaximumSize(fullSize);
        button.setPreferredSize(fullSize);
        button.setMinimumSize(fullSize);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(70, 70, 70));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(45, 45, 45));
            }
        });

//    button.addActionListener(e -> frame.showPanel(title));
        button.addActionListener(e -> {
            if (title.equals("Logout")) {
                int confirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Bạn có chắc muốn đăng xuất?",
                        "Xác nhận đăng xuất",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    Window window = SwingUtilities.getWindowAncestor(this);
                    if (window != null) {
                        window.dispose();
                    }
                    new Login().setVisible(true);
                }
            } else {
                frame.showPanel(title);
            }
        });

        add(button);
        add(Box.createVerticalStrut(12));
    }
    
//    private void addDropdownMenu(String iconPath, String title, String[][] subMenus, AdminPanels frame) {
//        ImageIcon icon = loadIcon(iconPath, 22, 22);
//        JButton mainButton = createStyledButton(title, icon, 20);
//
//        JPanel subMenuPanel = new JPanel();
//        subMenuPanel.setLayout(new BoxLayout(subMenuPanel, BoxLayout.Y_AXIS));
//        subMenuPanel.setBackground(new Color(55, 55, 55));
//        subMenuPanel.setVisible(false);
//
//        // Toggle
//        mainButton.addActionListener(e -> {
//            subMenuPanel.setVisible(!subMenuPanel.isVisible());
//            revalidate();
//            repaint();
//        });
//
//        add(mainButton);
//        add(Box.createVerticalStrut(5));
//        add(subMenuPanel);
//
//        for (String[] sub : subMenus) {
//            String subIconPath = sub[0];
//            String subTitle = sub[1];
//            ImageIcon subIcon = loadIcon(subIconPath, 18, 18);
//            JButton subBtn = createStyledButton(subTitle, subIcon, 45);
//            subBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//
//            subBtn.addActionListener(e -> mainFrame.showPanel(subTitle));
//            subMenuPanel.add(subBtn);
//            subMenuPanel.add(Box.createVerticalStrut(5));
//        }
//
//        add(Box.createVerticalStrut(10));
//    }
//    private JButton createStyledButton(String title, Icon icon, int leftPadding) {
//        JButton button = new JButton(title, icon);
//        button.setAlignmentX(Component.LEFT_ALIGNMENT);
//        button.setFocusPainted(false);
//        button.setHorizontalAlignment(SwingConstants.LEFT);
//        button.setBackground(new Color(45, 45, 45));
//        button.setForeground(Color.WHITE);
//        button.setFont(new Font("Segoe UI", Font.PLAIN, 15));
//        button.setBorderPainted(false);
//        button.setIconTextGap(15);
//        button.setMargin(new Insets(10, leftPadding, 10, 10));
//
//        Dimension fullSize = new Dimension(menuWidth, 40);
//        button.setMaximumSize(fullSize);
//        button.setPreferredSize(fullSize);
//
//        button.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent evt) {
//                button.setBackground(new Color(70, 70, 70));
//            }
//
//            @Override
//            public void mouseExited(MouseEvent evt) {
//                button.setBackground(new Color(45, 45, 45));
//            }
//        });
//
//        return button;
//    }

    // Hàm hỗ trợ load icon từ resources
    private ImageIcon loadIcon(String path, int width, int height) {
        URL url = getClass().getResource("/" + path);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } else {
            System.err.println("⚠ Không tìm thấy icon: " + path);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
