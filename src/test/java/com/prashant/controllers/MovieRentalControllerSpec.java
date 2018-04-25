package com.prashant.controllers;


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
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MovieRentalController.class, secure = false)
public class MovieRentalControllerSpec {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRentalService movieRentalService;

    @Test
    public void shouldGetTheListOfMovies() throws Exception {
        Movie movie1 = new Movie("5357,Iron Will (1994),Adventure");
        Movie movie2 = new Movie("8654,Prince Valiant (1954),Adventure");
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);

        when(movieRentalService.getAllMoviesFromFile()).thenReturn(movieList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/allMoviesFromFile").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"genre\":\"Adventure\",\"title\":\"Iron Will (1994)\",\"year\":\"1994\"}," +
                "{\"genre\":\"Adventure\",\"title\":\"Prince Valiant (1954)\",\"year\":\"1954\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}