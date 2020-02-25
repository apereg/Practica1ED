package ule.edi.event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.junit.*;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;

public class EventArrayImplTests {

	private DateFormat dformat = null;
	private EventArrayImpl e;
	private Person person;

	private Date parseLocalDate(String spec) throws ParseException {
		return dformat.parse(spec);
	}

	public EventArrayImplTests() {

		dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	@Before
	public void testBefore() throws Exception {
		e = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 10);
		person = new Person("1010", "AA", 10);

	}

	@Test
	public void testEventoVacio() throws Exception {

		Assert.assertTrue(e.getNumberOfAvailableSeats() == 10);
		Assert.assertEquals(e.getNumberOfAvailableSeats(), 10);
		Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
	}

	@Test
	public void testSellSeat1Adult() throws Exception {

		Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
		Assert.assertTrue(e.sellSeat(1, new Person("10203040A", "Alice", 34), false)); // venta normal
		Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);
		Assert.assertEquals(e.getNumberOfNormalSaleSeats(), 1);

	}

	@Test
	public void testgetCollection() throws Exception {
		Assert.assertEquals(e.sellSeat(1, new Person("1010", "AA", 10), true), true);
		Assert.assertTrue(e.getCollectionEvent() == 75);
	}

	// TODO EL RESTO DE MÉTODOS DE TESTS NECESARIOS PARA LA COMPLETA COMPROBACIÓN
	// DEL BUEN FUNCIONAMIENTO DE TODO EL CÓDIGO IMPLEMENTADO
	@Test
	public void testgetPriceWithOutDiscount() throws Exception {
		Assert.assertTrue(e.sellSeat(1, person, false));
		Assert.assertTrue(e.getCollectionEvent() == 100);
	}

	@Test
	public void testGetPriceWrong() throws Exception {
		Seat seat = new Seat(new EventArrayImpl("The Fabulous Eight", parseLocalDate("24/02/2018 17:00:00"), 4, 50.0, (byte) 8), 3, Type.NORMAL, person);
		Assert.assertEquals(e.getPrice(seat), 0.0, 0.05);
	}

	@Test
	public void testConstructorWithPrice() throws Exception {
		Event ep = new EventArrayImpl("The Fabulous Eight", parseLocalDate("24/02/2018 17:00:00"), 4, 50.0, (byte) 8);
		Assert.assertEquals(ep.getName(), "The Fabulous Eight");
		Assert.assertEquals(ep.getNumberOfSeats(), 4);
		Assert.assertEquals((byte) ep.getDiscountAdvanceSale(), (byte) 8);
		Assert.assertEquals((Double) ep.getPrice(), 50.0, 0.01);
		Assert.assertEquals(ep.getDateEvent(), parseLocalDate("24/02/2018 17:00:00"));
	}

	@Test
	public void testGetSoldSeats() {
		e.sellSeat(1, person, true);
		e.sellSeat(4, new Person("0101", "BB", 12), true);
		Assert.assertEquals(e.getNumberOfSoldSeats(), 2);
	}

	@Test
	public void testGetNumberOfSoldSeatsWithDiscount() {
		e.sellSeat(1, person, true);
		e.sellSeat(3, new Person("1001", "BB", 21), false);
		e.sellSeat(4, new Person("0101", "CC", 12), true);
		Assert.assertEquals(e.getNumberOfAdvanceSaleSeats(), 2);
	}

	@Test
	public void testGetNumberOfSoldSeatsWithoutDiscount() {
		e.sellSeat(1, person, true);
		e.sellSeat(3, new Person("1001", "BB", 21), false);
		Assert.assertEquals(e.getNumberOfNormalSaleSeats(), 1);
	}

	@Test
	public void testNumberOfAvaliableSeats() {
		e.sellSeat(1, person, true);
		Assert.assertEquals(e.getNumberOfAvailableSeats(), e.getNumberOfSeats() - 1);
	}

	@Test
	public void testGetSeat() {
		e.sellSeat(1, person, true);
		Assert.assertEquals(e.getSeat(1).getType(), Type.ADVANCE_SALE);
		Assert.assertEquals(e.getSeat(1).getEvent(), e);
		Assert.assertEquals(e.getSeat(1).getHolder(), person);
	}

	@Test
	public void testGetSeatWrong() {
		Assert.assertNull(e.getSeat(1));
	}

	@Test
	public void testGetSeatOutOfBounds() {
		Assert.assertNull(e.getSeat(1000));
	}

	@Test
	public void testRefundSeat() {
		e.sellSeat(2, person, true);
		Assert.assertEquals(e.refundSeat(2), person);
	}

	@Test
	public void testRefundSeatNull() {
		e.sellSeat(1, person, true);
		Assert.assertNull(e.refundSeat(2));
	}

	@Test
	public void testRefundSeatOutOfBounds() {
		Assert.assertNull(e.refundSeat(1500));
	}

	@Test
	public void testSellSealWrong() {
		e.sellSeat(1, person, true);
		Assert.assertFalse(e.sellSeat(1, person, false));
	}

	@Test
	public void testSellSealOutOfBounds() {
		Assert.assertFalse(e.sellSeat(1500, person, false));
	}

	@Test
	public void testNumberOfAttendingChildren() {
		e.sellSeat(1, person, true);
		e.sellSeat(2, new Person("1010", "0101", 65), true);
		Assert.assertEquals(e.getNumberOfAttendingChildren(), 1);
	}

	@Test
	public void testNumberOfAttendingAdults() {
		e.sellSeat(1, person, true);
		e.sellSeat(2, new Person("1010", "0101", 24), true);
		e.sellSeat(3, new Person("1010", "0101", 85), true);
		e.sellSeat(4, new Person("1010", "0101", 44), true);
		Assert.assertEquals(e.getNumberOfAttendingAdults(), 2);
	}

	@Test
	public void testNumberOfAttendingElderlyPeople() {
		e.sellSeat(1, person, true);
		e.sellSeat(2, new Person("1010", "0101", 24), true);
		e.sellSeat(3, new Person("1010", "0101", 85), true);
		e.sellSeat(4, new Person("1010", "0101", 44), true);
		Assert.assertEquals(e.getNumberOfAttendingElderlyPeople(), 1);
	}

	@Test
	public void testAvaliableSeatsList() {
		LinkedList<Integer> listaTest = new LinkedList<>();
		for (int i = 2; i <= e.getNumberOfSeats(); i++)
			listaTest.add(i);
		e.sellSeat(1, person, true);
		Assert.assertArrayEquals(e.getAvailableSeatsList().toArray(), listaTest.toArray());
	}

	@Test
	public void testAdvancedSaleSeatsList() {
		e.sellSeat(1, person, false);
		e.sellSeat(2, new Person("1010", "0101", 24), true);
		Object[] test = { 2 };
		Assert.assertArrayEquals(e.getAdvanceSaleSeatsList().toArray(), test);
	}

	@Test
	public void testMaxNumberConsecutiveSeats() {
		e.sellSeat(1, person, false);
		e.sellSeat(3, new Person("1010", "0101", 24), true);
		e.sellSeat(4, new Person("0101", "0110", 10), true);
		Assert.assertEquals(e.getMaxNumberConsecutiveSeats(), 2);
	}
	
	@Test
	public void testGetPerson() {
		e.sellSeat(1, new Person("1010", "0101", 24), true);
		e.sellSeat(3, person, true);
		Assert.assertEquals(e.getPosPerson(person), 3);
	}
	
	@Test
	public void testGetPersonWrong() {
		Assert.assertEquals(e.getPosPerson(person), -1);
	}
	
	@Test
	public void testIsAdvancedSale() {
		e.sellSeat(1, person, true);
		Assert.assertTrue(e.isAdvanceSale(person));
	}
	
	@Test
	public void testIsAdvancedSaleFalse() {
		e.sellSeat(1, person, false);
		Assert.assertFalse(e.isAdvanceSale(person));
	}
	
	@Test
	public void testIsAdvancedSaleWrongPerson() {
		Assert.assertFalse(e.isAdvanceSale(person));
	}
	
	@Test
	public void testIsAValidSeat() {
		Assert.assertNull(e.refundSeat(-1));
		Assert.assertNull(e.refundSeat(1500));
	}
}
