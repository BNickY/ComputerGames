package by.bnicky.computergames.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    public Game(){}

    @Id
    @SequenceGenerator(name="game_sequence", sequenceName="serial_games", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="game_sequence")
    @Column(name = "game_id", nullable = false)
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "game_name", nullable = false)
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "game_release_date")
    public Date getGameReleaseDate() {
        return gameReleaseDate;
    }

    public void setGameReleaseDate(Date gameReleaseDate) {
        this.gameReleaseDate = gameReleaseDate;
    }

    @Basic
    @Column(name = "game_rating")
    public Integer getGameRating() {
        return gameRating;
    }

    public void setGameRating(Integer gameRating) {
        this.gameRating = gameRating;
    }

    @Basic
    @Column(name = "game_raiting_esrb")
    public String getGameRaitingEsrb() {
        return gameRaitingEsrb;
    }

    public void setGameRaitingEsrb(String gameRaitingEsrb) {
        this.gameRaitingEsrb = gameRaitingEsrb;
    }

    @Basic
    @Column(name = "game_site")
    public String getGameSite() {
        return gameSite;
    }

    public void setGameSite(String gameSite) {
        this.gameSite = gameSite;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="developer_id", nullable = false)
    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="publisher_id", nullable = false)
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "game", cascade = CascadeType.ALL)
    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="game_genre", joinColumns={
            @JoinColumn(name="game_id", nullable = false)},
            inverseJoinColumns={@JoinColumn(name="genre_id", nullable = false)})
    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(gameName, gameReleaseDate, developer, publisher);
    }

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
