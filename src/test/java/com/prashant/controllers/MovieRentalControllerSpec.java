package com.prashant.controllers;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MovieRentalControllerSpec {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenNumber_thenAutoType() throws Exception {
        Map<String,String> params = new HashMap<>();
        params.put("genre","Adventure");
        params.put("year","1995");

//        Movie movie1 = new Movie("1,Toy Story (1995),Adventure|Animation|Children|Comedy|Fantasy");
//        Movie movie2 = new Movie("2,Jumanji (1995),Adventure|Children|Fantasy");
//        List<Movie> movieList = new ArrayList<>();
//        movieList.add(movie1);
//        movieList.add(movie2);
//
//        when(videoRental.getMovies()).thenReturn(movieList);
//
//        this.mockMvc.perform(get("/allMovies")
//                .param("genre", "Adventure")
//                .param("title", "Toy Story")
//                .param("year", "1995"))
//                .andExpect(status().isOk());

    }
}