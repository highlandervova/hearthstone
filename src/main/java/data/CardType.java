package data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

@Entity
@Table(name = "public.cardtype")
public class CardType {
    @Id
    private Integer id;
    private Integer typecard;
    private Integer subtype;
    private Integer damage;
    private Integer  hp;
    private Integer damageadd;
    private Integer  hpadd;
    @Transient
    private Integer activate;
    @Transient
    private boolean active;



    public CardType() {
    }

    public CardType(Integer id, Integer typecard, Integer subtype, Integer damage, Integer hp, Integer damageadd, Integer hpadd, Integer activate, boolean active) {
        this.id = id;
        this.typecard = typecard;
        this.subtype = subtype;
        this.damage = damage;
        this.hp = hp;
        this.damageadd = damageadd;
        this.hpadd = hpadd;
        this.active = active;
        this.activate = activate; // for hand control
    }

    public Integer getDamageadd() {
        return damageadd;
    }

    public void setDamageadd(Integer damageadd) {
        this.damageadd = damageadd;
    }

    public Integer getHpadd() {
        return hpadd;
    }

    public void setHpadd(Integer hpadd) {
        this.hpadd = hpadd;
    }

    public Integer getActivate() {
        return activate;
    }

    public void setActivate(Integer activate) {
        this.activate = activate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypecard() {
        return typecard;
    }

    public void setTypecard(Integer typecard) {
        this.typecard = typecard;
    }

    public Integer getSubtype() {
        return subtype;
    }

    public void setSubtype(Integer subtype) {
        this.subtype = subtype;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardType cardType = (CardType) o;
        return id.equals(cardType.id) && typecard.equals(cardType.typecard) && subtype.equals(cardType.subtype) && damage.equals(cardType.damage) && hp.equals(cardType.hp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typecard, subtype, damage, hp);
    }

    @Override
    public String toString() {
        return "CardType{" +
                "id=" + id +
                ", typecard=" + typecard +
                ", subtype=" + subtype +
                ", damage=" + damage +
                ", hp=" + hp +
                '}';
    }
}
