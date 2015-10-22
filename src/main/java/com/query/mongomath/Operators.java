package com.query.mongomath;

public enum Operators
{
	GTE(">=", "$gte"), 
	GT(">", "$gt"), 
	LTE("<=", "$lte"), 
	LT("<", "$lt"),
	NE("!=", "$ne"),
	EQ("=", null),
	LIKE("%", "$regex"),
	OR(",", "$or"),
	AND("&", "$and");
	
	private String operator;
	private String mongoOperator;
	
	
	private Operators(String operator, String mongoOperator){
		this.operator= operator;
		this.mongoOperator = mongoOperator;
	}


	public String getOperator()
	{
		return operator;
	}


	public void setOperator(String operator)
	{
		this.operator = operator;
	}


	public String getMongoOperator()
	{
		return mongoOperator;
	}


	public void setMongoOperator(String mongoOperator)
	{
		this.mongoOperator = mongoOperator;
	}

	
	
}

