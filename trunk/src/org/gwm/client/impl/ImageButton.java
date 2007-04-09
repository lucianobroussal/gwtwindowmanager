package org.gwm.client.impl;

import org.gwtwidgets.client.ui.PNGImage;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;

public class ImageButton extends FocusPanel {


    private Image imgOff;

    private String off;

    private String on;
    
    public ImageButton(String on, String off , int width , int height) {
        super();
        initImages(on , off, width, height);
        initListeners();
        setWidget(imgOff);
        setSize(width+"px", height+"px");
        this.on = on;
        this.off = off;
        
        imgOff.addClickListener(new ClickListener(){

            public void onClick(Widget arg0) {
                Window.alert("coucouc");
                
            }
            
        });
            
    }

    private void initImages(String on, String off, int width , int height) {
        imgOff = buildImage(off, width, height);
    }

    private Image buildImage(String img, int width, int height) {
        Image res= null;
        if(img.endsWith(".png")){
            res = new PNGImage(img , width , height);
        }else{
            res = new Image(img);
            res.setSize(height+"", height+"");
        }
        return res;
    }

    private void initListeners() {
        imgOff.addMouseListener(new MouseListenerAdapter() {

            public void onMouseEnter(Widget sender) {
                imgOff.setUrl(on);
            }
            public void onMouseLeave(Widget sender) {
                imgOff.setUrl(off);
            }

        });

    }


}
