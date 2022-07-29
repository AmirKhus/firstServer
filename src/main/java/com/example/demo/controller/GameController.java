package com.example.demo.controller;

import com.example.demo.entity.Game;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.GameRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.resource.CompanyResource;
import com.example.demo.resource.GameResource;
import com.example.demo.resource.GenreResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/game")
public class GameController {
    private final GenreRepository genreRepository;
    private final GameRepository gameRepository;
    private final CompanyRepository companyRepository;

    public GameController(GenreRepository genreRepository, GameRepository gameRepository, CompanyRepository companyRepository) {
        this.genreRepository = genreRepository;
        this.gameRepository = gameRepository;
        this.companyRepository = companyRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    GameResource[] getAll(@RequestParam(required = false) String  name,
                          @RequestParam(required = false) Integer  publisherId,
                          @RequestParam(required = false) Object expand) {

        Game[] entities;
//        = publisherId == null ?
//                gameRepository.select() :
//                gameRepository.selectByPublisherId(publisherId);
        if (publisherId == null) {
            if (name == null) {
                entities = gameRepository.select();
            }else{
                entities = gameRepository.selectByName(name);
            }
        }else {
            if (name == null) {
                entities = gameRepository.selectByPublisherId(publisherId);
            }else{
                entities = gameRepository.selectByName(name);
            }
        }
//        entities = name == null ?
//                gameRepository.select() :
//                gameRepository.selectByName(name);
        return Arrays.stream(entities)
                .map(entity -> {
                    GameResource resource = new GameResource(entity);
                    if (expand != null) {
                        resource.setPublisher(new CompanyResource(
                            companyRepository.select(entity.getDeveloper_id()))
                        );

                        resource.setDeveloper(new CompanyResource(
                            companyRepository.select(entity.getPublisher_id()))
                        );

                        resource.setGenreResource(new GenreResource(
                                genreRepository.select(entity.getGenre_id()))
                        );
                    }
                    return resource;
                })
                .toArray(GameResource[]::new);
    }

//    @RequestMapping(value = "/name", method = RequestMethod.GET)
//    GameResource[] getSelectName(@RequestParam(required = false) String  name,
//                                 @RequestParam(required = false) Object expand) {
//        System.out.println(name);
//        Game[] entities = name == null ?
//                gameRepository.select() :
//                gameRepository.selectByName(name);
//        return Arrays.stream(entities)
//                .map(entity -> {
//                    GameResource resource = new GameResource(entity);
//                    if (expand != null) {
//
//                        resource.setPublisher(new CompanyResource(
//                                companyRepository.select(entity.getDeveloper_id()))
//                        );
//
//                        resource.setDeveloper(new CompanyResource(
//                                companyRepository.select(entity.getPublisher_id()))
//                        );
//
//                        resource.setGenreResource(new GenreResource(
//                                genreRepository.select(entity.getGenre_id()))
//                        );
//                    }
//                    return resource;
//                })
//                .toArray(GameResource[]::new);
//    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    GameResource get(@PathVariable Integer id,
                      @RequestParam(required = false) Object expand) {
        Game entity = gameRepository.select(id);
        if (entity == null) return null;
        GameResource resource = new GameResource(entity);
        if (expand != null) {
            resource.setPublisher(new CompanyResource(
                    companyRepository.select(entity.getDeveloper_id()))
            );

            resource.setDeveloper(new CompanyResource(
                    companyRepository.select(entity.getPublisher_id()))
            );

            resource.setGenreResource(new GenreResource(
                    genreRepository.select(entity.getGenre_id()))
            );
        }

        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    GameResource post(@RequestBody GameResource resource) {
        Game entity = gameRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new GameResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    GameResource put(@PathVariable Integer id,
                      @RequestBody GameResource resource) {
        Game entity = gameRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new GameResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    GameResource delete(@PathVariable Integer id) {
        Game entity = gameRepository.delete(id);
        if (entity == null) return null;
        GameResource resource = new GameResource(entity);
        return resource;
    }
}
