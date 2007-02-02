package org.gwm.samples.gwmdemo.client;

import org.gwm.client.FramesManager;
import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.ui.Hyperlink;

public class DesktopScenarii extends AbstractScenarii {

    private GDesktopPane desktop;

    public DesktopScenarii(FramesManager framesManager, GDesktopPane desktop) {
        super(framesManager);
        this.desktop = desktop;
    }

    public void runScenarii() {
        buildScreenShotFrame("Screen1", "images/sc1.png");
        buildScreenShotFrame("Screen2", "images/sc2.png");
        buildScreenShotFrame("Screen3", "images/sc3.png");
    }

    protected Hyperlink createLink() {
        Hyperlink desktopDemoLink = new Hyperlink("Frames in desktop",
                "desktop");
        return desktopDemoLink;
    }

    private void buildScreenShotFrame(final String linkCaption, final String url) {
        GInternalFrame window = framesManager.newFrame("");
        window.setCaption(linkCaption);
        window.setSize(800, 500 );
        window.setTheme("default");
        window.setUrl(url);
        desktop.addFrame(window);
        window.minimize();
        window.setVisible(true);
        window.setVisible(false);
        
    }

}
