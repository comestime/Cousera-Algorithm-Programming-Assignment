package ood.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
	private final Level[] levels;
	
	public ParkingLot(int numLevels, int numSpotPerLevel) {
		levels = new Level[numLevels];
		for (int i = 0; i < numLevels; i++) {
			levels[i] = new Level(numSpotPerLevel);
		}
	}
	
	public boolean hasSpot(Vehicle v) {
		for (int i = 0; i < levels.length; i++) {
			if (levels[i].hasSpot(v)) return true;
		}
		return false;
	}
	
	public boolean park(Vehicle v) {
		for (int i = 0; i < levels.length; i++) {
			if (levels[i].park(v)) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean leave(Vehicle v) {
		for (int i = 0; i < levels.length; i++) {
			if (levels[i].leave(v)) return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		ParkingLot lot = new ParkingLot(2, 10);
		List<Vehicle> list = new ArrayList<> ();
		
		// step 1: over load the parking lot
		// remember to enable assertion in Eclipse settings
		System.out.println("Adding vehicles to parking lot");
		for (int i = 0; i < 30; i++) {
			final Vehicle v = i % 2 == 0 ? new Car() : new Truck();
			list.add(v);
			boolean hasSpot = lot.hasSpot(v);
			System.out.println(i + " " + hasSpot);
			if (i < 20) {
				assert hasSpot;
				assert lot.park(v);
			} else {
				assert !hasSpot;
				assert !lot.park(v);
			}
		}
		
		// step 2: clear the parking lot
		System.out.println("Clearing parking lot");
		assert list.size() == 30;
		int i = 0;
		for (Vehicle v : list) {
			assert i >= 20 || lot.leave(v);
			i++;
		}
	}
}
