package org.gwm.client.impl;

import org.gwm.client.GInternalFrame;
import org.gwm.client.GWmConstants;
import org.gwm.client.event.GDialogChoiceListener;
import org.gwtwidgets.client.ui.PNGImage;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GDialog {

    public static final int ERROR_MESSAGE = 1;

    public static final int INFORMATION_MESSAGE = 2;

    public static final int WARNING_MESSAGE = 3;

    public static final int QUESTION_MESSAGE = 4;

    public static final int PLAIN_MESSAGE = 5;

    // public static final int YES_OPTION = 6;
    //
    // public static final int NO_OPTION = 7;
    //
    // public static final int CANCEL_OPTION = 8;
    //
    // public static final int OK_OPTION = 9;
    public static final int YES_NO_OPTION = 5;

    public static final int YES_NO_CANCEL_OPTION = 6;

    public static final int OK_CANCEL_OPTION = 7;

    private static final String YES_OPTION_LABEL = "Yes";

    private static final String NO_OPTION_LABEL = "No";

    private static final String CANCEL_OPTION_LABEL = "Cancel";

    private static String theme = GWmConstants.DEFAULT_THEME;

    private static GInternalFrame currentDialog;

    private static Object selectedValue;

    public static final Option OK_OPTION = new Option("Ok");

    public static final Option YES_OPTION = new Option("Yes");

    public static final Option NO_OPTION = new Option("No");

    public static final Option CANCEL_OPTION = new Option("Cancel");

    public static void setTheme(String theme) {
        GDialog.theme = theme;
    }

    public static Object getSelectedValue() {
        return selectedValue;
    }

    public static void showConfirmDialog(Object message,
            GDialogChoiceListener choiceListener) {
        showMessage(message, null, QUESTION_MESSAGE,
                getOptions(YES_NO_CANCEL_OPTION), null, choiceListener);
    }

    public static void showConfirmDialog(Object message, String title,
            int optionType, GDialogChoiceListener choiceListener) {
        showMessage(message, title, QUESTION_MESSAGE, getOptions(optionType),
                null, choiceListener);
    }

    public static void showConfirmDialog(Object message, String title,
            int optionType, int messageType,
            GDialogChoiceListener choiceListener) {
        showMessage(message, title, messageType, getOptions(optionType), null,
                choiceListener);
    }

    public static void showConfirmDialog(Object message, String title,
            int optionType, int messageType, String imagePath,
            GDialogChoiceListener choiceListener) {
        showMessage(message, title, messageType, getOptions(optionType),
                imagePath, choiceListener);
    }

    public static void showInputDialog(Object message,
            GDialogChoiceListener choiceListener) {
        showInputDialog(message, null, QUESTION_MESSAGE,
                getOptions(OK_CANCEL_OPTION), null, null, choiceListener);
    }

    public static void showInputDialog(Object message, Object initialValue,
            GDialogChoiceListener choiceListener) {
        showInputDialog(message, null, QUESTION_MESSAGE,
                getOptions(OK_CANCEL_OPTION), null, initialValue,
                choiceListener);
    }

    private static Option[] getOptions(int optionType) {
        if (optionType == YES_NO_OPTION) {
            return new Option[] { OK_OPTION, NO_OPTION };
        } else if (optionType == YES_NO_CANCEL_OPTION) {
            return new Option[] { OK_OPTION, NO_OPTION, CANCEL_OPTION };

        } else if (optionType == OK_CANCEL_OPTION) {
            return new Option[] { OK_OPTION, CANCEL_OPTION };
        }
        throw new IllegalStateException("Invalid value");

    }

    public static void showMessage(Object message) {
        showMessage(message, null, INFORMATION_MESSAGE, null, null, null);
    }

    public static void showMessage(Object message, String title, int messageType) {
        showMessage(message, title, messageType, null, null, null);
    }

    public static void showMessage(Object message, String title,
            int messageType, Option[] options, String imagePath,
            GDialogChoiceListener choiceListener) {
        setDefaultDialogProperties(title, messageType);
        Image icon = getIcon(messageType, imagePath);
        currentDialog.setContent(new DialogPane(message, options, icon,
                choiceListener));
        currentDialog.setVisible(true);
        adjustDialogSizeToContent(currentDialog);
        
    }

    private static void adjustDialogSizeToContent(GInternalFrame internalFrame) {
        Widget content = internalFrame.getContent();
        int height = content.getOffsetHeight();
        currentDialog.setHeight(height);
        int width = content.getOffsetWidth();
        currentDialog.setWidth(width);
    }

    public static void showInputDialog(Object message, String title,
            int messageType, Option[] options, String imagePath,
            Object initialValue, GDialogChoiceListener choiceListener) {
        setDefaultDialogProperties(title, messageType);
        Image icon = getIcon(messageType, imagePath);
        currentDialog.setContent(new InputDialogPane(message, options, icon,
                initialValue, choiceListener));
        currentDialog.setVisible(true);
        adjustDialogSizeToContent(currentDialog);
    }

    private static void setDefaultDialogProperties(String title, int messageType) {
        if (currentDialog != null) {
            throw new IllegalStateException("A Dialog is already opened!");
        }
        currentDialog = new GwtInternalFrame("");
        currentDialog.setClosable(false);
        currentDialog.setMinimizable(false);
        currentDialog.setMaximizable(false);
        
        
        currentDialog.setTheme(theme);
        if (title != null) {
            currentDialog.setTitle(title);
        }
        
    }

    private static Image getIcon(int messageType, String imagePath) {
        Image icon = new Image(getImagePath(messageType, imagePath));
        icon.setWidth("24px");
        icon.setHeight("24px");
        return icon;

    }

    private static String getImagePath(int messageType, String image) {
        if (image != null) {
            return image;
        }
        switch (messageType) {
        case INFORMATION_MESSAGE:
            return "images/information.gif";
        case QUESTION_MESSAGE:
            return "images/question.gif";
        case PLAIN_MESSAGE:
            return "images/text.gif";
        case WARNING_MESSAGE:
            return "images/warning.gif";
        case ERROR_MESSAGE:
            return "images/error.gif";
        default:
        }
        return "images/unknown.gif";
    }

    static class InputDialogPane extends DialogPane {
        private TextBox textBoxInput;

        Object initialValue;

        public InputDialogPane(Object message, Option[] options, Image icon,
                Object initialValue, GDialogChoiceListener choiceListener) {
            super(message, options, icon, choiceListener);
            if(initialValue != null){
                textBoxInput.setText(initialValue.toString());
                this.initialValue = initialValue;
            }
        }

        protected void buildUI(Object message, Option[] options, Image icon) {
            super.buildUI(message, options, icon);
            textBoxInput = new TextBox();
            centerContentPanel.add(textBoxInput);
        }

        public String getInputValue() {
            return textBoxInput.getText();
        }
    }

    static class DialogPane extends Composite {

        DockPanel layout;

        GDialogChoiceListener choiceListener;

        protected VerticalPanel centerContentPanel;

        public DialogPane(Object message, Option[] options, Image icon,
                GDialogChoiceListener choiceListener) {
            this.choiceListener = choiceListener;
            buildUI(message, options, icon);
        }

        protected void buildUI(Object message, Option[] options, Image icon) {
            layout = new DockPanel();
            layout.setSpacing(10);
            centerContentPanel = new VerticalPanel();
            if (message != null) {
                centerContentPanel.add(new Label(message.toString()));
            }
            if (icon != null) {
                layout.add(icon, DockPanel.WEST);
            }
            if (options == null) {
                options = new Option[] {OK_OPTION};
            }
            if (options != null) {

                FlowPanel optionsPanel = new FlowPanel();
                DOM.setStyleAttribute(optionsPanel.getElement(), "textAlign",
                        "center");
                for (int i = 0; i < options.length; i++) {
                    final Option option = options[i];
                    Button optionBtn = new Button(option.getLabel());
                    optionBtn.addClickListener(new ClickListener() {
                        public void onClick(Widget sender) {
                            if (choiceListener != null) {
                                String inputValue = null;
                                if (option.equals(OK_OPTION)
                                        && DialogPane.this instanceof InputDialogPane) {
                                    inputValue = ((InputDialogPane) DialogPane.this)
                                            .getInputValue();

                                }
                                choiceListener.onChoice(option, inputValue);
                            }
                            currentDialog.dispose();
                            currentDialog = null;
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

    public static class Option {
        String label;

        public Option(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public static class OkOption extends Option {
        public OkOption() {
            super("OK");
        }
    }

    public static class YesOption extends Option {
        public YesOption() {
            super("Yes");
        }
    }

    public static class NoOption extends Option {
        public NoOption() {
            super("No");
        }
    }

    public static class CancelOption extends Option {
        public CancelOption() {
            super("Cancel");
        }
    }

}
