package project.service;

import project.entity.DeveloperStudio;

public interface DeveloperStudioService extends Service<DeveloperStudio>{
	DeveloperStudio readByName(String name);
	void changePublisherByName(String studioName, String publisherName);
}
