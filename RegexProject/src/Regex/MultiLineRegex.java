
/**
*
* @author Atakan Paşalı atakan.pasali@ogr.sakarya.edu.tr
* @since 10.04.2023
* <p>
*	Cok satirli yorumun regexini tutmak icin sinif
* </p>
*/




package Regex;

public class MultiLineRegex {

	
	
	 public MultiLineRegex(){
		
	}
	
	
	
	public String multiLineCommendRegex()
	{
		String regex;
			
		regex="\\s*[/]\\*[a-zA-Z0-9üğişıöç_.:,;()\\s/+]{1}[a-zA-Z0-9iüğşçöı,;.:_*?(){}\\s\\n\\t]*[*][/]";
		
		return regex;	
	}
			
			
	
	
}
