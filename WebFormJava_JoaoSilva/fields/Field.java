package fields;

import validators.Validator;

public abstract class Field<T> {
    private String label;
    private T data;
    private Validator[] validators;

    public Field(String label, Validator[] validators) {
        this.label = label;
        this.validators = validators;
    }

    public void setData(Object data) {
        try {
            this.data = (T) data; 
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid data type for field: " + label);
        }
    }

    public T getData() {
        return data;
    }

    public void validate() throws Exception {
        for (Validator validator : validators) {
            validator.validate(data);
        }
    }

    public String getLabel() {
        return label;
    }

    public abstract String renderHTML();
}
