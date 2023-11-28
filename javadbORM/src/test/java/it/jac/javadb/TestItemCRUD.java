package it.jac.javadb;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import it.jac.javadb.dao.ItemDao;
import it.jac.javadb.entity.Item;
import it.jac.javadb.entity.ItemFeature;

public class TestItemCRUD {


	private ItemDao dao = new ItemDao();
	
	@Test
	public void testConnection() {
		
		assertTrue(this.dao.testConnessione());
	}
	
	@Test
	public void testFindItem() {

		Item entity = new Item();
		
		entity.setCode("cod_001");
		entity.setName("item 001");
		entity.setDescription("description 1");
		entity.setLongDescription("descrizione molto lunga dell'item");
		entity.setCreationTime(LocalDateTime.now());
		entity.setCreationUser("test");

		this.dao.save(entity);

		Item item = this.dao.findItemById(entity.getId());
		
		assertNotNull(item);

		this.dao.delete(entity);
	}
	
	@Test
	public void testCreateItem() {
		
		Item entity = new Item();
		
		entity.setCode("cod_001");
		entity.setName("item 001");
		entity.setDescription("description 1");
		entity.setLongDescription("descrizione molto lunga dell'item");
		entity.setCreationTime(LocalDateTime.now());
		entity.setCreationUser("test");

		this.dao.save(entity);
		
		assertTrue(entity.getId() > 0);
		
		assertNotNull(this.dao.findItemById(entity.getId()));
		
		this.dao.delete(entity);
	}
	
	@Test
	public void testUpdateItem() {

		Item entity = new Item();
		
		entity.setCode("cod_001");
		entity.setName("item 001");
		entity.setDescription("description 1");
		entity.setLongDescription("descrizione molto lunga dell'item");
		entity.setCreationTime(LocalDateTime.now());
		entity.setCreationUser("test");

		this.dao.save(entity);

		entity = this.dao.findItemById(entity.getId());
		
		String old = entity.getDescription();
		entity.setDescription("!!!MODIFICATO!!!");
		
		this.dao.update(entity);
		
		Item entity2 = this.dao.findItemById(entity.getId());
		assertTrue("!!!MODIFICATO!!!".equals(entity2.getDescription()));
		
		entity2.setDescription(old);
		this.dao.update(entity2);
		
		this.dao.delete(entity);
	}
	
	@Test
	public void testCodeLength(){
		
		Item entity = new Item();
		
		entity.setCode("cod_001asdfghjkl");
		entity.setName("item 001");
		entity.setDescription("description 1");
		entity.setLongDescription("descrizione molto lunga dell'item");
		entity.setCreationTime(LocalDateTime.now());
		entity.setCreationUser("test");
		
		assertThrows(RuntimeException.class, () -> {
			this.dao.save(entity);
		});
		
	}
	
	
	@Test
	public void testAddFeature() {
		
		Item item = this.dao.findItemById(2);
		
		ItemFeature feature1 = new ItemFeature();
		feature1.setFeatureKey("colore");
		feature1.setFeatureValue("rosso");
		feature1.setCreationTime(LocalDateTime.now());
		feature1.setCreationUser("test-join");
		feature1.setItem(item);
		
		ItemFeature feature2 = new ItemFeature();
		feature2.setFeatureKey("larghezza");
		feature2.setFeatureValue("142cm");
		feature2.setCreationTime(LocalDateTime.now());
		feature2.setCreationUser("test-join");
		feature2.setItem(item);
		
		this.dao.saveFeature(feature1);
		this.dao.saveFeature(feature2);
		
	}
	
	@Test
	public void testReadFeature() {
		
		Item item = this.dao.findItemById(2);
		
		assertTrue(item.getFeatureList().size() > 0);
	}
	
	
}