/**
 * Game.java
 */
package by.bnicky.computergames.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nick Korp
 */
@Entity
@Table(name = "games", schema = "public", catalog = "ComputerGames")
public class Game implements Serializable{

    private static final long serialVersionUID = 922491056727582985L;
    private Long gameId;
    private String gameName;
    private Date gameReleaseDate;
    private Integer gameRating;
    private String gameRaitingEsrb;
    private String gameSite;
    private Developer developer;
    private Publisher publisher;
    private Income income;
    private Set<Genre> genres = new HashSet<>(0);

    /**
     * Builds a new object of Game
     */
    public Game(){}

    /**
     * @return the game id
     */
    @Id
    @SequenceGenerator(name="game_sequence", sequenceName="serial_games", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="game_sequence")
    @Column(name = "game_id", nullable = false)
    public Long getGameId() {
        return gameId;
    }

    /**
     * @param gameId the game id to set
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the game name
     */
    @Basic
    @Column(name = "game_name", nullable = false)
    public String getGameName() {
        return gameName;
    }

    /**
     * @param gameName the game name to set
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * @return the game release date
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "game_release_date")
    public Date getGameReleaseDate() {
        return gameReleaseDate;
    }

    /**
     * @param gameReleaseDate the game release date to set
     */
    public void setGameReleaseDate(Date gameReleaseDate) {
        this.gameReleaseDate = gameReleaseDate;
    }

    /**
     * @return the game rating
     */
    @Basic
    @Column(name = "game_rating")
    public Integer getGameRating() {
        return gameRating;
    }

    /**
     * @param gameRating the game rating to set
     */
    public void setGameRating(Integer gameRating) {
        this.gameRating = gameRating;
    }

    /**
     * @return the game rating esrb
     */
    @Basic
    @Column(name = "game_raiting_esrb")
    public String getGameRaitingEsrb() {
        return gameRaitingEsrb;
    }

    /**
     * @param gameRaitingEsrb the game rating esrb to set
     */
    public void setGameRaitingEsrb(String gameRaitingEsrb) {
        this.gameRaitingEsrb = gameRaitingEsrb;
    }

    /**
     * @return the game site
     */
    @Basic
    @Column(name = "game_site")
    public String getGameSite() {
        return gameSite;
    }

    /**
     * @param gameSite the game site to set
     */
    public void setGameSite(String gameSite) {
        this.gameSite = gameSite;
    }

    /**
     * @return the game developer
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="developer_id", nullable = false)
    public Developer getDeveloper() {
        return developer;
    }

    /**
     * @param developer the game developer to set
     */
    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    /**
     * @return the game publisher
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="publisher_id", nullable = false)
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the game publisher to set
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the game income
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "game", cascade = CascadeType.ALL)
    public Income getIncome() {
        return income;
    }

    /**
     * @param income the game income to set
     */
    public void setIncome(Income income) {
        this.income = income;
    }

    /**
     * @return the game genres
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="game_genre", joinColumns={
            @JoinColumn(name="game_id", nullable = false)},
            inverseJoinColumns={@JoinColumn(name="genre_id", nullable = false)})
    public Set<Genre> getGenres() {
        return genres;
    }

    /**
     * @param genres the game genres to set
     */
    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameName, game.gameName) &&
                Objects.equals(gameReleaseDate, game.gameReleaseDate) &&
                Objects.equals(developer, game.developer) &&
                Objects.equals(publisher, game.publisher);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(gameName, gameReleaseDate, developer, publisher);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", gameName='" + gameName + '\'' +
                ", gameReleaseDate=" + gameReleaseDate +
                ", gameRating=" + gameRating +
                ", gameRaitingEsrb='" + gameRaitingEsrb + '\'' +
                ", gameSite='" + gameSite + '\'' +
                ", developer=" + developer +
                ", publisher=" + publisher +
                ", income=" + income +
                '}';
    }
}
