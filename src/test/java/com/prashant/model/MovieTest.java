package com.prashant.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    @Test
    public void shouldGetTheTitleOfMovie() {
        Movie movie = new Movie("1", "Adventure|Animation|Children|Comedy|Fantasy", "Toy Story (1995)", "1995", true);
        String title = movie.getTitle();
        assertEquals(title, "Toy Story (1995)");
    }

    @Test
    public void shouldGetTheGenreOfMovie() {
        Movie movie = new Movie("1", "Adventure|Animation|Children|Comedy|Fantasy", "Toy Story (1995)", "1995", true);
        String genre = movie.getGenre();
        assertEquals(genre, "Adventure|Animation|Children|Comedy|Fantasy");
    }

    @Test
    public void shouldGetTheYearOfMovie() {
        Movie movie = new Movie("1", "Adventure|Animation|Children|Comedy|Fantasy", "Toy Story (1995)", "1995", true);
        String yearOfMovie = movie.getYear();
        assertEquals(yearOfMovie, "1995");
    }

    @Test
    public void shouldReturnTrueIfMovieHasCertainYear() {
        Movie movie = new Movie("1", "Adventure|Animation|Children|Comedy|Fantasy", "Toy Story (1995)", "1995", true);
        Boolean movieContainYear = movie.hasMovieCertainYear("1995");
        assertTrue(movieContainYear);
    }

    @Test
    public void shouldReturnTrueIfMovieHasCertainTitle() {
        Movie movie = new Movie("1", "Adventure|Animation|Children|Comedy|Fantasy", "Toy Story (1995)", "1995", true);
        Boolean movieContaintitle = movie.hasMovieCertainTitle("Toy Story (1995)");
        assertTrue(movieContaintitle);
    }

    @Test
    public void shouldReturnFalseIfMovieHasNotCertainTitle() {
        Movie movie = new Movie("1", "Adventure|Animation|Children|Comedy|Fantasy", "Toy Story (1995)", "1995", true);
        Boolean movieContainYear = movie.hasMovieCertainTitle("TToy Story (1995)");
        assertFalse(movieContainYear);
    }
}