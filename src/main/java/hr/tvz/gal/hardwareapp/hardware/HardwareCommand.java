package hr.tvz.gal.hardwareapp.hardware;

import hr.tvz.gal.hardwareapp.validation.EnumName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class HardwareCommand {

    //public enum Type {CPU, GPU, MBO, RAM, STORAGE,OTHER};

    @NotNull(message="Cant be null")
    private long id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "Code must not be empty")
    private String code;

    @NotNull(message = "Price must not be empty")
    @Positive(message = "Price must be larger than zero")
    private double price;

    @NotNull(message = "Stock must not be empty")
    @PositiveOrZero(message = "Stock must be positive")
    private int stock;

    @NotNull(message = "Type must not be empty")
    @EnumName(message = "Wrong enum type", regexp = "CPU|GPU|MBO|RAM|STORAGE|OTHER")
    private Hardware.Type type;

    public HardwareCommand(long id, String name, String code, double price, int stock, Hardware.Type type) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    public long getId(){return id;}
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public Hardware.Type getType() {
        return type;
    }
}
