package br.com.koradi.model.customer;

import br.com.koradi.model.order.Order;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * Customer Entity
 *
 * @author Cláudio Margulhano
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Accessors(chain = true)
@ToString
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @NonNull
  private String id;

  private String fullName;
  private String email;
  private LocalDate birthDate;
  private GenderType gender;
  private String phoneNumber;
  private String mobileNumber;
  @OneToOne private Address address;
  @OneToMany private Set<Order> orders;
}
