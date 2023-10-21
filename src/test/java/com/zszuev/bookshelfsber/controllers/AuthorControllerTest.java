package com.zszuev.bookshelfsber.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zszuev.bookshelfsber.entities.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AuthorControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetAllAuthors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAuthor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/{authorId}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAuthorNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/{authorId}", 999)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateAuthor() throws Exception {
        Author newAuthor = new Author(1L, "John", "Middle", "Doe", LocalDate.of(1990, 1, 1).toString(), "Biography of John Doe");
        String authorJson = new ObjectMapper().writeValueAsString(newAuthor);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateAuthor() throws Exception {
        Author updatedAuthor = new Author(1L, "Updated", "Middle", "Author", LocalDate.of(1985, 5, 10).toString(), "Updated Biography");
        String authorJson = new ObjectMapper().writeValueAsString(updatedAuthor);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/authors/{authorId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateAuthorNotFound() throws Exception {
        Author updatedAuthor = new Author(1L, "Updated", "Middle", "Author", LocalDate.of(1985, 5, 10).toString(), "Updated Biography");
        String authorJson = new ObjectMapper().writeValueAsString(updatedAuthor);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/authors/{authorId}", 999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/authors/{authorId}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAuthorNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/authors/{authorId}", 999)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
