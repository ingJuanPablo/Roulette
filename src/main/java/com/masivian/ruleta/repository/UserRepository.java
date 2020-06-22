package com.masivian.ruleta.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.ruleta.domain.User;

@Repository
public class UserRepository {

	private static final String KEY = "User";
	private RedisTemplate redisTemplate;
	private HashOperations hashOperations;
	
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	public UserRepository(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	/*
	 * Method for save User Object
	 * 
	 * @param user
	 */
	public void save(User user) {
		hashOperations.put(KEY, user.getId(), user);
	}
	
	/*
	 * Method for find all user objects
	 * 
	 * @return list
	 */
	public List findAll() {
		return hashOperations.values(KEY);
	}
	
	/*
	 * Method for find user object by Id
	 * 
	 * @param id
	 * @return user
	 */
	public User findUserById(String id) {
		return (User)hashOperations.get(KEY, id);
	}
	
	/*
	 * Method for update user object
	 * 
	 * @param user
	 */
	public void update(User user) {
		save(user);
	}
	
	/*
	 * Method for drop user object
	 * 
	 * @param id
	 */
	public void delete(String id) {
		hashOperations.delete(KEY, id);
	}
}
