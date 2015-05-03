import java.io.*;
import java.util.*;

public class City
{
	// instance data fields
	private String state;
	private String city;
	private double latitude;
	private double longitude;

	// constructors
	// create cities by reading from a file
	public City( String fileLine) throws FileNotFoundException
	{	
		String[] splited = fileLine.split(",");
		state = splited[0].toUpperCase();
		city = splited[1].toUpperCase();
		latitude = Double.parseDouble(splited[2]);
		longitude = Double.parseDouble(splited[3]);
	}
	
	public City()
	{
		
	}
	
	// create cities by userinput
	public City(String stateName, String cityName, double latitude,
			double longitude)
	{
		state = stateName;
		city = cityName;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	// accessors
	public String getCity()
	{
		return city;
	}

	public String getState()
	{
		return state;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	// When a new city is created, store this new city to the file
	public static void AddCity(City cityAdded, PrintWriter output) throws FileNotFoundException
	{
		output.print(cityAdded.state);
		output.print(cityAdded.city);
		output.print(cityAdded.latitude);
		output.print(cityAdded.longitude);
	}

	public static void AddOldFile(String old, String filename) throws FileNotFoundException
	{
		PrintWriter output = new PrintWriter(new File(filename));
		output.println(old);
	}
	
	public String toString()
	{
		String retval = "";
		retval += state + ", ";
		retval += city + ", ";
		retval += latitude + ", ";
		retval += longitude;
		return retval;
	}
}
