package com.slaweklida.songLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs") //stały adres
public class SongController {

    @Autowired
    SongRepository songRepository;

    @GetMapping("")
    public List<Song> getAll(){
        return songRepository.getAll();
    }

    @GetMapping("/{id}")
    public Song getById(@PathVariable("id") int id){ //{id} jest uzupelniane przez @PathVariable
        return songRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Song> songs) { //@RequestBody ponieważ PRZEKAZUJEMY zawartość w jsonie razem z zapytaniem
        return songRepository.save(songs);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Song updatedSong){
        Song song = songRepository.getById(id);
        if(song != null){
            song.setTitle(updatedSong.getTitle());
            song.setArtist(updatedSong.getArtist());

            songRepository.update(song);
            return 1;
        } else{
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Song updatedSong){
        Song song = songRepository.getById(id);
        if(song != null){
            if(updatedSong.getTitle() != null) song.setTitle(updatedSong.getTitle());
            if(updatedSong.getArtist() != null) song.setArtist(updatedSong.getArtist());

            songRepository.update(song);
            return 1;
        }else{
            return -1;
        }
    }

}
