package com.project.bookstore.idp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bookstore.testutils.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IdpController.class)
@AutoConfigureMockMvc
public class IDPControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private IdpService idpService;

    @Test
    void testCreateUser() throws Exception {
        Mockito.when(idpService.createUser(new User("", "vinhruc@gmail", "Vineeth R", "7411419248")))
                .thenReturn(new User("1234", "vinhruc@gmail", "Vineeth R", "7411419248"));

        User request = new User("", "vinhruc@gmail", "Vineeth R", "7411419248");
        mockMvc.perform(post("/idp/create-user").content(TestUtils.asJsonString(request)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userid").isString())
                .andExpect(jsonPath("$.username").isString())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.phNo").isString());
    }
}
