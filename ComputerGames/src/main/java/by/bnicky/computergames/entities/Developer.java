/**
 * Developer.java
 */
package by.bnicky.computergames.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author Nick Korp
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = "Developer.deleteByName",
        query="DELETE FROM developers WHERE  developer_name = :dev_name",
        resultClass = Developer.class),

        @NamedNativeQuery(name = "Developer.update",
        query="UPDATE developers SET  developer_name = :dev_name, developer_location = :dev_location" +
                ", developer_site = :dev_site WHERE developer_id = :dev_id",
        resultClass = Developer.class)
    }
)
@Table(name = "developers", schema = "public", catalog = "ComputerGames")
public class Developer implements Serializable{

    private static final long serialVersionUID = -7938455371488402380L;
    private Long developerId;
    private String developerName;
    private String developerLocation;
    private String developerSite;
    private List<Game> developerGames = new ArrayList<>(0);

    /**
     * Builds a new object of Developer
     */
    public Developer(){}

    /**
     * @return the developer id
     */
    @Id
    @SequenceGenerator(name="developer_sequence", sequenceName="serial_developers", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="developer_sequence")
    @Column(name = "developer_id", nullable = false)
    public Long getDeveloperId() {
        return developerId;
    }

    /**
     * @param developerId the developer id to set
     */
    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    /**
     * @return the developer name
     */
    @Basic
    @Column(name = "developer_name", nullable = false)
    public String getDeveloperName() {
        return developerName;
    }

    /**
     * @param developerName the developer name to set
     */
    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    /**
     * @return the developer location
     */
    @Basic
    @Column(name = "developer_location")
    public String getDeveloperLocation() {
        return developerLocation;
    }

    /**
     * @param developerLocation the developer location to set
     */
    public void setDeveloperLocation(String developerLocation) {
        this.developerLocation = developerLocation;
    }

    /**
     * @return the developer site
     */
    @Basic
    @Column(name = "developer_site")
    public String getDeveloperSite() {
        return developerSite;
    }

    /**
     * @param developerSite the developer site to set
     */
    public void setDeveloperSite(String developerSite) {
        this.developerSite = developerSite;
    }

    /**
     * @return the developer games
     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "developer", cascade = CascadeType.ALL)
    public List<Game> getDeveloperGames() {
        return developerGames;
    }

    /**
     * @param developerGames the developer games to set
     */
    public void setDeveloperGames(List<Game> developerGames) {
        this.developerGames = developerGames;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(developerName, developer.developerName);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(developerName);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Developer{" +
                "developerId=" + developerId +
                ", developerName='" + developerName + '\'' +
                ", developerLocation='" + developerLocation + '\'' +
                ", developerSite='" + developerSite +
                '}';
    }
}
