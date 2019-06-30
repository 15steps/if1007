package repository;

import model.OrderP;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderP, String> {

    List<OrderP> findAllByRestaurantId(String id);
}
