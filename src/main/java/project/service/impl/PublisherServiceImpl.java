package project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.entity.Publisher;
import project.repository.PublisherRepository;
import project.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService{
	
	@Autowired
	private PublisherRepository repository;
	
	@Override
	public Publisher read(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}
	
	@Override
	public List<Publisher> read() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	@Override
	public void save(Publisher entity) {
		// TODO Auto-generated method stub
		repository.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}
		
	@Override
	public Publisher readByName(String name) {
		// TODO Auto-generated method stub
		return repository.findByName(name);
	}
	
	@Override
	public List<Publisher> readByCountryOfMainOffice(String countryOfMainOffice) {
		// TODO Auto-generated method stub
		return repository.findByCountryOfMainOffice(countryOfMainOffice);
	}

	@Override
	public void edit(Long id, Publisher entity) {
		Publisher publisher = repository.findById(id).orElseThrow(IllegalArgumentException::new);
		if(entity.getName() != null) {
			publisher.setName(entity.getName());
		}
		if(entity.getCountryOfMainOffice() != null) {
			publisher.setCountryOfMainOffice(entity.getCountryOfMainOffice());
		}
		repository.save(publisher);
	}
	
}
