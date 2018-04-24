package com.prashant.services;

import com.prashant.model.Movie;
import com.prashant.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieRentalService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieRentalService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private List<Movie> loadMoviesFromFile() throws IOException {
        String fileName = "/Users/prashanttripathi/Downloads/ml-latest-small/movies.csv";
        return Files.lines(Paths.get(fileName))
                .skip(1)
                .map(Movie::new)
                .sorted(Comparator.comparing(Movie::getTitle))
                .collect(Collectors.toList());
    }

    public String retriveWelcomeMessage() {
        return "Your welcome in Video Rental Service";
    }

    public List<Movie> getAllMoviesFromFile() throws IOException {
        return loadMoviesFromFile();
    }

    public int insertMoviesIntoDBFromFile() throws IOException {
        List<Movie> rentalMovies = loadMoviesFromFile();
        movieRepository.save(rentalMovies);
        return rentalMovies.size();
    }

    public List getAllMoviesFromDB() {
        return movieRepository.findAll();
    }

    public List findMovieByGenre(String genre) {
        return movieRepository.findMovieByGenre(genre);
    }

    public List findMovieByYear(String year) {
        return movieRepository.findMovieByYear(year);
    }

    public void addMovieToDB(Movie request) {
        movieRepository.save(request);
    }

    public void updateColumn(String id, String year) {
        movieRepository.updateColumn(id,year);
    }

    public void deleteMovie(String id) {
        movieRepository.delete(id);
    }
}
