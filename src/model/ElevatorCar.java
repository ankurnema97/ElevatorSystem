package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ElevatorCar {

	private int id;
	private int currFloor;
	Direction direction;
	Status status;
	
	List<Map<Integer,Direction>> pendingRequest = new ArrayList<>();
	
	public ElevatorCar(int id, int currFloor, Direction direction, Status status) {
		this.currFloor=currFloor;
		this.id=id;
		this.direction=direction;
		this.status=status;
	}
	
	public void schedule() {
		//sorting list of request 
		//if it is multi-threaded then it first sorts
		Map<Integer, Direction> mapping = new HashMap<>();
		for (Map<Integer, Direction> map : pendingRequest) {
            List<Map.Entry<Integer, Direction>> entries = new ArrayList<>(map.entrySet());
            entries.sort(Map.Entry.comparingByKey());
            map.clear();
            for (Map.Entry<Integer, Direction> entry : entries) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
		
		Scanner sc = new Scanner(System.in);
		
		while(!pendingRequest.isEmpty()) {
			if(this.direction==Direction.NEUTRAL) {
				this.direction=Direction.UP;
			}
			if(this.direction==Direction.UP) {      //if lift is going up
				ListIterator it = pendingRequest.listIterator();
				while(it.hasNext()) {
					 Map.Entry pair=(Map.Entry) it.next();
					 int which_floor = (int) pair.getKey();
					 Direction which_direction = (Direction) pair.getValue();
					 if(which_floor>=this.currFloor && which_direction==this.direction) {
						 it.remove();
						 System.out.println("Enter Desired FloorNo.");
						 int desired_floor=sc.nextInt();
						 mapping.put(desired_floor,Direction.NEUTRAL);
						 pendingRequest.add(mapping);
						 mapping.clear(); 
					 }
					 else if(which_direction==Direction.NEUTRAL)
						 it.remove();
			    }
				this.direction=Direction.DOWN;
			}
			
			else {
				ListIterator list_iterator = pendingRequest.listIterator();
				while(list_iterator.hasPrevious()) {
					Map.Entry pair = (Entry) list_iterator.previous();
					int which_floor = (int) pair.getKey();
					Direction which_direction = (Direction) pair.getValue();
					if(which_floor>=this.currFloor && which_direction==this.direction) {
						 list_iterator.remove();
						 System.out.println("Enter Desired FloorNo.");
						 int desired_floor=sc.nextInt();
						 mapping.put(desired_floor,Direction.NEUTRAL);
						 pendingRequest.add(mapping);
						 mapping.clear(); 
					 }
					 else if(which_direction==Direction.NEUTRAL)
						 list_iterator.remove();
			    }
				this.direction=Direction.UP;
				}
			}
			
			
		}
	}
