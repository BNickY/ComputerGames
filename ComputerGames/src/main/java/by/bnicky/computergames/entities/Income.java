package by.bnicky.computergames.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "incomes", schema = "public", catalog = "ComputerGames")
public class Income implements Serializable{

    private static final long serialVersionUID = -8718437542819228774L;
    private Long gameId;
    private Long copiesSold;
    private Long income;
    private Game game;

    public Income(){}

    @Id
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters = @Parameter(name="property", value="games"))
    @Column(name = "game_id", nullable = false)
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "copies_sold")
    public Long getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(Long copiesSold) {
        this.copiesSold = copiesSold;
    }

    @Basic
    @Column(name = "income")
    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income1 = (Income) o;
        return Objects.equals(gameId, income1.gameId) &&
                Objects.equals(copiesSold, income1.copiesSold) &&
                Objects.equals(income, income1.income) &&
                Objects.equals(game, income1.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, copiesSold, income, game);
    }

    @Override
    public String toString() {
        return "Income{" +
                "gameId=" + gameId +
                ", copiesSold=" + copiesSold +
                ", income=" + income +
                ", game=" + game +
                '}';
    }
}
