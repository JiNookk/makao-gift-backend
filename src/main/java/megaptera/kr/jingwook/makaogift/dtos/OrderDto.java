package megaptera.kr.jingwook.makaogift.dtos;

public class OrderDto {
    private Long id;
    private String imagePath;
    private String manufacturer;
    private String name;
    private String receiver;

    public OrderDto() {
    }

    public OrderDto(Long id, String imagePath, String manufacturer, String name, String receiver) {
        this.id = id;
        this.imagePath = imagePath;
        this.manufacturer = manufacturer;
        this.name = name;
        this.receiver = receiver;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }

    public String getReceiver() {
        return receiver;
    }

    public Long getId() {
        return id;
    }
}
