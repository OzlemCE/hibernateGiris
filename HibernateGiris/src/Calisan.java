

public class Calisan {

  private int id;
  private String ad;
  private String soyad;
  private int maas;
  
  public Calisan() {}	
	public Calisan(String ad, String soyad, int maas)
	{
		this.ad=ad;
		this.soyad=soyad;
		this.maas=maas;		
	}
  
     public int getId() {
    	return id;
     }
     public void setId(int id) {
     	this.id = id;
     }
     public String getAd() {
	    return ad;
     }
     public void setAd(String ad) {
     	this.ad = ad;
     }
     public String getSoyad() {
	    return soyad;
     }
     public void setSoyad(String soyad) {
    	this.soyad = soyad;
     }
     public int getMaas() {
     	return maas;
     }
     public void setMaas(int maas) {
    	this.maas = maas;
     }	
}
