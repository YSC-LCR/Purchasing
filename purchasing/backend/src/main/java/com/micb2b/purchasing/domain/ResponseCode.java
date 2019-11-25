package com.micb2b.purchasing.domain;

/***
 * 回傳代碼
 *
 */
public enum ResponseCode{
	SUCCESS("0000", "Success"),
	FAIL("0001", "Fail"),
	TRUE("0002", "True"),
	FALSE("0003", "False"),
	
	TIMEOUT("9995", "Timeout"),
	CREDENTIALERROR("9996", "Credential error"),  //認證失敗
	DUPLICATED("9997", "Request duplicated"),     // 重複請求
	EXCEPTION("9998", "Exceptional Error"),			  // 系統例外
	UNKNOWN("9999", "Unknown Error");                 // 未知的錯誤
	
	private String code;
	private String description;
	
	private ResponseCode(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public ResponseCode get(String code) {
		ResponseCode result = null;
		for(ResponseCode statusCode : ResponseCode.values()) {
			if(statusCode.getCode().equals(code)) {
				result = statusCode;
			}
		}
		return result;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
