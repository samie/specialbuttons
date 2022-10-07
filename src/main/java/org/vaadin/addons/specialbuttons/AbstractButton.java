package org.vaadin.addons.specialbuttons;

import com.vaadin.flow.component.*;
import com.vaadin.flow.shared.Registration;

public abstract  class AbstractButton extends Component implements HasStyle, HasSize{

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

    public Registration addClickListener(
            ComponentEventListener<ClickEvent> listener) {
        if (this instanceof Component) {
            return ComponentUtil.addListener((Component) this, ClickEvent.class,
                    (ComponentEventListener) listener);
        } else {
            throw new IllegalStateException(String.format(
                    "The class '%s' doesn't extend '%s'. "
                            + "Make your implementation for the method '%s'.",
                    getClass().getName(), Component.class.getSimpleName(),
                    "addClickListener"));
        }
    }
}
