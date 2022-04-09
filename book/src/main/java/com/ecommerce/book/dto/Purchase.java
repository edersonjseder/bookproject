package com.ecommerce.book.dto;

import com.ecommerce.book.entity.Address;
import com.ecommerce.book.entity.Customer;
import com.ecommerce.book.entity.Order;
import com.ecommerce.book.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
