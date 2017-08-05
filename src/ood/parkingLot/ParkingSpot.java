package ood.parkingLot;

class ParkingSpot {
	private final VehicleSize size;
	private Vehicle currentVehicle;
	
	ParkingSpot(VehicleSize size) {
		this.size = size;
	}
	
	boolean fit(Vehicle v) {
		return currentVehicle == null && size.getSize() >= v.getSize().getSize();
	}
	
	/*
	 * record a vehicle is parked in the spot by updating the curentVehicle field
	 */
	void park(Vehicle v) {
		currentVehicle = v;
	}
	
	void leave() {
		currentVehicle = null;
	}
	
	Vehicle getVehicle() {
		return currentVehicle;
	}
}
