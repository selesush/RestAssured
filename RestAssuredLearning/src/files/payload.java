package files;

public class payload {
	
	public static String getHostData()
	{
		String b = "{"+
				"\"location\" : {"+
				"\"lat\": -33.8670522,"+
				"\"lng\": 151.1957362,"+
			"},"+
			    "\"accuracy\": 50,"+
			    "\"name\" : \"Google Shoes\","+
			    "\"phone_number\":\"(02) 9374 4000\"."+
			    "\"address\":\"48 Pirrama Road,Pyrmont,NSW 2009,Australia\","+
			    "\"types\":[\"shoe_store\"],"+
			    "\"website\": \"http://www.google.com.au/\","+
			    "\"language\": \"en-AU\""+
			"}";
		
		return b;
		
	}

}
