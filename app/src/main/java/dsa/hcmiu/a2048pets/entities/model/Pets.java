package dsa.hcmiu.a2048pets.entities.model;

/**
 * Created by Admin on 3/25/2018.
 */

public class Pets implements Pet {

    private int id;
    private int pic;
    private static int score = 0;

    public Pets(int id) {
        super();
        this.id = id;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public void assign(Pets p) {
        id = p.getId();
        pic = p.getPic();
    }

}
