package model;

import java.util.HashMap;
import java.util.List;

public class Floor {

	int floorNo;
	List<HashMap<Door,ElevatorCar>> list;
	
	public Floor(int floorNo, List<HashMap<Door,ElevatorCar>> list) {
		this.floorNo=floorNo;
		this.list=list;
	}
}
