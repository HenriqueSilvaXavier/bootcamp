package com.example.bootcamp_films;

import com.example.bootcamp_films.controller.AnimationController;
import com.example.bootcamp_films.entity.Animation;
import com.example.bootcamp_films.service.AnimationService;
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

@WebMvcTest(AnimationController.class)
class AnimationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimationService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAnimationSuccessfully() throws Exception {
        Animation animation = new Animation(
                "Toy Story","Pixar",1995,"Infantil",81,
                "Pixar", true
        );

        Mockito.when(service.create(any(Animation.class))).thenReturn(animation);

        mockMvc.perform(post("/api/animations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(animation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Toy Story"))
                .andExpect(jsonPath("$.animationStudio").value("Pixar"))
                .andExpect(jsonPath("$.dubbed").value(true));
    }

    @Test
    void createAnimationWithoutStudioShouldFail() throws Exception {
        Animation animation = new Animation(
                "Toy Story","Pixar",1995,"Infantil",81,
                "", true
        );

        mockMvc.perform(post("/api/animations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(animation)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void listAnimationsPagedSuccessfully() throws Exception {
        Animation a1 = new Animation("Toy Story","Pixar",1995,"Infantil",81,"Pixar",true);
        Animation a2 = new Animation("Shrek","DreamWorks",2001,"Comédia",90,"DreamWorks",true);

        Page<Animation> page = new PageImpl<>(List.of(a1,a2), PageRequest.of(0,5),2);

        Mockito.when(service.findAll(0,5)).thenReturn(page);

        mockMvc.perform(get("/api/animations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].title").value("Toy Story"));
    }

    @Test
    void getAnimationByIdSuccessfully() throws Exception {
        Animation a = new Animation("Toy Story","Pixar",1995,"Infantil",81,"Pixar",true);

        Mockito.when(service.findById(1L)).thenReturn(a);

        mockMvc.perform(get("/api/animations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Toy Story"));
    }

    @Test
    void updateAnimationSuccessfully() throws Exception {
        Animation updated = new Animation(
                "Toy Story 2","Pixar",1999,"Infantil",92,
                "Pixar", true
        );

        Mockito.when(service.update(Mockito.eq(1L), any(Animation.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/animations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Toy Story 2"));
    }

    @Test
    void deleteAnimationSuccessfully() throws Exception {
        Mockito.doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/api/animations/1"))
                .andExpect(status().isNoContent());
    }
}