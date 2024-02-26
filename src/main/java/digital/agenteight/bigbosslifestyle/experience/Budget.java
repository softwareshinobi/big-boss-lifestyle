package digital.agenteight.bigbosslifestyle.experience;

import digital.agenteight.bigbosslifestyle.transaction.Transaction;
import digital.agenteight.bigbosslifestyle.user.MyUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


@Entity
@Data
@Slf4j
@ToString
@NoArgsConstructor @RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="budget")
public class Budget {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    @Column(name="Name")
    String name;

    @NonNull
    @Column(name="Amount")
    Double amount;

    @NonNull
    @Column(name="my_user_id")
    Integer userId;

    public Budget(@NonNull String name) {
        this.name = name;
    }

    public void addTransaction(Transaction trans){

    }

    public void removeTransaction(Transaction trans){

        log.debug("remove the transaction");
    }

    private void addUser(MyUser myUser) {

    }

    private void removeUser(MyUser myUser) {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Budget budget = (Budget) o;
        return Objects.equals(id, budget.id) && name.equals(budget.name)
                && userId.equals(budget.userId) && amount.equals(budget.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userId, amount);
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", userId=" + userId +
                '}';
    }
}
