package com.masivian.ruleta.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.ruleta.domain.Bet;


@Repository
public class BetRepository{

	private static final String KEY = "Bet";
	private RedisTemplate redisTemplate;
	private HashOperations hashOperations;
	
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	public BetRepository(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	/*
	 * Method for save bet object
	 * 
	 * @param bet
	 * @return mssg
	 */
	public String save(Bet bet) {
		String mssg = "";
		if(validateBet(bet)) {
			hashOperations.put(KEY, bet.getId(), bet);
			mssg = "Succesfull Bet";
		}else {
			mssg = "Denied Bet";
		}
		
		return mssg;
	}
	
	/*
	 * Method for validate bet
	 * 
	 * @param bet
	 * @return boolean
	 */
	private boolean validateBet(Bet bet) {

		if(bet.getAmount() <= 10000 && bet.getNumber() <= 36) {
			return true;
		}else {
			return false;
		}

	}

	/*
	 * Method for find all bet objects
	 * 
	 * @return list
	 */
	public List findAll() {
		return hashOperations.values(KEY);
	}
	
	/*
	 * Method for find bet object by id
	 * 
	 * @param id
	 * @return bet
	 */
	public Bet findById(String id) {
		return (Bet) hashOperations.get(KEY, id);
	}
	
	/*
	 * Method for update bet object
	 * 
	 * @param bet
	 */
	public void update(Bet bet) {
		save(bet);
	}
	
	/*
	 * Method for drop bet object
	 * 
	 * @param id
	 */
	public void delete(String id) {
		hashOperations.delete(KEY, id);
	}
	
}
