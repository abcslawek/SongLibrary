package com.slaweklida.songLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Song> getAll(){
        return jdbcTemplate.query("SELECT id, title, artist FROM song", BeanPropertyRowMapper.newInstance(Song.class));
    }

    public Song getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, title, artist FROM song WHERE id = ?", BeanPropertyRowMapper.newInstance(Song.class), id);
    }

    public int save(List<Song> songs) {
        songs.forEach(song -> jdbcTemplate.update("INSERT INTO song(title, artist) VALUES(?, ?)", song.getTitle(), song.getArtist()));
        return 1;
    }

    public int update(Song song){
        return jdbcTemplate.update("UPDATE song SET title=?, artist=? WHERE id=?", song.getTitle(), song.getArtist(), song.getId());
    }
}
