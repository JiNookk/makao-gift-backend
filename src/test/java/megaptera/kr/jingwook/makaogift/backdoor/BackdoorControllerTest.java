package megaptera.kr.jingwook.makaogift.backdoor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class BackdoorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void setupProductsDB() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/backdoor/setup-products-db?products=3"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("OK")
                ));
    }
}
