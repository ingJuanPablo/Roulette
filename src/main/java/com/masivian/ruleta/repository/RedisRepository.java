package com.masivian.ruleta.repository;

import java.util.Map;

import com.masivian.ruleta.domain.Roulette;

public interface RedisRepository {

	Map<String, Roulette> findAll();
	Roulette findById(String id);
	void save(Roulette roulette);
	void delete(String id);
	String openRoulette(String id);
	String closeRoulette(String id);
	
}