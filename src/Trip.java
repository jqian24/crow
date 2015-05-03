import java.util.*;
import java.io.*;

public class Trip
{
	// data fields
	private static ArrayList<City> citySelected;
	private static double distance;

	// constructor
	public Trip(ArrayList<City> citySelected)
	{
		this.citySelected = new ArrayList<City>();
		this.citySelected = citySelected;
	}

	// calculate the distance
	private double calculateDistance(City c1, City c2)
	{
		final int EARTH_RADIUS = 6371000;
		double lad1 = Math.toRadians(c1.getLatitude());
		double longd1 = Math.toRadians(c1.getLongitude());
		double lad2 = Math.toRadians(c2.getLatitude());
		double longd2 = Math.toRadians(c2.getLongitude());
		double a = Math.sin((lad2 - lad1) / 2) * Math.sin((lad2 - lad1) / 2)
				+ Math.cos(lad1) * Math.cos(lad2)
				* Math.sin((longd2 - longd1) / 2.0)
				* Math.sin((longd2 - longd1) / 2.0);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		this.distance = EARTH_RADIUS * c;
		return this.distance;
	}

	// save trip details to a file
	public void saveToFile(String whereToSave, Trip tripToSave)
			throws FileNotFoundException
	{
		PrintWriter output = new PrintWriter(new File(whereToSave));
		output.println(tripToSave.toString());
	}

	public String toString()
	{
		String retval = "";
		double meterToMile = 0.00062137;
		double accumulation = 0;
		retval += "There are " + citySelected.size() + "cities in this trip.";
		for (int i = 0; i < citySelected.size() - 1; i++)
		{
			retval += citySelected.get(i).getCity() + " to "
					+ citySelected.get(i + 1).getCity();
			retval += " as the crow files is about " + (int) distance
					+ " meters" + "(~" + (int) (distance * meterToMile)
					+ " miles)" + '\n';
			accumulation += distance;
		}
		retval += citySelected.get(citySelected.size() - 1).getCity() + " to "
				+ citySelected.get(0).getCity()
				+ " as the crow files is about " + (int) distance + " meters"
				+ "(~" + (int) (distance * meterToMile) + " miles)" + '\n';
		accumulation += distance;
		retval += "Total Distance: " + (int) accumulation + " meters (~"
				+ (int) (accumulation * meterToMile) + " miles)";
		return retval;
	}
}
