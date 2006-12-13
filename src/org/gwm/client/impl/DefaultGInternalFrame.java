/*
 * Copyright (c) 2006 gwtwindowmanager.org (http://www.gwtwindowmanager.org)
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

import java.util.ArrayList;
import java.util.List;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The PWC nativeFrame implementation.
 * 
 * @author lb
 */

public class DefaultGInternalFrame implements GInternalFrame {

    private static final String ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD = "Only possible <<before>> a call of a show(...) method";

    private static final String ERROR_ONLY_POSSIBLE_AFTER_CALL_TO_SHOW_METHOD = "Only possible ##after## a call of a show(...) method";

    /**
     * Prefix id of the element if GWT widget is used for frame content.
     */
    private static final String WIN_CONTAINER_PREFIX = "GInternalFrameContainer_";

    /**
     * The frame id.
     */
    private String id;

    /**
     * The frame title
     */
    private String title;

    /**
     * The JSNI PWC handle
     */
    private JavaScriptObject nativeFrame;

    private List options;
    
    private GDesktopPane parent;

    public DefaultGInternalFrame(String id) {
        this.id = id;
        options = new ArrayList();
    }

    public void setParent(GDesktopPane parent) {
		this.parent = parent;
	}

	/**
     * Used for Gwt Widget content type
     * 
     * @return the id of the Element which receive the widget content
     */
    private String getWinContainerId() {
        return WIN_CONTAINER_PREFIX + getId();
    }

    public void showCenter() {
        showCenter(false);
    }

    public void showCenter(boolean modal) {
        if (nativeFrame == null) {
            nativeFrame = newWindow(id, buildOptions(options));
        }
        nativeShowCenter(modal);
        toFront();
    }

    public void setLocation(int top, int left) {
        if (nativeFrame != null) {
            nativeSetLocation(top, left);
        } else {
            options.add(new WindowOption("top", top));
            options.add(new WindowOption("left", left));
        }
    }

    public void setTitle(String title) {
        if (nativeFrame != null) {
            nativeSetTitle(title);
        } else {
            options.add(new WindowOption("title", title));
        }
        this.title = title;
    }

    public void setHeight(int height) {
        if (nativeFrame == null) {
            options.add(new WindowOption("height", height));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setMaximumHeight(int maxHeight) {
        if (nativeFrame == null) {
            options.add(new WindowOption("maxHeight", maxHeight));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setMaximumWidth(int maxWidth) {
        if (nativeFrame == null) {
            options.add(new WindowOption("maxWidth", maxWidth));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setMinimumHeight(int minHeight) {
        if (nativeFrame == null) {
            options.add(new WindowOption("minHeight", minHeight));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setMinimumWidth(int minWidth) {
        if (nativeFrame == null) {
            options.add(new WindowOption("minWidth", minWidth));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setWidth(int width) {
        if (nativeFrame == null) {
            options.add(new WindowOption("width", width));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setClosable(boolean closable) {
        if (nativeFrame == null) {
            options.add(new WindowOption("closable", closable));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setDraggable(boolean draggable) {
        if (nativeFrame == null) {
            options.add(new WindowOption("draggable", draggable));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setMaximizable(boolean maximizable) {
        if (nativeFrame == null) {
            options.add(new WindowOption("maximizable", maximizable));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setMinimizable(boolean minimizable) {
        if (nativeFrame == null) {
            options.add(new WindowOption("minimizable", minimizable));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setResizable(boolean resizable) {
        if (nativeFrame == null) {
            options.add(new WindowOption("resizable", resizable));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setLeft(int left) {
        if (nativeFrame == null) {
            options.add(new WindowOption("left", left));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setTop(int top) {
        if (nativeFrame == null) {
            options.add(new WindowOption("top", top));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    /**
     * Set the widget content into the unique div associated to the frame
     * 
     * @param widget
     * 
     * @see <a
     *      href="http://code.google.com/webtoolkit/documentation/com.google.gwt.user.client.ui.Widget.html">GWT
     *      Widget</a>
     */
    public void setContent(Widget widget) {
        if (nativeFrame == null) {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_AFTER_CALL_TO_SHOW_METHOD);
        }
        if (RootPanel.get(getWinContainerId()) == null && nativeFrame != null) {
            setContent("<div id='" + getWinContainerId() + "'/>");
        }
        RootPanel.get(getWinContainerId()).add(widget);
    }

    public void show(boolean modal) {
        if (nativeFrame == null) {
            nativeFrame = newWindow(id, buildOptions(options));
        }
        nativeShow(modal);
        toFront();
    }

    public void setSize(int width, int height) {
        if (nativeFrame != null) {
            nativeSetSize(width, height);
        } else {
            options.add(new WindowOption("width", width));
            options.add(new WindowOption("height", height));
        }
    }

    public void setStyle(String style) {
        if (nativeFrame == null) {
            options.add(new WindowOption("className", style));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public void setUrl(String url) {
        if (nativeFrame == null) {
            options.add(new WindowOption("url", url));
        } else {
            throw new IllegalStateException(
                    ERROR_ONLY_POSSIBLE_BEFORE_CALL_TO_SHOW_METHOD);
        }
    }

    public String getTitle() {
        return title;
    }

    /** *************************************************************** */
    /** ****************************** JSNI ************************* */
    /** *************************************************************** */

    private native void nativeShowCenter(boolean modal) /*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.showCenter(modal);
     }-*/;

    private native void nativeSetLocation(int top, int left)/*-{
     this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame.setLocation(top , left); 
     }-*/;

    private native void nativeSetSize(int width, int height)/*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.setSize(width , height); 
     }-*/;

    private native void nativeSetTitle(String title) /*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.setTitle(title); 
     }-*/;

   
    private native JavaScriptObject newWindow(String id,
            JavaScriptObject options) /*-{
     return  new $wnd.Window(id ,options );
     }-*/;

    private native static void addOption(JavaScriptObject jso, String name,
            boolean value) /*-{
     jso[name] = value;
     }-*/;

    private native static void addOption(JavaScriptObject jso, String name,
            double value)/*-{
     jso[name] = value;
     }-*/;

    private native static void addOption(JavaScriptObject jso, String name,
            String value)/*-{
     jso[name] = value;
     }-*/;

    private static native void addOption(JavaScriptObject jso, String name,
            Object value) /*-{
     jso[name] = value;
     }-*/;

    private static native JavaScriptObject createJsObject() /*-{
     obj= new Object();
     return obj ;
     }-*/;

    private native void nativeShow(boolean modal) /*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.show(modal); 
     }-*/;

    public native String getId() /*-{
     return this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame.getId();
     }-*/;

    public native void setContent(String content)/*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.getContent().innerHTML = content ; 
     }-*/;

    public native void minimize() /*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.minimize(); 
     }-*/;

    public native void maximize() /*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.maximize(); 
     }-*/;

    public native void toFront() /*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.toFront(); 
     }-*/;

    public native void destroy() /*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.destroy(); 
     }-*/;

    public native boolean isMaximized() /*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.isMaximized(); 
     }-*/;

    public native boolean isMinimized()/*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.isMinimized(); 
     }-*/;

    public native void setDestroyOnClose()/*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.setDestroyOnClose(); 
     }-*/;

    public native void updateWidth()/*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.updateWidth(); 
     }-*/;

    public native void updateHeight()/*-{
     frame = this.@org.gwm.client.impl.DefaultGInternalFrame::nativeFrame;
     if(frame != null)
     frame.updateHeight(); 
     }-*/;

    /**
     * 
     * Internal representation for a Frame option
     * 
     */
    private class WindowOption {
        private String name;

        private Object value;

        public WindowOption(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public WindowOption(String name, double value) {
            this.name = name;
            this.value = new Double(value);
        }

        public WindowOption(String name, long value) {
            this.name = name;
            this.value = new Long(value);
        }

        public WindowOption(String name, boolean bool) {
            this.name = name;
            this.value = new Boolean(bool);
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }
    }

    /**
     * Buid a valid JSNI Hashmap
     * 
     * @param opts
     *            the java options list for the frame
     * @return
     */
    private static JavaScriptObject buildOptions(List opts) {
        JavaScriptObject jso = createJsObject();
        for (int i = 0; i < opts.size(); i++) {
            WindowOption option = (WindowOption) opts.get(i);
            Object value = option.getValue();
            if (value instanceof Double) {
                addOption(jso, option.getName(), ((Double) value).doubleValue());
            } else if (value instanceof Long) {
                addOption(jso, option.getName(), ((Long) value).longValue());
            } else if (value instanceof Boolean) {
                addOption(jso, option.getName(), ((Boolean) value)
                        .booleanValue());
            } else if (value instanceof String) {
                addOption(jso, option.getName(), (String) value);
            } else {
                addOption(jso, option.getName(), value);
            }
        }
        return jso;
    }

	public GDesktopPane getParent() {
		return this.parent;
	}

}
