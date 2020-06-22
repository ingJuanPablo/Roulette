package com.masivian.ruleta.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.ruleta.domain.Roulette;
import com.masivian.ruleta.repository.BetRepository;
import com.masivian.ruleta.repository.RouletteRepository;

@RestController
public class RouletteController {
	
	@Autowired
	private RouletteRepository rouletteRepository;
	
	@Autowired
	private BetRepository betRepository;


	@RequestMapping(value = "/roulette", method = RequestMethod.GET)
	public Map<String, Roulette> findAll(){
		return rouletteRepository.findAll();
	}
	
	@RequestMapping(value = "/roulette/{id}", method = RequestMethod.GET)
	public Roulette findById(@PathVariable String id) {
		 return rouletteRepository.findById(id);
	}
	
	@RequestMapping(value = "/roulette", method = RequestMethod.POST)
	public String createRoulette(@RequestBody Roulette roulette) {
		rouletteRepository.save(roulette);
		return roulette.getId();
	}
	
	@RequestMapping(value = "/roulette/{id}", method = RequestMethod.POST)
	public void deleteRoulette(@PathVariable String id) {
		rouletteRepository.delete(id);
	}
	
	@RequestMapping(value = "/roulette/openRoulette/{id}", method = RequestMethod.POST)
	public String openRoulette(@PathVariable String id) {
		String mssg = rouletteRepository.openRoulette(id);
		return mssg;
	}
	
	@RequestMapping(value = "/roulette/closeRoulette/{id}", method = RequestMethod.POST)
	public List closeRoulette(@PathVariable String id) {
		String mssg = rouletteRepository.closeRoulette(id);
		return betRepository.findAll();
	}
}
