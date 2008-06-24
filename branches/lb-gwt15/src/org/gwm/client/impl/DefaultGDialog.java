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
 */
package org.gwm.client.impl;

import org.gwm.client.GDialog;
import org.gwm.client.event.GDialogChoiceListener;
import org.gwm.client.util.Gwm;
import org.gwm.client.util.GwmUtilities;
import org.gwm.client.util.widget.OverlayLayer;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The GWT default implementation for {@link GDialog}. This class providess
 * also built-in dialog methods showXXX() for message, confirm and input user
 * interactions.
 * 
 */

public class DefaultGDialog extends DefaultGFrame implements GDialog {

    private static final String DEFAULT_TITLE = "GDialog";

    private static DefaultGDialog internalDialog;

    private static OverlayLayer overlayLayer;

    private Object initialValue;

    private Object selectedValue;

    private Object[] selectionValues;

    private Option selectedOption;

    private int messageType = INFORMATION_MESSAGE;

    private int optionsType = OK_OPTION_TYPE;

    private GDialogChoiceListener choiceListener;

    private Object message;

    private Option[] options;

    private UIObject parent;

    private Image icon;

    private Object value;

    public static boolean wrapStringMessage;

    private static int defaultDialogWidth;

    private static int defaultDialogHeight;

    private static boolean overridesDialogSize;

    private static String defaultTheme = Gwm.getDefaultTheme();
    
    
    public DefaultGDialog() {
        this(DEFAULT_TITLE);
    }

    public DefaultGDialog(String caption) {
        super(caption);
        setTheme(Gwm.getDefaultTheme());
        setResizable(false);
        setMaximizable(false);
        setMinimizable(false);
        setClosable(false);
        if (isOverridesDialogSize()) {
            setSize(defaultDialogWidth, defaultDialogHeight);
        }
    }

    public void setIcon(Image icon) {
        this.icon = icon;

    }

    public Image getIcon() {
        return this.icon;
    }

    public void setInitialValue(Object initialValue) {
        this.initialValue = initialValue;

    }

    public Object getInitialValue() {
        return this.initialValue;
    }

    public Object getMessage() {
        return this.message;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
        icon = getIcon(messageType, null);
    }

    public int getMessageType() {
        return this.messageType;
    }

    public int getOptionType() {
        return this.optionsType;
    }

    public void setOptions(int optionType, Object[] options) {
        this.options = getOptions(optionType, options);
        this.optionsType = optionType;
    }

    private void setOptions(Option[] options) {
        this.options = options;
    }

    public Object[] getOptions() {
        return this.options;
    }

    public void setSelectionValues(Object[] selectionValues) {
        this.selectionValues = selectionValues;

    }

    public Object[] getSelectionValues() {
        return this.selectionValues;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;

    }

    public Object getSelectedValue() {
        return selectedValue;
    }

    public Object getSelectedOption() {
        return selectedOption;
    }

    public void setGDialogChoiceListener(GDialogChoiceListener choiceListener) {
        this.choiceListener = choiceListener;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public void setParent(UIObject parent) {
        this.parent = parent;

    }

    private static Image getIcon(int messageType, String imagePath) {
//        Image icon = new PNGImage(getImagePath(messageType, imagePath), 32, 32);
//        return icon;
        
        if (imagePath != null) {
            return new Image(imagePath);
        }
        
        GWmImageBundle imagesBundle = Gwm.getImageBundle();
        
        switch (messageType) {
        case INFORMATION_MESSAGE:
            return imagesBundle.information_icon().createImage();
        case QUESTION_MESSAGE:
            return imagesBundle.question_icon().createImage();
        case PLAIN_MESSAGE:
            return imagesBundle.text_icon().createImage();
        case WARNING_MESSAGE:
            return imagesBundle.warning_icon().createImage();
        case ERROR_MESSAGE:
            return imagesBundle.error_icon().createImage();
        default:
            return imagesBundle.unknown_icon().createImage();
        }
    }

    public void show() {
        _show(false);
    }

    public void setContent(String content) {
        throw new IllegalStateException(
                "Warning: user the setMessage(Object message) method instead");
    }

    public void setContent(Widget widget) {
        throw new IllegalStateException(
                "Warning: user the setMessage(Object message) method instead");
    }

    /**
     * Shows a confirmation dialog with
     * {@link GDialog#YES_NO_CANCEL_OPTION_TYPE}, no title and with the default
     * message type {@link GDialog#INFORMATION_MESSAGE}
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showConfirmDialog(UIObject parent, Object message,
            GDialogChoiceListener choiceListener) {
        showMessage(parent, message, null, QUESTION_MESSAGE, getOptions(
                YES_NO_CANCEL_OPTION_TYPE, null), null, choiceListener);
    }

    /**
     * Shows a confirmation dialog with the specified option types and with the
     * default message type {@link GDialog#INFORMATION_MESSAGE}
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param title
     *            the title of the dialog
     * @param optionType
     *            the option types of the dialog, eg.
     *            {@link GDialog#YES_NO_OPTION_TYPE},
     *            {@link GDialog#YES_NO_CANCEL_OPTION_TYPE}
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showConfirmDialog(UIObject parent, Object message,
            String title, int optionType, GDialogChoiceListener choiceListener) {
        showMessage(parent, message, title, QUESTION_MESSAGE, getOptions(
                optionType, null), null, choiceListener);
    }

    /**
     * Shows a confirmation dialog with the specified option types and message
     * type
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param title
     *            the title of the dialog
     * @param optionType
     *            the option types of the dialog, eg.
     *            {@link GDialog#YES_NO_OPTION_TYPE},
     *            {@link GDialog#YES_NO_CANCEL_OPTION_TYPE}
     * @param messageType
     *            the message type of the dialog, eg
     *            {@link GDialog#INFORMATION_MESSAGE},
     *            {@link GDialog#PLAIN_MESSAGE}
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showConfirmDialog(UIObject parent, Object message,
            String title, int optionType, int messageType,
            GDialogChoiceListener choiceListener) {
        showMessage(parent, message, title, messageType, getOptions(optionType,
                null), null, choiceListener);
    }

    /**
     * Shows a confirmation dialog with the specified option types and message
     * type
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param title
     *            the title of the dialog
     * @param optionType
     *            the option types of the dialog, eg.
     *            {@link GDialog#YES_NO_OPTION_TYPE},
     *            {@link GDialog#YES_NO_CANCEL_OPTION_TYPE}
     * @param messageType
     *            the message type of the dialog, eg
     *            {@link GDialog#INFORMATION_MESSAGE},
     *            {@link GDialog#PLAIN_MESSAGE}
     * @param imagePath
     *            the image path of the icon which will be displayed, if null
     *            the default image of the message type is choosen
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showConfirmDialog(UIObject parent, Object message,
            String title, int optionType, int messageType, String imagePath,
            GDialogChoiceListener choiceListener) {
        showMessage(parent, message, title, messageType, getOptions(optionType,
                null), imagePath, choiceListener);
    }

    /**
     * Shows an input dialog with option type
     * {@link GDialog#OK_CANCEL_OPTION_TYPE} message type
     * {@link GDialog#QUESTION_MESSAGE} and no title
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showInputDialog(UIObject parent, Object message,
            GDialogChoiceListener choiceListener) {
        showInputDialog(parent, message, null, QUESTION_MESSAGE, getOptions(
                OK_CANCEL_OPTION_TYPE, null), null, null, null, choiceListener);
    }

    /**
     * Shows an input dialog with option type
     * {@link GDialog#OK_CANCEL_OPTION_TYPE} and message type
     * {@link GDialog#QUESTION_MESSAGE}
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param title
     *            the title of the dialog
     * @param initialValue
     *            the initial value of the input dialog
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showInputDialog(UIObject parent, Object message,
            String title, Object initialValue,
            GDialogChoiceListener choiceListener) {
        showInputDialog(parent, message, title, QUESTION_MESSAGE, getOptions(
                OK_CANCEL_OPTION_TYPE, null), null, initialValue, null,
                choiceListener);
    }

    /**
     * Shows an input dialog with option type
     * {@link GDialog#OK_CANCEL_OPTION_TYPE} and the specified message type
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param title
     *            the title of the dialog
     * @param initialValue
     *            the initial value of the input dialog
     * @param messageType
     *            the message type of the dialog , eg
     *            {@link GDialog#QUESTION_MESSAGE}
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showInputDialog(UIObject parent, Object message,
            String title, Object initialValue, int messageType,
            GDialogChoiceListener choiceListener) {
        showInputDialog(parent, message, title, messageType, getOptions(
                OK_CANCEL_OPTION_TYPE, null), null, initialValue, null,
                choiceListener);
    }

    /**
     * Shows an input dialog with option type
     * {@link GDialog#OK_CANCEL_OPTION_TYPE}, the specified message type and a
     * custom image for the icon displayed
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param title
     *            the title of the dialog
     * @param initialValue
     *            the initial value of the input dialog
     * @param messageType
     *            the message type of the dialog , eg
     *            {@link GDialog#QUESTION_MESSAGE}
     * @param imagePath
     *            the image path for the icon displayed in the dialog
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showInputDialog(UIObject parent, Object message,
            String title, Object initialValue, int messageType,
            String imagePath, GDialogChoiceListener choiceListener) {
        showInputDialog(parent, message, title, messageType, getOptions(
                OK_CANCEL_OPTION_TYPE, null), imagePath, initialValue, null,
                choiceListener);
    }

    /**
     * Shows an input dialog with option type
     * {@link GDialog#OK_CANCEL_OPTION_TYPE}, the specified message type and a
     * custom image for the icon displayed
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param title
     *            the title of the dialog
     * @param initialValue
     *            the initial value of the input dialog
     * @param selectionValues
     *            the default values of the input dialog displayed as a list box
     * @param messageType
     *            the message type of the dialog , eg
     *            {@link GDialog#QUESTION_MESSAGE}
     * @param imagePath
     *            the image path for the icon displayed in the dialog
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showInputDialog(UIObject parent, Object message,
            String title, Object initialValue, Object[] selectionValues,
            int messageType, String imagePath,
            GDialogChoiceListener choiceListener) {
        showInputDialog(parent, message, title, messageType, getOptions(
                OK_CANCEL_OPTION_TYPE, null), imagePath, initialValue,
                selectionValues, choiceListener);
    }

    /**
     * Shows an input dialog with the specified option type, the specified
     * message type and a custom image for the icon displayed
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param title
     *            the title of the dialog
     * @param messageType
     *            the message type of the dialog , eg
     *            {@link GDialog#QUESTION_MESSAGE}
     * @param options
     *            the option buttons of the dialof, eg
     *            {@link GDialog#OK_CANCEL_OPTION_TYPE}
     * @param imagePath
     *            the image path for the icon displayed in the dialog
     * @param initialValue
     *            the initial value of the input dialog
     * @param selectionValues
     *            the default values of the input dialog displayed as a list box
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showInputDialog(UIObject parentFrame, Object message,
            String title, int messageType, Option[] options, String imagePath,
            Object initialValue, Object[] selectionValues,
            GDialogChoiceListener choiceListener) {
        setDefaultDialogProperties(title, messageType);
        internalDialog.setParent(parentFrame);
        internalDialog.setMessage(message);
        internalDialog.setMessageType(messageType);
        internalDialog.setOptions(options);
        internalDialog.setParent(parentFrame);
        internalDialog.setInitialValue(initialValue);
        internalDialog.setSelectionValues(selectionValues);
        internalDialog.setIcon(getIcon(messageType, imagePath));
        internalDialog.setGDialogChoiceListener(choiceListener);
        internalDialog._show(true);
    }

    private static Option[] getOptions(int optionType, Object[] optionsValues) {
        if (optionType == OK_OPTION_TYPE) {
            if (optionsValues != null) {
                Option option = new OkOption();
                option.setValue(optionsValues[0]);
                return new Option[] { option };
            }
            return new Option[] { OK_OPTION };
        } else if (optionType == YES_NO_OPTION_TYPE) {
            if (optionsValues != null) {
                Option yesOption = new YesOption();
                yesOption.setValue(optionsValues[0]);
                Option noOption = new NoOption();
                noOption.setValue(optionsValues[1]);
                return new Option[] { yesOption, noOption };
            }
            return new Option[] { YES_OPTION, NO_OPTION };
        } else if (optionType == YES_NO_CANCEL_OPTION_TYPE) {
            if (optionsValues != null) {
                Option yesOption = new YesOption();
                yesOption.setValue(optionsValues[0]);
                Option noOption = new NoOption();
                noOption.setValue(optionsValues[1]);
                Option cancelOption = new CancelOption();
                cancelOption.setValue(optionsValues[2]);
                return new Option[] { yesOption, noOption, cancelOption };
            }
            return new Option[] { YES_OPTION, NO_OPTION, CANCEL_OPTION };

        } else if (optionType == OK_CANCEL_OPTION_TYPE) {
            if (optionsValues != null) {
                Option okOption = new OkOption();
                okOption.setValue(optionsValues[0]);
                Option cancelOption = new CancelOption();
                cancelOption.setValue(optionsValues[1]);
                return new Option[] { okOption, cancelOption };
            }
            return new Option[] { OK_OPTION, CANCEL_OPTION };
        }
        throw new IllegalStateException("Invalid value");

    }

    /**
     * Shows a message dialog with message type
     * {@link GDialog#INFORMATION_MESSAGE}
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showMessage(UIObject parent, Object message,
            GDialogChoiceListener choiceListener) {
        showMessage(parent, message, null, INFORMATION_MESSAGE, null, null,
                choiceListener);
    }

    /**
     * Shows a message dialog with the message type specified
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param title
     *            the title of the dialog
     * @param messageType
     *            the message type of the dialog, eg
     *            {@link GDialog#INFORMATION_MESSAGE}
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showMessage(UIObject parent, Object message,
            String title, int messageType, GDialogChoiceListener choiceListener) {
        showMessage(parent, message, title, messageType, null, null,
                choiceListener);
    }

    public static void showOptionDialog(UIObject parent, Object message,
            String title, int optionType, int messageType, String imagePath,
            Object[] options, Object initialValue,
            GDialogChoiceListener choiceListener) {
        setDefaultDialogProperties(title, messageType);
        internalDialog.setParent(parent);
        internalDialog.setMessage(message);
        internalDialog.setMessageType(messageType);
        internalDialog.setOptions(optionType, options);
        internalDialog.setInitialValue(initialValue);
        internalDialog.setIcon(getIcon(messageType, imagePath));
        internalDialog.setGDialogChoiceListener(choiceListener);
        internalDialog._show(false);
    }

    /**
     * Shows a message dialog with the message type specified
     * 
     * @param parent
     *            the parent widget over which the dialog should be shown
     * @param message
     *            the widget or string message to show up in the dialog
     * @param title
     *            the title of the dialog
     * @param messageType
     *            the message type of the dialog, eg
     *            {@link GDialog#INFORMATION_MESSAGE}
     * @param options
     *            the option buttons of the dialog, eg {@link GDialog#OK_OPTION},
     *            {@link GDialog#NO_OPTION}
     * @param imagePath
     *            the image path of the icon displayed in the dialog
     * @param choiceListener
     *            the choiceListener triggered after user selection
     */
    public static void showMessage(UIObject parentFrame, Object message,
            String title, int messageType, Option[] options, String imagePath,
            GDialogChoiceListener choiceListener) {
        setDefaultDialogProperties(title, messageType);
        internalDialog.setParent(parentFrame);
        internalDialog.setMessage(message);
        internalDialog.setMessageType(messageType);
        internalDialog.setOptions(options);
        internalDialog.setIcon(getIcon(messageType, imagePath));
        internalDialog.setGDialogChoiceListener(choiceListener);

        internalDialog._show(false);

    }

    private void _show(boolean isInputDialog) {
        GwmUtilities.hideSelect();
        if (overlayLayer == null) {
            overlayLayer = new OverlayLayer();
        }
        buildContent(isInputDialog);
        overlayLayer.show(getTheme());
        getCurrentGDialog().setVisible(true);
        if (parent != null) {
            GwmUtilities.diplayAtRelativeGivenWidgetCenter(getCurrentGDialog(),
                    parent);
        } else {
            GwmUtilities.displayAtParentCenter(getCurrentGDialog());
        }

    }

    private DefaultGDialog getCurrentGDialog() {
        return internalDialog != null ? internalDialog : this;
    }

    private void buildContent(boolean isInputDialog) {
        if (message == null) {
            throw new IllegalStateException(
                    "Please use the GDialog.setMessage(Object) or verify your message object is not null");
        }
        Widget content = null;
        DefaultGDialog dialog = getCurrentGDialog();
        if (!isInputDialog) {
            content = new DialogPane(message, options, icon, choiceListener,
                    dialog);
        } else {
            content = new InputDialogPane(message, options, icon, initialValue,
                    selectionValues, choiceListener, dialog);
        }

        super.setContent(content);
    }

    private static void setDefaultDialogProperties(String title, int messageType) {
        // if (internalDialog != null) {
        // throw new IllegalStateException("A Dialog is already opened!");
        // }
        internalDialog = new DefaultGDialog();

        internalDialog.setTheme(defaultTheme);
        if (title != null) {
            internalDialog.setCaption(title);
        }

    }

   

    class InputDialogPane extends DialogPane {
        private TextBox textBoxInput;

        private ListBox selectionValuesInput;

        Object initialValue;

        Object selectionValues;

        public InputDialogPane(Object message, Option[] options, Image icon,
                Object initialValue, Object[] selectionValues,
                GDialogChoiceListener choiceListener, DefaultGDialog dialog) {
            super(message, options, icon, choiceListener, dialog);
            if (selectionValues != null) {
                selectionValuesInput = new ListBox();
                for (int i = 0; i < selectionValues.length; i++) {
                    selectionValuesInput.addItem(selectionValues[i].toString());
                    if (selectionValues[i].equals(initialValue)) {
                        selectionValuesInput.setSelectedIndex(i);
                    }
                }
                centerContentPanel.add(selectionValuesInput);
                centerContentPanel.setCellHorizontalAlignment(
                        selectionValuesInput,
                        HasHorizontalAlignment.ALIGN_CENTER);
            } else {
                textBoxInput = new TextBox();
                textBoxInput.setWidth("100%");
                if (initialValue != null) {
                    textBoxInput.setText(initialValue.toString());
                    this.initialValue = initialValue;
                }
                centerContentPanel.add(textBoxInput);
                centerContentPanel.setCellHorizontalAlignment(textBoxInput,
                        HasHorizontalAlignment.ALIGN_CENTER);
            }
        }

        protected void buildUI(Object message, Option[] options, Image icon) {
            super.buildUI(message, options, icon);
        }

        public String getInputValue() {
            if (selectionValuesInput != null) {
                return selectionValuesInput.getItemText(selectionValuesInput
                        .getSelectedIndex());
            }
            return textBoxInput.getText();
        }
    }

    static class DialogPane extends Composite {

        DockPanel layout;

        GDialogChoiceListener choiceListener;

        protected VerticalPanel centerContentPanel;

        private DefaultGDialog dialog;

        public DialogPane(Object message, Option[] options, Image icon,
                GDialogChoiceListener choiceListener, DefaultGDialog dialog) {
            this.choiceListener = choiceListener;
            this.dialog = dialog;
            buildUI(message, options, icon);
        }

        protected void buildUI(Object message, Option[] options, Image icon) {
            layout = new DockPanel();
            layout.setSpacing(10);
            centerContentPanel = new VerticalPanel();
            centerContentPanel.setSpacing(1);
            if (message != null) {
                if (message instanceof String) {
                    HTML html = new HTML(message.toString());
                    html.setWordWrap(wrapStringMessage);
                    centerContentPanel.add(html);
                } else if (message instanceof Widget)
                    centerContentPanel.add((Widget) message);
                else
                    throw new IllegalArgumentException(
                            "Error => The content message of the GDialog can be a Text or a Widget");

            }
            if (icon != null) {
                layout.add(icon, DockPanel.WEST);
            }
            if (options == null) {
                options = new Option[] { OK_OPTION };
            }
            if (options != null) {

                FlowPanel optionsPanel = new FlowPanel();
                DOM.setStyleAttribute(optionsPanel.getElement(), "textAlign",
                        "center");
                DOM.setStyleAttribute(optionsPanel.getElement(), "whiteSpace",
                        "nowrap");
                for (int i = 0; i < options.length; i++) {
                    final Option option = options[i];
                    Button optionBtn = new Button(option.getValue().toString());
                    optionBtn.addClickListener(new ClickListener() {
                        public void onClick(Widget sender) {
                            if (choiceListener != null) {
                                String inputValue = null;
                                if (option.equals(OK_OPTION)
                                        && DialogPane.this instanceof InputDialogPane) {
                                    inputValue = ((InputDialogPane) DialogPane.this)
                                            .getInputValue();

                                }
                                dialog.selectedOption = option;
                                dialog.selectedValue = inputValue;

                                choiceListener.onChoice(dialog);
                            }
                            overlayLayer.hide();

                            dialog.close();
                            GwmUtilities.showSelect();
                            dialog = null;
                            if (internalDialog != null) {
                                internalDialog = null;
                            }

                        }
                    });
                    optionsPanel.add(optionBtn);
                    DOM
                            .setStyleAttribute(optionBtn.getElement(),
                                    "margin", "1");

                }
                layout.add(optionsPanel, DockPanel.SOUTH);
            }
            layout.add(centerContentPanel, DockPanel.CENTER);
            initWidget(layout);
        }

    }
    /**
     * Base Class for GDialog options.
     */
    public static class Option {
        String labelId;

        Object value;

        public Option(String labelId) {
            this(labelId, labelId);
        }

        public Option(String labelId, Object value) {
            this.labelId = labelId;
            this.value = value;
        }

        public String getLabelId() {
            return labelId;
        }

        public String toString() {
            return labelId;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj)
                return true;
            return labelId.equals(((Option) obj).getLabelId());
        }

    }
    /**
     * Built-in OK Option. 
     */
    public static class OkOption extends Option {
        public OkOption() {
            super("OK");
        }
    }
    /**
     * Built-in YES Option. 
     */
    public static class YesOption extends Option {
        public YesOption() {
            super("Yes");
        }
    }
    /**
     * Built-in No Option. 
     */
    public static class NoOption extends Option {
        public NoOption() {
            super("No");
        }
    }

    /**
     * Built-in Cancel Option 
     */
    public static class CancelOption extends Option {
        public CancelOption() {
            super("Cancel");
        }
    }

    public static int getDefaultDialogHeight() {
        return defaultDialogHeight;
    }

    public static void setDefaultDialogHeight(int defaultDialogHeight) {
        DefaultGDialog.defaultDialogHeight = defaultDialogHeight;
    }

    public static int getDefaultDialogWidth() {
        return defaultDialogWidth;
    }

    public static void setDefaultDialogWidth(int defaultDialogWidth) {
        DefaultGDialog.defaultDialogWidth = defaultDialogWidth;
    }

    public static boolean isOverridesDialogSize() {
        return overridesDialogSize;
    }

    public static void setOverridesDialogSize(boolean overridesDialogSize) {
        DefaultGDialog.overridesDialogSize = overridesDialogSize;
    }

    public static String getDefaultTheme() {
        return defaultTheme;
    }

    public static void setDefaultTheme(String defaultTheme) {
        DefaultGDialog.defaultTheme = defaultTheme;
    }

    public static boolean isWrapStringMessage() {
        return wrapStringMessage;
    }

    public static void setWrapStringMessage(boolean wrap) {
        DefaultGDialog.wrapStringMessage = wrap;
    }

}
