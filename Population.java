import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
/**
 *	Population - Maintains a database of US cities upon which several sort methods
 *	(Insertion, Selection, and Merge) may be applied to order the cities by population or
 *	name in ascending or descending order while measuring elapsed time. Also handles
 *	additional queries for most populous city in a given state and list of cities with a 
 * 	given name.
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Pranav Avadhanam
 *	@since	January 15, 2023
 */
public class Population 
{
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	/*constructor*/
	public Population()
	{
		cities = new ArrayList<City>();
		readCityData();
	}
	
	/*Comparator classes: Define compare methods for sorting by city name and by population, ascending and descending*/
	public class nameOrder implements Comparator<City>
	{
		public int compare(City c1, City c2)
		{
			return c1.getCityName().compareTo(c2.getCityName());
		}	
	}
	
	public class nameOrderDescending implements Comparator<City>
	{
		public int compare(City c1, City c2)
		{
		//reverse order of compare method to sort in descending order
			return c2.getCityName().compareTo(c1.getCityName());
		}	
	}
	
	public class populationOrder implements Comparator<City>
	{
		public int compare(City c1, City c2)
		{
			//use default compareTo for population comparison
			return c1.compareTo(c2);
		}
	}
	
	public class populationOrderDescending implements Comparator<City>
	{
		public int compare(City c1, City c2)
		{
		//reverse order of compare method to sort in descending order
			return c2.getPopulation() - c1.getPopulation();
		}
	}
	
	/*main method/driver code*/
	public static void main(String[] args)
	{
		Population p = new Population();
		p.printIntroduction();
		p.run();
	}
	
	/**Print menu, ask user for input selection, then print result of corresponding operation*/
	public void run()
	{
		System.out.println("\n"+cities.size()+" cities in database\n");
		boolean quit = false;
		while(!quit)
		{
			//ask user for their selection among choices 1-6, 9(forgiving of mistyped input)
			printMenu();
			boolean correct = false;
			int selection = 0;
			while(!correct)
			{
				selection = Prompt.getInt("Enter selection");
				if(1 <= selection && selection <= 6 || selection == 9)
				{
					correct = true;
				}
				else
				{
					System.out.println("ERROR: Invalid selection");
				}
			}
			System.out.println("\n");
			
			//sort cities in ascending order by population, then print fifty least populous cities(in ascending order)
			if(selection == 1)
			{
				long time = populationAscending();
				//print header
				System.out.println("Fifty least populous cities");
				System.out.println("    State                  City                   Type           Population");
				//print cities
				for(int i = 1; i <= 50; i++)
				{
					System.out.printf("%2d", i); 
					System.out.println(": "+cities.get(i-1));
				}
				System.out.println("\nElapsed Time "+time+" milliseconds");
			}
			//sort cities in descending order by population, then print fifty most populous cities(in descending order)
			if(selection == 2)
			{
				long time = populationDescending();
				//print header
				System.out.println("Fifty most populous cities");
				System.out.println("    State                  City                   Type           Population");
				//print cities
				for(int i = 1; i <= 50; i++)
				{
					System.out.printf("%2d", i); 
					System.out.println(": "+cities.get(i-1));
				}
				System.out.println("\nElapsed Time "+time+" milliseconds");
			}
			//sort cities in ascending order by name, then print first fifty cities sorted by name(in ascending order)
			if(selection == 3)
			{
				long time = nameAscending();
				//print header
				System.out.println("Fifty cities sorted by name");
				System.out.println("    State                  City                   Type           Population");
				//print cities
				for(int i = 1; i <= 50; i++)
				{
					System.out.printf("%2d", i); 
					System.out.println(": "+cities.get(i-1));
				}
				System.out.println("\nElapsed Time "+time+" milliseconds");
			}
			//sort cities in descending order by name, then print last fifty cities sorted by name(in descending order)
			if(selection == 4)
			{
				long time = nameDescending();
				//print header
				System.out.println("Fifty cities sorted by name descending");
				System.out.println("    State                  City                   Type           Population");
				//print cities
				for(int i = 1; i <= 50; i++)
				{
					System.out.printf("%2d", i); 
					System.out.println(": "+cities.get(i-1));
				}
				System.out.println("\nElapsed Time "+time+" milliseconds");
			}
			/*prompt user for an input state, sort cities in descending order by population, then print first 50 cities
			that are of that input state while scanning through sorted list of cities*/
			if(selection == 5)
			{
				//prompt for state name
				boolean valid = false;
				String name = "";
				while(!valid)
				{
					name = Prompt.getString("Enter state name (ie. Alabama)");
					//search through all cities, to see if matching state name exists(case-sensitive)
					for(City c : cities)
					{
						if(c.getStateName().equals(name))
						{
							valid = true;
						}
					}
					//error message
					if(!valid)
					{
						System.out.println("ERROR: "+name+" is not valid");
					}
				}
				System.out.println("\n");
				
				populationDescending();
				//print header
				System.out.println("Fifty most populous cities in "+name);
				System.out.println("    State                  City                   Type           Population");
				//print cities
				int count = 1; int index = 0;
				while(index < cities.size() && count <= 50)
				{
					if(cities.get(index).getStateName().equals(name))
					{
						System.out.printf("%2d", count); 
						System.out.println(": "+cities.get(index));
						count++;
					}
					index++;
				}
			}
			/*prompt user for an input city name, sort cities in descending order by population, then print all cities
			with matching city name while scanning through sorted list of cities*/
			if(selection == 6)
			{
				//prompt for city name
				boolean valid = false;
				String name = "";
				while(!valid)
				{
					name = Prompt.getString("Enter city name");
					//search through all cities, to see if matching city name exists(case-sensitive)
					for(City c : cities)
					{
						if(c.getCityName().equals(name))
						{
							valid = true;
						}
					}
					//error message
					if(!valid)
					{
						System.out.println("ERROR: "+name+" is not valid");
					}
				}
				System.out.println("\n");
				
				populationDescending();
				//print header
				System.out.println("City "+name+" by population");
				System.out.println("    State                  City                   Type           Population");
				//print cities
				int count = 1; int index = 0;
				while(index < cities.size())
				{
					if(cities.get(index).getCityName().equals(name))
					{
						System.out.printf("%2d", count); 
						System.out.println(": "+cities.get(index));
						count++;
					}
					index++;
				}
			}	
			//user quits out of Population.java
			if(selection == 9)
			{
				quit = true;
			}
			System.out.print("\n");
		}
		
		System.out.print("Thanks for using Population!");
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() 
	{
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
   /** Reads the file DATA_FILE of city data and initializes an ArrayList of cities
	with that data*/
	public void readCityData()
	{
		Scanner input = FileUtils.openToRead(DATA_FILE);
		input.useDelimiter("[\t\n]");
		while(input.hasNext())
		{
			String stateName = input.next();
			String cityName = input.next();
			String type = input.next();
			int population = Integer.parseInt(input.next());
			cities.add(new City(stateName, cityName, type, population));
		}
	}
	
   /** sorts the list of cities by population in ascending order. Uses Selection sort
	@return the elapsed time of sorting, in milliseconds
	*/
	public long populationAscending()
	{
		long startMillisec = System.currentTimeMillis();
		SortMethods s = new SortMethods();
		s.selectionSort(cities, new populationOrder());
		long endMillisec = System.currentTimeMillis();
		return endMillisec - startMillisec;
	}
	
   /** sorts the list of cities by population in descending order. Uses Merge sort
	@return the elapsed time of sorting, in milliseconds
	*/
	public long populationDescending()
	{
		long startMillisec = System.currentTimeMillis();
		SortMethods s = new SortMethods();
		s.mergeSort(cities, new populationOrderDescending());
		long endMillisec = System.currentTimeMillis();
		return endMillisec - startMillisec;
	}
	
   /** sorts the list of cities by name in ascending order. Uses Insertion sort
	@return the elapsed time of sorting, in milliseconds
	*/
	public long nameAscending()
	{
		long startMillisec = System.currentTimeMillis();
		SortMethods s = new SortMethods();
		s.insertionSort(cities, new nameOrder());
		long endMillisec = System.currentTimeMillis();
		return endMillisec - startMillisec;
	}
	
   /** sorts the list of cities by name in descending order. Uses Merge sort
	@return the elapsed time of sorting, in milliseconds
	*/
	public long nameDescending()
	{
		long startMillisec = System.currentTimeMillis();
		SortMethods s = new SortMethods();
		s.mergeSort(cities, new nameOrderDescending());
		long endMillisec = System.currentTimeMillis();
		return endMillisec - startMillisec;
	}
}
