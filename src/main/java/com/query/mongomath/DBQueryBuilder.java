package com.query.mongomath;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class DBQueryBuilder
{

	
	/**
	 * Creates a BSON document that is ready for querying Mongo.
	 * Takes the input as String of mathematical like expressions.
	 * 
	 * Please @see QueryBuilderTester for sample use.
	 * 
	 * @param inputList i.e. [key1=value1, key2>value2, key3!=value3, key4.nested1.name=%abc, key4.nested1.count>100]
	 */
	public static Document createQuery(List <String> inputList) throws WrongFormatException
	{
		Document queryDocument = new Document();
		try{
			if(inputList!=null && inputList.size()>0){
				
				for(String line:inputList){
					String [] entries = line.trim().split(Operators.OR.getOperator());
					if(entries.length==1){
						putEntry(queryDocument, entries[0]);
					}else{
						List<Document> orList = new ArrayList<Document>();
						Document doc = null;
						for(int i=0; i< entries.length; i++){
							doc = new Document();
							putEntry(doc, entries[i]);
							orList.add(doc);
						}
						queryDocument.put(Operators.OR.getMongoOperator(), orList);
					}
				}
			}
		}catch(Exception e){
			throw new WrongFormatException(e.getMessage(), 
					e.getStackTrace());
		}

		return queryDocument;
	}

	private static void putEntry(final Document document, String entry)
	{
		if(entry.contains(Operators.GTE.getOperator())){
			putEntry(document, entry, Operators.GTE);
		}else if(entry.contains(Operators.GT.getOperator())){
			putEntry(document, entry, Operators.GT);
		}else if(entry.contains(Operators.LTE.getOperator())){
			putEntry(document, entry, Operators.LTE);
		}else if(entry.contains(Operators.LT.getOperator())){
			putEntry(document, entry, Operators.LT);
		}else if(entry.contains(Operators.NE.getOperator())){
			putEntry(document, entry, Operators.NE);
		}else if(entry.contains(Operators.EQ.getOperator())){
			putEntry(document, entry,  Operators.EQ);
		} 
	}

	
	private static void putEntry(final Document document, String entry, Operators operator){
		String key;
		Object value;
		String [] parts = entry.trim().split(operator.getOperator());
		if(operator.getMongoOperator()==null){
			key = parts[0].trim(); 
			value = parts[1].trim();
			value = checkValue(value);
		}else{
			value = new Document();
			key = parts[0].trim(); 
			Object innerValue = parts[1].trim();
			innerValue = checkValue(innerValue);
			((Document)value).put(operator.getMongoOperator(), innerValue);
		}
		document.put(key, value);
	}


	private static Object checkValue(Object value)
	{
		String stringVal = value.toString();
		char firstChar = stringVal.charAt(0);
		boolean isNumber = (firstChar >= '0' && firstChar <= '9');
		if(isNumber){
			return Double.valueOf(stringVal);
		}else if (firstChar==Operators.LIKE.getOperator().charAt(0)){
			Document doc = new Document();
			doc.put(Operators.LIKE.getMongoOperator(), 
					stringVal.replaceAll(Operators.LIKE.getOperator(), ""));
			return doc;
		}
		return stringVal;
	}
	
}
