package com.bank.bankingapplication.model.response;

import com.bank.bankingapplication.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Data
public class UserResponse implements Serializable {

	private static final long serialVersionUID = -1487747275533464940L;
	private Integer code;
	private String description;
	@Setter
    @Getter
    private User user;


	public UserResponse() {
	}


    @Override
	public String toString() {
		return "UserResponse{" +
				"code=" + code +
				", description='" + description + '\'' +
				", user=" + user +
				'}';
	}
}
