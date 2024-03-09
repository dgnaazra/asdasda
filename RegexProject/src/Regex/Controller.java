/**
*
* @author Atakan Paşalı atakan.pasali@ogr.sakarya.edu.tr
* @since 10.04.2023
* <p>
* Ana Kontrol Sınıfıdır. Verilen dosya adini alir ve içindeki fonksiyonlari ve yorum satirlarini KontrolEt() fonksiyonu ile Kontrol Eder. 
* </p>
*/

package Regex;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.io.BufferedWriter;

import java.util.List;
import java.util.ArrayList;
public class Controller {
	
	private String data;
	private String fileName;
	
	
	List<FunctionRegex> functions=new ArrayList<>();
	public Controller( String fileName) throws IOException
	{
		
		// Kurucu fonksiyon. Dosya ismi alarak onu readAllBytes methodu ile okur 
		this.fileName=fileName;

		
		
		this.data=new String(Files.readAllBytes(Paths.get(this.fileName)));
	
	}
	// Ana kontrolleri yapacak olan fonksiyondur
	public void KontrolEt() throws IOException
	{
		// temel Fonksiyon bulan regex patternidir.
		String regexFunction="([a-zA-Z0-9.*;:_?,]*[^\\S\\r\\n])*([a-zA-Z0-9.*_?,;:]*){1}\\s([a-zA-Z0-9_.,;:*@]*)([(]{1}([a-zA-Z0-9.*_\\s?;:,*/]*)[)]{1})(\\s|)([{]|)[\\n]*[\\s]*([{]|)([a-zA-Z0-9\\t\\n\\s.(),şıÇç_?=:;/*+\\\\\\\"]*|([{][a-zA-Z0-9\\t\\n\\s.(),şıÇç_?=:;/*+\\\\\\\"]*[}]){1})*[}]";
		
		//Regex patternlerine ulasabilmek icin siniflar tanimlanmistir.
		SingleComment mySingleComment=new SingleComment();
		MultiLineRegex myMultiLineComment=new MultiLineRegex();
		JavaDocRegex myJavaDocComment =new JavaDocRegex();
		
		//Her regex ifadesi icin ayri ayri matcherlar ve patternler tanimlanmistir
		Pattern FunctionOruntu=Pattern.compile(regexFunction);
		Matcher FunctionEslestir=FunctionOruntu.matcher(data);
		
		Pattern SingleCommentOruntu;
		Matcher SingleCommentEslestir;
		
		Pattern MultiLineCommentOruntu;
		Matcher MultiLineCommentEslestir;
		
		/*javadoc regexleri ikiye ayrildigi icin 2 ayri pattern ve matcher kullanilmistir. Inline olan fonksiyonlarin 
		 * icindeki javadoclari digeri ise fonksiyon disindakileri bulmak icin kullanilir.
		 */
		Pattern JavaDocCommentOruntuInline;
		Matcher JavaDocCommentEslestirInline;
		
		Pattern JavaDocCommentOruntu=Pattern.compile(myJavaDocComment.javaDocCommendRegex());
		Matcher JavaDocCommentEslestir=JavaDocCommentOruntu.matcher(data);
		
		int adet=0;
		// dosyaya yazdirmak icin gerekli degiskenler
		File tekSatirFile = new File("teksatir.txt");
		BufferedWriter tekSatirfileWriter=new BufferedWriter(new FileWriter(tekSatirFile));
		

		File cokSatirFile = new File("coksatir.txt");
		BufferedWriter cokSatirfileWriter=new BufferedWriter(new FileWriter(cokSatirFile));
		
		File JavaDocFile = new File("javadoc.txt");
		BufferedWriter JavaDocfileWriter=new BufferedWriter(new FileWriter(JavaDocFile));
		
		//Fonksiyon bulundugu surece icine girer
		while(FunctionEslestir.find())
		{	
			/* Her fonksiyon bulundugunda bulunan fonksiyon group methodu yardimiyla matchera verilir. Yani her bu while dongusune her girildiginde
			 * yorum satirlarini bulan matcher bulunan fonksiyonu datasi olarak alir. Boylece sadece ilgili fonksiyon okunmus olur.
			*/
			SingleCommentOruntu=Pattern.compile(mySingleComment.singleCommendRegex());
			SingleCommentEslestir=SingleCommentOruntu.matcher(FunctionEslestir.group(0));	
			
			MultiLineCommentOruntu=Pattern.compile(myMultiLineComment.multiLineCommendRegex());
			MultiLineCommentEslestir=MultiLineCommentOruntu.matcher(FunctionEslestir.group(0));
			
			JavaDocCommentOruntuInline=Pattern.compile(myJavaDocComment.JavaDocCommendRegexInside());
			JavaDocCommentEslestirInline=JavaDocCommentOruntuInline.matcher(FunctionEslestir.group(0));
			
			//Fonksiyonun parametreli mi parametresiz mi olduguna gore FunctionRegex nesnesi olusturulur. Eger parametresi yok ise FunctionRegex.HasParameters false olur.
			// Asıl amacı ayni isimde olan fonksiyonlari parametresine gore ayirt etmektir (Orn: Varsayilan kurucu fonksiyon ve parametreli kurucu fonksiyonu ayirir)
			if(FunctionEslestir.group(5)=="")
			{
			functions.add(new FunctionRegex(FunctionEslestir.group(3),false));
			}
			else
			{
				functions.add(new FunctionRegex(FunctionEslestir.group(3),true));
			}
				
			//Tek satirli yorum satirlari burada bulunur. Eger bulunursa ve iceri girerse FunctionRegex sinifinin  increaseSingleComment Methodu cagirilir.
		
				int countTekSatir=0;
				while(SingleCommentEslestir.find())
				{   
					countTekSatir++;
					//Dosyaya yazma islemleri yapilir. Fonksiyonun ismi sadece 1 defa yazilsin diye boyle bir kontrol olusturulmustur.
					if(countTekSatir==1)
					{
					tekSatirfileWriter.write("Fonksiyon :" + functions.get(adet).GetName());
				    tekSatirfileWriter.newLine();
					}
					tekSatirfileWriter.write(SingleCommentEslestir.group(0));	
					tekSatirfileWriter.newLine();
					functions.get(adet).increaseSingleComment();
				}
					
					
					
					// Islemler Yukaridaki while ile aynidir
					int countCokSatir=0;
				while(MultiLineCommentEslestir.find())
				{  countCokSatir++;
					if(countCokSatir==1)
					{
						cokSatirfileWriter.write("Fonksiyon :" + functions.get(adet).GetName());
						cokSatirfileWriter.newLine();
					}
						cokSatirfileWriter.write(MultiLineCommentEslestir.group(0));
						cokSatirfileWriter.newLine();
						functions.get(adet).increaseMultiLineComment();
				}
				
				
				
				//Burada sadece fonksiyonun icindeki javadoclar bulunur. Eger javadoc Fonkisyonun disinda ise altta baska bir while dongusu ile bulunacaktir
				int countJavaDoc=0;
				while(JavaDocCommentEslestirInline.find())
				{	 countJavaDoc++;
					if(countJavaDoc==1)
					{
						JavaDocfileWriter.write("Fonksiyon :" + functions.get(adet).GetName());
						JavaDocfileWriter.newLine();
					}
					JavaDocfileWriter.write(JavaDocCommentEslestirInline.group(0));
					JavaDocfileWriter.newLine();
					functions.get(adet).increaseJavaDocComment();
				}
					
			adet++;
		}
		
		//Fonksiyon disindaki javadoclari bulmak icin kullanilir. Javadoclar + baglantili oldugu fonksiyonun ismine kadar olan kismi bulur.
		while(JavaDocCommentEslestir.find())
		{	boolean hasParameters;
			String Functionname="";
			//group methodu yardimi ile fonksiyon ismi alinir
			String FunctionNameFromJavDoc=JavaDocCommentEslestir.group(5);
		//eger javadoc ve baglantili oldugu javadoc parametresiz ise hasParameters false olur. 
			if(JavaDocCommentEslestir.group(7)=="")
			{
				hasParameters=false;
				
			}
			else
			{
				hasParameters=true;
			}
			
			//bulunan javadoc ve fonksiyon ismi daha once olusturulan FunctionRegex nesnelerinin name degiskenleri ile karsilastirilir.
			for(int i=0;i<functions.size();i++)
			{	
				Functionname=functions.get(i).GetName();
			// Karsilastirma sonucunda eslesme saglanir ise parametreli mi parametresiz mi olduguna gore increaseJavaDocComment() methodu cagirilir.
				if(Functionname.equals(FunctionNameFromJavDoc))
				{
				
					
					//Bulunan javadoclar txt dosyalarina yazdirilir.
					if(functions.get(i).hasParameters==false&&hasParameters==false)
					{	JavaDocfileWriter.write("Fonksiyon :" +Functionname);
					JavaDocfileWriter.newLine();
					JavaDocfileWriter.write(JavaDocCommentEslestir.group(1));
					JavaDocfileWriter.newLine();
					functions.get(i).increaseJavaDocComment();
					}
					else if(functions.get(i).hasParameters==true&&hasParameters==true)
					{	
						JavaDocfileWriter.write("Fonksiyon :" +Functionname);
						JavaDocfileWriter.newLine();
						JavaDocfileWriter.write(JavaDocCommentEslestir.group(1));
						JavaDocfileWriter.newLine();
					functions.get(i).increaseJavaDocComment();
					}
				}
			
				
			}
	
		}
		JavaDocfileWriter.close();
		tekSatirfileWriter.close();
		cokSatirfileWriter.close();
		
		for(int i=0;i<functions.size();i++)
		{
		
		functions.get(i).Yazdir();
			
		}
			
	}

		
	
			
	
	
	

}
