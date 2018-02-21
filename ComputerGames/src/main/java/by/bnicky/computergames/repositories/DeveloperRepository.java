package by.bnicky.computergames.repositories;

import by.bnicky.computergames.entities.Developer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends BaseRepository<Developer, Long> {

    void deleteByDeveloperId(Long id);

    @Query("SELECT t from Developer t WHERE t.developerName = ?1")
    Developer findDeveloperByName(String name);

    @Query(value = "SELECT * FROM developers WHERE developer_location = ?1", nativeQuery = true)
    List<Developer> findDevelopersByCountry(String country);
}