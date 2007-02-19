/*
 * Copyright (c) 2007 gwtwindowmanager.org (http://www.gwtwindowmanager.org)
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

package org.gwm.samples.gwmdemo.client;

import org.gwm.client.GFrame;
import org.gwm.client.impl.DefaultGFrame;
import org.gwm.client.impl.DefaultGDialog;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WindowEditor extends Composite {

    VerticalPanel ui = new VerticalPanel();

    private ListBox style = new ListBox();

    private Button createWindow;


    private TextBox inputTitle;

    protected CheckBox inputDraggable;

    private CheckBox inputClosable;

    protected TextBox inputHeight;

    protected TextBox inputLeft;

    private ListBox inputHideEffect;

    private CheckBox inputMinimizable;

    private CheckBox inputResizable;

    private TextBox inputTop;

    private TextBox inputMinimumHeight;

    private TextBox inputMaximumHeight;

    private TextBox inputUrl;

    private TextBox inputWidth;

    private TextBox inputMinimumWidth;

    private TextBox inputMaximumWidth;

    private CheckBox inputMaximizable;

    private ListBox inputShowEffect;

    public WindowEditor() {
        initUI();
        initListeners();
        initWidget(ui);
    }

    private void initUI() {

        ui = new VerticalPanel();

        // style combobox
        style.addItem("default", "default");
        style.addItem("theme1", "theme1");
        style.addItem("alphacube", "alphacube");
        style.addItem("spread", "spread");
        style.addItem("darkX", "darkX");

        ui.setSpacing(1);
        ui.add(buildPropertyEditor("Theme : ", style,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// title
        inputTitle = new TextBox();
        ui.add(buildPropertyEditor("Title : ", inputTitle,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        ui.add(new Label(" [Url]"));

        inputUrl = new TextBox();
        inputUrl.setText("http://lbroussal.blogspot.com/");
        inputUrl.setWidth("150");
        ui.add(buildPropertyEditor("Url : ", inputUrl,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        ui.add(new Label(" [Size]"));

        // ////////////// width
        inputWidth = new TextBox();
        inputWidth.setText("750");
        ui.add(buildPropertyEditor("Width : ", inputWidth,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// minimum width
        inputMinimumWidth = new TextBox();
        ui.add(buildPropertyEditor("Minimum Width : ", inputMinimumWidth,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// maximum width
        inputMaximumWidth = new TextBox();
        ui.add(buildPropertyEditor("Maximum Width : ", inputMaximumWidth,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// heigth
        inputHeight = new TextBox();
        inputHeight.setText("460");
        ui.add(buildPropertyEditor("Height : ", inputHeight,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// minimum heigth
        inputMinimumHeight = new TextBox();
        ui.add(buildPropertyEditor("Minimum Height : ", inputMinimumHeight,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// maximum heigth
        inputMaximumHeight = new TextBox();
        ui.add(buildPropertyEditor("Maximum Height : ", inputMaximumHeight,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        ui.add(new Label(" [Position]"));

        // ////////////// top
        inputTop = new TextBox();
        inputTop.setText("65");
        ui.add(buildPropertyEditor("Top : ", inputTop,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// left
        inputLeft = new TextBox();
        inputLeft.setText("300");
        ui.add(buildPropertyEditor("Left : ", inputLeft,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        ui.add(new Label(" [Actions]"));

        // ////////////// resizable
        inputResizable = new CheckBox();
        inputResizable.setChecked(true);
        ui.add(buildPropertyEditor("Resizable : ", inputResizable,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// closable
        inputClosable = new CheckBox();
        inputClosable.setChecked(true);
        ui.add(buildPropertyEditor("Closable : ", inputClosable,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// minimizable
        inputMinimizable = new CheckBox();
        inputMinimizable.setChecked(true);
        ui.add(buildPropertyEditor("Minimizable : ", inputMinimizable,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// maximizable
        inputMaximizable = new CheckBox();
        inputMaximizable.setChecked(true);
        ui.add(buildPropertyEditor("Maximizable : ", inputMaximizable,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// draggable
        inputDraggable = new CheckBox();
        inputDraggable.setChecked(true);
        ui.add(buildPropertyEditor("Draggable : ", inputDraggable,
                "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // propertyEditor.add(new Label(" [FX]"));

        // ////////////// showEffect
        inputShowEffect = new ListBox();
        inputShowEffect.addItem("None", "");
        // inputShowEffect.addItem("Appear", "eval(function(){Effect.Appear})");
        // inputShowEffect.addItem("Show", "function(){Effect.Show}");
        // propertyEditor.add(buildPropertyEditor("Show effect : ",
        // inputShowEffect,
        // "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// hideEffect
        inputHideEffect = new ListBox();
        inputHideEffect.addItem("None", "");
        // inputHideEffect.addItem("Fade", "Effect.Fade");
        // inputHideEffect.addItem("Hide", "Effect.Hide");
        // propertyEditor.add(buildPropertyEditor("Hide effect : ",
        // inputHideEffect,
        // "gwtwindowmanager-WindowPropertyEditor-PropertyLabelCol", ""));

        // ////////////// create window button
        createWindow = new Button("Create window");
        ui.add(createWindow);
        ui.setCellHorizontalAlignment(createWindow,
                HasHorizontalAlignment.ALIGN_CENTER);
        ui.setSpacing(1);
    }

    private void initListeners() {
        createWindow.addClickListener(new ClickListener() {

            public void onClick(Widget sender) {
                GFrame newWindow = new DefaultGFrame("");

                newWindow.setTheme(style.getValue(style.getSelectedIndex()));
                try {
                    int width = Integer.parseInt(inputWidth.getText());
                    newWindow.setWidth(width);
                } catch (Exception e) {
                    DefaultGDialog.showMessage(null,
                            "Please enter a number for 'width' property",
                            "Invalid field value", DefaultGDialog.ERROR_MESSAGE);
                    return;
                }

                try {
                    int height = Integer.parseInt(inputHeight.getText());
                    newWindow.setHeight(height);
                } catch (Exception e) {
                    DefaultGDialog.showMessage(null,
                            "Please a enter number for 'heigth' property",
                            "Invalid field value", DefaultGDialog.ERROR_MESSAGE);
                    return;
                }

                if (!inputUrl.getText().equals("")) {

                    try {
                        newWindow.setUrl(inputUrl.getText().trim());
                    } catch (Exception e) {
                        DefaultGDialog.showMessage(null,
                                "Please a enter an internet URL",
                                "Invalid field value", DefaultGDialog.ERROR_MESSAGE);
                        return;
                    }
                }

                if (!inputMinimumWidth.getText().equals("")) {

                    try {
                        int minimumWidth = Integer.parseInt(inputMinimumWidth
                                .getText());
                        newWindow.setMinimumWidth(minimumWidth);
                    } catch (Exception e) {
                        DefaultGDialog
                                .showMessage(
                                        null,
                                        "Please a enter number for 'mininum width' property",
                                        "Invalid field value",
                                        DefaultGDialog.ERROR_MESSAGE);
                        return;
                    }
                }
                if (!inputMaximumWidth.getText().equals("")) {

                    try {
                        int maximumWidth = Integer.parseInt(inputMaximumWidth
                                .getText());
                        newWindow.setMaximumWidth(maximumWidth);
                    } catch (Exception e) {
                        DefaultGDialog
                                .showMessage(
                                        null,
                                        "Please a enter number for 'maximum width' property",
                                        "Invalid field value",
                                        DefaultGDialog.ERROR_MESSAGE);
                        return;
                    }
                }

                newWindow.setCaption(inputTitle.getText().trim());
                if (!inputMinimumHeight.getText().equals("")) {
                    try {
                        int minimumHeight = Integer.parseInt(inputMinimumHeight
                                .getText());
                        newWindow.setMinimumHeight(minimumHeight);
                    } catch (Exception e) {
                        DefaultGDialog
                                .showMessage(
                                        null,
                                        "Please a enter number for 'mininum heigth' property",
                                        "Invalid field value",
                                        DefaultGDialog.ERROR_MESSAGE);
                        return;
                    }
                }

                if (!inputMaximumHeight.getText().equals("")) {

                    try {
                        int maximumHeigth = Integer.parseInt(inputMaximumHeight
                                .getText());
                        newWindow.setMaximumHeight(maximumHeigth);
                    } catch (Exception e) {
                        DefaultGDialog
                                .showMessage(
                                        null,
                                        "Please a enter number for 'maximum height' property",
                                        "Invalid field value",
                                        DefaultGDialog.ERROR_MESSAGE);
                        return;
                    }
                }

                if (!inputTop.getText().equals("")) {

                    try {
                        int top = Integer.parseInt(inputTop.getText());
                        newWindow.setTop(top);
                    } catch (Exception e) {
                        DefaultGDialog.showMessage(null,
                                "Please a enter number for 'top' property",
                                "Invalid field value", DefaultGDialog.ERROR_MESSAGE);
                        return;
                    }
                }

                if (!inputLeft.getText().equals("")) {

                    try {
                        int left = Integer.parseInt(inputLeft.getText());
                        newWindow.setLeft(left);
                    } catch (Exception e) {
                        DefaultGDialog.showMessage(null,
                                "Please a enter number for 'left' property",
                                "Invalid field value", DefaultGDialog.ERROR_MESSAGE);
                        return;
                    }
                }

                newWindow.setResizable(inputResizable.isChecked());

                newWindow.setClosable(inputClosable.isChecked());

                newWindow.setMinimizable(inputMinimizable.isChecked());

                newWindow.setMaximizable(inputMaximizable.isChecked());

                newWindow.setDraggable(inputDraggable.isChecked());

                newWindow.setVisible(true);

            }

        });

    }

    private Widget buildPropertyEditor(String propertyName, Widget inputWidget,
            String labelStyle, String inputStyle) {
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        Label propertyLabel = new Label(propertyName);
        propertyLabel.setStyleName(labelStyle);
        horizontalPanel.add(propertyLabel);
        inputWidget.setStyleName(inputStyle);
        horizontalPanel.add(inputWidget);
        return horizontalPanel;

    }
}
