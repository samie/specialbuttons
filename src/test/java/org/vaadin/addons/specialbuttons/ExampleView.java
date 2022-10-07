package org.vaadin.addons.specialbuttons;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/** Test view for manual and automated testing of the component.
 *
 */
@Route("")
public class ExampleView extends VerticalLayout {

    public ExampleView() {

        // Hold button

        HoldButton holdButton = new HoldButton(null, 2000);
        holdButton.setIcon(new ClassResource(this, "mic.png"));
        holdButton.setWidth("200px");
        holdButton.setHeight("200px");

        holdButton.addClickListener(e -> {
            Notification.show("Hold done: "+holdButton.isActive());
            holdButton.setActive(!holdButton.isActive());
        });

        Button start = new Button("3s delay", e -> holdButton.setHoldTime(3000));
        Button start1 = new Button("0.5s delay", e -> holdButton.setHoldTime(500));
        Button start2 = new Button("0s delay", e -> holdButton.setHoldTime(0));
        Button start3 = new Button("Default delay", e -> holdButton.setHoldTime(HoldButton.DEFAULT_HOLD_TIME_MS));
        Button enableDisable = new Button("Enable/Disable", e -> holdButton.setEnabled(!holdButton.isEnabled()));
        add(new HorizontalLayout(holdButton,start,start1,start2,start3, enableDisable));

        // Slide button

        SlideButton slideButton = new SlideButton("Activate mic");
        slideButton.setWidth("400px");
        slideButton.setHeight("40px");

        slideButton.addClickListener(e -> {
            Notification.show("Slide done: "+slideButton.isActive());
            slideButton.setActive(!slideButton.isActive());
        });
        enableDisable = new Button("Enable/Disable", e -> slideButton.setEnabled(!slideButton.isEnabled()));


        add(new HorizontalLayout(slideButton, enableDisable));
    }
}
