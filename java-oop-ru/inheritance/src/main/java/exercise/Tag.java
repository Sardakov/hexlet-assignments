package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    String nameTag;
    Map<String, String> attribute;

    public String getNameTag() {
        return nameTag;
    }

    public Map<String, String> getAttribute() {
        return attribute;
    }

    public Tag(String nameTag, Map<String, String> attribute) {
        this.nameTag = nameTag;
        this.attribute = attribute;
    }
}
// END
