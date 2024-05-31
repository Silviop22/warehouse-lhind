package de.dlh.lhind.warehouse.item.repostitory;

import de.dlh.lhind.warehouse.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
