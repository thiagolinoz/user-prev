package br.com.linoz.api.entity;

public class User {

	private Integer id;
	private String name;
	private String document;
	private String address;
	/**
	 *  TODO implement Role role for jwt authentication
	 */
	
	public User() {
		
	}
	
	public User(Integer id, String name, String document, String address) {
		this.id = id;
		this.name = name;
		this.document = document;
		this.address = address;
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
	
	@Override
	public String toString() {
		return getName() +" of document "+ getDocument() +" who lives at "+ getAddress();
	}

}
