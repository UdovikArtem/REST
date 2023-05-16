package project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.entity.DeveloperStudio;
import project.entity.Game;
import project.repository.DeveloperStudioRepository;
import project.repository.GameRepository;
import project.service.GameService;

@Service
public class GameServiceImpl implements GameService{

	@Autowired
	private GameRepository gRepository;
	
	@Autowired
	private DeveloperStudioRepository studioRepository;
	
	@Override
	public Game read(Long id) {
		// TODO Auto-generated method stub
		return gRepository.findById(id).get();
	}

	@Override
	public List<Game> read() {
		// TODO Auto-generated method stub
		return gRepository.findAll();
	}

	@Override
	public void save(Game entity) {
		// TODO Auto-generated method stub
		DeveloperStudio studio = entity.getDeveloperStudio();
		Long id = studio.getId();
		studio = studioRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		entity.setDeveloperStudio(studio);
		studio.getGames().add(entity);
		studioRepository.save(studio);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Game game = gRepository.findById(id).get();
		DeveloperStudio studio = game.getDeveloperStudio();
		studio.getGames().remove(game);
		gRepository.delete(game);
	}

	@Override
	public List<Game> readByName(String name) {
		// TODO Auto-generated method stub
		return gRepository.findByName(name);
	}

	@Override
	public List<Game> readByScore(double score) {
		// TODO Auto-generated method stub
		return gRepository.findByScore(score);
	}
	
	@Override
	public void changeScore(Long id, double score) {
		Game entity = gRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		entity.setScore(score);
		gRepository.save(entity);
	}
	
	@Override
	public void edit(Long id, Game entity) {
		Game game = gRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		if(entity.getDeveloperStudio() != null) {
			Long studioId = entity.getDeveloperStudio().getId();
			if(studioId != null) {
				game.setDeveloperStudio(studioRepository.findById(studioId).orElseThrow(IllegalArgumentException::new));
			}
		}
		if(entity.getName() != null) {
			game.setName(entity.getName());
		}
		if(entity.getScore() != 0) {
			game.setScore(entity.getScore());
		}
		if(entity.getNumberOfSales() != 0) {
			game.setNumberOfSales(entity.getNumberOfSales());
		}
		studioRepository.findById(game.getDeveloperStudio().getId()).orElseThrow(IllegalArgumentException::new).getGames().add(game);
		gRepository.save(game);
	}
}
