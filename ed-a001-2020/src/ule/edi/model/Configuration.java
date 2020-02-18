package ule.edi.model;

public class Configuration {

	/**
	 * Considera niños en rango de edad [0, CHILDREN_EXMAX_AGE)
	 * 
	 * (menores de ...) 
	 */
	public static final Integer CHILDREN_EXMAX_AGE = 14;
	
	
	/**
	 * Considera ancianos en rango de edad [ELDERLY_PERSON_INMIN_AGE, Integer.MAX_VALUE)
	 * 
	 * (igual o mayor que ...) 
	 */
	public static final Integer ELDERLY_PERSON_INMIN_AGE = 65;
	
	public static final Double DEFAULT_PRICE=100.0;
	
	public static final Byte DEFAULT_DISCOUNT=25;
	
	public enum Type {
		ADVANCE_SALE,
		NORMAL
	}

	
}
