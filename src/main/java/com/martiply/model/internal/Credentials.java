package com.martiply.model.internal;

import com.martiply.model.Owner;
import com.martiply.model.User;


public class Credentials {

	private Owner owner;
	private User user;
	
	public Owner getOwner() {
		return owner;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
