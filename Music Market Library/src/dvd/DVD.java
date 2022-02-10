package dvd;

import java.util.*;


//DVD elemanlar� bu S�n�fta birbirine ba�lan�yor
public class DVD {
	
	private String adSoyad;
	private String baslik;
	private ArrayList<String>sarkiAdlari;
	private int cikisTarihi;
	private int fiyati;
	DVD next;
	DVD previous;
//Parametresiz Constructur	
	public DVD(){
		this.adSoyad=null;
		this.baslik=null;
		this.sarkiAdlari=null;
		this.cikisTarihi=0;
		this.fiyati=0;
		this.previous=null;
		this.next=null;
	}
// Parametreli Constructur
	public DVD(String adSoyad, String baslik, int cikisTarihi, int fiyati, ArrayList<String> sarkiAdlari){
		
		this.adSoyad=adSoyad;
		this.baslik=baslik;
		this.cikisTarihi=cikisTarihi;
		this.fiyati=fiyati;
		this.sarkiAdlari=sarkiAdlari;
		this.previous=null;
		this.next=null;
		
	}
	
// DVd yeait bilgileri de�i�tirmek yada ekrana basmak i�in Get ve Set Metotlar�
	
	public String getAdSoyad() {
		return adSoyad;
	}

	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}

	public String getBaslik(){
		return baslik;
	}
	
	public void setBaslik(String baslik){
		this.baslik = baslik;
	}
	
	public ArrayList<String> getSarkiAdlari() {
		return sarkiAdlari;
	}

	public void setSarkiAklari(ArrayList<String> sarkiAdlari) {
		this.sarkiAdlari = sarkiAdlari;
	}

	public int getCikisTarihi() {
		return cikisTarihi;
	}

	public void setCikisTarihi(int cikisTarihi) {
		this.cikisTarihi = cikisTarihi;
	}

	public int getFiyati() {
		return fiyati;
	}

	public void setFiyati(int fiyati) {
		this.fiyati = fiyati;
	}

	public DVD getNext() {
		return next;
	}

	public void setNext(DVD next) {
		this.next = next;
	}

	public DVD getPrevious() {
		return previous;
	}

	public void setPrevious(DVD previous) {
		this.previous = previous;
	}
	
	
	public String toString(DVD obj){
		Node list = new Node();
		return ("Ad�Soyad� : " + obj.adSoyad 
				+ "\nDVD' nin Ba�l��� : " + obj.baslik 
				+ "\nDVD' nin ��k�� Tarihi : " + obj.cikisTarihi
				+ "\nDVD' nin Fiyat� : " + obj.fiyati 
				+ "\nDVD' ye Ait �ark�lar : " + list.arrayListPress(obj));
	}
	public String toString2(DVD obj){
		Node list = new Node();
		return (obj.adSoyad 
				+ ",\n" + obj.baslik 
				+ ",\n" + obj.cikisTarihi
				+ ",\n" + obj.fiyati 
				+ ",\n" + list.arrayListPress(obj));
	}
	
}
