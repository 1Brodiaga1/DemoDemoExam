package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {

    // Получить название продукта по его ID
    public String getProductName(int productId) {
        String productName = null;
        String sql = "SELECT name_of_product FROM products WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                productName = rs.getString("name_of_product");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productName;
    }
}