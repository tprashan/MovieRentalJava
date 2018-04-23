package com.prashant.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    @Test
    public void shouldGetTheTitleOfMovie() {
        Movie movie = new Movie("1,Toy Story (1995),Adventure|Animation|Children|Comedy|Fantasy");
        String title = movie.getTitle();
        assertEquals(title, "Toy Story (1995)");
    }

    @Test
    public void shouldGetTheGenreOfMovie() {
        Movie movie = new Movie("1,Toy Story (1995),Adventure|Animation|Children|Comedy|Fantasy");
        String genre = movie.getGenre();
        assertEquals(genre, "Adventure|Animation|Children|Comedy|Fantasy");
    }

    @Test
    public void shouldGetTheYearOfMovie() {
        Movie movie = new Movie("1,Toy Story (1995),Adventure|Animation|Children|Comedy|Fantasy");
        String yearOfMovie = movie.getYear();
        assertEquals(yearOfMovie, "1995");
    }

    @Test
    public void shouldReturnTrueIfMovieHasCertainYear() {
        Movie movie = new Movie("1,Toy Story (1995),Adventure|Animation|Children|Comedy|Fantasy");
        Boolean movieContainYear = movie.hasMovieCertainYear("1995");
        assertTrue(movieContainYear);
    }

    @Test
    public void shouldReturnTrueIfMovieHasCertainTitle() {
        Movie movie = new Movie("1,Toy Story (1995),Adventure|Animation|Children|Comedy|Fantasy");
        Boolean movieContaintitle = movie.hasMovieCertainTitle("Toy Story (1995)");
        assertTrue(movieContaintitle);
    }

    @Test
    public void shouldReturnFalseIfMovieHasNotCertainTitle() {
        Movie movie = new Movie("1,Toy Story (1995),Adventure|Animation|Children|Comedy|Fantasy");
        Boolean movieContainYear = movie.hasMovieCertainTitle("TToy Story (1995)");
        assertFalse(movieContainYear);
    }
}