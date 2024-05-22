package it.jac.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.springboot.entity.Comune;

@Repository
public interface ComuniRepository extends JpaRepository<Comune, Integer>{
}
