package it.jac.javadb.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.jac.javadb.entity.Item;
import it.jac.javadb.entity.ItemFeature;
import it.jac.javadb.util.HibernateUtil;
import jakarta.persistence.Query;

public class ItemDao {

	private static final Logger log = LogManager.getLogger(ItemDao.class);

	public boolean testConnessione() {
		
		log.debug("try to open session");

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			return true;
			
		} catch(Exception e) {
			
			log.error("Error opening a new Session", e);
			return false;
		}
		
	}

	public Item findItemById(int id) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			//find(Tipologia, cosa)
			return session.find(Item.class, id);
		}
	}

	public List<Item> findAll() {

		log.debug("try to find all entities");
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			//La query è scritta in hql, hibernate query language
			//Viene specificata la class Item e non la tabella Item, perchè dentro item.java, Item 
			//è stato definito nelle regole di hibernate (guarda il file che capisci)
			
			Query query = session.createQuery("from Item", Item.class); 
	
			List<Item> list = query.getResultList();
			
			log.debug("found [" + list.size() + "] entities");
			
			return list;
		}
	}
	
	public List<Item> findLimitResults(int firstIndex, int pageSize) {
		
		log.debug("try to find subset [" + firstIndex + ", "+ pageSize + "]");
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			Query query = session.createQuery("from Item", Item.class);
	
			query.setFirstResult(firstIndex);
			query.setMaxResults(pageSize);
			
			List<Item> list = query.getResultList();
			
			log.debug("found [" + list.size() + "] entities");
			
			return list;
		}

	}

	public void save(Item item) {

		log.debug("try to save item " + item);
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			Transaction tx = session.beginTransaction();

			session.persist(item); //Ora il dato si trova sul database ma se non faccio commit, viene eliminato
				
			item.setLongDescription("descr modificata"); //Modifico l'oggetto durante la transazione aperta
			item.setName(item.getName() + " test");
			
			//hibernate ha capito che l'oggetto è stato modificato, la seguente istruzione serve per aggiornare
			tx.commit(); //Confermo che voglio il dato sul database
			log.debug("item saved");
				
		}
	}

	public void update(Item item) {

		log.debug("try to update item " + item);
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			Transaction tx = session.beginTransaction();
				
			session.merge(item); //merge == update
			
			tx.commit();
			log.debug("item updated");
				
		}
	}

	public void delete(Item item) {

		log.debug("try to delete item " + item);
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			Transaction tx = session.beginTransaction();

			session.remove(item);
			
			tx.commit();
			
			log.debug("item deleted");
				
		}		
	}
	
	public void saveFeature(ItemFeature feature) {

		log.debug("try to save feature " + feature);
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			Transaction tx = session.beginTransaction();

			session.persist(feature);
			
			tx.commit(); 
			log.debug("feature saved");
			
		}
	}

}


