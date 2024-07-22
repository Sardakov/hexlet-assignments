package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag{
    public SingleTag(String nameTag, Map<String, String> attribute) {
        super(nameTag, attribute);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("<" + nameTag);
        for (Map.Entry<String, String> item: attribute.entrySet()) {
            builder.append(" " + item.getKey() + "=" + "\"" + item.getValue() + "\"");
        }
        builder.append(">");
        return builder.toString();
    }
}
// END
