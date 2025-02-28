package db;

import model.ImplementationHistory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementationHistoryDAO {
    private final ProductDAO productDAO;

    public ImplementationHistoryDAO() {
        this.productDAO = new ProductDAO();
    }

    // Получить историю реализации для партнера
    public List<ImplementationHistory> getHistoryByPartnerId(int partnerId) {
        List<ImplementationHistory> historyList = new ArrayList<>();
        String sql = "SELECT ih.date_of_implementation, ih.quantity, ih.product_id " +
                "FROM implementation_history ih " +
                "WHERE ih.partner_id = ? " +
                "ORDER BY ih.date_of_implementation DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, partnerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ImplementationHistory history = new ImplementationHistory();
                history.setDateOfImplementation(rs.getDate("date_of_implementation"));
                history.setQuantity(rs.getInt("quantity"));
                history.setProductId(rs.getInt("product_id"));

                // Получаем название продукта по его ID
                String productName = productDAO.getProductName(history.getProductId());
                history.setProductName(productName); // Добавьте метод setProductName в класс ImplementationHistory

                historyList.add(history);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyList;
    }
}