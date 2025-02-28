package ui;

import model.ImplementationHistory;
import model.Partner;
import db.ImplementationHistoryDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PartnerSalesHistoryPanel extends JPanel {
    private final MainFrame mainFrame;
    private final Partner partner;

    public PartnerSalesHistoryPanel(MainFrame mainFrame, Partner partner) {
        this.mainFrame = mainFrame;
        this.partner = partner;
        setLayout(new BorderLayout());
        setBackground(AppStyles.PRIMARY_BG);

        // Заголовок
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AppStyles.PRIMARY_BG);

        JLabel titleLabel = new JLabel("История продаж: " + partner.getNameOfCompany());
        titleLabel.setFont(AppStyles.HEADER_FONT);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JButton backButton = new JButton("Назад");
        backButton.setFont(AppStyles.REGULAR_FONT);
        backButton.addActionListener(e -> mainFrame.showEditPanel(partner));
        headerPanel.add(backButton, BorderLayout.EAST);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING, AppStyles.PADDING));

        add(headerPanel, BorderLayout.NORTH);

        // Таблица с данными
        String[] columnNames = {"Наименование продукции", "Количество", "Дата продажи"};
        ImplementationHistoryDAO dao = new ImplementationHistoryDAO();
        List<ImplementationHistory> history = dao.getHistoryByPartnerId(partner.getId());

        Object[][] data = new Object[history.size()][3];
        for (int i = 0; i < history.size(); i++) {
            data[i][0] = history.get(i).getProductName(); // Используем getProductName
            data[i][1] = history.get(i).getQuantity();
            data[i][2] = history.get(i).getDateOfImplementation();
        }

        JTable table = new JTable(data, columnNames);
        table.setFont(AppStyles.REGULAR_FONT);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}