package com.query.mongomath.test;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.Test;

import com.query.mongomath.DBQueryBuilder;
import com.query.mongomath.WrongFormatException;

public class QueryBuilderTester
{

	@Test
	public void test() throws WrongFormatException{
		
		List<String> list = new ArrayList<String>();

		list.add("key1=value1");
		list.add("key2>value2");
		list.add("key3!=value3");
		list.add("key4.metric.name = %abc , key5.metric.avg > 34.56");
		list.add("key4.metric.count>100");
		
		System.out.println("Input: " + list);
		Document doc = DBQueryBuilder.createQuery(list);
		System.out.println("Output: " + doc.toJson());
	}
	
}
