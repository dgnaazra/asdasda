
/**
*
* @author Atakan Paşalı atakan.pasali@ogr.sakarya.edu.tr
* @since 10.04.2023
* <p>
*	Tek satirli yorumun regexini tutmak icin sinif
* </p>
*/





package Regex;


public class SingleComment {

	
	
	 public SingleComment(){
		
	}
	 
	
	public String singleCommendRegex()
	{
		
		String regex="//[a-zA-Z0-9<>._?*,/ ıiüğşçöÖÇŞĞÜİ]*";
		
		return regex;
	}
	
	
	
}
