package com.example.demo.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String email;
	private String role;

	public JwtResponse(String accessToken, Long id, String email) {
		this.token = accessToken;
		this.id = id;
		this.email = email;
	}
}
