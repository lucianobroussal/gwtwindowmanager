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
package org.gwm.client;

import org.gwm.client.event.GDialogChoiceListener;
import org.gwm.client.impl.DefaultGDialog.Option;

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
    
    public static final Option OK_OPTION = new Option("Ok");

    public static final Option YES_OPTION = new Option("Yes");

    public static final Option NO_OPTION = new Option("No");

    public static final Option CANCEL_OPTION = new Option("Cancel");

    /**
     * Shows the DefaultGDialog.
     * 
     */
    public void show();

    public void setMessageType(int messageType);

    public void setOptionType(int optionsType);

    public void setGDialogChoiceListener(GDialogChoiceListener choiceListener);

    public void setMessage(Object message);

}
