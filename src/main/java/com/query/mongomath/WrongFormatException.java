package com.query.mongomath;

public class WrongFormatException extends Exception
{

	private static final long serialVersionUID = 1L;

	public WrongFormatException(){
		super();
	}
	
	public WrongFormatException(String desc){
		super(desc);
	}

	public WrongFormatException(String desc, StackTraceElement[] stackTrace)
	{
		super(desc);
		super.setStackTrace(stackTrace);
	}

}
