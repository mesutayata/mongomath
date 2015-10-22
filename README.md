# mongomath
Generates mongo queries from strings like mathematical equations

mongomath generates queries for mongodb from strings like

[ 
	key1=value1
	key2>value2
	key3!=value3
	key4<=100
]

Result is a Bson document ready to query mongo db.

{
  "key1": "value1",
  "key2": {
    "$gt": "value2"
  },
  "key3": {
    "$ne": "value3"
  },
  "key4": {
    "$lte": 100
  }
}

