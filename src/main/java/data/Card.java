package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "public.card")
public class Card implements Serializable {
    @Id
    private Integer id;
    private String name;
    private String descr;
    private String mana;
    private String pictype;
    @Column(name = "pic")
    private byte[] pic;

    public Card() {
    }

    public Card(Integer id, String name, String descr, String mana, String pictype, byte[] pic) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.mana = mana;
        this.pictype = pictype;
        this.pic = pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getMana() {
        return mana;
    }

    public void setMana(String mana) {
        this.mana = mana;
    }

    public String getPictype() {
        return pictype;
    }

    public void setPictype(String pictype) {
        this.pictype = pictype;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) && Objects.equals(name, card.name) && Objects.equals(descr, card.descr) && Objects.equals(mana, card.mana) && Objects.equals(pictype, card.pictype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, descr, mana, pictype);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", mana='" + mana + '\'' +
                ", pictype='" + pictype + '\'' +
                               '}';
    }
}
