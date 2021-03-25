package org.vaadin.holdbutton;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.server.Resource;
import com.vaadin.shared.Registration;
import com.vaadin.shared.ui.JavaScriptComponentState;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

@JavaScript("holdbutton.js")
@StyleSheet("holdbutton.css")
public class HoldButton extends AbstractJavaScriptComponent {

    public static final int DEFAULT_HOLD_TIME_MS = 1000;
    private static final String HOLD_BUTTON_STYLE_NAME = "hold-button";

    public HoldButton() {
        setPrimaryStyleName(HOLD_BUTTON_STYLE_NAME);
        addFunction("afterHoldClick", e -> {
            this.fireEvent(new Button.ClickEvent(this));
        });
    }

    public HoldButton(String caption) {
        this();
        setCaption(caption);
    }


    public HoldButton(String caption, int holdMs) {
        this(caption);
        setHoldTime(holdMs);
    }


    @Override
    protected MyComponentState getState() {
        return getState(false);
    }

    @Override
    protected MyComponentState getState(boolean markAsDirty) {
        return (MyComponentState)super.getState(markAsDirty);
    }

    @Override
    public void setIcon(Resource icon) {
        this.setResource("buttonIcon", icon);
    }

    @Override
    public void setCaption(String caption) {
        getState(true).buttonCaption = caption;
    }

    public int getHoldTime() {
        return getState().holdtimems;
    }

    public void setHoldTime(int holdTime) {
        getState(true).holdtimems = holdTime;
    }

    public Registration addClickListener(Button.ClickListener listener) {
        return this.addListener(Button.ClickEvent.class, listener, Button.ClickListener.BUTTON_CLICK_METHOD);
    }

    public static class MyComponentState extends JavaScriptComponentState {
        public int holdtimems = DEFAULT_HOLD_TIME_MS;
        public String buttonCaption = null;
    }
}
