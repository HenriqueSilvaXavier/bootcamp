package com.example.bootcamp_films;

import com.example.bootcamp_films.controller.DocumentaryController;
import com.example.bootcamp_films.entity.Documentary;
import com.example.bootcamp_films.service.DocumentaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DocumentaryController.class)
class DocumentaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentaryService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createDocumentarySuccessfully() throws Exception {
        Documentary doc = new Documentary(
                "Planeta Terra","BBC",2006,"Natureza",50,true
        );

        Mockito.when(service.create(any(Documentary.class))).thenReturn(doc);

        mockMvc.perform(post("/api/documentaries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doc)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Planeta Terra"))
                .andExpect(jsonPath("$.narrated").value(true));
    }

    @Test
    void listDocumentariesPagedSuccessfully() throws Exception {
        Documentary d1 = new Documentary("Planeta Terra","BBC",2006,"Natureza",50,true);
        Documentary d2 = new Documentary("Cosmos","Sagan",1980,"Ciência",60,true);

        Page<Documentary> page = new PageImpl<>(List.of(d1,d2), PageRequest.of(0,5),2);

        Mockito.when(service.findAll(0,5)).thenReturn(page);

        mockMvc.perform(get("/api/documentaries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[1].title").value("Cosmos"));
    }

    @Test
    void getDocumentaryByIdSuccessfully() throws Exception {
        Documentary d = new Documentary("Cosmos","Sagan",1980,"Ciência",60,true);

        Mockito.when(service.findById(1L)).thenReturn(d);

        mockMvc.perform(get("/api/documentaries/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Cosmos"));
    }

    @Test
    void updateDocumentarySuccessfully() throws Exception {
        Documentary updated = new Documentary(
                "Cosmos: A Spacetime Odyssey","Tyson",2014,"Ciência",60,true
        );

        Mockito.when(service.update(Mockito.eq(1L), any(Documentary.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/documentaries/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.releaseYear").value(2014));
    }

    @Test
    void deleteDocumentarySuccessfully() throws Exception {
        Mockito.doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/api/documentaries/1"))
                .andExpect(status().isNoContent());
    }
}