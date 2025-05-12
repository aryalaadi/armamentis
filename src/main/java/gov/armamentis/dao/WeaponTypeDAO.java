package gov.armamentis.dao;

import gov.armamentis.model.WeaponTypeModel;
import gov.armamentis.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeaponTypeDAO {

    // Get all weapon types
    public List<WeaponTypeModel> all() {
        List<WeaponTypeModel> types = new ArrayList<>();
        String sql = "SELECT * FROM WeaponType";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                WeaponTypeModel type = new WeaponTypeModel(
                    rs.getInt("typeID"),
                    rs.getString("typeName")
                );
                types.add(type);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }

    // Find a type by ID
    public WeaponTypeModel findById(int typeID) {
        String sql = "SELECT * FROM WeaponType WHERE typeID = ?";
        WeaponTypeModel type = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, typeID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    type = new WeaponTypeModel(
                        rs.getInt("typeID"),
                        rs.getString("typeName")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return type;
    }
}
