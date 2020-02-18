package ule.edi.model;

import ule.edi.model.Configuration.Type;

public class Seat {
	

	private Person holder;
	
	private Event event;
	
	private Type type;
	
	public Seat(Event event, int position, Type type, Person holder) {
		
		this.holder = holder;
		this.event = event;
		this.type =type;
	}
	
	
	public Person getHolder() {
		return holder;
	}
	
	public Event getEvent() {
		return event;
	}

	@Override
	public String toString() {
		return "{'Event':'" + event.getName() + "', 'Holder':" + holder + ", 'Price':" + event.getPrice(this) + "}";
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}
