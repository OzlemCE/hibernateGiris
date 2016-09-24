

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
			System.out.println(" SessionFactory nesnesi olu�turulurken hata olu�tu."+e);
			e.printStackTrace();
		}
		 
		 TestCalisan TC= new TestCalisan();
		 
		 while(islem==1 || islem==2 || islem==3 || islem==4)
		 {
			 System.out.println("Yapmak istedi�iniz i�lem t�r�n� se�iniz.");
     		 System.out.println("Ki�i Ekle ->1 , Ki�i G�ncelle ->2, Ki�i Sil ->3, Ki�ileri Listele ->4)");
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
	         System.out.println("�al��anlar ve maa�lar�n�n listesi :");
	         for (Iterator iterator =  calisanlar.iterator(); iterator.hasNext();){
	            Calisan c = (Calisan) iterator.next(); 
	            System.out.println("Ad:" + c.getAd()+" Soyad:"+c.getSoyad()+ "  Maa�:"+c.getMaas());  
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("Ki�iler listelenirken hata olu�tu.");
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
		
	}

	private void CalisanSil() {
		// TODO Auto-generated method stub
		Scanner s= new Scanner(System.in);
		System.out.println("Kayd� silinecek �al��an�n id'sini giriniz :");
		int id=s.nextInt();
		  Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Calisan c =  (Calisan)session.get(Calisan.class, id); 
	         session.delete(c); 
	         tx.commit();
	         System.out.println(id+" numaral� id'ye sahip �al��an�n kayd� silindi.");
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("�al��an kayd� silirken hata olu�tu.");
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }	
	
		
	}

	private void CalisanGuncelle() {
		// TODO Auto-generated method stub
		Scanner s= new Scanner(System.in);
		System.out.println("Maa�� g�ncellenecek �al��an id :");
		int id=s.nextInt();
		System.out.println("�al��an�n yeni maa��n� giriniz:");
		int maas=s.nextInt();
		Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Calisan c =(Calisan)session.get(Calisan.class, id); 
	         c.setMaas( maas );
			 session.update(c); 
	         tx.commit();
	         System.out.println("�al��an maa�� g�ncellendi");
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("�al��an maa�� g�ncellenirken hata olu�tu.");
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
		System.out.println("�al��an Ad� :");
	    adi= s.next();
	    System.out.println("�al��an Soyad�");
	    soyadi=s.next();
	    System.out.println("�al��an Maa��");
	    maasi=s.nextInt();
		 Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         
			Calisan c = new Calisan(adi, soyadi, maasi);
	        session.save(c); 
	         tx.commit();
	         System.out.println("Yeni �al��an eklendi.");
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("Yeni �al��an eklenirken hata olu�tu.");
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		
	}

}
