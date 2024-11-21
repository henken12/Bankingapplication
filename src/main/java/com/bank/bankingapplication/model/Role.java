package com.bank.bankingapplication.model;

import lombok.Data;

@Data
public class Role {

	protected Integer id;
	private String name;

	private String description;

	public Role(String name, String description) {
		this.name = name;
		this.description = description;
	}


	@Override
	public String toString() {
		return this.name;
	}


}
