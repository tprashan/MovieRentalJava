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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MovieRentalController.class, secure = false)
public class MovieRentalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRentalService movieRentalService;
    Movie movie1 = new Movie("5357", "Adventure", "Iron Will (1994)", "1994", true);
    Movie movie2 = new Movie("8654", "Adventure", "Prince Valiant (1954)", "1954", true);

    @Test
    public void shouldGetTheListOfMoviesFromFile() throws Exception {
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

    @Test
    public void shouldGetTheNumberOfRowsInserted() throws Exception {

        when(movieRentalService.insertMoviesIntoDBFromFile()).thenReturn(10);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/stockMovies")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals("10", result.getResponse().getContentAsString(), false);
    }

    @Test
    public void shouldGetTheListOfMoviesFromDB() throws Exception {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);

        when(movieRentalService.getAllMoviesFromDB()).thenReturn(movieList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/allMoviesFromDb")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"genre\":\"Adventure\",\"title\":\"Iron Will (1994)\",\"year\":\"1994\"}," +
                "{\"genre\":\"Adventure\",\"title\":\"Prince Valiant (1954)\",\"year\":\"1954\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void shouldGetTheListOfMoviesOfcertainGenre() throws Exception {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);

        when(movieRentalService.findMovieByGenre("Adventure")).thenReturn(movieList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/moviesbyGenre")
                .param("genre", "Adventure")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"genre\":\"Adventure\",\"title\":\"Iron Will (1994)\",\"year\":\"1994\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void shouldGetTheListOfMoviesOfcertainYear() throws Exception {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);

        when(movieRentalService.findMovieByYear("1994")).thenReturn(movieList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/moviesbyYear")
                .param("year", "1994")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"genre\":\"Adventure\",\"title\":\"Iron Will (1994)\",\"year\":\"1994\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void shouldGetTheListOfMoviesOfcertainYearByUsingGetMapping() throws Exception {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);

        when(movieRentalService.findMovieByYear("1994")).thenReturn(movieList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/usersMovie/1994")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"genre\":\"Adventure\",\"title\":\"Iron Will (1994)\",\"year\":\"1994\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void shouldCallAddMovieToDB() throws Exception {
        doNothing().when(movieRentalService).addMovieToDB(movie1);

        String bodyJson = "{ \"genre\": \"Adventure\", \"title\": \"Iron Will (1994)\", \"year\": \"1994\" }";


        MvcResult result = mockMvc.perform(
                post("/addMovie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), "ok");
    }

    @Test
    public void shouldCallUpdate() throws Exception {
        doNothing().when(movieRentalService).updateColumn("5357", "1994");

        String bodyJson = "{ \"genre\": \"Adventure\", \"title\": \"Iron Will (1994)\", \"year\": \"1995\" }";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/updateMovie/5357")
                .content(bodyJson)
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), "updated");
    }

    @Test
    public void shouldCallDeleteMovie() throws Exception {
        doNothing().when(movieRentalService).deleteMovie("5357");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/removeMovie/5357");


        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), "deleted");
    }
}