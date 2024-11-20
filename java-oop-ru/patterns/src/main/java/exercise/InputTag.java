package exercise;

// BEGIN
public class InputTag implements TagInterface{
    private final String input;
    private final String value;

    public String getInput() {
        return input;
    }

    public String getValue() {
        return value;
    }

    public InputTag(String type, String value) {
        this.input = type;
        this.value = value;
    }

    // <input type="submit" value="Save">
    @Override
    public String render() {
        return "<input type=\"" + input + "\" value=\"" + value + "\">";
    }
}
// END
