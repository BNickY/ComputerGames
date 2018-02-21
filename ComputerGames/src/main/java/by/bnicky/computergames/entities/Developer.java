package by.bnicky.computergames.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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

    public Developer(){}

    @Id
    @SequenceGenerator(name="developer_sequence", sequenceName="serial_developers", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="developer_sequence")
    @Column(name = "developer_id", nullable = false)
    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    @Basic
    @Column(name = "developer_name", nullable = false)
    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    @Basic
    @Column(name = "developer_location")
    public String getDeveloperLocation() {
        return developerLocation;
    }

    public void setDeveloperLocation(String developerLocation) {
        this.developerLocation = developerLocation;
    }

    @Basic
    @Column(name = "developer_site")
    public String getDeveloperSite() {
        return developerSite;
    }

    public void setDeveloperSite(String developerSite) {
        this.developerSite = developerSite;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "developer", cascade = CascadeType.ALL)
    public List<Game> getDeveloperGames() {
        return developerGames;
    }

    public void setDeveloperGames(List<Game> developerGames) {
        this.developerGames = developerGames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(developerName, developer.developerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(developerName);
    }

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
