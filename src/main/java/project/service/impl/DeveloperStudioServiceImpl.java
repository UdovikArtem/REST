package project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.entity.DeveloperStudio;
import project.entity.Publisher;
import project.repository.DeveloperStudioRepository;
import project.repository.PublisherRepository;
import project.service.DeveloperStudioService;

@Service
public class DeveloperStudioServiceImpl implements DeveloperStudioService{

	@Autowired
	private DeveloperStudioRepository studioRepository;
	
	@Autowired
	private PublisherRepository pRepository;
	
	@Override
	public DeveloperStudio read(Long id) {
		// TODO Auto-generated method stub
		return studioRepository.findById(id).get();
	}

	@Override
	public List<DeveloperStudio> read() {
		// TODO Auto-generated method stub
		return studioRepository.findAll();
	}

	@Override
	public void save(DeveloperStudio entity) {
		// TODO Auto-generated method stub
		Publisher publisher = entity.getPublisher();
		Long id = publisher.getId();
		publisher = pRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		entity.setPublisher(publisher);
		publisher.getDeveloperStudios().add(entity);
		pRepository.save(publisher);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		DeveloperStudio studio = studioRepository.findById(id).get();
		Publisher publisher = studio.getPublisher();
		publisher.getDeveloperStudios().remove(studio);
		pRepository.save(publisher);
	}

	@Override
	public DeveloperStudio readByName(String name) {
		// TODO Auto-generated method stub
		return studioRepository.findByName(name);
	}

	@Override
	public void changePublisherByName(String studioName, String publisherName) {
		// TODO Auto-generated method stub
		DeveloperStudio entity = studioRepository.findByName(studioName);
		Publisher publisher = pRepository.findByName(publisherName);
		entity.setPublisher(publisher);
		publisher.getDeveloperStudios().add(entity);
		pRepository.save(publisher);
	}

	@Override
	public void edit(Long id, DeveloperStudio entity) {
		DeveloperStudio studio = studioRepository.findById(id).orElseThrow(IllegalArgumentException::new);	
		if(entity.getPublisher() != null) {
			Long pId = entity.getPublisher().getId();
			if(pId != null) {
				studio.setPublisher(pRepository.findById(pId).orElseThrow(IllegalArgumentException::new));
			}
		}
		if(entity.getName() != null) {
			studio.setName(entity.getName());
		}
		if(entity.getNumberOfDevelopers() != 0) {
			studio.setNumberOfDevelopers(entity.getNumberOfDevelopers());
		}
		pRepository.findById(studio.getPublisher().getId())
		.orElseThrow(IllegalArgumentException::new).getDeveloperStudios().add(studio);
		studioRepository.save(studio);
	}
}
