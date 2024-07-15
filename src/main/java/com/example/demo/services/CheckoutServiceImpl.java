package com.example.demo.services;

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
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        //get the customer, cart and cart items info
        Customer customer = purchase.getCustomer();
        Cart cart = purchase.getCart();
        Set<CartItem> cartItems = purchase.getCartItems();
        //check if cart is empty and return a message instead of tracking number if it is
        if (cartItems.isEmpty()) {
            return new PurchaseResponse("Order not purchased: Customer, Cart or Cart Items must not be null");
        }
        //check if party size is at least 1
        if (cart.getParty_size() < 1) {
            return new PurchaseResponse("Order not purchased: Party size must be 1 or more");
        }
        //generate and set tracking number
        String orderTrackingNumber = generateCartTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        //set cart status to ordered
        cart.setStatus(StatusType.ordered);

        //populate cart with items
        for (CartItem cartItem : cartItems) {
            cart.addCartItem(cartItem);
        }

        //populate customer with cart
        customer.addCart(cart);

        //save info to database
        customerRepository.save(customer);

        //return a response
        return new PurchaseResponse(orderTrackingNumber);
    }
    private String generateCartTrackingNumber() {

        return UUID.randomUUID().toString();
    }
}

