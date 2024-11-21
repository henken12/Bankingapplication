
package com.bank.bankingapplication.model.response;

import lombok.Data;


@Data
public class ResponseData {

	private String code;
    private String description;
    private Object data;

    public ResponseData(String code, String description) {
        this.code = code;
        this.description = description;
    }


    public ResponseData(String code, String description, Object data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }
}
