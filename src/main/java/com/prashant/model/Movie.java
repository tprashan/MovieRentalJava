package com.prashant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Arrays;

@Entity
public class Movie {

    @Transient
    private String movie;
    @Id
    private String id;
    @Column(name = "GENRE")
    private String genre;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "MOVIEYEAR")
    private String year;
    @Column(name = "TRENDING")
    private Boolean trending;

    public Movie() {
    }

    public Movie(String id, String movieGenre, String movieTitle, String movieYear, boolean trending) {
        this.id = id;
        this.genre = movieGenre;
        this.title = movieTitle;
        this.year = movieYear;
        this.trending = trending;
    }

    public Boolean hasMovieGenreType(String genreParam) {
        return genreParam == null || this.genre.contains(genreParam);
    }

    public boolean hasMovieCertainYear(String year) {
        return year == null || this.title.contains(year);
    }

    public boolean hasMovieCertainTitle(String titleParam) {
        return titleParam == null || this.title.contains(titleParam);
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getYear() {
        return this.year;
    }

    public String getId() {
        return this.id;
    }

    public Boolean getTrending() {
        return trending;
    }

    public void setTrending(Boolean trending) {
        this.trending = trending;
    }

    @Override
    public String toString() {
        return Arrays.toString(new String[]{this.id, this.genre, this.title, this.year});
    }
}
