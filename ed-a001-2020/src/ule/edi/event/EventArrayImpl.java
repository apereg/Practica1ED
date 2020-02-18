package ule.edi.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;


public class EventArrayImpl implements Event {
	
	private String name;
	private Date eventDate;
	private int nSeats;
	
	private Double price;    // precio de entradas 
	private Byte discountAdvanceSale;   // descuento en venta anticipada (0..100)
   	
	private Seat[] seats;
		
	
	
   public EventArrayImpl(String name, Date date, int nSeats){
	 //TODO 
	 // utiliza los precios por defecto: DEFAULT_PRICE y DEFAULT_DISCOUNT definidos en Configuration.java   
	 // Debe crear el array de butacas

	
	   
   }
   
   
   public EventArrayImpl(String name, Date date, int nSeats, Double price, Byte discount){
	   //TODO 
	   // Debe crear el array de butacas
	  
   }


@Override
public String getName() {
	// TODO Auto-generated method stub
	return null;
}


@Override
public Date getDateEvent() {
	// TODO Auto-generated method stub
	return null;
}


@Override
public Double getPrice() {
	// TODO Auto-generated method stub
	return null;
}


@Override
public Byte getDiscountAdvanceSale() {
	// TODO Auto-generated method stub
	return null;
}


@Override
public int getNumberOfSoldSeats() {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public int getNumberOfNormalSaleSeats() {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public int getNumberOfAdvanceSaleSeats() {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public int getNumberOfSeats() {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public int getNumberOfAvailableSeats() {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public Seat getSeat(int pos) {
	// TODO Auto-generated method stub
	return null;
}


@Override
public Person refundSeat(int pos) {
	// TODO Auto-generated method stub
	return null;
}


@Override
public boolean sellSeat(int pos, Person p, boolean advanceSale) {
	// TODO Auto-generated method stub
	return false;
}


@Override
public int getNumberOfAttendingChildren() {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public int getNumberOfAttendingAdults() {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public int getNumberOfAttendingElderlyPeople() {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public List<Integer> getAvailableSeatsList() {
	// TODO Auto-generated method stub
	return null;
}


@Override
public List<Integer> getAdvanceSaleSeatsList() {
	// TODO Auto-generated method stub
	return null;
}


@Override
public int getMaxNumberConsecutiveSeats() {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public Double getPrice(Seat seat) {
	// TODO Auto-generated method stub
	return null;
}


@Override
public Double getCollectionEvent() {
	// TODO Auto-generated method stub
	return null;
}


@Override
public int getPosPerson(Person p) {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public boolean isAdvanceSale(Person p) {
	// TODO Auto-generated method stub
	return false;
}
   


}	