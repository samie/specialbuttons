package org.vaadin.addons.specialbuttons;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;

@JsModule("./slide-button.ts")
@Tag("slide-button")
@CssImport(value = "./slide-button.css")
public class SlideButton extends Component implements HasStyle, HasSize, ClickNotifier {

    private static final String HOLD_BUTTON_STYLE_NAME = "slide-button";

    public SlideButton() {
        setClassName(HOLD_BUTTON_STYLE_NAME);
    }

    @ClientCallable
    public void afterSlideClick() {
        this.fireEvent(new ClickEvent(this));
    }

    public SlideButton(String caption) {
        this();
        setCaption(caption);
    }

    public SlideButton(String caption, int holdMs) {
        this(caption);
    }


    public void setCaption(String caption) {
        getElement().setProperty("buttonCaption",caption);
    }


    public boolean isActive() {
        return "true".equals(getElement().getProperty("active"));
    }

    public void setActive(boolean active) {
        getElement().setProperty("active",active);
    }
}
