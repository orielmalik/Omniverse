package com.example.omniverse;


import com.example.omniverse.Ids.UserId;

public class UserEntity {
	private String id;
	private UserId userId;

	private String username;
	private String email;
	private String avatar;
	private Role role;

	public UserEntity() {
	}

	public String getId() {
		return id;
	}

	public UserEntity setId(String id) {
		this.id = id;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public UserEntity setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserEntity setEmail(String email) {
		this.email = email;
		return this;
	}

	public Role getRole() {
		return this.role;
	}

	public UserEntity setRole(Role role) {
		this.role = role;
		return this;
	}

	public String getAvatar() {
		return avatar;
	}

	public UserEntity setAvatar(String avatar) {
		this.avatar = avatar;
		return this;
	}

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}
}
