package com.slaweklida.songLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs") //stała
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
    public int add(@RequestBody List<Song> songs) { //@RequestBody ponieważ zawartość przyjdzie w jsonie razem z zapytaniem
        return songRepository.save(songs);
    }

}
