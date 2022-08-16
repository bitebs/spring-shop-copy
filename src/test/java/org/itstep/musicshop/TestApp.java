package org.itstep.musicshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApp {
    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void getArtist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/Artist")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$", Matchers.hasSize(2)))
                //.andExpect(jsonPath("$[0].name",
                        //Matchers.equalTo("AC/DC")));

    }

    @Test
    @Order(2)
    public void getArtistById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/Artist/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                Matchers.equalTo("AC/DC")));

    }




    @Test
    @Order(3)
    public void postArtist() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post
                        ("/Artist")
                        .content(asJsonString(new Artist(1L, "user1")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
    }

    @Test
    @Order(4)
    public void putArtist() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.put
                        ("/Artist/2")
                        .content(asJsonString(new Artist( 4L, "user4")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.price", Matchers.equalTo(1.)));

    }

    @Test
    @Order(5)
    public void deleteArtist() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders.delete("/Artist/2"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
/*
    @Test
    @Disabled
    public void users() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].username", Matchers.equalTo("admin")))
        ;
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 2, 3, Integer.MAX_VALUE})
    public void getUserById(int id) throws Exception {
        ResultActions actions = mvc
                .perform(MockMvcRequestBuilders.get("/user/{id}",id)
                        .accept(MediaType.APPLICATION_JSON));
        actions
                .andExpect(status().isOk());
        switch (id){
            case 1:
                actions
                        .andExpect(jsonPath("$.username", Matchers.equalTo("admin")));
                break;
            case 2:
                actions
                        .andExpect(jsonPath("$.username", Matchers.equalTo("user")));
                break;
            default:
                actions
                        .andExpect(content().string(""));
        }

 */
    }

