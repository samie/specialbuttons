package org.vaadin.addons.specialbuttons;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.server.AbstractStreamResource;

@JsModule("./hold-button.ts")
@Tag("hold-button")
@CssImport(value = "./hold-button.css")
public class HoldButton extends Component implements HasStyle, HasSize, ClickNotifier {

    public static final int DEFAULT_HOLD_TIME_MS = 1000;
    private static final String HOLD_BUTTON_STYLE_NAME = "hold-button";

    public HoldButton() {
        setClassName(HOLD_BUTTON_STYLE_NAME);
    }

    @ClientCallable
    public void afterHoldClick() {
        this.fireEvent(new ClickEvent(this));
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
        return getElement().getProperty("holdtimems", DEFAULT_HOLD_TIME_MS);
    }

    public void setHoldTime(int holdTime) {
        getElement().getProperty("holdtimems", holdTime);
    }

    public boolean isActive() {
        return "true".equals(getElement().getProperty("active"));
    }

    public void setActive(boolean active) {
        getElement().setProperty("active", active);
    }


}

