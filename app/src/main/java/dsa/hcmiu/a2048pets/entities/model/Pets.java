package dsa.hcmiu.a2048pets.entities.model;

/**
 * Created by Admin on 3/25/2018.
 */

public class Pets {
    private int value;
    private int pic;
    private int id;
    public Pets(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public void assign(Pets p) {
        value = p.getValue();
        pic = p.getPic();
    }

}
