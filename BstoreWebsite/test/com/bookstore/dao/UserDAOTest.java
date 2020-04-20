package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest extends BaseDAOTest{


	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setupClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		//entityManager.getTransaction().begin();
		//entityManager.persist(user1);
		userDAO = new UserDAO(entityManager);
		
	}
	
	@Test
	public void testCreateUsers() {
	
		Users user1 = new Users();
		user1.setEmail("jonnysno11w@gmail.com");
		user1.setFullName("Jonny SNo11w");
		user1.setPassword("dfdfddfdf");

		user1 = userDAO.create(user1);
	
		assertTrue(user1.getUserId() > 0);
	}
	
	@Test(expected = PersistenceException.class)
	//@Test
	public void testCreateUsersFieldsNotSet() {
		
		Users user1 = new Users();
		//entityManager.getTransaction().begin();
		//entityManager.persist(user1);

		user1 = userDAO.create(user1);
			
	}
	
	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("jonny@mail.net");
		user.setFullName("Jonny Smith");
		user.setPassword("mysecret");
		
		user = userDAO.update(user);
		String expected = "mysecret";
		String actual = user.getPassword();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		
		Users user = userDAO.get(userId);
		if (user != null) {
			System.out.println(user.getEmail());
		}
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		
		assertNull(user);
	
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId = 5;
		userDAO.delete(userId);
		
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNoneExistUsers() {
		Integer userId = 55;
		userDAO.delete(userId);
		
	}
	
	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		
		for (Users user: listUsers) {
			System.out.println(user.getEmail());
		}
	
		assertTrue(listUsers.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		
		assertEquals(9, totalUsers);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "ravkat@gmail.com";
		Users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

}
