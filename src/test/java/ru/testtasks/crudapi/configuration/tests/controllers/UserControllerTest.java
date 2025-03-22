package ru.testtasks.crudapi.configuration.tests.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.testtasks.crudapi.AbstractCommonContext;
import ru.testtasks.crudapi.configuration.JwtToken;

import java.nio.charset.StandardCharsets;

class UserControllerTest extends AbstractCommonContext {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtToken jwtTokenUtil;
    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testGetUsers() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users?page=0&size=3")
                        .content(getUserFilterDtoJson())
                        .headers(getAuthHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                )
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(1));
    }

    private HttpHeaders getAuthHeaders() {
        final UserDetails userDetails = userDetailsService.loadUserByUsername("USER1");
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
        return httpHeaders;
    }

    private String getUserFilterDtoJson() {
        return "{\n" +
                "  \"phone\": \"7898177757777\"\n" +
                "}";
    }

}
