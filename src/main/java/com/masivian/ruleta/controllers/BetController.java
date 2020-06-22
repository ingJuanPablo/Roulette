package com.masivian.ruleta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.ruleta.domain.Bet;
import com.masivian.ruleta.repository.BetRepository;



@RestController
@CrossOrigin
@RequestMapping("/bets")
public class BetController {

	@Autowired
	private BetRepository betRepository;
	
	@PostMapping
	public String save(@RequestBody Bet bet) {
		String mssg = betRepository.save(bet);
		return mssg;
	}
	
	@GetMapping
	public List list() {
		return betRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Bet getBet(@PathVariable String id) {
		return betRepository.findById(id);
	}
	
	@PutMapping
	public Bet update(@RequestBody Bet bet) {
		betRepository.update(bet);
		return bet;
	}
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable String id) {
		betRepository.delete(id);
		return id;
	}
}
