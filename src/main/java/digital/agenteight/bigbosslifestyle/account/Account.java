package digital.agenteight.bigbosslifestyle.account;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


@Entity
@ToString
@NoArgsConstructor
@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user")
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NonNull
    @Column(name = "FirstName")
    String firstName;
    @NonNull
    @Column(name = "LastName")
    String lastName;
    @NonNull
    @Column(name = "email")
    String email;
    @NonNull
    @Column(name = "password")
    String password;


    private void addUser(Account myUser) {
        myUser.add(myUser);
    }

    private void add(Account myUser) {
    }

    private void removeUser(Account myUser) {
        myUser.remove(myUser);

    }

    private void remove(Account myUser) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account myUser = (Account) o;
        return Objects.equals(firstName, myUser.firstName) && Objects.equals(lastName, myUser.lastName) && email.equals(myUser.email) && Objects.equals(password, myUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password);
    }

}