package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SimulazioneEntity;


@Repository
public interface SimulazioneRepository extends JpaRepository<SimulazioneEntity, Integer> {

}
