/*
 * Copyright (c) 2006-2007 Luciano Broussal <luciano.broussal AT gmail.com>
 * (http://www.gwtwindowmanager.org)
 * 
 * Main Contributors :
 *      Johan Vos,Andy Scholz,Marcelo Emanoel  
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *  
 */
package org.gwm.client;

import org.gwm.client.event.GDialogChoiceListener;
import org.gwm.client.impl.DefaultGDialog.Option;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.UIObject;

/**
 * Provides the behavior handles to interact with a DefaultGDialog.
 */
public interface GDialog extends GFrame {

    public final int ERROR_MESSAGE = 1;

    public final int INFORMATION_MESSAGE = 2;

    public final int WARNING_MESSAGE = 3;

    public final int QUESTION_MESSAGE = 4;

    public final int PLAIN_MESSAGE = 5;

    public final int OK_OPTION_TYPE = 4;

    public final int YES_NO_OPTION_TYPE = 5;

    public final int YES_NO_CANCEL_OPTION_TYPE = 6;

    public final int OK_CANCEL_OPTION_TYPE = 7;

    public final Option OK_OPTION = new Option("Ok");

    public final Option YES_OPTION = new Option("Yes");

    public final Option NO_OPTION = new Option("No");

    public final Option CANCEL_OPTION = new Option("Cancel");

    public static final String YES_OPTION_LABEL = "Yes";

    public static final String NO_OPTION_LABEL = "No";

    public static final String CANCEL_OPTION_LABEL = "Cancel";

    /**
     * Shows the dialog.
     * 
     */
    public void show();

    /**
     * Sets the message type : ERROR_MESSAGE, INFORMATION_MESSAGE ... Implied
     * the built-in icon that will be displayed if no one is provided.
     * 
     * @param messageType
     */
    public void setMessageType(int messageType);

    /**
     * @return the message type.
     */
    public int getMessageType();

    /**
     * Define the user possibles actions.
     * 
     * @param optionType
     *            the predifined option type values, eg : YES_NO_OPTION_TYPE
     * @param options
     *            the button captions (usefull for i18n)
     */
    public void setOptions(int optionType, Object[] options);

    /**
     * @return the options.
     */
    public Object[] getOptions();

    /**
     * @return the option type.
     */
    public int getOptionType();

    /**
     * Sets the callback that will be called when user click an option button.
     * 
     * @param choiceListener
     */
    public void setGDialogChoiceListener(GDialogChoiceListener choiceListener);

    /**
     * Sets the message to diplay.
     * 
     * @param message
     */
    public void setMessage(Object message);

    /**
     * @return the message
     */
    public Object getMessage();

    /**
     * The selected value for input dialog type.
     * @return
     */
    public Object getSelectedValue();

    /**
     * @return the option button activated by the user.
     */
    public Object getSelectedOption();

    /**
     * The relative parent. If provided the dialog is diplayed at its center.
     * @param parent 
     */
    public void setParent(UIObject parent);

    /**
     * Overrides the built-in image.
     * @param icon
     */
    public void setIcon(Image icon);

    /**
     * @return the user provider image.
     */
    public Image getIcon();

    /**
     * The values that the user can choose. => build a ListBox with these values.
     * @param selectionValues
     */
    public void setSelectionValues(Object[] selectionValues);

    /**
     * @return the available choice diplayed in the dialog.
     */
    public Object[] getSelectionValues();

    /**
     * The initial choice : this is linked with the setSelectionValues()
     * @param initialValue
     */
    public void setInitialValue(Object initialValue);

    /**
     * @return The default choice among the possibles values.
     */
    public Object getInitialValue();

}
