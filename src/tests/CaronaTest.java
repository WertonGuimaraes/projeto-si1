package tests;

import static org.junit.Assert.*;
import model.Carona;

import org.junit.Before;
import org.junit.Test;

public class CaronaTest {

	Carona c;

	@Before
	public void init() {
		try {
			c = new Carona("casa", "trabalho", "12/12/2013", "12:30", 3);
		} catch (Exception e) {
		}
	}

	@Test
	public void testCriaCaronaValida() {
		try {
			c = new Carona("casa", "trabalho", "12/12/2013", "12:30", 3);
		} catch (Exception e) {
		}

		assertEquals(c.getHour(), "12:30");
		assertEquals(c.getOrigem(), "casa");

	}

	@Test
	public void testHoraValida() {
		assertTrue(c.getHour().equals("12:30"));
		c.setHour("12:36");
		assertTrue(c.getHour().equals("12:36"));
		try {
			c.setHour("33:33");
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testSetData() {
		assertEquals(c.getDate(), "12/12/2013");
		c.setDate("24/12/2015");
		assertEquals(c.getDate(), "24/12/2015");

	}

}
