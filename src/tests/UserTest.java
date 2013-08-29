package tests;

import static org.junit.Assert.*; 
import model.Usuario;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	Usuario user1,user2,user3;

	@Before
	public void init(){
		user1 = new Usuario("tiaraju","xpto","Tiaraju","tiarajums@gmail.com","rua xxx numero 222");
		user2 = new Usuario("crafards","xpto","Carlos Rafael","crafs@gmail.com","rua yyy numero 333");
		user3 = new Usuario("jessika","xpto","Jessika","reh.nally@gmail.com","rua www numero 123");
	}

	@Test
	public void testLoginNull() {
		try{
			user3 = new Usuario(null,"Carlos Rafael","xpto","crafs@gmail.com","rua yyy numero 333");
			fail();
		}catch(Exception e){}

	}

	@Test
	public void testUserNull() {
		user1 = new Usuario("tiaraju","xpto","Tiaraju","tiarajums@gmail.com","rua xxx numero 222");
		user2 = new Usuario("crafs","xpto","Carlos Rafael","crafs@gmail.com","rua yyy numero 333");
		try{
			user3 = new Usuario("rafael","xpto",null,"crafs@gmail.com","rua yyy numero 333");
			fail();
		}catch(Exception e){}
		
	}

	@Test
	public void testAddFriend(){
		user1.addFriend(user2);
		assertTrue(user1.isFriendOf(user2));
	}
	
	

}