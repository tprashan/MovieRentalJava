package com.prashant.repositories;

import com.prashant.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    @Query(value= "SELECT ID, GENRE, TITLE FROM MOVIE where GENRE = :GENRE", nativeQuery = true)
    List<String> findMovieByGenre(@Param("GENRE") String genre);

    @Query(value= "SELECT ID, GENRE, TITLE FROM MOVIE where MOVIEYEAR = :MOVIEYEAR", nativeQuery = true)
    List<String> findMovieByYear(@Param("MOVIEYEAR") String movieyear);

    @Transactional
    @Modifying
    @Query(value= "UPDATE MOVIE m SET m.MOVIEYEAR = ?2 where m.ID = ?1", nativeQuery = true)
    void updateColumn(String id, String year);
}
