package com.ecommerce.book.service;

import com.ecommerce.book.dto.Purchase;
import com.ecommerce.book.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
