package com.micb2b.purchasing.domain;

/***
 * 回傳model
 *
 */
public class ResponseModel<T> extends BaseModel<T> {
	
	public ResponseModel() {
		super();
	}
	
	public ResponseModel(ResponseCode statusCode) {
		super(statusCode);
	}
	
	public ResponseModel(String code, String message) {
		super(code, message);
	}
	
	public ResponseModel(T content, int totalElements, ResponseCode statusCode) {
		super(content, totalElements, statusCode);
	}

}
