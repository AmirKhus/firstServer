package com.example.demo.controller;


import com.example.demo.entity.Order;
import com.example.demo.repository.BuyerRepository;
import com.example.demo.repository.GameRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.resource.BuyerResource;
import com.example.demo.resource.GameResource;
import com.example.demo.resource.OrderResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    
    private final GameRepository gameRepository;
    private final BuyerRepository buyerRepository;
    private final OrderRepository orderRepository;

    public OrderController(GameRepository gameRepository, BuyerRepository buyerRepository, OrderRepository orderRepository) {
        this.gameRepository = gameRepository;
        this.buyerRepository = buyerRepository;
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    OrderResource[] getAll(@RequestParam(required = false) Object expand) {
        Order[] entities = orderRepository.select();
        return Arrays.stream(entities)
                .map(entity -> {
                    OrderResource resource = new OrderResource(entity);
                    if (expand != null) {
                        resource.setGameInfomation(
                                new GameResource(gameRepository.select(entity.getGame_id()))
                        );
                        resource.setBuyerInfomation(new BuyerResource(
                                buyerRepository.select(entity.getBuyer_id()))
                        );
                    }
                    System.out.println(resource.toString());
                    return resource;
                })
                .toArray(OrderResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    OrderResource get(@PathVariable Integer id,
                      @RequestParam(required = false) Object expand) {
        Order entity = orderRepository.select(id);
        if (entity == null) return null;
        OrderResource resource = new OrderResource(entity);
        if (expand != null) {
            resource.setGameInfomation(
                    new GameResource(gameRepository.select(entity.getGame_id()))
            );
            resource.setBuyerInfomation(new BuyerResource(
                    buyerRepository.select(entity.getBuyer_id()))
            );
        }
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    OrderResource post(@RequestBody OrderResource resource) {
        Order entity = orderRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new OrderResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    OrderResource put(@PathVariable Integer id,
                      @RequestBody OrderResource resource) {
        Order entity = orderRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new OrderResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    OrderResource delete(@PathVariable Integer id) {
        Order entity = orderRepository.delete(id);
        if (entity == null) return null;
        OrderResource resource = new OrderResource(entity);
        return resource;
    }
}
