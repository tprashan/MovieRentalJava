package com.prashant.videorental;

import com.prashant.model.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VideoRental {

    private final Map<String, String> movieType;
    private String genreParam;
    private String yearParam;
    private String titleParam;


    public VideoRental(Map<String, String> params) {
        this.movieType = params;
        setParameters();
    }

    private void setParameters() {
        for (Map.Entry<String, String> movieDataSet : movieType.entrySet()) {
            String dd = "" + movieDataSet.getKey() + ", " +movieDataSet.getValue();
            if((movieDataSet.getKey()).equals("genre")) {
                this.genreParam = movieDataSet.getValue();
            }else if((movieDataSet.getKey()).equals("year")){
                this.yearParam = movieDataSet.getValue();
            }else if((movieDataSet.getKey()).equals("title")){
                this.titleParam = movieDataSet.getValue();
            }
        }
    };

    private List<Movie> loadMoviesFromFileAndFilterOnParams() throws IOException {
        String fileName = "/Users/prashanttripathi/Downloads/ml-latest-small/movies.csv";
        return Files.lines(Paths.get(fileName))
                                    .skip(1)
                                    .map(Movie::new)
                                    .filter((Movie m) -> m.hasMovieGenreType(genreParam))
                                    .filter((Movie m) -> m.hasMovieCertainYear(yearParam))
                                    .filter((Movie m) -> m.hasMovieCertainTitle(titleParam))
                                    .sorted(Comparator.comparing(Movie::getTitle))
                                    .collect(Collectors.toList());

    }

    public List<Movie> getMovies() throws IOException {
        return loadMoviesFromFileAndFilterOnParams();
    };
}
