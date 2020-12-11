package app.beans;

import java.util.HashMap;
import java.util.Map;

public enum DepartmentType {
	
	marketing(1), accountancy(2), maintenance(3), highManagement(4), programming(5), userInterface(6), other(7);

	private int value;
	private static Map<Integer, DepartmentType> map = new HashMap<>();

	private DepartmentType(int value) {
		//allows Department Types to have values
		this.value = value;
	}

	static {
		//puts Department Types & their values inside a hashMap, value=key
		for (DepartmentType categoryType : DepartmentType.values()) {
			map.put(categoryType.value, categoryType);
		}
	}

	public static DepartmentType valueOf(int value) {
		//Receives value/key returns its DepartmentType
		return (DepartmentType) map.get(value);
	}

	public int getValue() {
		return value;
	}


}
