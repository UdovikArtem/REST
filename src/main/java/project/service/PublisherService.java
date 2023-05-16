package project.service;

import java.util.List;

import project.entity.Publisher;

public interface PublisherService extends Service<Publisher>{
	Publisher readByName(String name);
	List<Publisher> readByCountryOfMainOffice(String countryOfMainOffice);
}
