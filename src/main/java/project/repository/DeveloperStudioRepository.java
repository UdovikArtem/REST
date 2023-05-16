package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.entity.DeveloperStudio;

@Repository
public interface DeveloperStudioRepository extends JpaRepository<DeveloperStudio, Long>{
	DeveloperStudio findByName(String name);
}
