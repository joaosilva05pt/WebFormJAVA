package validators;
public class Required extends Validator {
    @Override
    public void validate(Object data) throws Exception {
        if (data == null || data.toString().trim().isEmpty()) {
            throw new Exception("Error: value empty");
        }
    }
}

