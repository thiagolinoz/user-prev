package br.com.linoz.api.entity;

public class User {

	private int id;
	private String name;
	private String document;
	private String address;
	/**
	 *  TODO implement Role role for jwt authentication
	 */
	
	public User() {
		
	}
	
	public User(int id, String name, String document, String address) {
		this.id = id;
		this.name = name;
		this.document = document;
		this.address = address;
	}
	
	public void setId(int id) {
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
	
	public int getId() {
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
	
	@Override
	public String toString() {
		return getName() +" of document "+ getDocument() +" who lives at "+ getAddress();
	}

}
