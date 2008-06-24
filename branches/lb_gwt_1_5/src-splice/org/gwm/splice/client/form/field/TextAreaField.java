package org.gwm.splice.client.form.field;

import org.gwm.splice.client.form.IFormField;

import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class TextAreaField extends AbstractFormField implements IFormField {

	private TextArea textArea;
	
	protected Widget getFieldWidget() {
		textArea = new TextArea();
		return textArea;
	}

	public Object getValue() {
		return textArea.getText();
	}

	public void setValue(Object value) {
		textArea.setText((String)value);
	}

}
