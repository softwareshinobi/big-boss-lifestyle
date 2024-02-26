package digital.agenteight.bigbosslifestyle.transaction;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;



import java.util.Date;
import java.util.Objects;


@Entity
@Data
@Slf4j
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    @Column(name = "Name")
    String name;

    @NonNull
    @Column(name = "Total")
    Double amount;

    @NonNull
    @Column(name = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date date;

    @NonNull
    @Column(name = "Description")
    String description;

    @NonNull
    @Column(name = "type")
    String type;

    @NonNull
    @Column(name = "my_user_id")
    Integer userId;

    public Transaction(@NonNull String name, @NonNull Double amount, @NonNull Date date, @NonNull String description, @NonNull String type) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && name.equals(that.name)
                && description.equals(that.description) && date.equals(that.date)
                && amount.equals(that.amount) && type.equals(that.type)
                && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, amount, type, date, userId);
    }
}

