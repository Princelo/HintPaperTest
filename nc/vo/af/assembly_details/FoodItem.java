package nc.vo.af.assembly_details;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

public class FoodItem extends SuperVO {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String pkMaterial;
	private String category;
	private UFDouble quantity;
	private UFBoolean bisspecial;
	private String uniqueID;
	private String longDefoodKey;
	private String shortDefoodKey;
	private String pkRecipe;
	private String pkCycle;
	private String pkSpace;
	private UFDouble installed;
	private UFDouble remain;
	private UFBoolean bisdefood;
	private UFBoolean bishot;

	public boolean isInstalledPortion() {
		return installed.compareTo(UFDouble.ZERO_DBL) > 0;
	}
	public boolean isInstalled() {
		return remain.compareTo(UFDouble.ZERO_DBL) <= 0;
	}
	public UFDouble getInstalled() {
		return installed;
	}
	public void setInstalled(UFDouble installed) {
		this.installed = installed;
	}
	public UFDouble getRemain() {
		return remain;
	}
	public void setRemain(UFDouble remain) {
		this.remain = remain;
	}
	public String getPkMaterial() {
		return pkMaterial;
	}
	public void setPkMaterial(String pkMaterial) {
		this.pkMaterial = pkMaterial;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public UFDouble getQuantity() {
		return quantity;
	}
	public void setQuantity(UFDouble quantity) {
		this.quantity = quantity;
	}
	public UFBoolean getBisspecial() {
		return bisspecial;
	}
	public void setBisspecial(UFBoolean bisspecial) {
		this.bisspecial = bisspecial;
	}
	public String getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	public String getLongDefoodKey() {
		return longDefoodKey;
	}
	public void setLongDefoodKey(String longDefoodKey) {
		this.longDefoodKey = longDefoodKey;
	}
	public String getShortDefoodKey() {
		return shortDefoodKey;
	}
	public void setShortDefoodKey(String shortDefoodKey) {
		this.shortDefoodKey = shortDefoodKey;
	}
	public String getPkRecipe() {
		return pkRecipe;
	}
	public void setPkRecipe(String pkRecipe) {
		this.pkRecipe = pkRecipe;
	}
	public String getPkCycle() {
		return pkCycle;
	}
	public void setPkCycle(String pkCycle) {
		this.pkCycle = pkCycle;
	}
	public String getPkSpace() {
		return pkSpace;
	}
	public void setPkSpace(String pkSpace) {
		this.pkSpace = pkSpace;
	}

	public UFBoolean getBisdefood() {
		return bisdefood;
	}

	public void setBisdefood(UFBoolean bisdefood) {
		this.bisdefood = bisdefood;
	}
	public UFBoolean getBishot() {
		return bishot;
	}
	public void setBishot(UFBoolean bishot) {
		this.bishot = bishot;
	}
	public String toString() {
		return pkMaterial + category + quantity + uniqueID + longDefoodKey + installed + remain;
	}
}
