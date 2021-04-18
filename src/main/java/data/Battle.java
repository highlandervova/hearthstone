package data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;


@Entity
public class Battle {
    @Id
    private String idBattle;
    private String idUser;
    private String login;
    private String name;
    private Integer raceid;
    private Integer manna;
    private Integer points;
    private Integer money;
    private Integer gold;
    private String hand;
    private String deck;
    private String table;

    public Battle() {}

    public Battle(String idBattle, String idUser, String login, String name, Integer raceid, Integer manna, Integer points, Integer money, Integer gold, String hand, String deck, String table) {
        this.idBattle = idBattle;
        this.idUser = idUser;
        this.login = login;
        this.name = name;
        this.raceid = raceid;
        this.manna = manna;
        this.points = points;
        this.money = money;
        this.gold = gold;
        this.hand = hand;
        this.deck = deck;
        this.table = table;
    }

    public String getIdBattle() {
        return idBattle;
    }

    public void setIdBattle(String idBattle) {
        this.idBattle = idBattle;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRaceid() {
        return raceid;
    }

    public void setRaceid(Integer raceid) {
        this.raceid = raceid;
    }

    public Integer getManna() {
        return manna;
    }

    public void setManna(Integer manna) {
        this.manna = manna;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battle battle = (Battle) o;
        return Objects.equals(idBattle, battle.idBattle) && Objects.equals(idUser, battle.idUser) && Objects.equals(login, battle.login) && Objects.equals(name, battle.name) && Objects.equals(raceid, battle.raceid) && Objects.equals(manna, battle.manna) && Objects.equals(points, battle.points) && Objects.equals(money, battle.money) && Objects.equals(gold, battle.gold) && Objects.equals(hand, battle.hand) && Objects.equals(deck, battle.deck) && Objects.equals(table, battle.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBattle, idUser, login, name, raceid, manna, points, money, gold, hand, deck, table);
    }

    @Override
    public String toString() {
        return "Battle{" +
                "idBattle='" + idBattle + '\'' +
                ", idUser='" + idUser + '\'' +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", raceid=" + raceid +
                ", manna=" + manna +
                ", points=" + points +
                ", money=" + money +
                ", gold=" + gold +
                ", hand='" + hand + '\'' +
                ", deck='" + deck + '\'' +
                ", table='" + table + '\'' +
                '}';
    }
}