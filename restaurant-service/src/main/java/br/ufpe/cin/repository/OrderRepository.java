package br.ufpe.cin.repository;

import br.ufpe.cin.internal.Order;
import br.ufpe.cin.model.RestaurantOrder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderRepository extends ElasticsearchRepository<RestaurantOrder, String> {

}
