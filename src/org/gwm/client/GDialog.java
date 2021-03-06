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
import org.gwm.client.impl.DefaultGDialog;
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
     * Sets the message type : {@link GDialog#ERROR_MESSAGE}, {@link GDialog#INFORMATION_MESSAGE}...
     * Default: {@link GDialog#INFORMATION_MESSAGE} if no message type is set
     * 
     * @param messageType the message type of the dialog
     */
    public void setMessageType(int messageType);

    /**
     * Gets the messages type of the dialog.
     * @return the message type.
     */
    public int getMessageType();

    /**
     * Defines the user's possibles actions.
     * Default is {@link GDialog#OK_OPTION_TYPE}.
     * 
     * @param optionType
     *            the predifined option type values, eg : {@link GDialog#YES_NO_OPTION_TYPE}, {@link GDialog#YES_NO_CANCEL_OPTION_TYPE}...
     * @param options
     *            optional: the button captions (usefull for i18n), if null uses the built in English labels
     */
    public void setOptions(int optionType, Object[] options);

    /**
     * Gets the options of the dialog.
     * @return the options set for this dialog like {@link GDialog#OK_OPTION}, {@link GDialog#YES_OPTION}....
     */
    public Object[] getOptions();

    /**
     * Gets the option type of the dialog.
     * @return the option type set for this dialog like {@link GDialog#YES_NO_OPTION_TYPE}, {@link GDialog#YES_NO_CANCEL_OPTION_TYPE}.
     */
    public int getOptionType();

    /**
     * Sets the callback that will be called when user clicks an option button.
     * 
     * @param choiceListener the listener which will be called on user action
     */
    public void setGDialogChoiceListener(GDialogChoiceListener choiceListener);

    /**
     * Sets the message to display. This can be a normal string, HTML string or a widget 
     * 
     * @param message the message object to display in the dialog
     */
    public void setMessage(Object message);

    /**
     * Gets the message displayed in the dialog.
     * @return the message displayed in the dialog, this can be a string or a widget
     */
    public Object getMessage();

    /**
     * The selected value for input dialogs created through a showInputDialog() method of {@link DefaultGDialog} .
     * @return the selected value of an input dialog
     */
    public Object getSelectedValue();

    /**
     * Gets the selected option the user has selected.
     * @return the option button pressed by the user, eg {@link GDialog#OK_OPTION}, {@link GDialog#YES_OPTION}...
     */
    public Object getSelectedOption();

    /**
     * The relative parent. If provided the dialog is displayed at its center.
     * @param parent the parent of the dialog 
     */
    public void setParent(UIObject parent);

    /**
     * Overrides the built-in image.
     * @param icon the icon displayed in the dialog
     */
    public void setIcon(Image icon);

    /**
     * Gets the icon of the image.
     * @return the user provided image.
     */
    public Image getIcon();

    /**
     * The values that the user can choose in an input dialog. => builds a ListBox with these values.
     * @param selectionValues the values to select from of an input dialog
     */
    public void setSelectionValues(Object[] selectionValues);

    /**
     * Gets the available choices of the input dialog.
     * @return the available choices displayed in the input dialog.
     */
    public Object[] getSelectionValues();

    /**
     * Sets the initial selected choice of the input dialog : this is linked with the {@link GDialog#setSelectionValues(Object[])}
     * @param initialValue the initial value of the dialog
     */
    public void setInitialValue(Object initialValue);

    /**
     * Gets the initial value of the input dialog.
     * @return The default choice among the possibles values of an input dialog.
     */
    public Object getInitialValue();

}
