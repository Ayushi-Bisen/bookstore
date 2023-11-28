package com.project.bookstore.idp;

import com.project.bookstore.testutils.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
        Mockito.when(idpService.createUser(new User("", "vinhruc@gmail", "Vineeth R", "7411419248", "pwd")))
                .thenReturn(new User("1234", "vinhruc@gmail", "Vineeth R", "7411419248", "pwd"));

        User request = new User("", "vinhruc@gmail", "Vineeth R", "7411419248", "pwd");
        mockMvc.perform(post("/idp/create-user").content(TestUtils.asJsonString(request)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userid").isString())
                .andExpect(jsonPath("$.username").isString())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.phNo").isString());
    }

    private record UserWithoutUsername(String name, String phNo, String password) { }

    @Test
    void createUserShouldReturnBadRequestWhenUsernameIsMissingInRequest() throws Exception {
        UserWithoutUsername request = new UserWithoutUsername("Vineeth R", "7411419248", "pwd");
        mockMvc.perform(post("/idp/create-user").content(TestUtils.asJsonString(request)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    private record UserWithoutName(String username, String phNo, String password) { }

    @Test
    void createUserShouldReturnBadRequestWhenNameIsMissingInRequest() throws Exception {
        UserWithoutName request = new UserWithoutName("Vineeth R", "7411419248", "pwd");
        mockMvc.perform(post("/idp/create-user").content(TestUtils.asJsonString(request)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private record UserWithoutPhoneNumber(String username, String name, String password) { }

    @Test
    void createUserShouldReturnBadRequestWhenPhoneNumberIsMissingInRequest() throws Exception {
        UserWithoutPhoneNumber request = new UserWithoutPhoneNumber("vin-450", "Vineeth R", "pwd");
        mockMvc.perform(post("/idp/create-user").content(TestUtils.asJsonString(request)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private record UserWithoutPassword(String username, String name, String phNo) { }

    @Test
    void createUserShouldReturnBadRequestWhenPasswordIsMissingInRequest() throws Exception {
        UserWithoutPassword request = new UserWithoutPassword("vin-450", "Vineeth R", "7411419248");
        mockMvc.perform(post("/idp/create-user").content(TestUtils.asJsonString(request)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
