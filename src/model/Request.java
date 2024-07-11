package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import main.Main;

public class Request {
	int floorNo;
	int doorNo;
	ExternalButton externalButton;
	
	public Request(int floorNo, int doorNo, ExternalButton externalButton) {
		this.doorNo=doorNo;
		this.floorNo=floorNo;
		this.externalButton=externalButton;
	}
	
	public void fulfillRequest() {
		Map<Integer,Direction> mapping;
		for(Floor list: Main.list_of_floors) {
			if(list.floorNo==this.floorNo) {
				for(HashMap<Door, ElevatorCar> map: list.list) {
					Iterator it=map.entrySet().iterator();
					while(it.hasNext()) {
						Map.Entry pair = (Map.Entry) it.next();
						Door temp_door = (Door) pair.getKey();
						ElevatorCar temp_car = (ElevatorCar) pair.getValue();
						if(temp_door.doorNo==this.doorNo) {
							mapping = new HashMap<>();
							mapping.put(this.floorNo,this.externalButton.direction);
							temp_car.pendingRequest.add(mapping);
							temp_car.schedule();
							return;
						}
					}
				}
			}
		}
	}
}
