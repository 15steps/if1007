package br.ufpe.cin.repository;

import br.ufpe.cin.model.OrderP;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<OrderP, String> {

    List<OrderP> findAllByRestaurantId(String id);
}
