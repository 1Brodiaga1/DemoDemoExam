package ui;

import db.PartnerDAO;
import model.Partner;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private final PartnerDAO partnerDAO;
    private final JPanel mainPanel;
    private final ImageIcon logoIcon;

    public MainFrame() {
        partnerDAO = new PartnerDAO();
        mainPanel = new JPanel(new BorderLayout());

        // Загрузка логотипа
        logoIcon = new ImageIcon(getClass().getResource("/resources/logo.png"));
        setIconImage(logoIcon.getImage());

        // Настройка основного окна
        setTitle("Учет партнеров");
        setSize(800, 600);
        setMinimumSize(new Dimension(600, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Создание верхней панели с логотипом
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AppStyles.SECONDARY_BG);

        JLabel logoLabel = new JLabel(logoIcon);
        headerPanel.add(logoLabel, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Система учета партнеров", SwingConstants.CENTER);
        titleLabel.setFont(AppStyles.HEADER_FONT);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        headerPanel.setBorder(BorderFactory.createEmptyBorder(
                AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING));

        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Инициализация и отображение списка партнеров
        showListPanel();
    }

    public void showListPanel() {
        List<Partner> partners = partnerDAO.getAllPartners();
        JPanel listPanel = new PartnerListPanel(this, partners);

        mainPanel.removeAll();
        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void showEditPanel(Partner partner) {
        JPanel editPanel = new PartnerEditPanel(this, partner);

        mainPanel.removeAll();
        mainPanel.add(editPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void showSalesHistoryPanel(Partner partner) {
        JPanel historyPanel = new PartnerSalesHistoryPanel(this, partner);
        mainPanel.removeAll();
        mainPanel.add(historyPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        // Установка системного внешнего вида
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Запуск приложения в потоке обработки событий
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}