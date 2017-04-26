package nc.vo.af.assembly_details;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFDouble;

public class TrolleyItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String srcAssemblyB;
	private String space;
	private String planServer;
	private String planSeat;
	private String kitchen;
	private String deviceNbr;
	private Set<String> categories;
	private Map<String, UFDouble> cateCapMap;
	private Map<String, UFDouble> installedMap;
	private String deviceID;
	private int startIndex;
	private int endIndex;
	private int vCellNumber;
	private int tCellNumber;
	private boolean isWide;
	private Set<String> longDefoodKey;
	private Set<String> shortDefoodKey;
	private boolean isSpecialPriority;
	private String hPart;
	private String vPart;
	private Set<String> installedIDs;
	private boolean isFree;
	private String deviceType;
	private String note;
	private boolean isSpecicalInstalled = false;
	private Map<String, UFDouble> origCateCapMap;
	private boolean isVirtual;
	private boolean isSpecial;
	private boolean isForHot;
	private String recipe;
	
	public boolean isFull() {
		if (!isFree) {
			for (String category : categories) {
				if (installedMap.get(category).compareTo(UFDouble.ZERO_DBL) <= 0) {
					return false;
				}
			}
			return true;
		} else {
			return !installedMap.isEmpty();
		}
	}
	
	public UFDouble getRemainCapacity(String category) {
		UFDouble installedQuantity = this.installedMap.get(category);
		installedQuantity = null == installedQuantity ? UFDouble.ZERO_DBL : installedQuantity;
		UFDouble capacity = this.cateCapMap.get(category);
		if (null == capacity) {
			return UFDouble.ZERO_DBL;
		}
		if (capacity.compareTo(installedQuantity) > 0) {
			return capacity.sub(installedQuantity);
		}
		if (capacity.compareTo(UFDouble.ZERO_DBL) == 0) {
			return new UFDouble(99999); //可以放儘量多
		}
		return UFDouble.ZERO_DBL;
	}

	public UFDouble getCapacity(String category) {
		return this.cateCapMap.get(category);
	}
	public boolean install(String category, FoodItem foodItem) {
		UFDouble quantity = foodItem.getQuantity();
		UFDouble origQuantity = this.installedMap.get(category);
		origQuantity = null == origQuantity ? UFDouble.ZERO_DBL : origQuantity;
		this.installedMap.put(category, origQuantity.add(quantity));
		this.addInstalledID(foodItem.getUniqueID());
		this.addDefood(foodItem.getLongDefoodKey());
		foodItem.setRemain(UFDouble.ZERO_DBL);
		foodItem.setInstalled(quantity);
		return true;
	}

	public boolean install(String category, FoodItem foodItem, UFDouble quantity) {
		UFDouble origQuantity = this.installedMap.get(category);
		origQuantity = null == origQuantity ? UFDouble.ZERO_DBL : origQuantity;
		this.installedMap.put(category, origQuantity.add(quantity));
		this.addInstalledID(foodItem.getUniqueID());
		this.addDefood(foodItem.getLongDefoodKey());
		foodItem.setRemain(foodItem.getRemain().sub(quantity));
		foodItem.setInstalled(foodItem.getInstalled().add(quantity));
		return true;
	}
	public void addDefood(String longDefoodKey) {
		this.longDefoodKey.add(longDefoodKey);
		this.shortDefoodKey.add(longDefoodKey.substring(20, 40));
	}
	public Set<String> getInstalledIDs() {
		return installedIDs;
	}
	public void setInstalledIDs(Set<String> installedIDs) {
		this.installedIDs = installedIDs;
	}
	public void addInstalledID(String installedID) {
		this.installedIDs.add(installedID);
	}
	public String gethPart() {
		return hPart;
	}
	public void sethPart(String hPart) {
		this.hPart = hPart;
	}
	public String getvPart() {
		return vPart;
	}
	public void setvPart(String vPart) {
		this.vPart = vPart;
	}
	public String getSrcAssemblyB() {
		return srcAssemblyB;
	}
	public void setSrcAssemblyB(String srcAssemblyB) {
		this.srcAssemblyB = srcAssemblyB;
	}
	public String getSpace() {
		return space;
	}
	public void setSpace(String space) {
		this.space = space;
	}
	public String getPlanServer() {
		return planServer;
	}
	public void setPlanServer(String planServer) {
		this.planServer = planServer;
	}
	public String getPlanSeat() {
		return planSeat;
	}
	public void setPlanSeat(String planSeat) {
		this.planSeat = planSeat;
	}
	public Set<String> getCategories() {
		return categories;
	}
	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}
	public Map<String, UFDouble> getCateCapMap() {
		return cateCapMap;
	}
	public void setCateCapMap(Map<String, UFDouble> cateCapMap) {
		this.cateCapMap = cateCapMap;
	}
	public Map<String, UFDouble> getOrigCateCapMap() {
		return origCateCapMap;
	}

	public void setOrigCateCapMap(Map<String, UFDouble> origCateCapMap) {
		this.origCateCapMap = origCateCapMap;
	}

	public Map<String, UFDouble> getInstalledMap() {
		return installedMap;
	}
	public void setInstalledMap(Map<String, UFDouble> installedMap) {
		this.installedMap = installedMap;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public int getvCellNumber() {
		return vCellNumber;
	}
	public void setvCellNumber(int vCellNumber) {
		this.vCellNumber = vCellNumber;
	}
	public int gettCellNumber() {
		return tCellNumber;
	}
	public void settCellNumber(int tCellNumber) {
		this.tCellNumber = tCellNumber;
	}
	public boolean isWide() {
		return isWide;
	}
	public void setWide(boolean isWide) {
		this.isWide = isWide;
	}
	public Set<String> getLongDefoodKey() {
		return longDefoodKey;
	}
	public void setLongDefoodKey(Set<String> longDefoodKey) {
		this.longDefoodKey = longDefoodKey;
	}
	public Set<String> getShortDefoodKey() {
		return shortDefoodKey;
	}
	public void setShortDefoodKey(Set<String> shortDefoodKey) {
		this.shortDefoodKey = shortDefoodKey;
	}
	public boolean isSpecialPriority() {
		return isSpecialPriority;
	}
	public void setSpecialPriority(boolean isSpecialPriority) {
		this.isSpecialPriority = isSpecialPriority;
	}
	public boolean isFree() {
		return isFree;
	}
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getKitchen() {
		return kitchen;
	}

	public void setKitchen(String kitchen) {
		this.kitchen = kitchen;
	}

	public String getDeviceNbr() {
		return deviceNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isSpecicalInstalled() {
		return isSpecicalInstalled;
	}

	public void setSpecicalInstalled(boolean isSpecicalInstalled) {
		this.isSpecicalInstalled = isSpecicalInstalled;
	}

	public UFDouble getOrigCapacity(String category) {
		return this.origCateCapMap.get(category);
	}

	public String toString() {
		return space.substring(0, 1) + isFree + isVirtual + deviceID.substring(17, 20);
	}

	public boolean installSpecial(FoodItem foodItem) {
	    this.setSpecicalInstalled(true);
		this.installedMap.put(foodItem.getUniqueID(), UFDouble.ONE_DBL);
		this.addInstalledID(foodItem.getUniqueID());
//		this.addDefood(foodItem.getLongDefoodKey());
		foodItem.setRemain(UFDouble.ZERO_DBL);
		foodItem.setInstalled(UFDouble.ONE_DBL);
		this.addDefood(foodItem.getLongDefoodKey());
		this.cateCapMap = new HashMap<String, UFDouble>();
		return true;
	}

	public boolean isVirtual() {
		return isVirtual;
	}

	public void setVirtual(boolean isVirtual) {
		this.isVirtual = isVirtual;
	}

	public boolean isSpecial() {
		return isSpecial;
	}

	public void setSpecial(boolean special) {
		isSpecial = special;
	}

	public boolean isForHot() {
		return isForHot;
	}

	public void setForHot(boolean forHot) {
		isForHot = forHot;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
}
