package org.vaadin.addons.specialbuttons;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.shared.Registration;

@Tag("hold-button")
@JsModule("./hold-button.ts")
@CssImport("./hold-button.css")
public class HoldButton extends AbstractButton {

    public static final int DEFAULT_HOLD_TIME_MS = 1000;

    public HoldButton() {
        setHoldTime(DEFAULT_HOLD_TIME_MS);
        setActive(true);
    }

    @ClientCallable
    public void afterHoldClick() {
        this.fireEvent(new ClickEvent(this, true));
    }

    public HoldButton(String caption) {
        this();
        setCaption(caption);
    }


    public HoldButton(String caption, int holdMs) {
        this(caption);
        setHoldTime(holdMs);
    }

    public void setIcon(AbstractStreamResource resource) {
        getElement().setAttribute("icon", resource);
    }


    public void setCaption(String caption) {
        getElement().setProperty("buttonCaption", caption);
    }

    public int getHoldTime() {
        return getElement().getProperty("holdtime", DEFAULT_HOLD_TIME_MS);
    }

    public void setHoldTime(int holdTime) {
        getElement().setProperty("holdtime", holdTime);
    }

    public boolean isActive() {
        return "true".equals(getElement().getProperty("active", "true"));
    }

    public void setActive(boolean active) {
        getElement().setProperty("active", active);
    }


}

