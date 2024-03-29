package org.vaadin.addons.specialbuttons;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.shared.Registration;


/** A button that needs to be pressed for given period before click event is triggered.
 *
 */

@Tag("hold-button")
@JsModule("./hold-button.ts")
@CssImport("./hold-button.css")
public class HoldButton extends AbstractButton {

    public static final int DEFAULT_HOLD_TIME_MS = 1000;

    public HoldButton() {
        setHoldTime(DEFAULT_HOLD_TIME_MS);
        setActive(true);
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
        getElement().removeAllChildren();
        getElement().setAttribute("icon", resource);
    }

    public void setIcon(Icon icon) {
        getElement().removeAttribute("icon");
        getElement().removeAllChildren();
        getElement().appendChild(icon.getElement());
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

    public Registration addHoldListener(ComponentEventListener<HoldClickEvent> listener) {
        return addListener(HoldClickEvent.class, listener);
    }
    @DomEvent("hold-event")
    public static class HoldClickEvent extends SpecialClickEvent {

        public HoldClickEvent(HoldButton source, boolean fromClient) {
            super(source, fromClient);
        }
    }

}

