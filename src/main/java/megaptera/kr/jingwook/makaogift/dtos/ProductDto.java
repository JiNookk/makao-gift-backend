package megaptera.kr.jingwook.makaogift.dtos;

public class ProductDto {
    private Long id;
    private String name;
    private String manufacturer;
    private String description;
    private String imagePath;
    private Long price;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String manufacturer, String description, String imagePath, Long price) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Long getPrice() {
        return price;
    }
}
