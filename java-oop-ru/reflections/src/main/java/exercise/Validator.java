package exercise;


import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.text.Annotation;
import java.util.*;

// BEGIN
public class Validator {


    public static Map<String, List<String>> advancedValidate(Address address) {
        String nonNullMessage = "can not be null";
        String minLengthMessage = "length less than 4";
        Map<String, List<String>> errors = new HashMap<>();
        Field[] fields = address.getClass().getDeclaredFields();
        Field[] annotatedMinLengthFields = Arrays.stream(
                        address.getClass()
                                .getDeclaredFields())
                .filter(k-> k.isAnnotationPresent(MinLength.class))
                .toArray(Field[]::new);
        try {
            for (Field field : annotatedMinLengthFields) {
                field.setAccessible(true);
                if (field.get(address).toString().length() < 4) {
                    errors.put(field.getName(), new ArrayList<>());
                    errors.get(field.getName()).add(minLengthMessage);
                }
            }
            var listNullFields = validate(address);
            for (var field : listNullFields) {
                errors.put(field, new ArrayList<>());
                errors.get(field).add(nonNullMessage);
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return errors;
    }

    public static List<String> validate(Address address) {
        List<String> errors = new ArrayList<>();

        Field[] annotatedNonNullFields = Arrays.stream(
                address.getClass()
                        .getDeclaredFields())
                .filter(k -> k.isAnnotationPresent(NotNull.class))
                .toArray(Field[]::new);
        try {
            for (Field annotatedNonNullField : annotatedNonNullFields) {
                annotatedNonNullField.setAccessible(true);
                if (annotatedNonNullField.get(address) == null) {
                    errors.add(annotatedNonNullField.getName());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return errors;
    }
}
// END
