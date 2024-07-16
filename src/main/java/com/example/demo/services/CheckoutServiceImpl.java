package com.example.demo.services;
import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import com.example.demo.services.Purchase;
import com.example.demo.services.PurchaseResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;import java.util.UUID;
@Service
public class CheckoutServiceImpl implements CheckoutService {
    private CustomerRepository customerRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        // get the cart info
        Cart cart = purchase.getCart();

        // generate and set tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // populate cart with items
        Set <CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> cart.addCartItem(item));

        // set cart status to ordered
        cart.setStatus(StatusType.ordered);

        // get customer info
        Customer customer = purchase.getCustomer();
        // save info to database
        cartRepository.save(cart);
        // populate customer with cart
        customer.addCart(cart);

        // check if cart is null or empty
        if (cartItems == null || cartItems.isEmpty()) {
            return new PurchaseResponse("Order not purchased: Cart Items must not be empty");
        }

        // check if party size is at least 1
        if (cart.getParty_size() < 1) {
            return new PurchaseResponse("Order not purchased: Party size must be 1 or more");
        }

        // return tracking number
        return new PurchaseResponse(orderTrackingNumber);

    }
    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

}


