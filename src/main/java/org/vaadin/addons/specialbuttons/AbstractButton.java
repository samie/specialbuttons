package org.vaadin.addons.specialbuttons;

import com.vaadin.flow.component.*;

public abstract  class AbstractButton extends Component implements HasStyle, HasSize, ClickNotifier {


    private boolean enabled;


    public AbstractButton() {
    }

    public AbstractButton(String caption, ComponentEventListener<ClickEvent> listener) {
        setCaption(caption);
    }

    public AbstractButton(String caption) {
        this();
        setCaption(caption);
    }
    public void setCaption(String caption) {
        getElement().setProperty("caption", caption);
    }

    public static class ClickEvent extends ComponentEvent<AbstractButton> {

        /**
         * Creates a new event using the given source and indicator whether the
         * event originated from the client side or the server side.
         *
         * @param source     the source component
         * @param fromClient <code>true</code> if the event originated from the client
         *                   side, <code>false</code> otherwise
         */
        public ClickEvent(AbstractButton source, boolean fromClient) {
            super(source, fromClient);
        }
    }

    public boolean isEnabled() {
        return getElement().isEnabled();
    }

    public void setEnabled(boolean enabled) {
        getElement().setEnabled(enabled);
    }

    protected void fireEvent(ClickEvent clickEvent) {
        if (!isEnabled()) return;
        super.fireEvent(clickEvent);
    }

}
