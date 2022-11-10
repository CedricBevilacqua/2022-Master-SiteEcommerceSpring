package sra1.jee.projet.drive.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sra1.jee.projet.drive.model.OrderLine;


public interface OrderLineRepository extends CrudRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(int orderId);
}
