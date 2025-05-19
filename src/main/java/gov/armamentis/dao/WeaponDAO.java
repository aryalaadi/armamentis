package gov.armamentis.dao;

import gov.armamentis.model.InventoryModel;
import gov.armamentis.model.WeaponModel;
import gov.armamentis.util.DBUtil;

import java.sql.*;
import java.util.*;

public class WeaponDAO {

    // Get all weapons
    public List<WeaponModel> all() {
        List<WeaponModel> list = new ArrayList<>();
        String sql = "SELECT weaponID, name, typeID FROM Weapon";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                WeaponModel w = new WeaponModel();
                w.setWeaponID(rs.getInt("weaponID"));
                w.setName(rs.getString("name"));
                w.setTypeID(rs.getInt("typeID"));
                list.add(w);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Find by ID
    public WeaponModel find(int id) {
        String sql = "SELECT weaponID, name, typeID FROM Weapon WHERE weaponID = ?";
        WeaponModel w = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    w = new WeaponModel();
                    w.setWeaponID(rs.getInt("weaponID"));
                    w.setName(rs.getString("name"));
                    w.setTypeID(rs.getInt("typeID"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return w;
    }

    public List<WeaponModel> searchByName(String searchTerm) {
        List<WeaponModel> weapons = new ArrayList<>();
        // Remove quotes around the ? placeholder
        String sql = "SELECT * FROM Weapon WHERE Name LIKE ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            // The % wildcards should be in the parameter, not the SQL
            stmt.setString(1, "%" + searchTerm + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    WeaponModel weapon = new WeaponModel();
                    weapon.setWeaponID(rs.getInt("WeaponID"));  // Match exact column case
                    weapon.setName(rs.getString("Name"));
                    weapon.setTypeID(rs.getInt("TypeID"));
                    weapons.add(weapon);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return weapons;
    }
    // Add new
    public void add(WeaponModel w) {
        String sql = "INSERT INTO Weapon (name, typeID) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, w.getName());
            stmt.setInt(2, w.getTypeID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void update(WeaponModel w) {
        String sql = "UPDATE Weapon SET name = ?, typeID = ? WHERE weaponID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, w.getName());
            stmt.setInt(2, w.getTypeID());
            stmt.setInt(3, w.getWeaponID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void delete(int id) {
        String sql = "DELETE FROM Weapon WHERE weaponID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
