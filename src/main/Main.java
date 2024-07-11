package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Direction;
import model.Door;
import model.ElevatorCar;
import model.ExternalButton;
import model.Floor;
import model.Request;
import model.Status;

public class Main {
	
	public static List<Floor> list_of_floors = new ArrayList<>();
	
	public static void main(String args[]) {
		
		//input of 10 floors each having 3 doors and 3 elevators
		HashMap<Door,ElevatorCar> map;
		List<HashMap<Door,ElevatorCar>> list_of_doors = new ArrayList<>();
		List<ElevatorCar> elevatorCars = new ArrayList<>();
		for(int i=0;i<3;i++) {
			elevatorCars.add(new ElevatorCar(i,1,Direction.NEUTRAL,Status.IDLE));
		}
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<3;j++) {
				map=new HashMap<>();
				map.put(new Door(j,new ExternalButton(Direction.NEUTRAL)), elevatorCars.get(j));
				list_of_doors.add(map); 
			}
			list_of_floors.add(new Floor(i,list_of_doors));
		}
		
		Request request = new Request(1,1,new ExternalButton(Direction.UP));
		request.fulfillRequest();
		Request request2 = new Request(2,2,new ExternalButton(Direction.UP));
		request2.fulfillRequest();
	}
}
