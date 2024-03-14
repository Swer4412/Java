package it.jac.springboot.apicomuni.servizi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.jac.springboot.apicomuni.entity.Comune;

@Service
public class ComuniService {
	
	public List<Comune> findAll() {
		List<Comune> result = new ArrayList<>();
		
		result.add(new Comune());
		result.add(new Comune());
		result.add(new Comune());
		result.add(new Comune());
		result.add(new Comune());
		result.add(new Comune());
		
		return result;
	}
	
}
