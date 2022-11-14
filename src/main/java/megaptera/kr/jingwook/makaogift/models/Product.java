package megaptera.kr.jingwook.makaogift.models;

import megaptera.kr.jingwook.makaogift.dtos.ProductDto;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private Name name;

    private String manufacturer;

    private String description;

    private String imagePath;

    private Long pieces;

    private Long price;

    public Product() {
    }

    public Product(Long id, Name name, String manufacturer, String description, String imagePath, Long pieces, Long price) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
        this.imagePath = imagePath;
        this.pieces = pieces;
        this.price = price;
    }

    public static Product fake(Name name) {
        return new Product(1L, name, "메가테라", "테스트용 아이템", "../resources/test.jpg", 2L, 10_000L);
    }

    public static Product fake(String name) {
        return Product.fake(new Name(name));
    }

    public Long id() {
        return id;
    }

    @Embedded
    public Name name() {
        return name;
    }

    public String manufacturer() {
        return manufacturer;
    }

    public String description() {
        return description;
    }

    public String imagePath() {
        return imagePath;
    }

    public Long pieces() {
        return pieces;
    }

    public Long price() {
        return price;
    }

    public ProductDto toDto() {
        return new ProductDto(id, name.value(), manufacturer, description, imagePath, pieces, price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass().equals(Product.class) &&
                this.id.equals(((Product) other).id);
    }
}
