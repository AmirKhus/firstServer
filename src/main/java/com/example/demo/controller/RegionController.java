package com.example.demo.controller;



import com.example.demo.entity.Region;
import com.example.demo.repository.BuyerRepository;
import com.example.demo.repository.RegionRepository;
import com.example.demo.resource.BuyerResource;
import com.example.demo.resource.RegionResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/region")
public class RegionController {
    private final RegionRepository regionRepository;
    private final BuyerRepository buyerRepository;

    public RegionController(RegionRepository regionRepository, BuyerRepository buyerRepository) {
        this.regionRepository = regionRepository;
        this.buyerRepository = buyerRepository;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    RegionResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(regionRepository.select())
                .map(entity -> {
                    RegionResource resource = new RegionResource(entity);
                    if (expand != null)
                        resource.setBuyerResource(
                                Arrays.stream(buyerRepository.selectByRegionId(entity.getId()))
                                        .map(e -> new BuyerResource(e))
                                        .toArray(BuyerResource[]::new)
                        );
                    return resource;
                })
                .toArray(RegionResource[]::new);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    RegionResource get(@PathVariable Integer id,
                          @RequestParam(required = false) Object expand) {
        Region entity = regionRepository.select(id);
        if (entity == null) return null;
        RegionResource resource = new RegionResource(entity);
        if (expand != null)
            resource.setBuyerResource(
                    Arrays.stream(buyerRepository.selectByRegionId(entity.getId()))
                            .map(e -> new BuyerResource(e))
                            .toArray(BuyerResource[]::new)
            );
        return resource;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST)
    RegionResource post(@RequestBody RegionResource resource) {
        Region entity = regionRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new RegionResource(entity);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    RegionResource put(@PathVariable Integer id,
                          @RequestBody RegionResource resource) {
        Region entity = regionRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new RegionResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    RegionResource delete(@PathVariable Integer id) {
        Region entity = regionRepository.delete(id);
        if (entity == null) return null;
        return new RegionResource(entity);
    }
}
