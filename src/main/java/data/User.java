package data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "public.user")
public class User implements Serializable{
        @Id
        @Column(name = "id")
        private String  id;
        private String  login;
        private String  pass;
        private String  name;
        private String  lastname;
        private String  email;
        private Integer raceid;
        private Integer lvl;
        private Integer points;
        private Integer money;
        private Integer gold;
        private Date creationdate;
        private String deck;
        private Date deckdate;

    public User() { }

    public User(String id, String login, String pass, String name, String lastname, String email, Integer raceid, Integer lvl, Integer points, Integer money, Integer gold, Date creationdate, String deck, Date deckdate) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.raceid = raceid;
        this.lvl = lvl;
        this.points = points;
        this.money = money;
        this.gold = gold;
        this.creationdate = creationdate;
        this.deck = deck;
        this.deckdate = deckdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRaceid() {
        return raceid;
    }

    public void setRaceid(Integer raceid) {
        this.raceid = raceid;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
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

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public Date getDeckdate() {
        return deckdate;
    }

    public void setDeckdate(Date deckdate) {
        this.deckdate = deckdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(pass, user.pass) && Objects.equals(name, user.name) && Objects.equals(lastname, user.lastname) && Objects.equals(email, user.email) && Objects.equals(raceid, user.raceid) && Objects.equals(lvl, user.lvl) && Objects.equals(points, user.points) && Objects.equals(money, user.money) && Objects.equals(gold, user.gold) && Objects.equals(creationdate, user.creationdate) && Objects.equals(deck, user.deck) && Objects.equals(deckdate, user.deckdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, pass, name, lastname, email, raceid, lvl, points, money, gold, creationdate, deck, deckdate);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", raceid=" + raceid +
                ", lvl=" + lvl +
                ", points=" + points +
                ", money=" + money +
                ", gold=" + gold +
                ", creationdate=" + creationdate +
                ", deck='" + deck + '\'' +
                ", deckdate=" + deckdate +
                '}';
    }
}
