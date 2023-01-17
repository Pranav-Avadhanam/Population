import java.util.Comparator;
import java.util.List;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *	Implements Bubble, Selection, Insertion, and Merge sort with a swap helper method
 *
 *	@author Pranav Avadhanam
 *	@since	January 15, 2023
 */
public class SortMethods 
{
	
	/**
	 *	Bubble Sort algorithm - in Comparator-defined order
	 *	Each inner loop, iteratively swaps every consecutive pair if it is unordered
	 *	@param arr		ArrayList of City objects to sort
	 *	@param c		Comparator class c that implements a compare(City,City) method
	 */
	 
	 
	 public void bubbleSort(List<City> arr, Comparator<City> c)
	 {	
	 	for(int outer = 0; outer < arr.size(); outer++)
	 	{
	 		for(int inner = 1; inner < arr.size(); inner++)
	 		{
	 			if(c.compare(arr.get(inner-1), arr.get(inner)) > 0)
	 			{
	 				swap(arr, inner-1, inner);
	 			}
	 		}
	 	}
	 }
	
	
	/**
	 *	Swaps two City objects in ArrayList arr
	 *	@param arr		ArrayList of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	public void swap(List<City> arr, int x, int y)
	{
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in Comparator-defined order
	 *	For each loop iteration n(n ranges from arr.length to 2), the inner loop finds
	 *	the largest element from index 0 to index n-1 and swaps it to index n-1 - 
	 *	generating sorted region from the right of the array
	 *	@param arr		ArrayList of City objects to sort
	 *	@param c		Comparator class c that implements a compare(City,City) method
	 */
	 public void selectionSort(List<City> arr, Comparator<City> c)
	 {
	 	for(int outer = arr.size(); outer > 1; outer--)
	 	{
	 		int maxInd = 0;
	 		for(int i = 1; i < outer; i++)
	 		{
	 			if(c.compare(arr.get(i), arr.get(maxInd)) > 0)
	 			{
	 				maxInd = i;
	 			}
	 		}
	 		swap(arr, maxInd, outer-1);
	 	}
	 }
	 
	
	
	/**
	 *	Insertion Sort algorithm - in Comparator-defined order
	 *	Generates sorted region from the left, starting with a sorted 'array of one'. Each
	 *	iteration, element one to the right of the sorted region is inserted into appropriate
	 * 	position in sorted region
	 *	@param arr		ArrayList of City objects to sort
	 *	@param c		Comparator class c that implements a compare(City,City) method
	 */
	 public void insertionSort(List<City> arr, Comparator<City> c)
	 {
	 	for(int i = 1; i < arr.size(); i++)
		{
			City insertValue = arr.remove(i);
			int j = i;
			while(j > 0 && c.compare(arr.get(j-1), insertValue) > 0)
			{
				j--;
			}
			arr.add(j, insertValue);
		}	
	 }
	
	
	/**
	 *	Merge Sort algorithm - in Comparator-defined order
	 *	Recursively halve array until 1 or 2-size base-case array met(then swap its elements to order if needed)
	 *	Then keep merging sorted subarrays until entire array is sorted
	 *	@param arr		ArrayList of City objects to sort
	 *	@param c		Comparator class c that implements a compare(City,City) method
	 */
	public void mergeSort(List<City> arr, Comparator<City> c)
	{
		sort(arr, 0, arr.size() - 1, c);//initial call to the sort
	}
	
   /**
    *	sort helper method that performs the recursion of mergeSort
	*	@param arr	ArrayList of City objects to sort
	*	@param l	left index(inclusive) of subarray to sort
	*	@param r	right index(inclusive) of subarray to sort
	*	@param c		Comparator class c that implements a compare(City,City) method
	*
	*/
	public void sort(List<City> arr, int l, int r, Comparator<City> c)
	{
		if(l == r || l+1 == r)//base case
		{
			if(c.compare(arr.get(l), arr.get(r)) > 0)
			{
				swap(arr, l , r);
			}
			return;
		}
		sort(arr, l, (l+r)/2, c);//sort left half
		sort(arr, (l+r)/2 + 1, r, c);//sort right half
		merge(arr, l, (l+r)/2, r, c);//merge both sorted subarrays
	}
	
   /**
   	*	merge method: Merges two sorted subarrays into one larger sorted array
   	*	Has two pointers in each subarray, the pointed-to element that is smaller among the two
   	*	is added to the merged array and the corresponding pointer is shifted one to the right.
    *	Process continued until either subarray runs out of elements to merge - then remainder
    *	of elements in the other array is directly added to merged.
  	*	@param arr	ArrayList of City objects to sort
	*	@param l	left index(inclusive) of subarray to sort
	*   @param m	middle index(average of left and right indices)
	*	@param r	right index(inclusive) of subarray to sort
	*	@param c		Comparator class c that implements a compare(City,City) method
   	*/
	public void merge(List<City> arr, int l, int m, int r, Comparator<City> c)
	{
		int lP = l;//left pointer
		int rP = m+1;//right pointer
		int mP = 0;//pointer into merged array
		City[] merged = new City[r-l+1];
		//compare pointers to merge arrays
		while(lP<=m && rP<=r)
		{
			if(c.compare(arr.get(lP), arr.get(rP)) > 0)
			{
				merged[mP] = arr.get(rP);
				rP++;
				mP++;
			}
			else
			{
				merged[mP] = arr.get(lP);
				lP++;
				mP++;
			}
		}
		//add remaining elements to merged array
		while(lP<=m)
		{
			merged[mP] = arr.get(lP);
			lP++;
			mP++;
		}
		while(rP<=r)
		{
			merged[mP] = arr.get(rP);
			rP++;
			mP++;
		}
		
		//copy over elements from merged into original ArrayList
		for(int i= 0; i<r-l+1; i++)
		{
			arr.set(l+i, merged[i]);
		}
	}
}
