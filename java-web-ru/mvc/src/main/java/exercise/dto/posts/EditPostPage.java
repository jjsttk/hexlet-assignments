package exercise.dto.posts;

import java.util.List;
import java.util.Map;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// BEGIN
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EditPostPage {
    private String name;
    private String body;
    Map<String, List<ValidationError<Object>>> errors;
}
// END
