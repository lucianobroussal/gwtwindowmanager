package org.gwm.client.impl;

import java.util.*;

import org.gwm.client.*;
import org.gwm.client.tools.*;

public class DesktopManager {

    private static List frames = new ArrayList();

    static void addFrame (GInternalFrame f) {
        frames.add (f);
    }

    static void removeFrame (GInternalFrame f) {
        frames.remove (f);
    }

    public static List getAllFrames () {
        return frames;
    }

}
