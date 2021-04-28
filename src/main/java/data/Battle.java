package data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.smartcardio.Card;
import java.util.*;


@Entity
public class Battle {
    @Id
    private String idBattle;
    private String idUserHero1;
    private String loginHero1;
    private String nameHero1;
    private Integer raceidHero1;
    private Integer mannaHero1;
    private Integer currentMannaHero1;
    private Integer hpHero1;
    private Integer maxHpHero1;
    private Integer attackHero1;
    private Integer pointsHero1;
    private Integer moneyHero1;
    private Integer goldHero1;
    private String handHero1;
    private String deckHero1;
    @Transient
    private List<Integer> deckCollectionHero1;
    @Transient
    private List<Integer> handCollectionHero1;
    @Transient
    private ArrayList<CardType> tableCollectionHero1;
    private String tableHero1;
    private String idUserHero2;
    private String loginHero2;
    private String nameHero2;
    private Integer raceIdHero2;
    private Integer mannaHero2;
    private Integer currentMannaHero2;
    private Integer hpHero2;
    private Integer maxHpHero2;
    private Integer attackHero2;
    private Integer pointsHero2;
    private Integer moneyHero2;
    private Integer goldHero2;
    private String handHero2;
    private String deckHero2;
    @Transient
    private List<Integer> deckCollectionHero2;
    @Transient
    private List<Integer> handCollectionHero2;
    @Transient
    private ArrayList<CardType> tableCollectionHero2;
    private String tableHero2;


    public Battle() {}

    public Battle(String idBattle, String idUserHero1, String loginHero1, String nameHero1, Integer raceidHero1, Integer mannaHero1, Integer currentMannaHero1, Integer hpHero1, Integer maxHpHero1, Integer attackHero1, Integer pointsHero1, Integer moneyHero1, Integer goldHero1, String handHero1, String deckHero1, String tableHero1, String idUserHero2, String loginHero2, String nameHero2, Integer raceIdHero2, Integer mannaHero2, Integer currentMannaHero2, Integer hpHero2, Integer maxHpHero2, Integer attackHero2, Integer pointsHero2, Integer moneyHero2, Integer goldHero2, String handHero2, String deckHero2, String tableHero2) {
        this.idBattle = idBattle;
        this.idUserHero1 = idUserHero1;
        this.loginHero1 = loginHero1;
        this.nameHero1 = nameHero1;
        this.raceidHero1 = raceidHero1;
        this.mannaHero1 = mannaHero1;
        this.currentMannaHero1 = currentMannaHero1;
        this.hpHero1 = hpHero1;
        this.maxHpHero1 = maxHpHero1;
        this.attackHero1 = attackHero1;
        this.pointsHero1 = pointsHero1;
        this.moneyHero1 = moneyHero1;
        this.goldHero1 = goldHero1;
        this.handHero1 = handHero1;
        this.deckHero1 = deckHero1;
        this.tableHero1 = tableHero1;
        this.idUserHero2 = idUserHero2;
        this.loginHero2 = loginHero2;
        this.nameHero2 = nameHero2;
        this.raceIdHero2 = raceIdHero2;
        this.mannaHero2 = mannaHero2;
        this.currentMannaHero2 = currentMannaHero2;
        this.hpHero2 = hpHero2;
        this.maxHpHero2 = maxHpHero2;
        this.attackHero2 = attackHero2;
        this.pointsHero2 = pointsHero2;
        this.moneyHero2 = moneyHero2;
        this.goldHero2 = goldHero2;
        this.handHero2 = handHero2;
        this.deckHero2 = deckHero2;
        this.tableHero2 = tableHero2;
    }

    public List<Integer> getDeckCollectionHero1() {
        return deckCollectionHero1;
    }

    public void setDeckCollectionHero1(List<Integer> deckCollectionHero1) {
        this.deckCollectionHero1 = deckCollectionHero1;
    }


    public List<Integer> getDeckCollectionHero2() {
        return deckCollectionHero2;
    }

    public void setDeckCollectionHero2(List<Integer> deckCollectionHero2) {
        this.deckCollectionHero2 = deckCollectionHero2;
    }

    public List<Integer> getHandCollectionHero1() {
        return handCollectionHero1;
    }

    public void setHandCollectionHero1(List<Integer> handCollectionHero1) {
        this.handCollectionHero1 = handCollectionHero1;
    }


    public List<Integer> getHandCollectionHero2() {
        return handCollectionHero2;
    }

    public void setHandCollectionHero2(List<Integer> handCollectionHero2) {
        this.handCollectionHero2 = handCollectionHero2;
    }

    public ArrayList<CardType> getTableCollectionHero1() {
        return tableCollectionHero1;
    }

    public void setTableCollectionHero1(ArrayList<CardType> tableCollectionHero1) {
        this.tableCollectionHero1 = tableCollectionHero1;
    }


    public ArrayList<CardType> getTableCollectionHero2() {
        return tableCollectionHero2;
    }

    public void setTableCollectionHero2(ArrayList<CardType> tableCollectionHero2) {
        this.tableCollectionHero2 = tableCollectionHero2;
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

    public Integer getMannaHero1() {
        return mannaHero1;
    }

    public void setMannaHero1(Integer mannaHero1) {
        this.mannaHero1 = mannaHero1;
    }

    public Integer getCurrentMannaHero1() {
        return currentMannaHero1;
    }

    public void setCurrentMannaHero1(Integer currentMannaHero1) {
        this.currentMannaHero1 = currentMannaHero1;
    }

    public Integer getHpHero1() {
        return hpHero1;
    }

    public void setHpHero1(Integer hpHero1) {
        this.hpHero1 = hpHero1;
    }

    public Integer getMaxHpHero1() {
        return maxHpHero1;
    }

    public void setMaxHpHero1(Integer maxHpHero1) {
        this.maxHpHero1 = maxHpHero1;
    }

    public Integer getAttackHero1() {
        return attackHero1;
    }

    public void setAttackHero1(Integer attackHero1) {
        this.attackHero1 = attackHero1;
    }

    public Integer getPointsHero1() {
        return pointsHero1;
    }

    public void setPointsHero1(Integer pointsHero1) {
        this.pointsHero1 = pointsHero1;
    }

    public Integer getMoneyHero1() {
        return moneyHero1;
    }

    public void setMoneyHero1(Integer moneyHero1) {
        this.moneyHero1 = moneyHero1;
    }

    public Integer getGoldHero1() {
        return goldHero1;
    }

    public void setGoldHero1(Integer goldHero1) {
        this.goldHero1 = goldHero1;
    }

    public String getHandHero1() {
        return handHero1;
    }

    public void setHandHero1(String handHero1) {
        this.handHero1 = handHero1;
    }

    public String getDeckHero1() {
        return deckHero1;
    }

    public void setDeckHero1(String deckHero1) {
        this.deckHero1 = deckHero1;
    }

    public String getTableHero1() {
        return tableHero1;
    }

    public void setTableHero1(String tableHero1) {
        this.tableHero1 = tableHero1;
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

    public Integer getMannaHero2() {
        return mannaHero2;
    }

    public void setMannaHero2(Integer mannaHero2) {
        this.mannaHero2 = mannaHero2;
    }

    public Integer getCurrentMannaHero2() {
        return currentMannaHero2;
    }

    public void setCurrentMannaHero2(Integer currentMannaHero2) {
        this.currentMannaHero2 = currentMannaHero2;
    }

    public Integer getHpHero2() {
        return hpHero2;
    }

    public void setHpHero2(Integer hpHero2) {
        this.hpHero2 = hpHero2;
    }

    public Integer getMaxHpHero2() {
        return maxHpHero2;
    }

    public void setMaxHpHero2(Integer maxHpHero2) {
        this.maxHpHero2 = maxHpHero2;
    }

    public Integer getAttackHero2() {
        return attackHero2;
    }

    public void setAttackHero2(Integer attackHero2) {
        this.attackHero2 = attackHero2;
    }

    public Integer getPointsHero2() {
        return pointsHero2;
    }

    public void setPointsHero2(Integer pointsHero2) {
        this.pointsHero2 = pointsHero2;
    }

    public Integer getMoneyHero2() {
        return moneyHero2;
    }

    public void setMoneyHero2(Integer moneyHero2) {
        this.moneyHero2 = moneyHero2;
    }

    public Integer getGoldHero2() {
        return goldHero2;
    }

    public void setGoldHero2(Integer goldHero2) {
        this.goldHero2 = goldHero2;
    }

    public String getHandHero2() {
        return handHero2;
    }

    public void setHandHero2(String handHero2) {
        this.handHero2 = handHero2;
    }

    public String getDeckHero2() {
        return deckHero2;
    }

    public void setDeckHero2(String deckHero2) {
        this.deckHero2 = deckHero2;
    }

    public String getTableHero2() {
        return tableHero2;
    }

    public void setTableHero2(String tableHero2) {
        this.tableHero2 = tableHero2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battle battle = (Battle) o;
        return Objects.equals(idBattle, battle.idBattle) && Objects.equals(idUserHero1, battle.idUserHero1) && Objects.equals(loginHero1, battle.loginHero1) && Objects.equals(nameHero1, battle.nameHero1) && Objects.equals(raceidHero1, battle.raceidHero1) && Objects.equals(mannaHero1, battle.mannaHero1) && Objects.equals(currentMannaHero1, battle.currentMannaHero1) && Objects.equals(hpHero1, battle.hpHero1) && Objects.equals(maxHpHero1, battle.maxHpHero1) && Objects.equals(attackHero1, battle.attackHero1) && Objects.equals(pointsHero1, battle.pointsHero1) && Objects.equals(moneyHero1, battle.moneyHero1) && Objects.equals(goldHero1, battle.goldHero1) && Objects.equals(handHero1, battle.handHero1) && Objects.equals(deckHero1, battle.deckHero1) && Objects.equals(tableHero1, battle.tableHero1) && Objects.equals(idUserHero2, battle.idUserHero2) && Objects.equals(loginHero2, battle.loginHero2) && Objects.equals(nameHero2, battle.nameHero2) && Objects.equals(raceIdHero2, battle.raceIdHero2) && Objects.equals(mannaHero2, battle.mannaHero2) && Objects.equals(currentMannaHero2, battle.currentMannaHero2) && Objects.equals(hpHero2, battle.hpHero2) && Objects.equals(maxHpHero2, battle.maxHpHero2) && Objects.equals(attackHero2, battle.attackHero2) && Objects.equals(pointsHero2, battle.pointsHero2) && Objects.equals(moneyHero2, battle.moneyHero2) && Objects.equals(goldHero2, battle.goldHero2) && Objects.equals(handHero2, battle.handHero2) && Objects.equals(deckHero2, battle.deckHero2) && Objects.equals(tableHero2, battle.tableHero2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBattle, idUserHero1, loginHero1, nameHero1, raceidHero1, mannaHero1, currentMannaHero1, hpHero1, maxHpHero1, attackHero1, pointsHero1, moneyHero1, goldHero1, handHero1, deckHero1, tableHero1, idUserHero2, loginHero2, nameHero2, raceIdHero2, mannaHero2, currentMannaHero2, hpHero2, maxHpHero2, attackHero2, pointsHero2, moneyHero2, goldHero2, handHero2, deckHero2, tableHero2);
    }

    @Override
    public String toString() {
        return "Battle{" +
                "idBattle='" + idBattle + '\'' +
                ", idUserHero1='" + idUserHero1 + '\'' +
                ", loginHero1='" + loginHero1 + '\'' +
                ", nameHero1='" + nameHero1 + '\'' +
                ", raceidHero1=" + raceidHero1 +
                ", mannaHero1=" + mannaHero1 +
                ", currentMannaHero1=" + currentMannaHero1 +
                ", hpHero1=" + hpHero1 +
                ", maxHpHero1=" + maxHpHero1 +
                ", attackHero1=" + attackHero1 +
                ", pointsHero1=" + pointsHero1 +
                ", moneyHero1=" + moneyHero1 +
                ", goldHero1=" + goldHero1 +
                ", handHero1='" + handHero1 + '\'' +
                ", deckHero1='" + deckHero1 + '\'' +
                ", tableHero1='" + tableHero1 + '\'' +
                ", idUserHero2='" + idUserHero2 + '\'' +
                ", loginHero2='" + loginHero2 + '\'' +
                ", nameHero2='" + nameHero2 + '\'' +
                ", raceIdHero2=" + raceIdHero2 +
                ", mannaHero2=" + mannaHero2 +
                ", currentMannaHero2=" + currentMannaHero2 +
                ", hpHero2=" + hpHero2 +
                ", maxHpHero2=" + maxHpHero2 +
                ", attackHero2=" + attackHero2 +
                ", pointsHero2=" + pointsHero2 +
                ", moneyHero2=" + moneyHero2 +
                ", goldHero2=" + goldHero2 +
                ", handHero2='" + handHero2 + '\'' +
                ", deckHero2='" + deckHero2 + '\'' +
                ", tableHero2='" + tableHero2 + '\'' +
                '}';
    }
}