package ule.edi.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;

public class EventArrayImpl implements Event {

	private String name;
	private Date eventDate;
	private int nSeats;

	private Double price; // precio de entradas
	private Byte discountAdvanceSale; // descuento en venta anticipada (0..100)

	private Seat[] seats;

	public EventArrayImpl(String name, Date date, int nSeats) {
		// TODO
		// utiliza los precios por defecto: DEFAULT_PRICE y DEFAULT_DISCOUNT definidos
		// en Configuration.java
		// Debe crear el array de butacas
		this.name = name;
		this.eventDate = date;
		this.price = Configuration.DEFAULT_PRICE;
		this.discountAdvanceSale = Configuration.DEFAULT_DISCOUNT;
		this.nSeats = nSeats;
		this.seats = new Seat[nSeats];
	}

	public EventArrayImpl(String name, Date date, int nSeats, Double price, Byte discount) {
		// TODO
		// Debe crear el array de butacas
		this.name = name;
		this.eventDate = date;
		this.price = price;
		this.discountAdvanceSale = discount;
		this.nSeats = nSeats;
		this.seats = new Seat[nSeats];
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public Date getDateEvent() {
		// TODO Auto-generated method stub
		return this.eventDate;
	}

	@Override
	public Double getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}

	@Override
	public Byte getDiscountAdvanceSale() {
		// TODO Auto-generated method stub
		return this.discountAdvanceSale;
	}

	@Override
	public int getNumberOfSoldSeats() {
		// TODO Auto-generated method stub
		return this.seats.length;
	}

	@Override
	public int getNumberOfNormalSaleSeats() {
		int count = 0;
		for (Seat seat : this.seats) {
			if (seat != null)
				if (seat.getType() == Configuration.Type.NORMAL)
					count++;
		}
		return count;
	}

	@Override
	public int getNumberOfAdvanceSaleSeats() {
		int count = 0;
		for (Seat seat : this.seats) {
			if (seat != null)
				if (seat.getType() == Configuration.Type.ADVANCE_SALE)
					count++;
		}
		return count;
	}

	@Override
	public int getNumberOfSeats() {
		return this.nSeats;
	}

	@Override
	public int getNumberOfAvailableSeats() {
		int count = 0;
		for (Seat seat : this.seats) {
			if (seat == null)
				count++;
		}
		return count;
	}

	@Override
	public Seat getSeat(int pos) {
		pos--;
		if (this.isAValidSeat(pos))
			if (this.seats[pos] != null)
				return this.seats[pos];
		return null;

	}

	@Override
	public Person refundSeat(int pos) {
		pos--;
		if (this.isAValidSeat(pos)) {
			if (this.seats[pos] != null) {
				Person person = this.seats[pos].getHolder();
				this.seats[pos] = null;
				return person;
			}
		}
		return null;

	}

	@Override
	public boolean sellSeat(int pos, Person p, boolean advanceSale) {
		pos--;
		if (this.isAValidSeat(pos)) {
			if (this.seats[pos] == null) {
				if (advanceSale)
					this.seats[pos] = new Seat(this, pos, Configuration.Type.ADVANCE_SALE, p);
				else 
					this.seats[pos] = new Seat(this, pos, Configuration.Type.NORMAL, p);
				return true;
			}
		}
		return false;
	}

	@Override
	public int getNumberOfAttendingChildren() {
		int count = 0;
		for (Seat seat : this.seats) {
			if (seat != null)
				if (seat.getHolder().getAge() < Configuration.CHILDREN_EXMAX_AGE)
					count++;

		}
		return count;
	}

	@Override
	public int getNumberOfAttendingAdults() {
		int count = 0;
		for (Seat seat : this.seats) {
			if (seat != null)
				if (seat.getHolder().getAge() >= Configuration.CHILDREN_EXMAX_AGE
						&& seat.getHolder().getAge() < Configuration.ELDERLY_PERSON_INMIN_AGE)
					count++;

		}
		return count;
	}

	@Override
	public int getNumberOfAttendingElderlyPeople() {
		int count = 0;
		for (Seat seat : this.seats) {
			if (seat != null)
				if (seat.getHolder().getAge() >= Configuration.ELDERLY_PERSON_INMIN_AGE)
					count++;
		}
		return count;
	}

	@Override
	public List<Integer> getAvailableSeatsList() {
		List<Integer> avaliableSeats = new LinkedList<Integer>();

		for (int i = 0; i < this.seats.length; i++)
			if (this.seats[i] == null)
				avaliableSeats.add(i + 1);
		return avaliableSeats;
	}

	@Override
	public List<Integer> getAdvanceSaleSeatsList() {
		List<Integer> avaliableSeats = new LinkedList<Integer>();

		for (int i = 0; i < this.seats.length; i++) {
			if (this.seats[i] != null)
				if(this.seats[i].getType() == Configuration.Type.ADVANCE_SALE)
					avaliableSeats.add(i + 1);
		}
		return avaliableSeats;
	}

	@Override
	public int getMaxNumberConsecutiveSeats() {
		int maxConsecutive = 0, aux = 0;

		for (int i = 0; i < this.seats.length; i++) {
			if (this.seats[i] == null) {
				if(aux < maxConsecutive)
					maxConsecutive = aux;
				aux = 0;
			} else {
				aux++;
			}
		
		}
		return maxConsecutive;
	}

	@Override
	public Double getPrice(Seat seat) {
		if(seat.getEvent() == this) {
			if(seat.getType() == Configuration.Type.ADVANCE_SALE) {
				return (this.price - (this.price * (this.discountAdvanceSale / 100.0)));		
			}
			
			return this.price;	
		}
		return 0.0;
	}

	@Override
	public Double getCollectionEvent() {
		Double totalPrice = 0.0;
		for (Seat seat: this.seats) 
			if(seat != null) 
				totalPrice += this.getPrice(seat);				
			
		
		return totalPrice;
	}

	@Override
	public int getPosPerson(Person p) {
		for (int i = 0; i < this.seats.length; i++) {
			if(this.seats[i] != null)
				if(this.seats[i].getHolder().equals(p))
					return i+1;
		}
		return -1;
	}

	@Override
	public boolean isAdvanceSale(Person p) {
		int pos = this.getPosPerson(p);
		if(pos != -1)
			return this.getSeat(pos--).getType() == Configuration.Type.ADVANCE_SALE;
		return false;
	}

	private boolean isAValidSeat(int input) {
		return input >= 0 && input < this.seats.length;
	}

}