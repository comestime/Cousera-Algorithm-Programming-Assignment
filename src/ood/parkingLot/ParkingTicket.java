package ood.parkingLot;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class ParkingTicket {
	// ticket number starts from 1
	private static int ticketNoCounter = 1;
	// ticket number with 0 is invalid
	private final int ticketNo;
	private final LocalDateTime issueTime;
	private static final double rate = 0.5;
	private final int level;
	private final ParkingSpot spot;
	private boolean paid;
	
	public ParkingTicket(int levelNum, ParkingSpot spot) {
		// ticket number
		ticketNo = ticketNoCounter;
		ticketNoCounter++;
		// issue time, for fee calculation
		issueTime = LocalDateTime.now();
		// record the parking spot
		level = levelNum;
		this.spot = spot;
	}
	
	public int getTicketNo() {
		return ticketNo;
	}
	
	public LocalDateTime getIssueTime() {
		return issueTime;
	}
	
	public int getLevel() {
		return level;
	}
	
	public ParkingSpot getSpot() {
		return spot;
	}
	
	public boolean getPaidStatus() {
		return paid;
	}
	
	// better practice is to have a "payment machine" to set this bit
	void setPaidStatus() {
		paid = true;
	}
	
	public double getPrice() {
		LocalDateTime payTime = LocalDateTime.now();
		
		LocalDateTime tempDateTime = LocalDateTime.from(issueTime);
		
		long years = tempDateTime.until( payTime, ChronoUnit.YEARS);
		tempDateTime = tempDateTime.plusYears( years );

		long months = tempDateTime.until( payTime, ChronoUnit.MONTHS);
		tempDateTime = tempDateTime.plusMonths( months );

		long days = tempDateTime.until( payTime, ChronoUnit.DAYS);
		tempDateTime = tempDateTime.plusDays( days );


		long hours = tempDateTime.until( payTime, ChronoUnit.HOURS);
		tempDateTime = tempDateTime.plusHours( hours );

		long minutes = tempDateTime.until( payTime, ChronoUnit.MINUTES);
		tempDateTime = tempDateTime.plusMinutes( minutes );

		long seconds = tempDateTime.until( payTime, ChronoUnit.SECONDS);
		
		System.out.println( years + " years " + 
		        months + " months " + 
		        days + " days " +
		        hours + " hours " +
		        minutes + " minutes " +
		        seconds + " seconds.");
		
		return (minutes * 60 + seconds) * rate;
	}
	
	public static void main(String[] args) {
		ParkingTicket ticket = new ParkingTicket(2, null);
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(ticket.getPrice());		
	}
}
