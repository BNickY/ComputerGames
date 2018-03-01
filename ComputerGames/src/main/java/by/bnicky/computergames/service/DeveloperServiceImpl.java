/**
 *DeveloperServiceImpl.java
 */
package by.bnicky.computergames.service;

import by.bnicky.computergames.entities.Developer;
import by.bnicky.computergames.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * @author Nick Korp
 */
@Service
@Transactional
public class DeveloperServiceImpl {

    private final DeveloperRepository developerRepository;

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }


    /*
     * (non-Javadoc)
     *
     * @see by.bnicky.computergames.repository.DeveloperRepository#deleteByDeveloperId(Long)
     */
    public void deleteDeveloperById(Long id) {
        developerRepository.deleteByDeveloperId(id);
    }

    /**
     * Delete developer by name
     * @param name developer name
     */
    public void deleteDeveloperByName(String name) {
        manager.createNativeQuery("Developer.deleteByName")
                .setParameter("dev_name",name)
                .executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see by.bnicky.computergames.repository.DeveloperRepository#save(Object)
     */
    public Long saveDeveloper(Developer developer) {
        developerRepository.save(developer);
        return developer.getDeveloperId();
    }

    /**
     * Update developer
     * @param id the developer id
     * @param developer the object of {@link Developer}
     */
    public void updateDeveloper(Long id, Developer developer) {
        manager.createNativeQuery("Developer.update")
                .setParameter("dev_id", id)
                .setParameter("dev_name", developer.getDeveloperName())
                .setParameter("dev_location", developer.getDeveloperLocation())
                .setParameter("dev_site", developer.getDeveloperSite())
                .executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see DeveloperRepository#findAll()
     */
    public List<Developer> findAllDevelopers() {
        return developerRepository.findAll();
    }

    /*
     * (non-Javadoc)
     *
     * @see DeveloperRepository#findOne(Serializable)
     */
    public Developer findDeveloperById(Long id) {
        return developerRepository.findOne(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see DeveloperRepository#findDeveloperByName(String)
     */
    public Developer findDeveloperByName(String name) {
        return developerRepository.findDeveloperByName(name);
    }

    /*
     * (non-Javadoc)
     *
     * @see DeveloperRepository#findDevelopersByCountry(String)
     */
    public List<Developer> findDeveloperByCountry(String country) {
        return developerRepository.findDevelopersByCountry(country);
    }
}
