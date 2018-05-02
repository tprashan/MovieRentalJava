package com.prashant.viewModels;

public class Summary {
    private int totalMovies;
    private int trendingMovies;
    private int nonTrendingMovies;

    public Summary(int totalMovies, int trendingMovies, int nonTrendingMovies) {

        this.totalMovies = totalMovies;
        this.trendingMovies = trendingMovies;
        this.nonTrendingMovies = nonTrendingMovies;
    }

    public int getTotalMovies() {
        return totalMovies;
    }

    public int getTrendingMovies() {
        return trendingMovies;
    }

    public int getNonTrendingMovies() {
        return nonTrendingMovies;
    }

}
