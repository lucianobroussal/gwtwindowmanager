package org.gwm.client.impl;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface GWmImageBundle extends ImageBundle {

    /**
     * Notice that the gwt.resource metadata tag is not present, so the method
     * name itself is assumed to match the associated image filename.
     * 
     * One of btn_submit_icon.png, btn_submit_icon.gif, or btn_submit_icon.jpg
     * must be located in the same package as MyImageBundle.
     */
    public AbstractImagePrototype information_icon();

    public AbstractImagePrototype warning_icon();

    public AbstractImagePrototype error_icon();

    public AbstractImagePrototype question_icon();

    public AbstractImagePrototype text_icon();

    public AbstractImagePrototype unknown_icon();

    public AbstractImagePrototype window_icon();
}
