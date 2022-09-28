package org.vaadin.addons.specialbuttons;

import com.vaadin.server.ClassResource;
import com.vaadin.ui.*;
import org.vaadin.addonhelpers.AbstractTest;

/**
 * Add many of these with different configurations,
 * combine with different components, for regressions
 * and also make them dynamic if needed.
 */
public class BasicHoldButtonUsageUI extends AbstractTest {

    private boolean buttonActive;

    @Override
    public Component getTestComponent() {
        HorizontalLayout l = new HorizontalLayout();
// Create a layout for other components
        VerticalLayout layout = new VerticalLayout();

// Use TextField for standard text input
        TextField textField = new TextField("Your name");

// Button click listeners can be defined as lambda expressions
        Button button = new Button("Say hello",
                e -> Notification.show("Hello!"));

// Add the web components to the HTML element
        layout.addComponents(textField, button);
        HoldButton btn = new HoldButton(null, 500);
        btn.setIcon(new ClassResource("mic.png"));
        btn.setWidth("200px");
        btn.setHeight("200px");

        btn.addClickListener(e -> {
            Notification.show("Click ok: "+btn.isActive());
            btn.setActive(!btn.isActive());
        });

        Button start = new Button("3s delay", e -> {btn.setHoldTime(3000);});
        Button start1 = new Button("0.5s delay", e -> {btn.setHoldTime(500);});
        Button start2 = new Button("0s delay", e -> {btn.setHoldTime(0);});
        Button start3 = new Button("Default delay", e -> {btn.setHoldTime(HoldButton.DEFAULT_HOLD_TIME_MS);});

        l.addComponents(btn,start,start1,start2,start3);

        return l;
    }

}
