package com.prashant.controllers;

import com.prashant.model.Movie;
import com.prashant.repositories.MovieRepository;
import com.prashant.videorental.VideoRental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class VideoRentalController {

    private final MovieRepository movieRepository;

    @Autowired
    public VideoRentalController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping("/allMovies")
    public List<Movie> getMovies(@RequestParam Map<String,String> params) throws IOException {
        VideoRental rental = new VideoRental(params);
        List<Movie> rentalMovies = rental.getMovies();
        movieRepository.save(rentalMovies);
        return rentalMovies;
    }

    @RequestMapping("/allMoviesFromDb")
    public List getAllMovies() throws IOException {
        return movieRepository.findAll();
    }

    @RequestMapping("/actionMoviesFromDb")
    public List getActionMovies() throws IOException {
        return movieRepository.findMovieByGenre("Action");
    }

    @RequestMapping("/moviesForYearFromDb")
    public List getMoviesForYear() throws IOException {
        return movieRepository.findMovieByYear("2015");
    }

    @RequestMapping("/movies")
    public List<Movie> getMoviesWithParam(@RequestParam(value="genre") String genre,
                                 @RequestParam(value="year") String year,
                                 @RequestParam(value="title") String title) throws IOException {
        Map<String,String> params = new HashMap<>();
        params.put("genre",genre);
        params.put("year",year);
        params.put("title",title);

        VideoRental rental = new VideoRental(params);
        return rental.getMovies();
    }

    @GetMapping("/usersMovie")
    public List index() throws IOException{
        return movieRepository.findAll();
    };

    @GetMapping("/usersMovie/{year}")
    public List show(@PathVariable("year") String movieYear) throws IOException{
        return movieRepository.findMovieByYear(movieYear);
    }

    @PostMapping("/createMovie")
    public String create(@RequestBody Movie request) throws IOException {
        movieRepository.save(request);
        return "ok";
    }

    @PutMapping("/updateMovie/{id}")
    public String update(@PathVariable String id, @RequestBody Movie request) {
        movieRepository.updateColumn(id,request.getYear());
        return "updated";
    }

    @DeleteMapping("/users/{year}")
    public void delete(@PathVariable String id) {
        movieRepository.delete(id);
    }

}


