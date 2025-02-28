package db;

import model.Partner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartnerDAO {

    // Получить список всех партнеров
    public List<Partner> getAllPartners() {
        List<Partner> partners = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM partners ORDER BY name_of_company")) {

            while (rs.next()) {
                Partner partner = new Partner();
                partner.setId(rs.getInt("id"));
                partner.setTypeOfCompany(rs.getString("type_of_company"));
                partner.setNameOfCompany(rs.getString("name_of_company"));
                partner.setLegalAddress(rs.getString("legal_address"));
                partner.setTin(rs.getString("tin"));
                partner.setFullName(rs.getString("full_name"));
                partner.setPhoneNumber(rs.getString("phone_number"));
                partner.setEmail(rs.getString("email"));
                partner.setLogo(rs.getString("logo"));
                partner.setRating(rs.getInt("rating"));

                // Рассчитать скидку для партнера на основе общих продаж
                double discount = calculateDiscount(partner.getId());
                partner.setDiscount(discount);

                partners.add(partner);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partners;
    }

    // Получить информацию о партнере по ID
    public Partner getPartnerById(int id) {
        Partner partner = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM partners WHERE id = ?")) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    partner = new Partner();
                    partner.setId(rs.getInt("id"));
                    partner.setTypeOfCompany(rs.getString("type_of_company"));
                    partner.setNameOfCompany(rs.getString("name_of_company"));
                    partner.setLegalAddress(rs.getString("legal_address"));
                    partner.setTin(rs.getString("tin"));
                    partner.setFullName(rs.getString("full_name"));
                    partner.setPhoneNumber(rs.getString("phone_number"));
                    partner.setEmail(rs.getString("email"));
                    partner.setLogo(rs.getString("logo"));
                    partner.setRating(rs.getInt("rating"));

                    // Рассчитать скидку для партнера
                    double discount = calculateDiscount(partner.getId());
                    partner.setDiscount(discount);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partner;
    }

    // Добавить нового партнера
    public boolean addPartner(Partner partner) {
        String sql = "INSERT INTO partners (type_of_company, name_of_company, legal_address, " +
                "tin, full_name, phone_number, email, logo, rating) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, partner.getTypeOfCompany());
            stmt.setString(2, partner.getNameOfCompany());
            stmt.setString(3, partner.getLegalAddress());
            stmt.setString(4, partner.getTin());
            stmt.setString(5, partner.getFullName());
            stmt.setString(6, partner.getPhoneNumber());
            stmt.setString(7, partner.getEmail());
            stmt.setString(8, partner.getLogo());
            stmt.setInt(9, partner.getRating());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        partner.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Обновить информацию о партнере
    public boolean updatePartner(Partner partner) {
        String sql = "UPDATE partners SET type_of_company = ?, name_of_company = ?, " +
                "legal_address = ?, tin = ?, full_name = ?, phone_number = ?, " +
                "email = ?, logo = ?, rating = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, partner.getTypeOfCompany());
            stmt.setString(2, partner.getNameOfCompany());
            stmt.setString(3, partner.getLegalAddress());
            stmt.setString(4, partner.getTin());
            stmt.setString(5, partner.getFullName());
            stmt.setString(6, partner.getPhoneNumber());
            stmt.setString(7, partner.getEmail());
            stmt.setString(8, partner.getLogo());
            stmt.setInt(9, partner.getRating());
            stmt.setInt(10, partner.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Расчет скидки на основе общего объема продаж
    private double calculateDiscount(int partnerId) {
        double totalSales = getTotalSales(partnerId);

        // Расчет скидки согласно правилам:
        if (totalSales < 10000) {
            return 0.0;
        } else if (totalSales < 50000) {
            return 5.0;
        } else if (totalSales < 300000) {
            return 10.0;
        } else {
            return 15.0;
        }
    }

    // Получить общий объем продаж для партнера
    private double getTotalSales(int partnerId) {
        double totalSales = 0.0;

        String sql = "SELECT SUM(p.cost_price) as total_sales " +
                "FROM implementation_history ih " +
                "JOIN products p ON ih.product_id = p.id " +
                "WHERE ih.partner_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, partnerId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalSales = rs.getDouble("total_sales");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalSales;
    }

    // Получить список типов партнеров
    public List<String> getPartnerTypes() {
        List<String> types = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT DISTINCT type_of_company FROM partners")) {

            while (rs.next()) {
                types.add(rs.getString("type_of_company"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }
}
