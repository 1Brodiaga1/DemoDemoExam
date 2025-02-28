package ui;

import model.Partner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PartnerListPanel extends JPanel {
    private final MainFrame mainFrame;
    private final List<Partner> partners;

    public PartnerListPanel(MainFrame mainFrame, List<Partner> partners) {
        this.mainFrame = mainFrame;
        this.partners = partners;

        setLayout(new BorderLayout());
        setBackground(AppStyles.PRIMARY_BG);

        // Создаем заголовок
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AppStyles.PRIMARY_BG);

        JLabel titleLabel = new JLabel("Список партнеров");
        titleLabel.setFont(AppStyles.HEADER_FONT);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JButton addButton = new JButton("Добавить партнера");
        addButton.setFont(AppStyles.REGULAR_FONT);
        addButton.setBackground(AppStyles.ACCENT_COLOR);
        addButton.setForeground(Color.WHITE);

        addButton.addActionListener(e ->
                mainFrame.showEditPanel(new Partner()));

        headerPanel.add(addButton, BorderLayout.EAST);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(
                AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING));

        add(headerPanel, BorderLayout.NORTH);

        // Создаем панель со списком партнеров
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(AppStyles.PRIMARY_BG);

        for (Partner partner : partners) {
            JPanel partnerPanel = createPartnerPanel(partner);
            listPanel.add(partnerPanel);
            listPanel.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createPartnerPanel(Partner partner) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(AppStyles.PRIMARY_BG);
        panel.setBorder(AppStyles.PANEL_BORDER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        infoPanel.setBackground(AppStyles.PRIMARY_BG);

        // Верхняя строка: Тип | Наименование
        JLabel nameLabel = new JLabel(partner.getTypeOfCompany() + " | " + partner.getNameOfCompany());
        nameLabel.setFont(AppStyles.REGULAR_FONT);
        infoPanel.add(nameLabel);

        // Директор
        JLabel directorLabel = new JLabel("Директор: " + partner.getFullName());
        directorLabel.setFont(AppStyles.SMALL_FONT);
        infoPanel.add(directorLabel);

        // Телефон
        JLabel phoneLabel = new JLabel(partner.getPhoneNumber());
        phoneLabel.setFont(AppStyles.SMALL_FONT);
        infoPanel.add(phoneLabel);

        // Рейтинг
        JLabel ratingLabel = new JLabel("Рейтинг: " + partner.getRating());
        ratingLabel.setFont(AppStyles.SMALL_FONT);
        infoPanel.add(ratingLabel);

        infoPanel.setBorder(BorderFactory.createEmptyBorder(AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING));
        panel.add(infoPanel, BorderLayout.CENTER);

        // Отображение скидки
        JPanel discountPanel = new JPanel(new BorderLayout());
        discountPanel.setBackground(AppStyles.PRIMARY_BG);

        JLabel discountLabel = new JLabel(String.format("%.0f%%", partner.getDiscount()));
        discountLabel.setFont(AppStyles.HEADER_FONT);
        discountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        discountPanel.add(discountLabel, BorderLayout.CENTER);
        discountPanel.setBorder(BorderFactory.createEmptyBorder(AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING));

        panel.add(discountPanel, BorderLayout.EAST);

        // Обработчик клика для редактирования
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.showEditPanel(partner);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(AppStyles.SECONDARY_BG);
                infoPanel.setBackground(AppStyles.SECONDARY_BG);
                discountPanel.setBackground(AppStyles.SECONDARY_BG);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(AppStyles.PRIMARY_BG);
                infoPanel.setBackground(AppStyles.PRIMARY_BG);
                discountPanel.setBackground(AppStyles.PRIMARY_BG);
            }
        });

        return panel;
    }
}