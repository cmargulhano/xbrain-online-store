package br.com.koradi.model.order;

import br.com.koradi.model.product.Product;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * OrderProduct Entity
 *
 * @author Cláudio Margulhano
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_product")
public class OrderProduct implements Serializable {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @ManyToOne private Order order;

  @ManyToOne private Product product;
}
