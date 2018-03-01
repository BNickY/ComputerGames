/**
 * Publisher.java
 */
package by.bnicky.computergames.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author Nick Korp
 */
@Entity
@Table(name = "publishers", schema = "public", catalog = "ComputerGames")
public class Publisher implements Serializable{

    private static final long serialVersionUID = -629199332739490727L;
    private Long publisherId;
    private String publisherName;
    private String publisherLocation;
    private String publisherSite;
    private List<Game> publisherGames = new ArrayList<>(0);

    /**
     * Builds a new object of Publisher
     */
    public Publisher(){}


    /**
     * @return the publisher id
     */
    @Id
    @SequenceGenerator(name="publisher_sequence", sequenceName="serial_publishers", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="publisher_sequence")
    @Column(name = "publisher_id", nullable = false)
    public Long getPublisherId() {
        return publisherId;
    }

    /**
     * @param publisherId the publisher id to set
     */
    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    /**
     * @return the publisher name
     */
    @Basic
    @Column(name = "publisher_name", nullable = false)
    public String getPublisherName() {
        return publisherName;
    }

    /**
     * @param publisherName the publisher name to set
     */
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    /**
     * @return the publisher location
     */
    @Basic
    @Column(name = "publisher_location")
    public String getPublisherLocation() {
        return publisherLocation;
    }

    /**
     * @param publisherLocation the publisher location to set
     */
    public void setPublisherLocation(String publisherLocation) {
        this.publisherLocation = publisherLocation;
    }

    /**
     * @return the publisher site
     */
    @Basic
    @Column(name = "publisher_site")
    public String getPublisherSite() {
        return publisherSite;
    }

    /**
     * @param publisherSite the publisher site to set
     */
    public void setPublisherSite(String publisherSite) {
        this.publisherSite = publisherSite;
    }

    /**
     * @return the publisher games
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher", cascade = CascadeType.ALL)
    public List<Game> getPublisherGames() {
        return publisherGames;
    }

    /**
     * @param publisherGames the publisher games to set
     */
    public void setPublisherGames(List<Game> publisherGames) {
        this.publisherGames = publisherGames;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(publisherName, publisher.publisherName);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(publisherName);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Publisher{" +
                "publisherId=" + publisherId +
                ", publisherName='" + publisherName + '\'' +
                ", publisherLocation='" + publisherLocation + '\'' +
                ", publisherSite='" + publisherSite + '\'' +
                '}';
    }
}
