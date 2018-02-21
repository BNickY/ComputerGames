package by.bnicky.computergames.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "genres", schema = "public", catalog = "ComputerGames")
public class Genre implements Serializable{

    private static final long serialVersionUID = 3450030022138384204L;
    private Long genreId;
    private String genreName;
    private List<Game> gamesByGenre = new ArrayList<>(0);

    public Genre(){}

    @Id
    @SequenceGenerator(name="genre_sequence", sequenceName="serial_genres", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="genre_sequence")
    @Column(name = "genre_id", nullable = false)
    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    @Basic
    @Column(name = "genre_name", nullable = false, length = 50)
    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres", cascade = CascadeType.ALL)
    public List<Game> getGamesByGenre() {
        return gamesByGenre;
    }

    public void setGamesByGenre(List<Game> gamesByGenre) {
        this.gamesByGenre = gamesByGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(genreName, genre.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreName);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreId=" + genreId +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
