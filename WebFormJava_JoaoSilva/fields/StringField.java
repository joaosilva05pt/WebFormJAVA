
package fields;

import validators.Validator;

public class StringField extends Field<String> {
    public StringField(String label, Validator[] validators) {
        super(label, validators);
    }

    @Override
    public String renderHTML() {
        return String.format("<label for='%s'>%s:</label>\n<input name='%s' type='text' value='%s'/><br>",
                getLabel().toLowerCase(), getLabel(), getLabel().toLowerCase(), getData() == null ? "" : getData());
    }
}
