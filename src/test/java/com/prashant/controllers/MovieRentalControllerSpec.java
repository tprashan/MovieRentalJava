package com.prashant.controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.prashant.model.Movie;
import com.prashant.services.MovieRentalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MovieRentalController.class)
public class MovieRentalControllerSpec {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRentalService movieRentalService;

    @Test
    public void whenNumber_thenAutoType() throws Exception {
        Movie movie1 = new Movie("4261,Adventure,Iron Will (1994)");
        Movie movie2 = new Movie("5239,Adventure,Prince Valiant (1954)");
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);

        when(movieRentalService.getAllMoviesFromFile()).thenReturn(movieList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/allMoviesFromFile").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[[ \"4261\", \"Adventure\", \"Iron Will (1994)\" ], [ \"5239\", \"Adventure\", \"Prince Valiant (1954)\" ]]";

        JSONAssert.assertEquals("[]", Arrays.toString(result.getResponse().getContentAsByteArray()), false);
    }
}