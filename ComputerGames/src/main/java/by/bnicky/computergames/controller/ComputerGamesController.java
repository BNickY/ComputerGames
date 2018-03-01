/**
 * ComputerGamesController.java
 */
package by.bnicky.computergames.controller;

import by.bnicky.computergames.entities.Developer;
import by.bnicky.computergames.exceptions.ResourceNoContentExceprion;
import by.bnicky.computergames.exceptions.ResourceNotFoundException;
import by.bnicky.computergames.service.DeveloperServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * @author Nick Korp
 */
@RestController
@RequestMapping(value = "/computergames")
public class ComputerGamesController {

    private final Logger logger = LoggerFactory.getLogger(ComputerGamesController.class);

    private final DeveloperServiceImpl developerService;
    private final MessageSource messageSource;

    @Autowired
    public ComputerGamesController(DeveloperServiceImpl developerService, MessageSource messageSource) {
        this.developerService = developerService;
        this.messageSource = messageSource;
    }

    /**
     * Returns message about successful create
     * @param locale - current locale
     * @param developer - the object of {@link Developer}
     * @return String
     */
    @RequestMapping(value = "/dev", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createDeveloper(Locale locale, @RequestBody Developer developer){
        logger.info(messageSource.getMessage("createDeveloper",null,locale));
        Long id = developerService.saveDeveloper(developer);
        logger.info(messageSource.getMessage("developerCreated",new Long[]{id},locale));
        return ResponseEntity.ok().body(messageSource.getMessage("developerCreated",new Long[]{id},locale));
    }

    /**
     * Returns message about successful update
     * @param locale - current locale
     * @param id - id of the developer that needs to be updated
     * @param developer - the object of {@link Developer}
     * @return String
     */
    @RequestMapping(value = "/dev/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> updateDeveloper(Locale locale, @PathVariable("id") Long id, @RequestBody Developer developer) {
        logger.info(messageSource.getMessage("updateDeveloper",new Long[]{id},locale));

        if(developerService.findDeveloperById(id) == null){
            logger.error(messageSource.getMessage("developerNotFound",null,locale));
            throw new ResourceNotFoundException(messageSource.getMessage("developerNotFound",null,locale));
        }
        developerService.updateDeveloper(id, developer);
        logger.info(messageSource.getMessage("developerUpdated",new Long[]{id},locale));
        return ResponseEntity.ok().body(messageSource.getMessage("developerUpdated",new Long[]{id},locale));
    }

    /**
     * Returns the object of {@link Developer} from database
     * @param locale - current locale
     * @param id - id of the developer
     * @return Developer
     */
    @RequestMapping(value = "/dev/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Developer> getDeveloperById(Locale locale, @PathVariable("id") Long id) {
        logger.info(messageSource.getMessage("getDeveloper",new Long[]{id},locale));
        Developer developer = developerService.findDeveloperById(id);
        if(developer == null){
            logger.error(messageSource.getMessage("developerNotFound",null,locale));
            throw new ResourceNotFoundException(messageSource.getMessage("developerNotFound",null,locale));
        }
        logger.info(messageSource.getMessage("sendDeveloper",new Long[]{id}, locale));
        return ResponseEntity.ok().body(developer);
    }

    /**
     * Returns the object of {@link Developer} from database
     * @param locale - current locale
     * @param name - name of the developer
     * @return Developer
     */
    @RequestMapping(value = "/dev/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Developer> getDeveloperByName(Locale locale, @PathVariable("name") String name) {
        logger.info(messageSource.getMessage("getDeveloper",new String[]{name},locale));
        Developer developer = developerService.findDeveloperByName(name);
        if(developer == null){
            logger.error(messageSource.getMessage("developerNotFound",null,locale));
            throw new ResourceNotFoundException(messageSource.getMessage("developerNotFound",null,locale));
        }
        logger.info(messageSource.getMessage("sendDeveloper",new String[]{name}, locale));
        return ResponseEntity.ok().body(developer);
    }

    /**
     * Returns the array of objects of {@link Developer} from database
     * @param locale - current locale
     * @param country - developers country
     * @return List<Developer>
     */
    @RequestMapping(value = "/dev/country/{country}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Developer>> getDeveloperByCountry(Locale locale,@PathVariable("country") String country) {
        logger.info(messageSource.getMessage("getDeveloper",new String[]{country},locale));
        List<Developer> developers = developerService.findDeveloperByCountry(country);
        if(developers == null){
            logger.error(messageSource.getMessage("developerNotFound",null,locale));
            throw new ResourceNotFoundException(messageSource.getMessage("developerNotFound",null,locale));
        }
        else if(developers.size() == 0) {
            logger.error(messageSource.getMessage("developerNotFound",null,locale));
            throw new ResourceNoContentExceprion(messageSource.getMessage("developerNotFound",null,locale));
        }
        logger.info(messageSource.getMessage("sendDeveloper",new String[]{country}, locale));
        return ResponseEntity.ok().body(developers);
    }

    /**
     * Returns the array of objects of {@link Developer} from database
     * @param locale - current locale
     * @return List<Developer>
     */
    @RequestMapping(value = "/dev/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Developer>> getAllDevelopers(Locale locale){
        logger.info(messageSource.getMessage("getAllDevelopers",null,locale));
        List<Developer> developers = developerService.findAllDevelopers();
        if(developers == null){
            logger.error(messageSource.getMessage("developerNotFound",null,locale));
            throw new ResourceNotFoundException(messageSource.getMessage("developerNotFound",null,locale));
        }
        else if(developers.size() == 0) {
            logger.error(messageSource.getMessage("developerNotFound",null,locale));
            throw new ResourceNoContentExceprion(messageSource.getMessage("developerNotFound",null,locale));
        }
        logger.info(messageSource.getMessage("sendDeveloper",new String[]{"all"}, locale));
        return ResponseEntity.ok().body(developers);
    }

    /**
     * Returns message about successful delete
     * @param locale - current locale
     * @param id - id of the developer
     * @return String
     */
    @RequestMapping(value = "/dev/id/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteDeveloperById(Locale locale, @PathVariable("id") Long id) {
        logger.info(messageSource.getMessage("getDeveloper",new Long[]{id},locale));
        if(developerService.findDeveloperById(id) == null){
            logger.error(messageSource.getMessage("developerNotFound",null,locale));
            throw new ResourceNotFoundException(messageSource.getMessage("developerNotFound",null,locale));
        }
        logger.info(messageSource.getMessage("deleteDeveloper",new Long[]{id},locale));
        developerService.deleteDeveloperById(id);
        return ResponseEntity.ok().body(messageSource.getMessage("deleteDeveloper",new Long[]{id},locale));
    }

    /**
     * Returns message about successful delete
     * @param locale - current locale
     * @param name - name of the developer
     * @return String
     */
    @RequestMapping(value = "/dev/name/{name}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteDeveloperByName(Locale locale, @PathVariable("name") String name) {
        logger.info(messageSource.getMessage("getDeveloper",new String[]{name},locale));
        if(developerService.findDeveloperByName(name) == null){
            logger.error(messageSource.getMessage("developerNotFound",null,locale));
            throw new ResourceNotFoundException(messageSource.getMessage("developerNotFound",null,locale));
        }
        logger.info(messageSource.getMessage("deleteDeveloper",new String[]{name},locale));
        developerService.deleteDeveloperByName(name);
        return ResponseEntity.ok().body(messageSource.getMessage("deleteDeveloper",new String[]{name},locale));
    }
}