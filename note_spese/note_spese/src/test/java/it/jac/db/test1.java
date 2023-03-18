package it.jac.db;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.jac.db.service.AuthService;

public class test1 {
	//Non dobbiamo fare partire la classe ma la funzione, non con maven build ma Junit
	@Test
	void testIsEmpty() {
		
		String a = "";
		assertTrue(a.isEmpty());
		//Assert = funzioni che lanciano delle eccezioni se non si affermano le condizioni
		//scrivi assert → ctrl + spazio → clicca sull assert che ti serve, clicca su junit.jupiter

	}
	
	@Test //ricordati di mettere @test
	void testLogin() {
		
		String matricola = "2023_MI_0000071_0010";
		
		assertDoesNotThrow(() -> {
			AuthService.getInstance().loginDipendente(matricola);
			});
		
	}
}
