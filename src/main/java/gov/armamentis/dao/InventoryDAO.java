package gov.armamentis.dao;

import gov.armamentis.model.InventoryModel;
import gov.armamentis.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    public List<InventoryModel> getAll() throws SQLException {
        String sql = "SELECT * FROM Inventory";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<InventoryModel> list = new ArrayList<>();
            while (rs.next()) {
                InventoryModel inv = new InventoryModel(
                    rs.getInt("InventoryID"),
                    (Integer)rs.getObject("UserID"),
                    (Integer)rs.getObject("WeaponID"),
                    rs.getInt("Quantity"),
                    (Integer)rs.getObject("UnitID"),
                    rs.getDate("DateIssued"),
                    rs.getDate("DateReturned")
                );
                list.add(inv);
            }
            return list;
        }
    }

    public void insert(InventoryModel inv) throws SQLException {
        String sql = "INSERT INTO Inventory (UserID, WeaponID, Quantity, UnitID, DateIssued, DateReturned) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setObject(1, inv.getUserID(), Types.INTEGER);
            ps.setObject(2, inv.getWeaponID(), Types.INTEGER);
            ps.setInt(3, inv.getQuantity());
            ps.setObject(4, inv.getUnitID(), Types.INTEGER);
            ps.setDate(5, inv.getDateIssued());
            ps.setDate(6, inv.getDateReturned());
            ps.executeUpdate();
        }
    }

    public void update(InventoryModel inv) throws SQLException {
        String sql = "UPDATE Inventory SET UserID=?, WeaponID=?, Quantity=?, UnitID=?, DateIssued=?, DateReturned=? WHERE InventoryID=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setObject(1, inv.getUserID(), Types.INTEGER);
            ps.setObject(2, inv.getWeaponID(), Types.INTEGER);
            ps.setInt(3, inv.getQuantity());
            ps.setObject(4, inv.getUnitID(), Types.INTEGER);
            ps.setDate(5, inv.getDateIssued());
            ps.setDate(6, inv.getDateReturned());
            ps.setInt(7, inv.getInventoryID());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Inventory WHERE InventoryID=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}



