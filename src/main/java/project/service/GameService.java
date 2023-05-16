package project.service;

import java.util.List;

import project.entity.Game;

public interface GameService extends Service<Game>{
	List<Game> readByName(String name);
	List<Game> readByScore(double score);
	void changeScore(Long id, double score);
}
