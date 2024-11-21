package com.bank.bankingapplication.client;

import com.bank.bankingapplication.model.response.ResponseData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CentralBaseResponse extends ResponseData {

    private Object data;

}
