package com.prashant.services;

import com.prashant.model.Movie;
import com.prashant.repositories.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieRentalServiceSpec {

    @InjectMocks
    private MovieRentalService movieRentalService;

    @Mock
    private MovieRepository movieRepositoryMock;


    @Test
    public void shouldReturnWelcomeMessage() {
        String actualMessage = movieRentalService.retriveWelcomeMessage();
        assertEquals("Your welcome in Video Rental Service", actualMessage);
    }

    @Test
    public void shouldReturnListOfMoviesFromFile() throws IOException {
        List<Movie> actualList = movieRentalService.getAllMoviesFromFile();
        int numberOfMovies = actualList.size();

        assertEquals(9125, numberOfMovies);
    }

    @Test
    public void shouldReturnNumberOfInsertedMoviesIntoDB() throws IOException {
        Movie movie = new Movie("5357,Iron Will (1994),Adventure");
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);

        when(movieRepositoryMock.save(any(List.class))).thenReturn(movieList);

        int actualList = movieRentalService.insertMoviesIntoDBFromFile();

        assertEquals(movieList.size(), actualList);
    }

    @Test
    public void shouldReturnMoviesFromCertainGenre() {
        List<String> movieList = new ArrayList<>();
        movieList.add("5357,Iron Will (1994),Adventure");

        when(movieRepositoryMock.findMovieByGenre("Adventure")).thenReturn(movieList);

        List actualList = movieRentalService.findMovieByGenre("Adventure");

        assertEquals(movieList, actualList);
    }
}