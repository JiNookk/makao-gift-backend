package megaptera.kr.jingwook.makaogift.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("backdoor")
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;

    public BackdoorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("setup-products-db")
    public String setupProductsDB(
            @RequestParam Long products
    ) {
        jdbcTemplate.execute("DELETE FROM product");

        for (long i = 0; i < products; i += 1) {
            long productId = i + 1;

            jdbcTemplate.update("INSERT INTO " +
                            "product(id, description, image_path, manufacturer, name, price)" +
                            "VALUES(?, '테스트용 아이템입니다.', '../resources/test.jpg', '메가테라', '테스트 아이템', 10000)",
                    productId);
        }

        return "OK";
    }
}
