package de.dlh.lhind.warehouse.item.service;

import de.dlh.lhind.warehouse.item.model.Item;
import de.dlh.lhind.warehouse.item.model.ItemDto;
import de.dlh.lhind.warehouse.item.model.ItemOrder;
import de.dlh.lhind.warehouse.item.repostitory.ItemOrderRepository;
import de.dlh.lhind.warehouse.item.repostitory.ItemRepository;
import de.dlh.lhind.warehouse.shared.fault.model.BadRequest;
import de.dlh.lhind.warehouse.shared.fault.model.ResourceNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemOrderRepository itemOrderRepository;

    public ItemService(ItemRepository itemRepository, ItemOrderRepository itemOrderRepository) {
        this.itemRepository = itemRepository;
        this.itemOrderRepository = itemOrderRepository;
    }

    @Transactional
    public void bookItem(ItemDto itemDto) throws ResourceNotFound, BadRequest {
        Item requestedItem = itemRepository.findById(itemDto.getId()).orElseThrow(() -> new ResourceNotFound("Item not found"));
        if (requestedItem.getStockQuantity() < itemDto.getQuantity()) {
            throw new BadRequest("Not enough items in stock");
        }

        requestedItem.setStockQuantity(requestedItem.getStockQuantity() - itemDto.getQuantity());
        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setItem(requestedItem);
        itemOrder.setRequestedQuantity(itemDto.getQuantity());
        itemOrder.setUserIp(itemDto.getUserIp());

        itemOrderRepository.save(itemOrder);
        itemRepository.save(requestedItem);
    }

    public ItemDto getById(Long id) throws ResourceNotFound {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Item not found"));
        return new ItemDto.Builder()
                .withId(item.getId())
                .withName(item.getName())
                .withDescription(item.getDescription())
                .withPrice(item.getPrice())
                .withQuantity(item.getStockQuantity())
                .build();
    }

}
