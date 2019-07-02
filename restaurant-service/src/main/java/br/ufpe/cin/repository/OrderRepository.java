package br.ufpe.cin.repository;

import br.ufpe.cin.internal.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findAllByRestaurantId(String id);
}
