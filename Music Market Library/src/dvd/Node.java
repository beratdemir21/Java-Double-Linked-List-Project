package dvd;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.plaf.SliderUI;


public class Node {

public	DVD head;
public	DVD tail;
	// Listeye yeni bir Dvd ekler
	public void add(String adSoyad, String baslik, int tarihi, int fiyati, ArrayList<String> sarkiAdlari) {

		DVD objDvd = new DVD(adSoyad, baslik, tarihi, fiyati, sarkiAdlari);		
		
		if(!elemanVarMi()){
			//Listede Eleman Yoksa
			head=objDvd;
			tail=objDvd;
		}else{			
			if(head==tail){
			//Listede Tek Eleman Varsa
				if(compareObj(head.getAdSoyad(), objDvd.getAdSoyad()) < 0){
					objDvd.previous=head;
					head.next=objDvd;
					tail=objDvd;
					
				}else if(compareObj(head.getAdSoyad(), objDvd.getAdSoyad()) > 0){
								
					objDvd.next=head;
					head.previous=objDvd;
					tail=head;
					head=objDvd;
					
				}else if(compareObj(head.getAdSoyad(), objDvd.getAdSoyad()) == 0){
					if(equalsTarih(head.getCikisTarihi(), objDvd.getCikisTarihi())){
						objDvd.previous=head;
						head.next=objDvd;
						tail=objDvd;		
					}else{
						objDvd.next=head;
						head.previous=objDvd;
						tail=head;
						head=objDvd;
					}
				}
			}else{
				//Listede Ýki veya daha fazla eleman vardýr		
				DVD position1=head;
				
				while(position1 != null){
					
					if(compareObj(position1.getAdSoyad(), objDvd.getAdSoyad()) > 0 && position1==head){
						objDvd.next=head;
						head.previous=objDvd;
						head=objDvd;
						
					}else if(compareObj(position1.getAdSoyad(), objDvd.getAdSoyad()) == 0){	
						//position = head ise iterasyon yapýlacak
						
						if(equalsTarih(position1.getCikisTarihi(), objDvd.getCikisTarihi()) && position1.next != null){
							
						if(compareObj(position1.next.getAdSoyad(), objDvd.getAdSoyad()) <= 0 ){
							
							if(position1.next != null){
								
								if(compareObj(position1.next.getAdSoyad(), objDvd.getAdSoyad()) == 0){
									position1=position1.next;
									continue;									
								}else{
										objDvd.next=position1;
										objDvd.previous=position1.previous;
										position1.previous.next=objDvd;
										position1.previous=objDvd;
										position1=head;
										break;							
									}								
								}	
							
							}else{
								objDvd.next=position1.next;
								objDvd.previous=position1;
								position1.next.previous=objDvd;
								position1.next=objDvd;
								break;
										
							}
						}else if(!equalsTarih(position1.getCikisTarihi(), objDvd.getCikisTarihi())){
							if(position1.previous != null){
								objDvd.next=position1;
								objDvd.previous=position1.previous;
								position1.previous.next=objDvd;
								position1.previous=objDvd;
								break;
							}else{
								objDvd.next=position1;
								position1.previous=objDvd;
								head=objDvd;
								break;
							}
						}else if(position1 == tail){
							if(compareObj(position1.getAdSoyad(), objDvd.getAdSoyad())==0){
								if(equalsTarih(position1.getCikisTarihi(), objDvd.getCikisTarihi())){
									objDvd.previous=position1;
									position1.next=objDvd;
									tail=objDvd;
									break;
								}else{
									objDvd.next=position1;
									objDvd.previous=position1.previous;
									position1.previous.next=objDvd;
									position1.previous=objDvd;
									break;
								}
							}
						}
						
						
					}else if(compareObj(position1.getAdSoyad(), objDvd.getAdSoyad()) <= 0 ){						
						//position ortalarda bir yerdeyse iterasyon ortalarda yapýlacak
						
						if(position1.next != null){
							if(compareObj(position1.next.getAdSoyad(), objDvd.getAdSoyad()) <= 0){
								if(compareObj(position1.getAdSoyad(), objDvd.getAdSoyad()) == 0){
									
									position1=position1.next;
									continue;	
								}
							}else{							
								objDvd.next=position1.next;
								objDvd.previous=position1;
								position1.next.previous=objDvd;
								position1.next=objDvd;
								break;	
							}
						}else{	
							objDvd.previous=position1;
							position1.next=objDvd;
							tail=objDvd;
							break;
						}
					}
							
							
						position1=position1.next;	
					}
				}
			}
		}

	//Listeden Birsanatçýya ait tüm Dvd leri siler	
	public void delete(String adSoyad){
		DVD position=head;
		
		while(position != null){
			if(position.getAdSoyad().trim() == adSoyad){
				System.out.println(adSoyad);
				if(position==head){
					position=position.next;
					position.previous=null;
					head=position;
					continue;
				}else{
					if(position != tail){
						if(position.getAdSoyad() == adSoyad){
							position.previous.next=position.next;
							position.next.previous=position.previous;		
							position=position.next;
							continue;
						}
					}else{
						
						if(position.getAdSoyad() == adSoyad){
							tail=position.previous.previous;
							System.out.println(tail.getAdSoyad() +" " + tail.getBaslik());
						}
					}
				}
			}
			position=position.next;
		}		
		
	}

	//Dvd nin içeriðindeki þarký adlarýný yazdýrýr
	public String arrayListPress(DVD position) {	
		int i=0;
		String liste="";
		for (String x : position.getSarkiAdlari()){	
			if(!x.endsWith(".")){				
				liste += x + ",";	
			}else{
				liste += x;
			}		
		}			
		return liste;
	}
	
	//DVD listesini artan yada azalan sýrada ekrana yazdýrýr
	public void printScr(String sirala) {

		DVD obj = new DVD();
		
		if(elemanVarMi()){
			
			if(sirala == "sondanBasa"){
				DVD position = tail;
				while (position != null) {
					System.out.println(obj.toString(position));	
					System.out.println();
					position = position.previous;
				}
			}else{
				DVD position = head;
				while (position != null) {
					System.out.println(obj.toString(position));					
					System.out.println();
					position = position.getNext();
		}
			}
			
			
		}else{
			System.out.println("Listede eleman yok");
		}
	}

	// 2000 den öncesine ait Dvdleri ekrana yazdýrýr 
	public String twoThousand(){
		DVD obj = new DVD(); 		
		int i=0;
		String alItem="";
		
		if(elemanVarMi()){
			DVD position = head;
			while(position != null){
				if(position.getCikisTarihi() < 2000){
					i++;
					alItem += obj.toString(position) + "\n\n";
				}
				position=position.next;
			}
			if(i==0){
				System.out.println("Opss! Listede 2000 den önce çýkmýþ herhangi bir DVD bulunmamaktadýr");
			}
		}
		return alItem;
	}
	
	//BÝr sanatçýya ait tüm Dvdleri ekrana yazdýrýr
	public String singer(String adSoyad){
		
		DVD position = head;
		String alItem="";
		if(elemanVarMi()){
			int i=0;
			while(position != null){
				if(compareObj(position.getAdSoyad(), adSoyad) == 0){
					i++;
					System.out.println(position.toString(position));
					alItem += position.toString(position) + "\n";
					position=position.next;
					continue;
				}

				position=position.next;
			}
			if(i>0){
				System.out.println("Þarkýcýný toplam " + i + " DVD' si bulundu.");
			}else{
				System.out.println("Þarkýcýya Ait Herhangi Bir DVD Listede Bulunamadý.");
				System.exit(0);
			}
			
		}else{
			System.out.println("Listede Hiç Bir Þarkýcý Yok.");
		}
		
		return alItem;
	}

	//Sanatcinin adýSoyadý ve DVd baþlýðý girildiðinde listeden siler
	public void deleteNodeHeader(String text){
		String baslik="";
		String adSoyad="";
		
		StringTokenizer str = new StringTokenizer(text, ",");
		
		adSoyad = str.nextToken();
		baslik = str.nextToken();
				
		
		DVD position3 = head;
		
		while(position3 != null){
		
		if(compareObj(position3.getAdSoyad(), adSoyad)==0){
			if(compareObj(position3.getBaslik(), baslik) == 0){
				if(position3==head){
					position3=position3.next;
					head=position3;
					break;
				}else{
					if(position3 != tail){
						position3.previous.next=position3.next;
						position3.next.previous=position3.previous;
						position3=position3.next;
						break;
					}else{						
						tail=position3.previous;
						tail.next=null;					
						break;
						
					}
				}
			}
		}
			position3=position3.next;
		}
		
	}
	
	//iki tane string ifdeyi karþýlaþtýrýr integer bir deðer döndürür
	public int compareObj(String ad1, String ad2) {
		return (ad1.compareToIgnoreCase(ad2));
	}

	//iki tane integer deðeri karþýlaþtýrýr
	public boolean equalsTarih(int tarih1, int tarih2) {
		if (tarih1 <= tarih2) {
			return true;
		} else {
			return false;
		}
	}

	//Listede Eleman Varmý Kontrol Eder
	public boolean elemanVarMi() {
		if (head != null) {
			return true;
		} else {
			return false;
		}
	}

	//bilgiler.txt dosyasýdan verileri okur
	public void readFile(){
		
		Scanner fileIn = null;		
	
		try{
			fileIn = new Scanner(new FileInputStream("bilgiler.txt"));
		}catch(FileNotFoundException e){
			System.out.println("Dosya Bulunamadý!");
			System.exit(0);
		}

		int i=0;
		String adSoyad="";
		String baslik="";
		int tarih=0;
		int fiyat=0;
		boolean durum=false;
		
		ArrayList<String>parcaListesi = new ArrayList<String>();
		
		while(fileIn.hasNextLine()){		
			
			String str1 = fileIn.nextLine();
			
			StringTokenizer line1 = new StringTokenizer(str1, ",");
			
			String strToken="";
			
			while(line1.hasMoreTokens()){

			  strToken=line1.nextToken();			
			  
			  		
			  		switch(i){			  		
					case 0:
						adSoyad=strToken;
						i++;
						break;
					case 1:
						baslik=strToken;
						i++;
						break;
					case 2:
						tarih=Integer.parseInt(strToken.trim());
						i++;
						break;
					case 3:
						fiyat=Integer.parseInt(strToken.trim());
						i++;
						break;
					default :						
					  	if(!strToken.endsWith(".")){					  						  			
					  		parcaListesi.add(strToken);
					  		
					  	}else{			  		
					  		parcaListesi.add(strToken);
							add(adSoyad, baslik, tarih, fiyat, parcaListesi);
							parcaListesi = new ArrayList<String>();
							i=0;
					  	}
					  	break;
			  		}					
				}
		  }
		fileIn.close();
	}
	
	//Bir Karekter Katarýný verilen bir delimiter'a göre parçalara ayýrýr
	public void division(String txt1){
		
		int i=0;
		int j=0;
		String adSoyad="";
		String baslik="";
		int tarih=0;
		int fiyat=0;
		
		ArrayList<String>parcaListesi = new ArrayList<String>();
	
			
			String str1 = txt1;
			
			StringTokenizer line1 = new StringTokenizer(str1, ",");
			
			String strToken="";
			
			while(line1.hasMoreTokens()){

			  strToken=line1.nextToken();			
			  
			  		
			  		switch(i){		
					case 0:
						adSoyad=strToken;
						i++;
						break;
					case 1:
						baslik=strToken;
						i++;
						break;
					case 2:
						tarih=Integer.parseInt(strToken.trim());
						i++;
						break;
					case 3:
						fiyat=Integer.parseInt(strToken.trim());
						i++;
						break;
					default :						
					  	if(!strToken.endsWith(".")){					  						  			
					  		parcaListesi.add(strToken);
					  		
					  	}else if(strToken.endsWith(".")){	
					  		
					  			
					  		parcaListesi.add(strToken);
							add(adSoyad, baslik, tarih, fiyat, parcaListesi);
						  	}
					  	break;
			  		}					
				}
		  
	}
	
	//bilgiler.txt Dosyasýna Baðlý Listedeki verileri yazar
	public void writeToFile(){
		
		PrintWriter fileOut = null;
		
		try{
			fileOut = new PrintWriter(new FileOutputStream("bilgiler.txt"));
		}catch(FileNotFoundException e){
			System.out.println("Yazýlacak Dosya Bulunamadý!");
		}
		
		
		DVD position=head;
		DVD obj = new DVD();
		
		while(position != null){
			fileOut.println(position.toString2(position));
			position=position.next;
		}		
		
		fileOut.close();
		fileOut.println("Dosyaya yazdýrma iþlemi baþarýlý oldu");
	}
	
}


