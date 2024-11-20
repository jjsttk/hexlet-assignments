package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag {

    private final String tagBody;
    private final List<Tag> childrenList;

    public PairedTag(String tagName, Map<String, String> attributes, String tagBody, List<Tag> childrenList) {
        this.tagName = tagName;
        this.attributes = attributes;
        this.tagBody = tagBody;
        this.childrenList = childrenList;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.append(tagBody);
        if (childrenList != null && !childrenList.isEmpty()) {
            childrenList.forEach(child -> result.append(child.toString()));
        }
        result.append("</").append(tagName).append(">");
        return result.toString();
    }


}
// END
