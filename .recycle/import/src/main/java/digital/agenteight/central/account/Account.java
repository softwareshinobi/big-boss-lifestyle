package digital.agenteight.central.account;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Entity
@ToString
@NoArgsConstructor
@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NonNull
    @Column(name = "first_name")
    String firstName;
    
    @NonNull
    @Column(name = "last_name")
    String lastName;
    
    @NonNull
    @Column(name = "email_address")
    String email;
    
    @NonNull
    @Column(name = "password")
    String password;

}