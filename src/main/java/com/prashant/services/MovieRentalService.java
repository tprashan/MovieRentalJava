package com.prashant.services;

import com.prashant.model.Movie;
import com.prashant.repositories.MovieRepository;
import com.prashant.viewModels.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieRentalService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieRentalService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private String getMovieGenre(String movie) {
        String[] movieStatus = movie.split(",");
        return movieStatus[movieStatus.length - 1];
    }

    private String getMovieTitle(String movie) {
        String[] movieStatus = movie.split(",");
        return movieStatus.length == 3 ? movieStatus[1] : movieStatus[2];
    }

    private String getMovieYear(String movie) {
        String[] seperateTitle = getMovieTitle(movie).split("\\(");
        return seperateTitle.length > 1 ? seperateTitle[1].split("\\)")[0] : "None";
    }

    private String getMovieId(String movie) {
        return movie.split(",")[0];
    }

    private String getFileName() throws IOException {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("dev.properties");
        prop.load(stream);
        return prop.getProperty("MovieFilePath");
    }

    private List<Movie> loadMoviesFromFile() throws IOException {
        Random random = new Random();
        return Files.lines(Paths.get(getFileName()))
                .skip(1)
                .map(m -> new Movie(getMovieId(m), getMovieGenre(m), getMovieTitle(m), getMovieYear(m), random.nextBoolean()))
                .sorted(Comparator.comparing(Movie::getTitle))
                .collect(Collectors.toList());
    }

    private List<String> movieIds() throws IOException {
        List<String> ids = new ArrayList<>();
        loadMoviesFromFile().forEach(movie -> ids.add(movie.getId()));
        return ids;
    }

    public String retriveWelcomeMessage() {
        return "Your welcome in Video Rental Service";
    }

    public List<Movie> getAllMoviesFromFile() throws IOException {
        return loadMoviesFromFile();
    }

    public int insertMoviesIntoDBFromFile() throws IOException {
        List<Movie> rentalMovies = loadMoviesFromFile();
        return movieRepository.save(rentalMovies).size();
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

    public List findTrendingMovie(Boolean trending) {
        return movieRepository.findTrendingMovie(trending);
    }

    public Summary getSummary() {
        int totalMovies = getAllMoviesFromDB().size();
        int trendingMovies = findTrendingMovie(true).size();
        int nonTrendingMovies = findTrendingMovie(false).size();

        return new Summary(totalMovies, trendingMovies, nonTrendingMovies);
    }

    public void addMovieToDB(Movie request) {
        movieRepository.save(request);
    }

    public void updateColumn(String id, String year) {
        movieRepository.updateColumn(id, year);
    }

    public void deleteMovie(String id) {
        movieRepository.delete(id);
    }
}
