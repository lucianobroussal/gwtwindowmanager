package org.gwm.client.impl;

import java.util.ArrayList;
import java.util.List;

import org.gwm.client.GInternalFrame;

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
