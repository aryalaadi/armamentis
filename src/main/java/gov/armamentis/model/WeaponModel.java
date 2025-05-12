package gov.armamentis.model;

public class WeaponModel {
    private int weaponID;
    private String name;
    private int typeID;
    
    public WeaponModel() {
        // empty
    }

    
	public WeaponModel(int weaponID, String name, int typeID) {
		super();
		this.weaponID = weaponID;
		this.name = name;
		this.typeID = typeID;
	}
	public int getWeaponID() {
		return weaponID;
	}
	public void setWeaponID(int weaponID) {
		this.weaponID = weaponID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
    
    
}
