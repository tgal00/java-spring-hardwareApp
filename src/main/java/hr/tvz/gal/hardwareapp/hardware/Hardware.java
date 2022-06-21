package hr.tvz.gal.hardwareapp.hardware;

import hr.tvz.gal.hardwareapp.review.Review;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hardware {


    public enum Type {CPU, GPU, MBO, RAM, STORAGE, OTHER};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String code;
    private double price;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    private int stock;

    @OneToMany(targetEntity = Review.class)

    private List<Review> reviews;

    public Hardware(){}

    public Hardware(long id,String name, String code, double price, Type type, int stockNumber) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.stock = stockNumber;
    }

    public long getId(){return id;}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int numberOnStock) {
        this.stock = numberOnStock;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hardware hardware = (Hardware) o;

        if (id != hardware.id) return false;
        return code != null ? code.equals(hardware.code) : hardware.code == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
