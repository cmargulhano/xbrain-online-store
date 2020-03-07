package br.com.koradi.model.delivery;

import br.com.koradi.model.customer.Customer;
import br.com.koradi.model.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Delivery Entity
 *
 * @author Cláudio Margulhano
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "delivery")
public class Delivery implements Serializable {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  private BigDecimal price;

  private LocalDateTime orderDate;

  @ManyToOne private Customer customer;

  @OneToOne private Order order;
}
