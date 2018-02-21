package by.bnicky.computergames.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "publishers", schema = "public", catalog = "ComputerGames")
public class Publisher implements Serializable{

    private static final long serialVersionUID = -629199332739490727L;
    private Long publisherId;
    private String publisherName;
    private String publisherLocation;
    private String publisherSite;
    private List<Game> publisherGames = new ArrayList<>(0);

    public Publisher(){}

    @Id
    @SequenceGenerator(name="publisher_sequence", sequenceName="serial_publishers", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="publisher_sequence")
    @Column(name = "publisher_id", nullable = false)
    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    @Basic
    @Column(name = "publisher_name", nullable = false)
    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Basic
    @Column(name = "publisher_location")
    public String getPublisherLocation() {
        return publisherLocation;
    }

    public void setPublisherLocation(String publisherLocation) {
        this.publisherLocation = publisherLocation;
    }

    @Basic
    @Column(name = "publisher_site")
    public String getPublisherSite() {
        return publisherSite;
    }

    public void setPublisherSite(String publisherSite) {
        this.publisherSite = publisherSite;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher", cascade = CascadeType.ALL)
    public List<Game> getPublisherGames() {
        return publisherGames;
    }

    public void setPublisherGames(List<Game> publisherGames) {
        this.publisherGames = publisherGames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(publisherName, publisher.publisherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisherName);
    }

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
