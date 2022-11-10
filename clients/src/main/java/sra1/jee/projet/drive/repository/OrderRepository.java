package sra1.jee.projet.drive.repository;

import org.springframework.data.repository.CrudRepository;

import sra1.jee.projet.drive.model.Order;


public interface OrderRepository extends CrudRepository<Order, Integer> {
    Iterable<Order> findByClientId(long clientId);
}
