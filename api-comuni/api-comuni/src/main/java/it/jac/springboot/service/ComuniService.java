package it.jac.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import it.jac.springboot.dao.ComuniRepository;
import it.jac.springboot.entity.Comune;

@Service
public class ComuniService {

	private ComuniRepository repository;
	
	public ComuniService(ComuniRepository repository) {
		
		this.repository = repository;
	}
	
	public List<Comune> findAll() {

//		se non vengono passati in input dei dati recupero solo i primi 10 elementi
		
		Page<Comune> page = this.repository.findAll(Pageable.ofSize(10).withPage(1));
		
		return page.getContent();		
	}
	
	public Page<Comune> findAll(int size, int pageNumber) {
		
//		passo in input la dimensione della pagina e il numero di pagina 
		
		Pageable pageable = Pageable.ofSize(size).withPage(pageNumber);
		
		return this.repository.findAll(pageable);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_view')")	
	public List<Comune> findAll(int size, int pageNumber, String sortColumn) {

//		passo in input la dimensione della pagina, il numero di pagina e la colonna di ordinamento
		
		PageRequest pageable = PageRequest.of(pageNumber, size, Sort.by(sortColumn));
		
		return this.repository.findAll(pageable).getContent();
	}
	
	public long count() {
		
		return this.repository.count();
	}
}
