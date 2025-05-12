package gov.armamentis.model;

public class WeaponTypeModel {
    private int typeID;
    private String typeName;

    // Constructors
    public WeaponTypeModel() {
        // default constructor
    }

    public WeaponTypeModel(int typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
    }

    // Getters and Setters
    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
