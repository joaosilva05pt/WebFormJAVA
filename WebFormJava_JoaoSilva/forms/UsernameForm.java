package forms;

import fields.StringField;
import fields.NumberField;
import validators.Length;
import validators.Required;
import validators.NumberRange;
import validators.*;

public class UsernameForm extends Form {
    public UsernameForm() {
        super();
        this.put("username", new StringField("Username", new Validator[]{new Length(3)}));
        this.put("email", new StringField("Email", new Validator[]{new Required()}));
        this.put("age", new NumberField("Age", new Validator[]{new NumberRange(16, 99)}));
    }
}
