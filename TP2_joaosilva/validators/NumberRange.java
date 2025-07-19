package validators;
public class NumberRange extends Validator {
    private int min, max;

    public NumberRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(Object data) throws Exception {
        if (data == null) {
            throw new Exception("Error: value empty");
        }
        int value = Integer.parseInt(data.toString());
        if (value < min || value > max) {
            throw new Exception("Error: value not in range");
        }
    }
}
