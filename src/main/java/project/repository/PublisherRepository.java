package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{
	Publisher findByName(String name);
	List<Publisher> findByCountryOfMainOffice(String countryOfMainOffice);
}
