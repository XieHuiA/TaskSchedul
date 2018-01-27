package com.alibaba.schedule.exception;

public class ShrekException extends RuntimeException{
	
	 public ShrekException(int errorCode, String errorMessage) {
	        super(errorCode + " - " + errorMessage);
	    }

	    public ShrekException(int errorCode, String errorMessage,
	                             Throwable cause) {
	        super(errorCode + " - " + errorMessage, cause);
	    }

}
