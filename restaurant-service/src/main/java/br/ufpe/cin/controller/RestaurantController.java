package br.ufpe.cin.controller;

import br.ufpe.cin.model.OrderP;
import br.ufpe.cin.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;
    private final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<OrderP> ListOrders() {
        return restaurantService.getAllOrders();
    }

    @GetMapping(value = "/{restaurantId}")
    public List<OrderP> listOrdersByRestaurantId(@PathVariable String restaurantId){
        return restaurantService.getAllOrdersByRestaurantId(restaurantId);
    }

    @PostMapping(value = "orderUpdate")
    public void orderUpdate(){

    }



}