package it.unicam.morpheus.sogniario.boundary;

import it.unicam.morpheus.sogniario.exception.EntityNotFoundException;
import it.unicam.morpheus.sogniario.exception.IdConflictException;
import org.springframework.data.domain.Page;

public interface EntityBoundary<T,I>{

    T getInstance(I id) throws EntityNotFoundException;

    T create(T object) throws EntityNotFoundException, IdConflictException;

    T update(T object) throws EntityNotFoundException, IdConflictException;

    boolean delete(I id);

    boolean exists(I id);

    Page<T> getPage(int page, int size) throws EntityNotFoundException;

}