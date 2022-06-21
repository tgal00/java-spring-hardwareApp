package hr.tvz.gal.hardwareapp.hardware;

public class HardwareDTO {

    private String name;
    private double price;
    private String code;
    private Hardware.Type type;
    private double stock;

    public HardwareDTO(String _name, double _price, String _code, Hardware.Type _type, double _stock) {
        this.name = _name;
        this.price = _price;
        this.code = _code;
        this.type = _type;
        this.stock = _stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    public Hardware.Type getType() {
        return type;
    }

    public double getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "HardwareDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", code='" + code + '\'' +
                ", type=" + type +
                ", stock=" + stock +
                '}';
    }
}
