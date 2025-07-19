package fields;

import validators.Validator;

public class PasswordField extends Field<String> {

    public PasswordField(String label, Validator[] validators) {
        super(label, validators);
    }

    @Override
    public String renderHTML() {
        return String.format(
            "<label for='%s'>%s:</label><input name='%s' type='password' value='%s'/>",
            getLabel().toLowerCase(), getLabel(), getLabel().toLowerCase(), getData() != null ? getData() : ""
        );
    }

}
