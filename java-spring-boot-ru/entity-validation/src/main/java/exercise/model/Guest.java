package exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;




@Entity
@Table(name = "guests")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Guest {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    // BEGIN
    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Email(message = "Некорректный формат электронной почты")
    private String email;

    @NotNull(message = "Номер телефона не может быть пустым")
    @Pattern(regexp = "\\+\\d{11,13}", message = "Номер телефона должен начинаться с + и содержать от 11 до 13 цифр")
    private String phoneNumber;

    @Pattern(regexp = "\\d{4}", message = "Номер клубной карты должен состоять из четырех цифр")
    private String clubCard;

    @NotNull(message = "Дата истечения срока действия карты не может быть пустой")
    @FutureOrPresent
    private LocalDate cardValidUntil;
    // END

    @CreatedDate
    private LocalDate createdAt;
}
