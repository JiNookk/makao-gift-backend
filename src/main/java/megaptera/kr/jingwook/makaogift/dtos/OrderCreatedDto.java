package megaptera.kr.jingwook.makaogift.dtos;

public class OrderCreatedDto {
    private Long id;
    private Long orderCount;
    private Long totalPrice;
    private String to;
    private String address;
    private String message;

    public OrderCreatedDto() {
    }

    public OrderCreatedDto(Long id, Long orderCount, Long totalPrice, String to, String address, String message) {
        this.id = id;
        this.orderCount = orderCount;
        this.totalPrice = totalPrice;
        this.to = to;
        this.address = address;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public String getTo() {
        return to;
    }

    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }
}
