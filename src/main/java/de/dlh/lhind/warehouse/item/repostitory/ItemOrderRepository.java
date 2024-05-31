package de.dlh.lhind.warehouse.item.repostitory;

import de.dlh.lhind.warehouse.item.model.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {
}
