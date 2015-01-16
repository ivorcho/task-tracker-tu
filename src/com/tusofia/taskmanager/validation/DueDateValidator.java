package com.tusofia.taskmanager.validation;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dueDateValidator")
public class DueDateValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		if (value == null) {
            return;
        }
		Date date = (Date) value;
		if (date.before(new Date())) {
            throw new ValidatorException(
                   new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date range error!","Due date cannot be before today!"));
        }
	}

}
