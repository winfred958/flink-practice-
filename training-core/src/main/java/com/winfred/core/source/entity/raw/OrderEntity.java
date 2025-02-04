package com.winfred.core.source.entity.raw;

import com.winfred.core.annotation.MockSourceName;
import com.winfred.core.source.entity.OrderJoinMock;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author winfred
 */
@MockSourceName(name = "qa_order_test")
public class OrderEntity implements OrderJoinMock {

  private static final long serialVersionUID = 5980053617720035282L;

  @Setter
  private String orderId;

  @Setter
  private Long serverTime;

  @Getter
  @Setter
  private String userId;

  @Getter
  @Setter
  private String orderStatus;
  @Getter
  @Setter
  private String payStatus;
  @Getter
  @Setter
  private String shipStatus;
  @Getter
  @Setter
  private String payType;

  @Override
  public String getOrderId() {
    if (null == orderId) {
      this.orderId = UUID.randomUUID().toString();
    }
    return orderId;
  }

  public Long getServerTime() {
    if (null == serverTime) {
      this.serverTime = System.currentTimeMillis();
    }
    return serverTime;
  }
}
