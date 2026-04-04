package com.ecommerce_oms.domain.Service;

import com.ecommerce_oms.domain.entity.Inventory;
import com.ecommerce_oms.domain.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InventoryService
{
    private InventoryRepository inventoryRepository;
    public InventoryService (InventoryRepository inventoryRepository)
    {
        this.inventoryRepository=inventoryRepository;
    }
    @Transactional
    public Inventory reserveStock(Long productId, int quantity)
    {
        Inventory inventory=inventoryRepository.findById(productId).orElseThrow(()->new RuntimeException ("Inventory not found for productId:"+productId));

        if(inventory.getAvailableQuantity()<quantity)
        {
           throw  new RuntimeException("Insufficient stock for productId: " + productId);
        }
        inventory.setAvailableQuantity(inventory.getAvailableQuantity()-quantity);
        inventory.setReservedQuantity(inventory.getReservedQuantity()+quantity);
        return inventoryRepository.save(inventory);

        }
public Inventory releaseStock(Long productId, int quantity)
{
        Inventory inventory=inventoryRepository.findById(productId).orElseThrow(()->new RuntimeException("Inventory not found for productId:"+productId));
        if(inventory.getReservedQuantity()<quantity)
        {
            throw  new RuntimeException("Cannot release more stock than reserved for productId: " + productId);

        }
        inventory.setAvailableQuantity(inventory.getAvailableQuantity()+quantity);
        inventory.setReservedQuantity(inventory.getReservedQuantity()-quantity);
        return inventoryRepository.save(inventory);

}

public Inventory confirmStock(Long productId, int quantity)
{
        Inventory inventory=inventoryRepository.findById(productId).orElseThrow(()->new RuntimeException("Invalid productId"+productId));
        if(inventory.getReservedQuantity()<quantity){
            throw  new RuntimeException("Cannot release more stock than reserved for productId: " + productId);

        }
        inventory.setReservedQuantity(inventory.getReservedQuantity()-quantity);


        return inventoryRepository.save(inventory);
}




}
