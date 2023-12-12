package it.jac.mvc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.mvc.dao.ItemDao;
import it.jac.mvc.entity.Item;

public class ItemService {

	private static final Logger log = LogManager.getLogger(ItemService.class);
			
	private ItemDao dao = new ItemDao();

	public Item findItemById(int id) {
		
		Item result = dao.findItemById(id);
		
		return result;
	}

	public List<Item> findAll() {
		return dao.findAll();
	}

	public void saveItem(Item item) {
		
		item.setValidFrom(LocalDate.now());
		item.setValidTo(LocalDate.now().plusDays(100));
		
		item.setCreationUser("system");
		item.setCreationTime(LocalDateTime.now());
		
		dao.save(item);
	}

	public void updateItem(Item item) {

		item.setUpdateUser("system");
		//Non serve aggiornarlo perchè è versionato da hibernate
//		item.setUpdateTime(LocalDateTime.now()); 
		
		dao.update(item);
	}
	
	public void deleteItem(Item item) {
		
		dao.delete(item);
	}

}
