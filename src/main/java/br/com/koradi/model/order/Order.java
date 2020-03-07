package br.com.koradi.model.order;

import br.com.koradi.model.customer.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

/** @author Cláudio Margulhano */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "orders")
public class Order implements Serializable {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  private BigDecimal price;

  private LocalDateTime orderDate;

  @ManyToOne(fetch = EAGER)
  private Customer customer;

  @OneToMany(fetch = EAGER, cascade = ALL)
  private Set<OrderProduct> orderProducts;
}
