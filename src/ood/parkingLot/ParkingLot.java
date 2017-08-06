package ood.parkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParkingLot {
	private final Level[] levels;
	public PaymentMachine machine;
	
	public ParkingLot(int numLevels, int numSpotPerLevel) {
		machine = new PaymentMachine();
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
	
	public ParkingTicket park(Vehicle v) {
		for (int i = 0; i < levels.length; i++) {
			ParkingSpot spot = levels[i].park(v);
			if (spot == null) {
				// current spot is unavailable
				continue;
			} else {
				// current spot is available, and issue new ticket
				return machine.issueTicket(i, spot);
			}
		}
		return null;		
	}
	
	public double checkPrice(ParkingTicket ticket) {
		System.out.println(machine.checkPrice(ticket));
		return machine.checkPrice(ticket);
	}
	
	public void validateTicket(ParkingTicket ticket, int money) {
		machine.validateTicket(ticket, money);
	}
	
	public boolean leave(ParkingTicket ticket) {
		// invalid ticket
		if (ticket == null) return false;
		// if the ticket is not paid, not allow to leave
		if (!ticket.getPaidStatus()) return false;
		return levels[ticket.getLevel()].leave(ticket.getSpot()); 
	}
	
	public static void main(String[] args) throws Exception {
		ParkingLot lot = new ParkingLot(2, 10);
		List<Vehicle> list = new ArrayList<> ();
		List<ParkingTicket> tickets = new ArrayList<>();
		
		// step 1: over load the parking lot
		// remember to enable assertion in Eclipse settings
		System.out.println("Adding vehicles to parking lot");
		for (int i = 0; i < 30; i++) {
			final Vehicle v = i % 2 == 0 ? new Car() : new Truck();
			list.add(v);
			boolean hasSpot = lot.hasSpot(v);
			ParkingTicket ticket = lot.park(v);
			if (i < 20) {
				assert hasSpot;
				assert ticket != null;
				tickets.add(ticket);
				System.out.println(i + " " + hasSpot);
				System.out.println("Ticket No. " + ticket.getTicketNo());
			} else {
				assert !hasSpot;
				assert ticket == null;
			}
			TimeUnit.SECONDS.sleep(i % 5);
		}
		TimeUnit.SECONDS.sleep(10);
		
		// step 2: clear the parking lot
		System.out.println("Clearing parking lot");
		assert list.size() == 30;
		int i = 0;
		for (ParkingTicket t : tickets) {
			System.out.println(lot.checkPrice(t));
			lot.validateTicket(t, 100);
			assert i >= 20 || lot.leave(t);
			i++;
		}
	}
}
