package project.service;

import java.util.List;

import project.entity.AbstractEntity;

public interface Service<T extends AbstractEntity> {

	T read(Long id);

	List<T> read();

	void save(T entity);

	void delete(Long id);

	void edit(Long id, T entity);
}
