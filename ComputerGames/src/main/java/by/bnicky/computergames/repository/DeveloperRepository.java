/**
 *DeveloperRepository.java
 */
package by.bnicky.computergames.repository;

import by.bnicky.computergames.entities.Developer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nick Korp
 */
@Repository
public interface DeveloperRepository extends BaseRepository<Developer, Long> {

    /**
     * Delete developer by id
     * @param id the developer id
     */
    void deleteByDeveloperId(Long id);

    /**
     * Finds and returns the developer by its name
     * @param name the developer name
     * @return {@link Developer}
     */
    @Query("SELECT t from Developer t WHERE t.developerName = ?1")
    Developer findDeveloperByName(String name);

    /**
     * Finds and returns all developers by country
     * @param country the developers country
     * @return List of {@link Developer}
     */
    @Query(value = "SELECT * FROM developers WHERE developer_location = ?1", nativeQuery = true)
    List<Developer> findDevelopersByCountry(String country);
}