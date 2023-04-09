package br.faccat.projeto.projetofaccat;




import br.faccat.projeto.projetofaccat.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class LoginControllerTest {

    private MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void testGetUsers() throws Exception {

        User newUser=new User();
        newUser.setName("tim");
        newUser.setPassword("1234");
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }
    @Test
    public void testSaveUser() throws Exception {
        User newUser=new User();
        newUser.setName("tim");
        newUser.setUsername("timbeck1997");
        newUser.setPassword("1234");

        mockMvc.perform(post("/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(newUser)))
                .andExpect(status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("tim"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("timbeck1997"));
    }


}
