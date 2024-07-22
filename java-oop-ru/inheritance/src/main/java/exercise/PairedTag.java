package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag{
    String body;
    List<Tag> children;
    public PairedTag(String nameTag, Map<String, String> attribute, String body, List<Tag> children) {
        super(nameTag, attribute);
        this.body = body;
        this.children = new ArrayList<>(children);
    }

    @Override
    public String toString() {
//        String formatted = String.format("<%s %s=\"%s\">", nameTag, )
        StringBuilder builder = new StringBuilder("<" + nameTag);
        for (Map.Entry<String, String> item: attribute.entrySet()) {
            builder.append(" " + item.getKey() + "=" + "\"" + item.getValue() + "\"");
        }
        builder.append(">");
        for (var child : children) {
            builder.append("<" + child.getNameTag() + " ");
            var items = child.attribute.entrySet();
            for (var item : items) {
                builder.append(item.getKey() + "=\"" + item.getValue() + "\">");
            }
        }
        builder.append(String.format("%s</%s>", body, nameTag));
        return builder.toString();
    }
}
// END
