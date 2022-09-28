package org.vaadin.addons.specialbuttons;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.server.Resource;
import com.vaadin.shared.Registration;
import com.vaadin.shared.ui.JavaScriptComponentState;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.Button;

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
    protected HoldButtonState getState() {
        return getState(false);
    }

    @Override
    protected HoldButtonState getState(boolean markAsDirty) {
        return (HoldButtonState)super.getState(markAsDirty);
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

    public boolean isActive() {
        return getState(false).active;
    }

    public void setActive(boolean active) {
        getState(true).active = active;
    }

    public static class HoldButtonState extends JavaScriptComponentState {
        public int holdtimems = DEFAULT_HOLD_TIME_MS;
        public String buttonCaption = null;
        public boolean active = true;
    }
}
