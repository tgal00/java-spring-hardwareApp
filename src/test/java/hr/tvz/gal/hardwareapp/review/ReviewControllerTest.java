package hr.tvz.gal.hardwareapp.review;

import hr.tvz.gal.hardwareapp.security.domain.User;
import hr.tvz.gal.hardwareapp.security.repository.UserRepository;
import hr.tvz.gal.hardwareapp.security.service.JwtServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtServiceImpl impl;
    @Autowired
    private UserRepository userRep;

    @Test
    void getAllReviews() throws Exception{

        User user = new User();
        if(userRep.findByUsername("admin").isPresent()) {
            user = userRep.findByUsername("admin").get();
        }
        String token = impl.createJwt(user);

        this.mockMvc.perform(
                        get("/review").header(HttpHeaders.AUTHORIZATION,"Bearer "+ token)
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
    void getAllReviewsByHardwareCode() throws Exception{

        String TEST_CODE = "1";
        User user = new User();
        if(userRep.findByUsername("admin").isPresent()) {
            user = userRep.findByUsername("admin").get();
        }
        String token = impl.createJwt(user);

        this.mockMvc.perform(
                        get("/review/?code=" + TEST_CODE).header(HttpHeaders.AUTHORIZATION,"Bearer "+ token)
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}