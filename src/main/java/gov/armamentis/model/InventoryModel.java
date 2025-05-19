package gov.armamentis.model;

import java.sql.Date;

public class InventoryModel {
    private int inventoryID;
    private Integer userID;
    private Integer weaponID;
    private int quantity;
    private Integer unitID;
    private Date dateIssued;
    private Date dateReturned;

    public InventoryModel() {}

    public InventoryModel(int inventoryID, Integer userID, Integer weaponID, int quantity, Integer unitID, Date dateIssued, Date dateReturned) {
        this.inventoryID = inventoryID;
        this.userID = userID;
        this.weaponID = weaponID;
        this.quantity = quantity;
        this.unitID = unitID;
        this.dateIssued = dateIssued;
        this.dateReturned = dateReturned;
    }

    // getters & setters
    public int getInventoryID() { return inventoryID; }
    public void setInventoryID(int inventoryID) { this.inventoryID = inventoryID; }

    public Integer getUserID() { return userID; }
    public void setUserID(Integer userID) { this.userID = userID; }

    public Integer getWeaponID() { return weaponID; }
    public void setWeaponID(Integer weaponID) { this.weaponID = weaponID; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Integer getUnitID() { return unitID; }
    public void setUnitID(Integer unitID) { this.unitID = unitID; }

    public Date getDateIssued() { return dateIssued; }
    public void setDateIssued(Date dateIssued) { this.dateIssued = dateIssued; }

    public Date getDateReturned() { return dateReturned; }
    public void setDateReturned(Date dateReturned) { this.dateReturned = dateReturned; }
}
