package dsa.hcmiu.a2048pets.entities.model;

public class ShopItem {

    private String name;
    private long id;
    private int picture;
    private long price;
    private boolean purchase;

    public ShopItem(String name, long id, int picture, long price, boolean purchase) {
        this.name = name;
        this.id = id;
        this.picture = picture;
        this.price = price;
        this.purchase = purchase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isPurchase() {
        return purchase;
    }

    public void setPurchase(boolean purchase) {
        this.purchase = purchase;
    }
}
