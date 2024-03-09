
/**
*
* @author Atakan Paşalı atakan.pasali@ogr.sakarya.edu.tr
* @since 10.04.2023
* <p>
*	Bulunan fonksiyonlar icin FunctionRegex nesneleri olusturulacak. Parametre bilgisi, yorum satirlari bilgisi, ve isim bilgisi tutulur
* </p>
*/





package Regex;

public class FunctionRegex {
	
	private String name;
	 private int countSingleComment;
	 private int countMultiLineComment;
	 private int countJavaDocComment;
	public boolean hasParameters;
	public FunctionRegex(String name,boolean hasParameters)
	{
		this.name=name;
		countSingleComment=0;
		countMultiLineComment=0;
		countJavaDocComment=0;
		this.hasParameters=hasParameters;
	}
	public String GetName()
	{
		return name;
		
		
	}
	
	
	public void increaseSingleComment()
	{
		
		countSingleComment++;
		
		
	}
	
	public void increaseMultiLineComment()
	{
		
		countMultiLineComment++;
		
	}
	public void increaseJavaDocComment()
	{
		
		 countJavaDocComment++;
		
		
	}
	public void Yazdir()
	{
		
		System.out.println("Fonksiyon:" +name);
		System.out.println("Tek Satir Yorum Sayisi:" +countSingleComment);
		System.out.println("Coklu Satirli Yorum Sayisi:" +countMultiLineComment);
		System.out.println("JavaDoc Yorum Sayisi:" +countJavaDocComment);
		System.out.println("--------------------------------------------------------");
		
		
	}


}
