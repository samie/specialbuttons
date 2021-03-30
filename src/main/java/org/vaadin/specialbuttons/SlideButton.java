package org.vaadin.specialbuttons;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.server.Resource;
import com.vaadin.shared.Registration;
import com.vaadin.shared.ui.JavaScriptComponentState;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.Button;

@JavaScript("slidebutton.js")
@StyleSheet("slidebutton.css")
public class SlideButton extends AbstractJavaScriptComponent {

    private static final String HOLD_BUTTON_STYLE_NAME = "slide-button";

    public SlideButton() {
        setPrimaryStyleName(HOLD_BUTTON_STYLE_NAME);
        addFunction("afterSlideClick", e -> {
            this.fireEvent(new Button.ClickEvent(this));
        });
    }

    public SlideButton(String caption) {
        this();
        setCaption(caption);
    }

    public SlideButton(String caption, int holdMs) {
        this(caption);
    }

    @Override
    protected SlideButtonState getState() {
        return getState(false);
    }

    @Override
    protected SlideButtonState getState(boolean markAsDirty) {
        return (SlideButtonState)super.getState(markAsDirty);
    }

    @Override
    public void setCaption(String caption) {
        getState(true).buttonCaption = caption;
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

    public static class SlideButtonState extends JavaScriptComponentState {
        public String buttonCaption = null;
        public boolean active = true;
    }
}
