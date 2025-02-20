package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

// BEGIN
@Data
public class GuestCreateDTO {
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
}
// END
