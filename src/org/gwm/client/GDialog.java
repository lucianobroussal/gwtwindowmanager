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
package org.gwm.client;

import org.gwm.client.event.GDialogChoiceListener;

/**
 * Provides the behavior handles to interact with a DefaultGDialog.
 */
public interface GDialog extends GFrame {

    public static final int ERROR_MESSAGE = 1;

    public static final int INFORMATION_MESSAGE = 2;

    public static final int WARNING_MESSAGE = 3;

    public static final int QUESTION_MESSAGE = 4;

    public static final int PLAIN_MESSAGE = 5;

    public static final int OK_OPTION_TYPE = 4;

    public static final int YES_NO_OPTION_TYPE = 5;

    public static final int YES_NO_CANCEL_OPTION_TYPE = 6;

    public static final int OK_CANCEL_OPTION_TYPE = 7;

    /**
     * Shows the DefaultGDialog.
     * 
     */
    public void show();

    public void setMessageType(int messageType);

    public void setOptionType(int optionsType);

    public void setGDialogChoiceListener(GDialogChoiceListener choiceListener);

    public void setMessage(Object  message);

}
