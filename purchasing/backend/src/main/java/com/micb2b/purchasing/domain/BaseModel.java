package com.micb2b.purchasing.domain;

/***
 * 共用model
 *
 */
public class BaseModel<T> {
	/***
	 * 狀態代碼
	 */
	protected String code;
	/***
	 * 回傳訊息
	 */
	protected String message;
	/***
	 * 資料
	 */
	protected T content;
	/***
	 * 資料長度
	 */
	protected int totalElements;
	
	public BaseModel() {
		this(ResponseCode.UNKNOWN);
	}
	
	public BaseModel(ResponseCode statusCode) {
		setStatusCode(statusCode);
	}
	
	public BaseModel(String code, String message) {
		setCodeMessage(code, message);
	}
	
	public BaseModel(T content, int totalElements, ResponseCode statusCode) {
		setContent(content);
		setTotalElements(totalElements);
		setCodeMessage(statusCode.getCode(), statusCode.getDescription());
	}
	
	public void setStatusCode(ResponseCode statusCode) {
		setCodeMessage(statusCode.getCode(), statusCode.getDescription());
	}
	
	public void setCodeMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

//	public String toString() {
//		return JSONUtils.convertToJSON(this);
//	}
}
