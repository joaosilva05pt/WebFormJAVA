
package fields;

import validators.Validator;

public class NumberField extends Field<Integer> {
    public NumberField(String label, Validator[] validators) {
        super(label, validators);
    }

    @Override
    public String renderHTML() {
        return String.format("<label for='%s'>%s:</label>\n<input name='%s' type='number' value='%s'/><br>",
                getLabel().toLowerCase(), getLabel(), getLabel().toLowerCase(), getData() == null ? "" : getData());
    }
}
