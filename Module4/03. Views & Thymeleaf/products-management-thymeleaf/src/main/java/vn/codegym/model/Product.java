package vn.codegym.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product {
    private int id;
    @NotBlank(message = "Tên sản phẩm không được để trống!")
    @Size(min = 2, max = 50, message = "Tên sản phẩm phải từ 2 đến 50 ký tự!")
    private String name;
    @NotNull(message = "Giá sản phẩm không được để trống!")
    @Min(value = 0, message = "Giá sản phẩm phải lớn hơn hoặc bằng 0!")
    private double price;
    @NotBlank(message = "Mô tả sản phẩm không được để trống!")
    private String description;
    @NotBlank(message = "Nhà sản xuất không được để trống!")
    private String manufacturer;

    public Product() {
    }

    public Product(int id, String name, double price, String description, String manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}