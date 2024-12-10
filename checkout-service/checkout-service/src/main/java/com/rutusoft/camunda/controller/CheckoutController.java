
package com.rutusoft.camunda.controller;

import com.rutusoft.camunda.pojo.Order;
import com.rutusoft.camunda.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutController {
	
	@Autowired
	private com.rutusoft.camunda.service.OrderService checkoutService;
	
	@PostMapping("/checkout")
	public Response checkoutOrder(@RequestBody Order order ) {
		return checkoutService.checkoutOrder(order);
	}
}