package hr.tvz.gal.hardwareapp.hardware;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.gal.hardwareapp.security.domain.User;
import hr.tvz.gal.hardwareapp.security.repository.UserRepository;
import hr.tvz.gal.hardwareapp.security.service.JwtServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//@SpringBootTest

@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})

class HardwareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtServiceImpl impl;
    @Autowired
    private UserRepository userRep;
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void getAllHardware() throws  Exception{

        User user = new User();
        if(userRep.findByUsername("admin").isPresent()) {
            user = userRep.findByUsername("admin").get();
        }
        String token = impl.createJwt(user);

        this.mockMvc.perform(
                        get("/hardware").header(HttpHeaders.AUTHORIZATION,"Bearer "+ token)
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



    @Test
    void getHardwareByCode() throws Exception {
        String TEST_CODE = "1";

        this.mockMvc.perform(
                        get("/hardware/" + TEST_CODE)
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(TEST_CODE));

    }

    @Test
    void save() throws Exception {
        long TEST_ID = 4;
        String TEST_NAME = "i7 8500k";
        String TEST_CODE = "4";
        Double TEST_PRICE = 1999.99;
        int TEST_STOCK = 120;
        Hardware.Type TEST_TYPE = Hardware.Type.CPU;

        HardwareCommand hardwareCommand = new HardwareCommand(
                TEST_ID,
                TEST_NAME,
                TEST_CODE,
                TEST_PRICE,
                TEST_STOCK,
                TEST_TYPE
        );
        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(TEST_CODE))
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.price").value(TEST_PRICE))
                .andExpect(jsonPath("$.stock").value(TEST_STOCK))
                .andExpect(jsonPath("$.type").value(TEST_TYPE.name()));
    }






    @Test
    void update() throws Exception{
        long TEST_ID = 2;
        String TEST_NAME = "";
        String TEST_CODE = "4";
        Double TEST_PRICE = 1999.99;
        int TEST_STOCK = 120;
        Hardware.Type TEST_TYPE = Hardware.Type.CPU;

        HardwareCommand hardwareCommand = new HardwareCommand(
                TEST_ID,
                TEST_NAME,
                TEST_CODE,
                TEST_PRICE,
                TEST_STOCK,
                TEST_TYPE
        );
        this.mockMvc.perform(
                        put("/hardware/" + TEST_CODE)
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }
}