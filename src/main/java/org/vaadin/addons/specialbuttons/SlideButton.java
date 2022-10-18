package org.vaadin.addons.specialbuttons;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;

/** A button that needs to slided left to right for a click.
 *
 */
@JsModule("./slide-button.ts")
@Tag("slide-button")
@CssImport("./slide-button.css")
public class SlideButton  extends AbstractButton {


    public SlideButton() {
        setActive(true);
    }

    @ClientCallable
    public void afterSlideClick() {
        fireEvent(new ClickEvent(this, true));
    }

    public SlideButton(String caption) {
        this();
        setCaption(caption);
    }

    public SlideButton(String caption, int holdMs) {
        this(caption);
    }


    public String getCaption() {
        return getElement().getProperty("caption");
    }

    public void setCaption(String caption) {
        getElement().setProperty("caption",caption);
    }


    public boolean isActive() {
        return "true".equals(getElement().getProperty("active"));
    }

    public void setActive(boolean active) {
        getElement().setProperty("active",active);
    }
}
