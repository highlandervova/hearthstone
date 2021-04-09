package data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "public.race")
public class Race implements Serializable {
    @Id
    private String id;
    private String name;
    @Column(name = "avatar")
    private byte[] avatar;
    private String pictype;

    public Race() {

    }


    public Race(String id, String name, byte[] avatar, String pictype) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.pictype = pictype;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public String getPictype() {
        return pictype;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public void setPictype(String pictype) {
        this.pictype = pictype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Objects.equals(id, race.id) && Objects.equals(name, race.name) && Objects.equals(pictype, race.pictype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pictype);
    }

    @Override
    public String toString() {
        return "Race{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pictype='" + pictype + '\'' +
                '}';
    }
}
