

import java.util.Scanner;
import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class TestCalisan
{
	private static SessionFactory factory;
	static int islem=1;
	
	public static void main(String [] args)
	{
		 try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println(" SessionFactory nesnesi oluþturulurken hata oluþtu."+e);
			e.printStackTrace();
		}
		 
		 TestCalisan TC= new TestCalisan();
		 
		 while(islem==1 || islem==2 || islem==3 || islem==4)
		 {
			 System.out.println("Yapmak istediðiniz iþlem türünü seçiniz.");
     		 System.out.println("Kiþi Ekle ->1 , Kiþi Güncelle ->2, Kiþi Sil ->3, Kiþileri Listele ->4)");
			 Scanner s= new Scanner(System.in);
			 islem=s.nextInt();
		 if(islem==1)
		 {
			 TC.CalisanEkle();
		 }
		 else if(islem==2)
		 {
			 TC.CalisanGuncelle();
		 }
		 else if(islem==3)
		 {
			TC.CalisanSil(); 
		 }
		 else if(islem ==4)
		 {
			 TC.CalisanListele();
		 }
		 }
		 
	}

	private void CalisanListele() {
		// TODO Auto-generated method stub
		  Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List calisanlar = session.createQuery("FROM Calisan").list(); 
	         System.out.println("Çalýþanlar ve maaþlarýnýn listesi :");
	         for (Iterator iterator =  calisanlar.iterator(); iterator.hasNext();){
	            Calisan c = (Calisan) iterator.next(); 
	            System.out.println("Ad:" + c.getAd()+" Soyad:"+c.getSoyad()+ "  Maaþ:"+c.getMaas());  
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("Kiþiler listelenirken hata oluþtu.");
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		
	}

	private void CalisanSil() {
		// TODO Auto-generated method stub
		Scanner s= new Scanner(System.in);
		System.out.println("Kaydý silinecek çalýþanýn id'sini giriniz :");
		int id=s.nextInt();
		  Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Calisan c =  (Calisan)session.get(Calisan.class, id); 
	         session.delete(c); 
	         tx.commit();
	         System.out.println(id+" numaralý id'ye sahip çalýþanýn kaydý silindi.");
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("Çalýþan kaydý silirken hata oluþtu.");
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }	
	
		
	}

	private void CalisanGuncelle() {
		// TODO Auto-generated method stub
		Scanner s= new Scanner(System.in);
		System.out.println("Maaþý güncellenecek çalýþan id :");
		int id=s.nextInt();
		System.out.println("Çalýþanýn yeni maaþýný giriniz:");
		int maas=s.nextInt();
		Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Calisan c =(Calisan)session.get(Calisan.class, id); 
	         c.setMaas( maas );
			 session.update(c); 
	         tx.commit();
	         System.out.println("Çalýþan maaþý güncellendi");
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("Çalýþan maaþý güncellenirken hata oluþtu.");
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
	}

	public void CalisanEkle() {
		// TODO Auto-generated method stub
		String adi;
	    String soyadi;
		int maasi;
		Scanner s= new Scanner(System.in);
		System.out.println("Çalýþan Adý :");
	    adi= s.next();
	    System.out.println("Çalýþan Soyadý");
	    soyadi=s.next();
	    System.out.println("Çalýþan Maaþý");
	    maasi=s.nextInt();
		 Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         
			Calisan c = new Calisan(adi, soyadi, maasi);
	        session.save(c); 
	         tx.commit();
	         System.out.println("Yeni çalýþan eklendi.");
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("Yeni çalýþan eklenirken hata oluþtu.");
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
	}

}
