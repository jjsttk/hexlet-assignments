package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private final String label;
    private final TagInterface tag;


    public LabelTag(String label, TagInterface tag) {
        this.label = label;
        this.tag = tag;
    }



    // <label>Press Submit<input type="submit" value="Save"></label>
    @Override
    public String render() {
        return "<label>" + label + tag.render() + "</label>";

    }
}
// END
