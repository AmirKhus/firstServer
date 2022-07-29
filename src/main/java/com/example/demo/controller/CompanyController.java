package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.repository.GameRepository;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.resource.CompanyResource;
import com.example.demo.resource.GameResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {
    private final CompanyRepository companyRepository;
    private final GameRepository gameRepository;

    public CompanyController(CompanyRepository companyRepository, GameRepository gameRepository) {
        this.companyRepository = companyRepository;
        this.gameRepository = gameRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    CompanyResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(companyRepository.select())
                .map(entity -> {
                    CompanyResource resource = new CompanyResource(entity);
                    if (expand != null)
                        resource.setGameResource(
                                Arrays.stream(gameRepository.selectByPublisherId(entity.getId()))
                                        .map(e -> new GameResource(e))
                                        .toArray(GameResource[]::new)
                        );
                    return resource;
                })
                .toArray(CompanyResource[]::new);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    CompanyResource get(@PathVariable Integer id,
                       @RequestParam(required = false) Object expand) {
        Company entity = companyRepository.select(id);
        if (entity == null) return null;
        CompanyResource resource = new CompanyResource(entity);
        if (expand != null)
            resource.setGameResource(
                    Arrays.stream(gameRepository.selectByPublisherId(entity.getId()))
                            .map(e -> new GameResource(e))
                            .toArray(GameResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    CompanyResource post(@RequestBody CompanyResource resource) {
        Company entity = companyRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new CompanyResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    CompanyResource put(@PathVariable Integer id,
                       @RequestBody CompanyResource resource) {
        Company entity = companyRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new CompanyResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    CompanyResource delete(@PathVariable Integer id) {
        Company entity = companyRepository.delete(id);
        if (entity == null) return null;
        return new CompanyResource(entity);
    }
}
