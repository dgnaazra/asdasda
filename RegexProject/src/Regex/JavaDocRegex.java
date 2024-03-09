
/**
*
* @author Atakan Paşalı atakan.pasali@ogr.sakarya.edu.tr
* @since 10.04.2023
* <p>
*	JavaDoc larin inside ve outside olarak 2 adet regexini tutmak icin sinif
* </p>
*/




package Regex;

public class JavaDocRegex {
		
	
	public JavaDocRegex()
	{
	}
	public String javaDocCommendRegex()
	{
		String regex;
		regex="(\\s*[/]\\*\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n]|[^\\u0000-\\u007F])))*\\*[/])\\s*[a-zA-Z\\s0-9iüğıöç.:@,;_?*/]*\\s([a-zA-Z0-9iüğıöç.:@,;_?*/]*)([(]([a-zA-Z\\s0-9iüğıöçq@.:,;_?*/]*)[)])";
		return regex;	
	}
	public String JavaDocCommendRegexInside()
	{
		String regex;
		regex="\\s*/\\*\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n]|[^\u0000-\u007F])))*\\*/";	
		return regex;	
		
		
	}

	
	
	
	
	
}
