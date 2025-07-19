# WebFormJAVA 
Forms are key to the web data acquisition process.
Several steps are required to complete the process, namely:
creating the form in HTML, user input, and
validation and storage of the data by the web server.
In this practical work, we will create the Form class, which represents a
model in this domain, to assist in creating and validating data in
web forms. Of course, it is necessary to apply the concepts and
techniques learned in OOP.

A form consists of several fields. Each field has an associated label, which allows identification of the field. Each field can be of the StringField or NumberField type, depending on the input data type. Data validation is also necessary, and if a field is invalid, a validation error is added to the error list. Validators are created to validate each field, such as Length, Required, or NumberRange. Additionally, the form allows rendering in HTML and displaying the entered data in JSON format.