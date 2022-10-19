package org.vaadin.addons.specialbuttons;

import com.vaadin.flow.component.*;

public abstract  class AbstractButton extends Component implements HasStyle, HasSize, ClickNotifier {


    private boolean enabled;


    public AbstractButton() {
    }

    public AbstractButton(String caption, ComponentEventListener<? extends SpecialClickEvent> listener) {
        setCaption(caption);
    }

    public AbstractButton(String caption) {
        this();
        setCaption(caption);
    }
    public void setCaption(String caption) {
        getElement().setProperty("caption", caption);
    }

    public static class SpecialClickEvent extends ClickEvent<AbstractButton> {

        protected SpecialClickEvent(Component source, boolean fromClient) {
            super(source, fromClient, -1, -1, -1, -1, 1, -1, false, false, false, false);
        }

    }

    public boolean isEnabled() {
        return getElement().isEnabled();
    }

    public void setEnabled(boolean enabled) {
        getElement().setEnabled(enabled);
    }

    protected void fireSpecialClickEvent(SpecialClickEvent clickEvent) {
        if (!isEnabled()) return;
        super.fireEvent(clickEvent);
    }

}
