package com.rutusoft.camunda.listner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rutusoft.camunda.pojo.Order;
import com.rutusoft.camunda.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class MessageListener {

	public static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);


	@Autowired
	private OrderService orderService;

	@Autowired
	private ObjectMapper objectMapper;

	private static final String STATUS_ORDER_PAID = "Paid";
	private static final String STATUS_ORDER_SHIPPED = "Shipped";
	private static final String STATUS_ORDER_COMPLETED = "Completed";

	/**
	 * Handles payment received events.
	 */
	@KafkaListener(topics = "payment-received-event", groupId = "order-service")
	@Transactional
	public void paymentReceived(String messageJson) {
		try {
			Order order = objectMapper.readValue(messageJson, Order.class);
			LOGGER.info("Updating order [{}] status to [{}]", order.getOrderId(), STATUS_ORDER_PAID);
			orderService.updateOrderStatus(order, STATUS_ORDER_PAID);
		} catch (Exception e) {
			LOGGER.error("Error processing paymentReceived event: {}", e.getMessage(), e);
		}
	}

	/**
	 * Handles goods shipped events.
	 */
	@KafkaListener(topics = "goods-shipped-event", groupId = "order-service")
	@Transactional
	public void orderShipped(String messageJson) {
		try {
			Order order = objectMapper.readValue(messageJson, Order.class);
//			LOGGER.info("Updating order [{}] status to [{}]", order.getOrderId(), STATUS_ORDER_SHIPPED);
			orderService.updateOrderStatus(order, STATUS_ORDER_SHIPPED);
		} catch (Exception e) {
			LOGGER.error("Error processing orderShipped event: {}", e.getMessage(), e);
		}
	}

	/**
	 * Handles order completed events.
	 */
	@KafkaListener(topics = "order-completed-event", groupId = "order-service")
	@Transactional
	public void orderCompleted(String messageJson) {
		try {
			LOGGER.info("Order completed event: {}", messageJson);
//			Order order = objectMapper.readValue(messageJson, Order.class);
//			LOGGER.info("Updating order [{}] status to [{}]", order.getOrderId(), STATUS_ORDER_COMPLETED);
//			orderService.updateOrderStatus(order, STATUS_ORDER_COMPLETED);
		} catch (Exception e) {
			LOGGER.error("Error processing orderCompleted event: {}", e.getMessage(), e);
		}
	}
}
