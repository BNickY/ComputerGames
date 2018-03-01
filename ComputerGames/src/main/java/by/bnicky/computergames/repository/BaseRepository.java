/**
 *BaseRepository.java
 */
package by.bnicky.computergames.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nick Korp
 */
@NoRepositoryBean
interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    /**
     * Delete object
     * @param deleted delete the {@link T} object
     */
    void delete(T deleted);

    /**
     * Find all objects
     * @return array of objects of type {@link T}
     */
    List<T> findAll();

    /**
     * Find object by id
     * @param id id of object
     * @return object of type {@link T}
     */
    T findOne(ID id);

    /**
     * Save object
     * @param persisted object of type {@link T} to save
     * @return Long
     */
    Long save(T persisted);
}
