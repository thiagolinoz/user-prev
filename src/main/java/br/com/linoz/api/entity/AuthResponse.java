package br.com.linoz.api.entity;

public class AuthResponse {
	
	public String token;
	
	public AuthResponse() {
	}

	public AuthResponse(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return this.token;
	}
	
}
