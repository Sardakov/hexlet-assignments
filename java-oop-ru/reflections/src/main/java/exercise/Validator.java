package exercise;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.IntStream;

// BEGIN
public class Validator {
    public static List<String> validate(Address object) {
        List<String> result = new ArrayList<>();
        Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (var field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    var value = field.get(object);
                    if (value == null) {
                        result.add(field.getName());
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return result;
    }
}


