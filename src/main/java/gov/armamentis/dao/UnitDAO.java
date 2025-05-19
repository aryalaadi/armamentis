package gov.armamentis.dao;

import gov.armamentis.model.UnitModel;
import gov.armamentis.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnitDAO {

    public List<UnitModel> getAll() throws SQLException {
        List<UnitModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Unit";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                UnitModel unit = new UnitModel();
                unit.setUnitID(rs.getInt("UnitID"));
                unit.setName(rs.getString("Name"));
                list.add(unit);
            }
        }
        return list;
    }

    public void insert(UnitModel unit) throws SQLException {
        String sql = "INSERT INTO Unit (Name) VALUES (?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, unit.getName());
            stmt.executeUpdate();
        }
    }

    public void update(UnitModel unit) throws SQLException {
        String sql = "UPDATE Unit SET Name = ? WHERE UnitID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, unit.getName());
            stmt.setInt(2, unit.getUnitID());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Unit WHERE UnitID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
