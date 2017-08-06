package ood.parkingLot;

class PaymentMachine {
	ParkingTicket issueTicket(int level, ParkingSpot spot) {
		return new ParkingTicket(level, spot);
	}
	
	double checkPrice(ParkingTicket ticket) {
		return ticket.getPrice();
	}
	
	void validateTicket(ParkingTicket ticket, int money) {
		System.out.println("Processing Ticket " + ticket.getTicketNo());
		if (ticket.getPrice() > money) {
			System.out.println("No sufficient payment!");
			return;
		}
		System.out.println("Ticket No. " + ticket.getTicketNo() + " paid $" + money + "!");
		ticket.setPaidStatus();
	}
}
