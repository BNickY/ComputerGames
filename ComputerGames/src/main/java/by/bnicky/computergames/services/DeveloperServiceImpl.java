package by.bnicky.computergames.services;

import by.bnicky.computergames.entities.Developer;
import by.bnicky.computergames.repositories.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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


    public void deleteDeveloperById(Long id) {
        developerRepository.deleteByDeveloperId(id);
    }

    public void deleteDeveloperByName(String name) {
        manager.createNativeQuery("Developer.deleteByName")
                .setParameter("dev_name",name)
                .executeUpdate();
    }

    public Long saveDeveloper(Developer developer) {
        developerRepository.save(developer);
        return developer.getDeveloperId();
    }

    public void updateDeveloper(Long id, Developer developer) {
        manager.createNativeQuery("Developer.update")
                .setParameter("dev_id", id)
                .setParameter("dev_name", developer.getDeveloperName())
                .setParameter("dev_location", developer.getDeveloperLocation())
                .setParameter("dev_site", developer.getDeveloperSite())
                .executeUpdate();
    }

    public List<Developer> findAllDevelopers() {
        return developerRepository.findAll();
    }

    public Developer findDeveloperById(Long id) {
        return developerRepository.findOne(id);
    }

    public Developer findDeveloperByName(String name) {
        return developerRepository.findDeveloperByName(name);
    }

    public List<Developer> findDeveloperByCountry(String country) {
        return developerRepository.findDevelopersByCountry(country);
    }
}
