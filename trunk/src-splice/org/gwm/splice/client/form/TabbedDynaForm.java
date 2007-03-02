package org.gwm.splice.client.form;

import org.gwm.splice.client.service.data.Attributes;

import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class TabbedDynaForm extends TabPanel implements IDataForm {
	
	private Attributes data;
	private Attributes schema;
	private TabInfo[] tabs;
	
	private IDataForm[] forms;

	// ///////////////////////////////////////////////////////////////////////////////

	public TabbedDynaForm() {
		super();
	}

	public TabbedDynaForm(TabInfo[] tabs, Attributes schema) {
		super();
		this.tabs = tabs;
		this.schema = schema;
		init();
	}

	public TabbedDynaForm(TabInfo[] tabs, Attributes schema, Attributes data) {
		super();
		this.tabs = tabs;
		this.schema = schema;
		this.data = data;
		init();
	}
	
	private void init() {
		clear();
		if(tabs == null || tabs.length == 0) {
			return;
		}
		forms = new IDataForm[tabs.length];
		for (int i = 0; i < tabs.length; i++) {
			Attributes sa = schema != null ? schema.extract(tabs[i].fields) : null;
			Attributes da = data != null ? data.extract(tabs[i].fields) : null;
			forms[i] = new DynaForm(sa, da);
			add((Widget) forms[i], tabs[i].title);
		}
	}

	// ///////////////////////////////////////////////////////////////////////////////

	public TabInfo[] getTabs() {
		return tabs;
	}

	public void setTabs(TabInfo[] tabs) {
		this.tabs = tabs;
	}

	public Attributes getData() {
		return data;
	}

	public Attributes getSchema() {
		return schema;
	}

	public void setData(Attributes data) {
		this.data = data;
	}

	public void setSchema(Attributes schema) {
		this.schema = schema;
	}
	
	public void addListener(IDataFormListener listener) {
		if(forms == null) {
			return;
		}
		for (int i = 0; i < forms.length; i++) {
			forms[i].addListener(listener);
		}
	}

	public void removeListener(IDataFormListener listener) {
		if(forms == null) {
			return;
		}
		for (int i = 0; i < forms.length; i++) {
			forms[i].removeListener(listener);
		}
	}

	// ///////////////////////////////////////////////////////////////////////////////

	public class TabInfo {
		private String title;
		private String fields;
		
		public TabInfo(String title, String fields) {
			this.title = title;
			this.fields = fields;
		}

		public String getFields() {
			return fields;
		}
		public void setFields(String fields) {
			this.fields = fields;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
		
	}


}
