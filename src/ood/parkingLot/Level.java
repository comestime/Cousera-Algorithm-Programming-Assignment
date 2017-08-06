package ood.parkingLot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Level {
	// the reference to spots is not modifiable
	private final List<ParkingSpot> spots;
	
	Level(int numOfSpots) {
		List<ParkingSpot> list = new ArrayList<>(numOfSpots);
		int i = 0;
		for (; i < numOfSpots / 2; i++) {
			list.add(new ParkingSpot(VehicleSize.Compact));
		}
		for (; i < numOfSpots; i++) {
			list.add(new ParkingSpot(VehicleSize.Large));
		}
		// the elements inside spots are not modifiable
		spots = Collections.unmodifiableList(list);
	}
	
	boolean hasSpot(Vehicle v) {
		for (ParkingSpot spot : spots) {
			if (spot.fit(v)) return true;
		}
		return false;
	}
	
	ParkingSpot park(Vehicle v) {
		for (ParkingSpot spot : spots) {
			if (spot.fit(v)) {
				spot.park(v);
				return spot;
			}
		}
		return null;
	}
	
	boolean leave(ParkingSpot spot) {
		if (spot == null) return false;
		spot.leave();
		return true;
	}
}
