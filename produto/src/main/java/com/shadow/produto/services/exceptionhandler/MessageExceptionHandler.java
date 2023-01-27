package com.shadow.produto.services.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageExceptionHandler {

    private Date timestamp;
    private Integer status;
    private String message;

}
