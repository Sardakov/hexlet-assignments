package exercise;

// BEGIN
public class LabelTag implements TagInterface{
    private String type;
    private String value;

    public LabelTag(String text, TagInterface textTag) {
        this.type = text;
        this.value = textTag.render();
    }
    @Override
    public String render() {
        return "<label>" + type + value + "</label>";
    }
}
// END
