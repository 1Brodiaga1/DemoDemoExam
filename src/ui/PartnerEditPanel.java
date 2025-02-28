package ui;

import db.PartnerDAO;
import model.Partner;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PartnerEditPanel extends JPanel {
    private final MainFrame mainFrame;
    private final Partner partner;
    private final PartnerDAO partnerDAO;

    private JTextField nameField;
    private JComboBox<String> typeComboBox;
    private JSlider ratingSlider;
    private JTextField addressField;
    private JTextField directorField;
    private JTextField phoneField;
    private JTextField emailField;

    public PartnerEditPanel(MainFrame mainFrame, Partner partner) {
        this.mainFrame = mainFrame;
        this.partner = partner;
        this.partnerDAO = new PartnerDAO();

        setLayout(new BorderLayout());
        setBackground(AppStyles.PRIMARY_BG);

        // Заголовок
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AppStyles.PRIMARY_BG);

        String title = (partner.getId() == 0) ? "Добавление партнера" : "Редактирование партнера";
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(AppStyles.HEADER_FONT);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JButton backButton = new JButton("Назад");
        backButton.setFont(AppStyles.REGULAR_FONT);
        backButton.addActionListener(e -> mainFrame.showListPanel());

        headerPanel.add(backButton, BorderLayout.EAST);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(
                AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING));

        add(headerPanel, BorderLayout.NORTH);

        // Форма редактирования
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(AppStyles.PRIMARY_BG);
        formPanel.setBorder(BorderFactory.createEmptyBorder(
                AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Наименование компании
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Наименование:");
        nameLabel.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        nameField = new JTextField(partner.getNameOfCompany());
        nameField.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(nameField, gbc);

        // Тип партнера
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        JLabel typeLabel = new JLabel("Тип партнера:");
        typeLabel.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(typeLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        List<String> partnerTypes = partnerDAO.getPartnerTypes();
        typeComboBox = new JComboBox<>(partnerTypes.toArray(new String[0]));
        if (partner.getTypeOfCompany() != null) {
            typeComboBox.setSelectedItem(partner.getTypeOfCompany());
        }
        typeComboBox.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(typeComboBox, gbc);

        // Рейтинг
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        JLabel ratingLabel = new JLabel("Рейтинг:");
        ratingLabel.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(ratingLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        ratingSlider = new JSlider(1, 5, Math.max(1, partner.getRating()));
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setFont(AppStyles.SMALL_FONT);
        ratingSlider.setBackground(AppStyles.PRIMARY_BG);
        formPanel.add(ratingSlider, gbc);

        // Адрес
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        JLabel addressLabel = new JLabel("Адрес:");
        addressLabel.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        addressField = new JTextField(partner.getLegalAddress());
        addressField.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(addressField, gbc);

        // ФИО директора
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0;
        JLabel directorLabel = new JLabel("ФИО директора:");
        directorLabel.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(directorLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        directorField = new JTextField(partner.getFullName());
        directorField.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(directorField, gbc);

        // Телефон
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.0;
        JLabel phoneLabel = new JLabel("Телефон:");
        phoneLabel.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        phoneField = new JTextField(partner.getPhoneNumber());
        phoneField.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(phoneField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.0;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        emailField = new JTextField(partner.getEmail());
        emailField.setFont(AppStyles.REGULAR_FONT);
        formPanel.add(emailField, gbc);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // Кнопки
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(AppStyles.PRIMARY_BG);

        JButton cancelButton = new JButton("Отмена");
        cancelButton.setFont(AppStyles.REGULAR_FONT);
        cancelButton.addActionListener(e -> mainFrame.showListPanel());

        JButton saveButton = new JButton("Сохранить");
        saveButton.setFont(AppStyles.REGULAR_FONT);
        saveButton.setBackground(AppStyles.ACCENT_COLOR);
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> savePartner());

        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(
                AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING));

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void savePartner() {
        // Обновляем данные партнера из формы
        partner.setNameOfCompany(nameField.getText());
        partner.setTypeOfCompany((String) typeComboBox.getSelectedItem());
        partner.setRating(ratingSlider.getValue());
        partner.setLegalAddress(addressField.getText());
        partner.setFullName(directorField.getText());
        partner.setPhoneNumber(phoneField.getText());
        partner.setEmail(emailField.getText());

        boolean success;
        if (partner.getId() == 0) {
            // Новый партнер
            success = partnerDAO.addPartner(partner);
        } else {
            // Обновление существующего
            success = partnerDAO.updatePartner(partner);
        }

        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Данные успешно сохранены",
                    "Успех",
                    JOptionPane.INFORMATION_MESSAGE);
            mainFrame.showListPanel();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Ошибка при сохранении данных",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}