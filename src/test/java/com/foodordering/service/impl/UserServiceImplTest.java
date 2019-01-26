package com.foodordering.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.foodordering.entity.Authority;
import com.foodordering.entity.User;
import com.foodordering.repository.AuthorityRepository;
import com.foodordering.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private AuthorityRepository authorityRepository;
	
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private User user;
	private Authority authority;
	
	@Before
	public void setup() {
		authority = new Authority();
		authority.setAuthority("ADMINadsfasdf");
		
		user = new User();
		user.setEmail("test@test.com");
		user.setPassword("testPswd");
		user.setAuthority(authority);
	}
	
	@Test
	public void testFindUserByEmail_ShouldBeEqualsAndNotNull() {
		when(userRepository.findUserByEmail(Mockito.anyString())).thenReturn(user);
		
		User userTest = userServiceImpl.findUserByEmail("testEmail");
		assertEquals(user, userTest);
		assertEquals("test@test.com", userTest.getEmail());
		assertNotNull(userTest);
	}
	
	@Test
	public void testFindUserByEmail_ShouldReturnNull() {
		when(userRepository.findUserByEmail(Mockito.anyString())).thenReturn(null);
		
		User userTest = userServiceImpl.findUserByEmail("testEmail");
		assertNull(userTest);
	}
	
	@Test
	public void testSaveUser_ShouldReturnResult() {
		when(authorityRepository.findByAuthority(Mockito.anyString())).thenReturn(authority);
		when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		
		User userTest = userServiceImpl.saveUser(new User());
		assertNotNull(userTest);
		assertEquals(user, userTest);
	}
	
	@Test
	public void testSaveUser_ShouldReturnNull() {
		when(authorityRepository.findByAuthority(Mockito.anyString())).thenReturn(null);
		when(userRepository.save(Mockito.any(User.class))).thenReturn(null);
		
		User userTest = userServiceImpl.saveUser(new User());
		assertNull(userTest);
	}
}
