package com.tinydemo.security.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommonResponse<T> {

	private int code;

	private String message;

	private T data;

}
