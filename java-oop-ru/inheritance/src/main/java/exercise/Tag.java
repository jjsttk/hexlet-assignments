package exercise;

import java.util.Map;

// BEGIN
public abstract class Tag {
    String tagName;
    Map<String, String> attributes;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tagName);
        if (attributes != null && !attributes.isEmpty()) {
            attributes.forEach((key, value) ->
                    sb.append(" ")
                            .append(key)
                            .append("=\"")
                            .append(value)
                            .append("\""));
        }
        sb.append(">");
        return sb.toString();
    }
}
// END
