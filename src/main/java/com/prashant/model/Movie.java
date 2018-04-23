package com.prashant.model;

import javax.persistence.*;

@Entity
public class Movie {

    @Transient
    private String movie;
    @Id
    @GeneratedValue()
    private String id;
    @Column(name = "GENRE")
    private String genre;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "MOVIEYEAR")
    private String year;

    public Movie() {
    }

    public Movie(String movie) {
        this.movie = movie;
        this.id = movie.split(",")[0];
        this.genre = setMovieGenere();
        this.title = setMovieTitle();
        this.year = setMovieYear();
    }

    private String setMovieYear() {
        String[] seperateTitle = this.title.split("\\(");
        return seperateTitle.length > 1 ? seperateTitle[1].split("\\)")[0] : "None";
    }

    private String setMovieGenere() {
        String[] movieStatus = movie.split(",");
        return movieStatus[movieStatus.length - 1];
    }

    private String setMovieTitle() {
        String[] movieStatus = movie.split(",");
        return movieStatus.length == 3 ? movieStatus[1] : movieStatus[2];
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

    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setYear(String year){
        this.year = year;
    }

    public void setTitle(String title){
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
}
