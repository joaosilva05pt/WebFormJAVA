package validators;

public class Length extends Validator {
    private final int min;

    public Length(int min) {
        this.min = min;
    }

    @Override
    public void validate(Object data) throws Exception {
        if (data == null || ((String) data).length() < min) {
            throw new Exception("Length must be at least " + min + " characters");
        }
    }
}
