package com.prashant.controllers;

import com.prashant.model.Movie;
import com.prashant.services.MovieRentalService;
import com.prashant.viewModels.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class MovieRentalController {

    private final MovieRentalService movieRentalService;

    @Autowired
    public MovieRentalController(MovieRentalService movieRentalService) {
        this.movieRentalService = movieRentalService;
    }

    @RequestMapping("/")
    public String welcome() {
        return movieRentalService.retriveWelcomeMessage();
    }

    @RequestMapping("/allMoviesFromFile")
    public List<Movie> getAllMovies() throws IOException {
        return movieRentalService.getAllMoviesFromFile();
    }

    @RequestMapping("/stockMovies")
    public int insertMovies() throws IOException {
        return movieRentalService.insertMoviesIntoDBFromFile();
    }

    @RequestMapping("/allMoviesFromDb")
    public List getAllMoviesFromDB() {
        return movieRentalService.getAllMoviesFromDB();
    }

    @RequestMapping("/moviesbyGenre")
    public List getMoviesByGenre(@RequestParam(value = "genre") String genre) {
        return movieRentalService.findMovieByGenre(genre);
    }

    @RequestMapping("/moviesbyYear")
    public List getMoviesByYear(@RequestParam(value = "year") String year) {
        return movieRentalService.findMovieByYear(year);
    }

    //TODO : using specific HTTP Verb request

    @GetMapping("/movies")
    public List trendingMovies(@RequestParam("trending") Boolean trending) {
        return movieRentalService.findTrendingMovie(trending);
    }

    @GetMapping("/moviesSummary")
    public Summary moviesSummary() {
        return movieRentalService.getSummary();
    }

    @GetMapping("/usersMovie/{year}")
    public List show(@PathVariable("year") String movieYear) {
        return movieRentalService.findMovieByYear(movieYear);
    }

    @PostMapping("/addMovie")
    public String create(@RequestBody Movie request) {
        movieRentalService.addMovieToDB(request);
        return "ok";
    }

    @PutMapping("/updateMovie/{id}")
    public String update(@PathVariable String id, @RequestBody Movie request) {
        movieRentalService.updateColumn(id, request.getYear());
        return "updated";
    }

    @DeleteMapping("/removeMovie/{id}")
    public String delete(@PathVariable String id) {
        movieRentalService.deleteMovie(id);
        return "deleted";
    }

//    @RequestMapping("/moviesss")
//    public List<Movie> getMoviesWithParam(@RequestParam(value="genre") String genre,
//                                          @RequestParam(value="year") String year,
//                                          @RequestParam(value="title") String title) throws IOException {
//        Map<String,String> params = new HashMap<>();
//        params.put("genre",genre);
//        params.put("year",year);
//        params.put("title",title);
//
//        return rental.getMovies();
//    }

//    @RequestMapping("/allMovies")
//    public List<Movie> getMovies(@RequestParam Map<String,String> params) throws IOException {
//        return rental.getMovies();
//    }

}
