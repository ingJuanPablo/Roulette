package com.masivian.ruleta.repository;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.ruleta.domain.Roulette;

@Repository
public class RouletteRepository implements RedisRepository{

	private static final String KEY = "Roulette";
	private RedisTemplate<String, Roulette> redisTemplate;
	private HashOperations hashOperations;
	
	public RouletteRepository(RedisTemplate<String, Roulette> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public Map<String, Roulette> findAll() {
		return hashOperations.entries(KEY);
	}

	@Override
	public Roulette findById(String id) {
		return (Roulette)hashOperations.get(KEY, id);
	}

	@Override
	public void save(Roulette roulette) {
		hashOperations.put(KEY, roulette.getId(), roulette);		
	}

	@Override
	public void delete(String id) {
		hashOperations.delete(KEY, id);
	}

	@Override
	public String openRoulette(String id) {
		Roulette roulette = findById(id);
		String mssg = "";
		if(verifyRouletteState(roulette)) {
			roulette.setState("open");
			save(roulette);
			mssg = "Succesfull";
		}else {
			mssg = "Denied";
		}
		
		return mssg;
	}

	@Override
	public String closeRoulette(String id) {
		Roulette roulette = findById(id);
		String mssg = "Closed Succesfull";
		roulette.setState("closed");
		save(roulette);
		
		return mssg;
	}
	
	/*
	 * Method for verify state of roulette
	 * 
	 * @param roulette
	 * @return boolean
	 */
	public boolean verifyRouletteState(Roulette roulette) {
		if(roulette.getState().equals("init")) {
			return true;
		}else {
			return false;
		}
	}

}