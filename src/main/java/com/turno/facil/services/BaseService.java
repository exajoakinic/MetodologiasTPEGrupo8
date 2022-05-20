package com.turno.facil.services;

import com.turno.facil.models.BaseEntity;
import com.turno.facil.services.exceptions.NotFoundException;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends BaseEntity> {

    protected JpaRepository<T, Long> repository;
    protected String entityName;

    public BaseService(JpaRepository<T, Long> repository) {
        this.repository = repository;
        this.entityName = GenericTypeResolver.resolveTypeArgument(getClass(), BaseService.class).getSimpleName();
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public T findById(Long id) {
        Optional<T> obj = repository.findById(id);
        return obj.orElseThrow(()-> new NotFoundException(entityName, id));
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T update(T object, Long id) {
        if (repository.existsById(id)) {
            object.setId(id);
            return repository.save(object);
        }
        throw new NotFoundException(entityName, id);
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException(entityName, id);
        }
    }

}
