/*
 * Copyright (c) 2006-2007 Luciano Broussal <luciano.broussal AT gmail.com>
 * (http://www.gwtwindowmanager.org)
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


import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractScenarii implements Scenarii {

   

    public AbstractScenarii() {
    }

    public Hyperlink getLink() {
        Hyperlink hyperlink = createLink();
        hyperlink.addClickListener(new ClickListener() {

            public void onClick(Widget sender) {
                runScenarii();

            }

        });
        return hyperlink;
    }

    public void runScenarii() {

    }

    protected abstract Hyperlink createLink();

}
