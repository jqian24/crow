import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


public class AsTheCrowFiles {
	private static final Scanner STDIN = new Scanner(System.in);


	//	public static boolean cityChosenAvailable(String cityChosen,ArrayList<City> cityAvailable)
	//	{
	//		for (int i = 0; i<cityAvailable.size();i++)
	//		{
	//			if (cityChosen.equalsIgnoreCase(cityAvailable.get(i).getCity()))
	//			{
	//				return true;				
	//			}
	//		}
	//		return false;
	//	}
//
//	private static boolean isLatValid(Scanner in){
//		if (in.hasNextDouble()){
//			if(in.hasNextDouble() && in.nextDouble() > -90 && in.nextDouble() < 90){
//				return true;
//			}
//			return false;
//		}
//		return false;
//	}

	//	public static String SaveOldFile(Scanner in, String filename) throws FileNotFoundException{
	//		in = new Scanner(new File(filename));
	//		String temp = null;
	//		while(in.hasNextLine()){
	//			temp +=in.nextLine()+'\n';
	//		}
	//		return temp;
	//	}
	
	private static boolean numIsValid(String input)
	{
		char[] inputChar = input.toCharArray();
		// check if it has the probability to be a negative no.
		if (inputChar[0] == '-')
		{
			for (int i = 1; i < inputChar.length; i++)
			{
				// check if it is a negative no. or not a no. at all
				if (inputChar[i] < '0' || inputChar[i] > '9')
				{
					// when it is not a no.
					System.out.println("Invalid input. Try again.");
					
					return false;
				}
			}
			
		}
		// when input will not be a negative no.
		for (int i = 0; i < inputChar.length; i++)
		{
			if (inputChar[i] < '0' || inputChar[i] > '9')
			{
				// when input is not no.
				System.out.println("Invalid input. Try again.");
				return false;
			}
		}
		double lat = Double.parseDouble(input);
		if (lat<-90.0 || lat>90.0)
		{
			return false;
		}
		// when input is a no.
		return true;

	}
	
	private static boolean numIsValidForLon(String input)
	{
		char[] inputChar = input.toCharArray();
		// check if it has the probability to be a negative no.
		if (inputChar[0] == '-')
		{
			for (int i = 1; i < inputChar.length; i++)
			{
				// check if it is a negative no. or not a no. at all
				if (inputChar[i] < '0' || inputChar[i] > '9')
				{
					// when it is not a no.
					System.out.println("Invalid input. Try again.");				
					return false;
				}
			}
			
		}
		// when input will not be a negative no.
		for (int i = 0; i < inputChar.length; i++)
		{
			if (inputChar[i] < '0' || inputChar[i] > '9')
			{
				// when input is not no.
				System.out.println("Invalid input. Try again.");
				return false;
			}
		}
		double lon = Double.parseDouble(input);
		if (lon<-180.0 || lon>180.0)
		{
			return false;
		}
		// when input is a no.
		return true;

	}
	

	public static void main(String[] args) throws FileNotFoundException
	{
		int choice;//the user input of the choice 
		boolean choiceValid;//use to check if the input is valid
		ArrayList<City> cityAvailable = new ArrayList<City> ();
		ArrayList<City> cityChosen = new ArrayList<City> ();
		Scanner in = null;
		int count = 0;
		PrintWriter output = null;//create a PrintWriter object to write more city information to the file
		String fileinput = null; // the filename of the file our scanner reads
		String fileoutput = null; // the filename of the new file that stores the information
		City chosen = new City();//use to save the city information when creating trip
		System.out.println("As The Crow Flies");
		System.out.println();
		System.out.println("1. Load available cities from a file");
		System.out.println("2. Display available cities");
		System.out.println("3. Create a trip");
		System.out.println("4. Add a city to available cities");
		System.out.println("5. Exit Program");
		System.out.print("Enter choice as integer [1-5]: ");

		boolean hasInput = true;

		while (hasInput) 
		{
			if (STDIN.hasNextInt())
			{
				choice = STDIN.nextInt();
				STDIN.nextLine();
				if (choice<1||choice>5)
				{
					System.out.println("Invalid input. Try again.");
					System.out.print("Enter choice as integer [1-5]: ");
				}
				if (choice == 1)
				{	
					System.out.print("Enter the filename: ");
					try 
					{	
						fileinput = STDIN.nextLine();
						in = new Scanner(new File(fileinput));	
						while (in.hasNextLine())
						{
							String fileNextLine = in.nextLine();
							City newCity = new City(fileNextLine);
							cityAvailable.add(newCity);
						}
						System.out.println(cityAvailable.size()+" cities added");	
						output = new PrintWriter(fileinput);
					}
					catch (FileNotFoundException fnfe)
					{
						System.out.println("Unable to read file");
						System.out.println("0 cities added");				
					}


					System.out.println();
					System.out.println("1. Load available cities from a file");
					System.out.println("2. Display available cities");
					System.out.println("3. Create a trip");
					System.out.println("4. Add a city to available cities");
					System.out.println("5. Exit Program");
					System.out.print("Enter choice as integer [1-5]: ");
				}
				if (choice == 2)
				{
					for (int i = 0; i < cityAvailable.size(); i++)
					{
						System.out.println(cityAvailable.get(i).toString());
					}

					System.out.println();
					System.out.println("1. Load available cities from a file");
					System.out.println("2. Display available cities");
					System.out.println("3. Create a trip");
					System.out.println("4. Add a city to available cities");
					System.out.println("5. Exit Program");
					System.out.print("Enter choice as integer [1-5]: ");
				}
				if (choice == 3)
				{

					System.out.println("There are "+cityAvailable.size()+" cities to choose from.");
					if (count == 0)
					{
						System.out.println("New trip created, needs at least two cities.");
						count++;
					}
					else
					{
						System.out.print("Add to current trip (y/n)?");
						if (STDIN.next()=="n"||STDIN.next()=="N")//MAY BE WRONG!!!!
						{
							cityChosen = new ArrayList<City>(); // ??shouldn't construct a new arraylist
						}
					}
					System.out.print("Enter next city name (or enter to end): ");
					String nextCity = STDIN.nextLine();
					while (STDIN.hasNext())
					{

						String cityChosen1 = STDIN.next().toUpperCase();


						//						boolean cityIsAvailable = cityChosenAvailable(cityChosen1,cityAvailable);
						//						if (cityIsAvailable)
						//						{
						for (int i = 0; i<cityAvailable.size();i++)
						{
							if (cityChosen1.equalsIgnoreCase(cityAvailable.get(i).getCity()))
							{
								chosen = cityAvailable.get(i);
								cityChosen.add(chosen);
							}
						}

						//					}
						System.out.print("Enter next city name (or enter to end): ");
						nextCity = STDIN.nextLine();
					}


				System.out.println("There are "+cityChosen.size()+" cities to choose from.");
					if (cityChosen.size() <2)
					{
						System.out.println("Must have at least 2 cities to choose from.");
					}
					else
					{	
						Trip trip = new Trip(cityAvailable);
						System.out.println(trip.toString());
						System.out.println("Write trip details to file (y/n)?");
						if (STDIN.next() == "y"||STDIN.next() == "Y")
						{
							System.out.print("Enter filename: ");
							fileoutput = STDIN.next();
							trip.saveToFile(fileoutput,trip);//????????
						}
					}

					System.out.println();
					System.out.println("1. Load available cities from a file");
					System.out.println("2. Display available cities");
					System.out.println("3. Create a trip");
					System.out.println("4. Add a city to available cities");
					System.out.println("5. Exit Program");
					System.out.print("Enter choice as integer [1-5]: ");
				}
				if (choice == 4)
				{
					System.out.print("Enter state name: ");
					// read userinput for a new City object
					String stateName = STDIN.nextLine();
					System.out.print("Enter city name: ");
					String cityName = STDIN.nextLine();
					double lon = 0;
					double lat = 0;
					System.out.print("Enter latitude as double (-90.0 to 90.0): ");
					String input = STDIN.nextLine();
					// check if the input of numbers is valid
					while (!numIsValid(input))
					{
						System.out.print("Enter latitude as double (-90.0 to 90.0): ");
						input = STDIN.next();
					}
					lat = Double.parseDouble(input);
					STDIN.nextLine();
					
					System.out.print("Enter longitude as double (-180.0 to 180.0): ");
					input = STDIN.nextLine();
					// check if the input of numbers is valid
					while (!numIsValidForLon(input))
					{
						System.out.print("Enter latitude as double (-180.0 to 180.0): ");
						input = STDIN.next();
					}
					lon = Double.parseDouble(input);
					STDIN.nextLine();

					City newCityAdded = new City(stateName, cityName, lat, lon);
					cityAvailable.add(newCityAdded); // add the new city to the available city list
					// write the new city to the original file the scanner read 
					City.AddCity(newCityAdded,output); 
					System.out.println("Added:" + stateName.toUpperCase() +", "
							+ cityName.toUpperCase() +", "+ lat +", "+lon);
					System.out.println();
					System.out.println("1. Load available cities from a file");
					System.out.println("2. Display available cities");
					System.out.println("3. Create a trip");
					System.out.println("4. Add a city to available cities");
					System.out.println("5. Exit Program");
					System.out.print("Enter choice as integer [1-5]: ");
				}
				if (choice == 5)
				{
					System.out.print("Thank you for your business.");
					System.exit(0);
				}
			}
			if (STDIN.hasNext()&&(!STDIN.hasNextInt()))
			{
				System.out.println("Invalid input. Try again.");
				System.out.print("Enter choice as integer [1-5]: ");
				STDIN.nextLine();
			}

		}
	}

}
