package br.com.linoz.api.entity;

import java.util.Collections;
import java.util.Set;

public class User {

	private Integer id;
	private String name;
	private String document;
	private String address;
	private String username;
	private String password;
	private Set<Role> roles;
	
	public User() {
		
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param document
	 * @param address
	 * 
	 * Constructor for user at API
	 */
	public User(Integer id, String name, String document, String address) {
		this.id = id;
		this.name = name;
		this.document = document;
		this.address = address;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param role
	 * 
	 * Constructor to login user at API  
	 */
	public User(String username, String password, Set<Role> role) {
		this.username = username;
		this.password = password;
		this.roles = role;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDocument() {
		return document;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	@Override
	public String toString() {
		return getName() +" of document "+ getDocument() +" who lives at "+ getAddress();
	}
	
	public static User findByUserName(String userLogin) {
		// here I mocked the credentials
		String userUsername = "user";
		String userPassword = "1PeZoMdztW5XEBj6PH0fNKqrewOHv+4jfzDOsqHQDy0=";
		
		String adminUsername = "admin";
		String adminPassword = "4P4ksvaVY8/8oSvR5z7mrKmZh7M9zEw/A9VY2DlEMkg=";
		
		if (userLogin.equals(userUsername)) {
			return new User(userUsername, userPassword, Collections.singleton(Role.USER));
		} else if (userLogin.equals(adminUsername)) {
			return new User(adminUsername, adminPassword, Collections.singleton(Role.ADMIN));
		} 
		
		return null;
	}

}
