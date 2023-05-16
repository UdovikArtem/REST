package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
	List<Game> findByName(String name);
	List<Game> findByScore(double score);
}
