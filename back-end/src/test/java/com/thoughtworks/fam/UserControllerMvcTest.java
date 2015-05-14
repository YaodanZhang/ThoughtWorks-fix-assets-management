package com.thoughtworks.fam;

import com.thoughtworks.fam.controller.AuthController;
import com.thoughtworks.fam.model.User;
import com.thoughtworks.fam.service.AuthService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerMvcTest {


    @Mock
    private AuthService authService;

    private MockMvc mockMvc;

    @InjectMocks
    private AuthController authController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void should_create_user_success() throws Exception {
        given(authService.createUser("jtao@thoughtworks.com"))
                .willReturn(new User("jtao", "jtao@thoughtworks.com"));
        this.mockMvc.perform(post("/user/create").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "jtao@thoughtworks.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("jtao")));
    }

    @Test
    public void should_create_user_failed() throws Exception {
        given(authService.createUser("jtao1@thoughtworks.com")).willReturn(new User("not exist", "not exist"));
        this.mockMvc.perform(post("/user/create").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "jtao1@thoughtworks.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("not exist")));

    }

    @Test
    public void should_login_user_success() throws Exception {
        given(authService.isValid("admin", "P@ss123456")).willReturn(new User("jtao", "jtao@thoughtworks.com"));
        this.mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("userName", "admin")
                .param("password", "P@ss123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("jtao")));

    }

    @Test
    public void should_login_failed() throws Exception {
        given(authService.isValid("admin", "P@ss123456")).willReturn(new User("not exist", "not exist"));
        this.mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("userName", "admin")
                .param("password", "P@ss123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("not exist")));

    }

}