package megaptera.kr.jingwook.makaogift.dtos;

public class OrderDto {
    private Long productId;
    private Long orderCount;
    private Long totalPrice;
    private String to;
    private String address;
    private String message;

    public OrderDto() {
    }

    public OrderDto(Long productId, Long orderCount, Long totalPrice, String to, String address, String message) {
        this.productId = productId;
        this.orderCount = orderCount;
        this.totalPrice = totalPrice;
        this.to = to;
        this.address = address;
        this.message = message;
    }

    public Long getProductId() {
        return productId;
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
