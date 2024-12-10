package com.rutusoft.camunda.repository;

import com.rutusoft.camunda.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
    Optional<OrderInfo> findByTraceId(String traceId);
}
