# mongomath
Generates mongo queries from strings like mathematical equations.


1. Example

[ 
	key1=value1
	key2>value2
]

Result is a Bson document ready to query mongo db.

{
  "key1": "value1",
  "key2": {
    "$gt": "value2"
  }
}


2. How to use ?
		
		
		List<String> list = new ArrayList<String>();

		list.add("key1=value1");
		list.add("key2>value2");
		list.add("key3!=value3");
		list.add("key4.metric.name = %abc , key5.metric.avg > 34.56");
		list.add("key4.metric.count>100");
		
		System.out.println("Input: " + list);
		
		// Here the conversion is done.
		Document doc = DBQueryBuilder.createQuery(list);
		
		System.out.println("Output: " + doc.toJson());
	
	
The above code block will give the output:
	
Input: 

[
	key1=value1, 
	key2>value2, 
	key3!=value3, 
	key4.metric.name = %abc , key5.metric.avg > 34.56, 
	key4.metric.count>100
]

Output: 

{
  "key1": "value1",
  "key2": {
    "$gt": "value2"
  },
  "key3": {
    "$ne": "value3"
  },
  "$or": [
    {
      "key4.metric.name": {
        "$regex": "abc"
      }
    },
    {
      "key5.metric.avg": {
        "$gt": 34.56
      }
    }
  ],
  "key4.metric.count": {
    "$gt": 100
  }
}



	
	
	