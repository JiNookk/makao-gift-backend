package megaptera.kr.jingwook.makaogift.dtos;

public class OrderResultDto {
    private String status;

    public OrderResultDto() {
    }

    public OrderResultDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
