package com.ecommerce_oms.domain.Service;
import com.ecommerce_oms.domain.entity.Order;
import com.ecommerce_oms.domain.entity.OrderItem;
import com.ecommerce_oms.domain.entity.Product;
import com.ecommerce_oms.domain.entity.User;
import com.ecommerce_oms.domain.repository.OrderItemRepository;
import com.ecommerce_oms.domain.repository.OrderRepository;
import com.ecommerce_oms.domain.repository.ProductRepository;
import com.ecommerce_oms.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;


@Service
public class OrderService
{
    private InventoryService inventoryService;
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private ProductRepository productRepository;

    public OrderService(InventoryService inventoryService, UserRepository userRepository, OrderRepository orderRepository,OrderItemRepository orderItemRepository,ProductRepository productRepository)
    {
        this.inventoryService = inventoryService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository=orderItemRepository;
        this.productRepository=productRepository;
    }
    @Transactional
    public void placeOrder(Long userId,Long productId,int quantity)
 {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found" + userId));
        Order order = new Order();

        order.setUser(user);
        order.setStatus("CREATED");
        order.setTotalAmount(BigDecimal.ZERO);
        order.setOrderNumber(UUID.randomUUID().toString());

        orderRepository.save(order);
        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product not found"+productId));
        OrderItem orderItem=new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setPriceAtOrderTime(product.getPrice());
        orderItem.setQuantity(quantity);
        order.getOrderItems().add(orderItem);
        orderItemRepository.save(orderItem);
        BigDecimal lineTotal= orderItem.getPriceAtOrderTime().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
        order.setTotalAmount(lineTotal);
        orderRepository.save(order);
        inventoryService.reserveStock( productId, quantity);

    }

}