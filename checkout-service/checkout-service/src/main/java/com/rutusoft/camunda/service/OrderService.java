package com.rutusoft.camunda.service;

import com.rutusoft.camunda.pojo.Order;
import com.rutusoft.camunda.pojo.Response;

public interface OrderService {
    Response checkoutOrder(Order order);

    void updateOrderStatus(Order order, String status);
}
