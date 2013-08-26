package testes;

import model.Carona;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CaronaTest {

	Carona c;
	
	@Before
	public void init(){
		try {
			c = new Carona("casa","trabalho","12/12/2013","12:30",3);
		} catch (Exception e) {}
	}
	
	
	@Test
	public void testCriaCaronaValida(){
		try {
			c = new Carona("casa","trabalho","12/12/2013","12:30",3);
		} catch (Exception e) {}
		
		Assert.assertEquals(c.getHour(),"12:30");
		Assert.assertEquals(c.getOrigem(),"casa");
		
	}
	
	@Test
	public void testHoraValida(){
		Assert.assertTrue(c.getHour().equals("12:30"));
		c.setHour("12:36");
		Assert.assertTrue(c.getHour().equals("12:36"));
		try{
			c.setHour("33:33");
			Assert.fail();
		}catch(Exception e){}
	}
	
	@Test
	public void testSetData(){
		Assert.assertEquals(c.getDate(),"12/12/2013");
		c.setDate("24/12/2015");
		Assert.assertEquals(c.getDate(),"24/12/2015");
		
	}

}
