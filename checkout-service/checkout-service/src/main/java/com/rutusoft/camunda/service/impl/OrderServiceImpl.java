package com.rutusoft.camunda.service.impl;

import java.util.Optional;

import com.rutusoft.camunda.entity.OrderInfo;
import com.rutusoft.camunda.pojo.Item;
import com.rutusoft.camunda.pojo.Order;
import com.rutusoft.camunda.pojo.Response;
import com.rutusoft.camunda.repository.OrderInfoRepository;
import com.rutusoft.camunda.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderInfoRepository orderInfoRepository;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	private static final String TOPIC_ORDER_CHECKOUT = "order-placed-event";
	private static final String STATUS_ORDER_INPROGRESS = "In progress";

	@Override
	public Response checkoutOrder(Order order) {
		LOGGER.info("Checking out order id {} ", order.getOrderId());

		LOGGER.info("Sending order to Kafka topic: {}", TOPIC_ORDER_CHECKOUT);

		// Calculate total amount
		Double totalAmount = order.getItems().stream()
				.mapToDouble(Item::getAmount)
				.sum();
		order.setTotalAmount(totalAmount);

		// Send message to Kafka
		kafkaTemplate.send(TOPIC_ORDER_CHECKOUT, order);

		// Update order status
		updateOrderStatus(order, STATUS_ORDER_INPROGRESS);

		// Prepare and return response
		Response response = new Response();
		response.setData(order.getOrderId());
		response.setMessage("Order placed successfully");
		return response;
	}

	@Override
	public void updateOrderStatus(Order order, String status) {
		OrderInfo orderInfo = null;
		LOGGER.info("Order id (trace id): {}", order.getOrderId());

		// Retrieve existing order info if available
		Optional<OrderInfo> orderInfoData = orderInfoRepository.findByTraceId(order.getOrderId());
		if (orderInfoData.isPresent()) {
			LOGGER.info("Updating existing order");
			orderInfo = orderInfoData.get();
		} else {
			LOGGER.info("Creating new order");
			orderInfo = new OrderInfo();
			orderInfo.setCustId(order.getCustomer().getId());
			orderInfo.setTotalAmount(order.getTotalAmount());
			orderInfo.setTraceId(order.getOrderId());
		}

		LOGGER.info("Updating order {} status to {}", orderInfo.getTraceId(), status);

		// Update status and save
		orderInfo.setStatus(status);
		orderInfoRepository.saveAndFlush(orderInfo);
	}
}
