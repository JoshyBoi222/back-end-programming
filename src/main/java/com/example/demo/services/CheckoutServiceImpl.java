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
    public PurchaseResponse checkout(Purchase purchase) {

        // get the cart and cart items
        Cart cart = purchase.getCart();
        Set <CartItem> cartItems = purchase.getCartItems();

        // check if cart is null or empty before generating tracking number
        if (cartItems == null || cartItems.isEmpty()) {
            return new PurchaseResponse("Checkout Error - You Choose Nothing To Buy");
        }

        // check if party size is at least 1 before generating tracking number
        if (cart.getParty_size() < 1) {
            return new PurchaseResponse("Checkout Error - Party Size Must Be At Least 1");
        }

        // generate and set tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // set cart status to ordered
        cart.setStatus(StatusType.ordered);

        // populate cart with items
        cartItems.forEach(item -> cart.add(item));

        // get the customer
        Customer customer = purchase.getCustomer();

        // save cart info to database
        cartRepository.save(cart);

        // populate customer with cart
        customer.add(cart);

        // return the order tracking number
        return new PurchaseResponse(orderTrackingNumber);

    }
    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

}


