package data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ListFinalBattle {

    @Id
    private String idBattle;
    private String idUserHero1;
    private String loginHero1;
    private String nameHero1;
    private Integer raceidHero1;
    private Integer pointsHero1;
    private Integer goldHero1;
    private String idUserHero2;
    private String loginHero2;
    private String nameHero2;
    private Integer raceIdHero2;
    private Integer pointsHero2;
    private Integer goldHero2;
    private int Win;

    public ListFinalBattle() { }

    public ListFinalBattle(String idBattle, String idUserHero1, String loginHero1, String nameHero1, Integer raceidHero1, Integer pointsHero1, Integer goldHero1, String idUserHero2, String loginHero2, String nameHero2, Integer raceIdHero2, Integer pointsHero2, Integer goldHero2, int win) {
        this.idBattle = idBattle;
        this.idUserHero1 = idUserHero1;
        this.loginHero1 = loginHero1;
        this.nameHero1 = nameHero1;
        this.raceidHero1 = raceidHero1;
        this.pointsHero1 = pointsHero1;
        this.goldHero1 = goldHero1;
        this.idUserHero2 = idUserHero2;
        this.loginHero2 = loginHero2;
        this.nameHero2 = nameHero2;
        this.raceIdHero2 = raceIdHero2;
        this.pointsHero2 = pointsHero2;
        this.goldHero2 = goldHero2;
        Win = win;
    }

    public String getIdBattle() {
        return idBattle;
    }

    public void setIdBattle(String idBattle) {
        this.idBattle = idBattle;
    }

    public String getIdUserHero1() {
        return idUserHero1;
    }

    public void setIdUserHero1(String idUserHero1) {
        this.idUserHero1 = idUserHero1;
    }

    public String getLoginHero1() {
        return loginHero1;
    }

    public void setLoginHero1(String loginHero1) {
        this.loginHero1 = loginHero1;
    }

    public String getNameHero1() {
        return nameHero1;
    }

    public void setNameHero1(String nameHero1) {
        this.nameHero1 = nameHero1;
    }

    public Integer getRaceidHero1() {
        return raceidHero1;
    }

    public void setRaceidHero1(Integer raceidHero1) {
        this.raceidHero1 = raceidHero1;
    }

    public Integer getPointsHero1() {
        return pointsHero1;
    }

    public void setPointsHero1(Integer pointsHero1) {
        this.pointsHero1 = pointsHero1;
    }

    public Integer getGoldHero1() {
        return goldHero1;
    }

    public void setGoldHero1(Integer goldHero1) {
        this.goldHero1 = goldHero1;
    }

    public String getIdUserHero2() {
        return idUserHero2;
    }

    public void setIdUserHero2(String idUserHero2) {
        this.idUserHero2 = idUserHero2;
    }

    public String getLoginHero2() {
        return loginHero2;
    }

    public void setLoginHero2(String loginHero2) {
        this.loginHero2 = loginHero2;
    }

    public String getNameHero2() {
        return nameHero2;
    }

    public void setNameHero2(String nameHero2) {
        this.nameHero2 = nameHero2;
    }

    public Integer getRaceIdHero2() {
        return raceIdHero2;
    }

    public void setRaceIdHero2(Integer raceIdHero2) {
        this.raceIdHero2 = raceIdHero2;
    }

    public Integer getPointsHero2() {
        return pointsHero2;
    }

    public void setPointsHero2(Integer pointsHero2) {
        this.pointsHero2 = pointsHero2;
    }

    public Integer getGoldHero2() {
        return goldHero2;
    }

    public void setGoldHero2(Integer goldHero2) {
        this.goldHero2 = goldHero2;
    }

    public int getWin() {
        return Win;
    }

    public void setWin(int win) {
        Win = win;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListFinalBattle that = (ListFinalBattle) o;
        return Win == that.Win && Objects.equals(idBattle, that.idBattle) && Objects.equals(idUserHero1, that.idUserHero1) && Objects.equals(loginHero1, that.loginHero1) && Objects.equals(nameHero1, that.nameHero1) && Objects.equals(raceidHero1, that.raceidHero1) && Objects.equals(pointsHero1, that.pointsHero1) && Objects.equals(goldHero1, that.goldHero1) && Objects.equals(idUserHero2, that.idUserHero2) && Objects.equals(loginHero2, that.loginHero2) && Objects.equals(nameHero2, that.nameHero2) && Objects.equals(raceIdHero2, that.raceIdHero2) && Objects.equals(pointsHero2, that.pointsHero2) && Objects.equals(goldHero2, that.goldHero2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBattle, idUserHero1, loginHero1, nameHero1, raceidHero1, pointsHero1, goldHero1, idUserHero2, loginHero2, nameHero2, raceIdHero2, pointsHero2, goldHero2, Win);
    }

    @Override
    public String toString() {
        return "ListFinalBattle{" +
                "idBattle='" + idBattle + '\'' +
                ", idUserHero1='" + idUserHero1 + '\'' +
                ", loginHero1='" + loginHero1 + '\'' +
                ", nameHero1='" + nameHero1 + '\'' +
                ", raceidHero1=" + raceidHero1 +
                ", pointsHero1=" + pointsHero1 +
                ", goldHero1=" + goldHero1 +
                ", idUserHero2='" + idUserHero2 + '\'' +
                ", loginHero2='" + loginHero2 + '\'' +
                ", nameHero2='" + nameHero2 + '\'' +
                ", raceIdHero2=" + raceIdHero2 +
                ", pointsHero2=" + pointsHero2 +
                ", goldHero2=" + goldHero2 +
                ", Win=" + Win +
                '}';
    }
}
