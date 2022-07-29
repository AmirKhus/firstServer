package com.example.demo.controller;

import com.example.demo.entity.Genre;
import com.example.demo.repository.GameRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.resource.GameResource;
import com.example.demo.resource.GenreResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/genre")
public class GenreController {
    private final GenreRepository genreRepository;
    private final GameRepository gameRepository;

    public GenreController(GenreRepository genreRepository, GameRepository gameRepository) {
        this.genreRepository = genreRepository;
        this.gameRepository = gameRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    GenreResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(genreRepository.select())
                .map(entity -> {
                    GenreResource resource = new GenreResource(entity);
                    if (expand != null)
                        resource.setGameResource(
                                Arrays.stream(gameRepository.selectByPublisherId(entity.getId()))
                                        .map(e -> new GameResource(e))
                                        .toArray(GameResource[]::new)
                        );
                    return resource;
                })
                .toArray(GenreResource[]::new);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    GenreResource get(@PathVariable Integer id,
                        @RequestParam(required = false) Object expand) {
        Genre entity = genreRepository.select(id);
        if (entity == null) return null;
        GenreResource resource = new GenreResource(entity);
        if (expand != null)
            resource.setGameResource(
                    Arrays.stream(gameRepository.selectByPublisherId(entity.getId()))
                            .map(e -> new GameResource(e))
                            .toArray(GameResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    GenreResource post(@RequestBody GenreResource resource) {
        Genre entity = genreRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new GenreResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    GenreResource put(@PathVariable Integer id,
                        @RequestBody GenreResource resource) {
        Genre entity = genreRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new GenreResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    GenreResource delete(@PathVariable Integer id) {
        Genre entity = genreRepository.delete(id);
        if (entity == null) return null;
        return new GenreResource(entity);
    }
}
