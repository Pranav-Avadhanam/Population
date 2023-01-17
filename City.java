/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Pranav Avadhanam
 *	@since	January 15, 2023
 */
public class City implements Comparable<City> 
{
	
	// fields
	private String stateName;
	private String cityName;
	private String type;
	private int population;
	
	// constructor
	
	public City(String stateName, String cityName, String type, int population)
	{
		this.stateName = stateName;
		this.cityName = cityName;
		this.type = type;
		this.population = population;
	}
	
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.stateName - other.stateName)
	 *		else returns (this.cityName - other.cityName)
	 */
	 public int compareTo(City other)
	 {
	 	if(this.population != other.population)
	 	{
	 		return this.population - other.population;
	 	}
	 	else if(!this.stateName.equals(other.stateName))
	 	{
	 		return this.stateName.compareTo(other.stateName);
	 	}
	 	else
	 	{
	 		return this.cityName.compareTo(other.cityName);
	 	}
	 }
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 public boolean equals(Object other)
	 {
	 	if(other instanceof City && ((City)other).cityName.equals(cityName)
	 	   && ((City)other).stateName.equals(stateName) && ((City)other).type.equals(type)
	 	   && ((City)other).population == population)
	 	{
	 		return true;
	 	}
	 	return false;
	 }
	
	/**	Accessor methods */
	public String getCityName()
	{
		return cityName;
	}
	
	public String getStateName()
	{
		return stateName;
	}
	
	public String getCityType()
	{
		return type;
	}
	
	public int getPopulation()
	{
		return population;
	}
	
	/**	toString */
	@Override
	public String toString() 
	{
		return String.format("%-22s %-22s %-12s %,12d", stateName, cityName, type,
						population);
	}
}