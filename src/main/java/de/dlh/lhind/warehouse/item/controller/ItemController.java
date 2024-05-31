package de.dlh.lhind.warehouse.item.controller;

import de.dlh.lhind.warehouse.item.model.ItemDto;
import de.dlh.lhind.warehouse.item.service.ItemService;
import de.dlh.lhind.warehouse.shared.fault.model.BadRequest;
import de.dlh.lhind.warehouse.shared.fault.model.ResourceNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long id) throws ResourceNotFound {
        return ResponseEntity.ok(service.getById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateItem(@PathVariable Long id, @Validated @RequestBody ItemDto itemDto) throws ResourceNotFound, BadRequest {
        itemDto.setId(id);
        service.bookItem(itemDto);
        return ResponseEntity.noContent().build();
    }
}
