package digital.agenteight.bigbosslifestyle.transaction;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
//@Slf4j
@ToString
//@NoArgsConstructor
//@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "Name")
    private String name;

    @NonNull
    @Column(name = "Total")
    private Double amount;

    @NonNull
    @Column(name = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NonNull
    @Column(name = "Description")
    private String description;

    @NonNull
    @Column(name = "type")
    private String type;

    @NonNull
    @Column(name = "my_user_id")
    private Integer userId;

    public Transaction(@NonNull String name, @NonNull Double amount, @NonNull Date date, @NonNull String description, @NonNull String type) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.type = type;
    }

    public Transaction() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
