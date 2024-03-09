/**
*
* @author Atakan Paşalı atakan.pasali@ogr.sakarya.edu.tr
* @since 10.04.2023
* <p>
*	Main sinifi. Arguman olarak dosya ismini alir ve Controller sinifi yardimiyla kontrol eder. Eger dosya ismi ile ilgili sikinti olusursa hata verir.
* </p>
*/




package Regex;

import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {
		
		System.out.println(args[0]);
		if(args.length>0)
		{
			String ControlArgument="";
			ControlArgument=args[0].substring(args[0].length()-5);
			
			if(!ControlArgument.equals(".java"))
			{	
				System.out.println("Lutfen gecerli bir uzanti giriniz");
			return;
			}
			try
			{
				Controller control=new Controller(args[0]);		
				control.KontrolEt();
				
		    } catch (Exception e) {
				System.out.println("e =" + e);
				System.out.println("Lutfen Gecerli Bir Dosya Giriniz");
			
			}
		
		}
		else
		{
			System.out.println("Lutfen bir dosya giriniz");
			
			
			
		}
	
		
	
	
		
		
		
		
	}

}
