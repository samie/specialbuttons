package org.vaadin.addons.specialbuttons;

import com.sun.tools.javac.util.List;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

/** Test view for manual and automated testing of the component.
 *
 */
@Route("")
public class ExampleView extends VerticalLayout {

    public ExampleView() {

        // Hold button

        HoldButton holdButton = new HoldButton(null, 2000);
        holdButton.setIcon(new ClassResource(this, "mic.png"));

        HoldButton holdButton2 = new HoldButton(null, 2000);
        holdButton2.setIcon(new Icon(VaadinIcon.POWER_OFF));
        holdButton2.setWidth("200px");
        holdButton2.setHeight("200px");

        holdButton.addHoldListener(e -> {
            Notification.show(e.getSource().getClass()+": "+e.getClass().getSimpleName()+". Active: "+holdButton.isActive());
            holdButton.setActive(!holdButton.isActive());
        });
        holdButton2.addHoldListener(e -> {
            Notification.show(e.getSource().getClass()+": "+e.getClass().getSimpleName()+". Active: "+holdButton2.isActive());
            holdButton2.setActive(!holdButton2.isActive());
        });

        Button start = new Button("3s delay", e -> holdButton.setHoldTime(3000));
        Button start1 = new Button("0.5s delay", e -> holdButton.setHoldTime(500));
        Button start2 = new Button("0s delay", e -> holdButton.setHoldTime(0));
        Button start3 = new Button("Default delay", e -> holdButton.setHoldTime(HoldButton.DEFAULT_HOLD_TIME_MS));
        Button enableDisable = new Button("Enable/Disable", e -> holdButton.setEnabled(!holdButton.isEnabled()));
        enableDisable.setIcon(VaadinIcon.POWER_OFF.create());
        add(new HorizontalLayout(holdButton,holdButton2,start,start1,start2,start3, enableDisable));
        holdButton.addClickShortcut(Key.KEY_H);

        // Slide button

        SlideButton slideButton = new SlideButton("Activate mic");
        slideButton.setWidth("400px");
        slideButton.setHeight("40px");

        slideButton.addSlideListener(e -> {
            Notification.show(e.getSource().getClass()+": "+e.getClass().getSimpleName()+". Active: "+slideButton.isActive());
            slideButton.setActive(!slideButton.isActive());
        });
        enableDisable = new Button("Enable/Disable", e -> slideButton.setEnabled(!slideButton.isEnabled()));
        add(new HorizontalLayout(slideButton, enableDisable));
        slideButton.addClickShortcut(Key.KEY_S);

        // Cancellable button

        CancellableButton cancellableButton = new CancellableButton("Activate mic");

        cancellableButton.addTimeoutListener(e -> {
            Notification.show(e.getSource().getClass()+": "+e.getClass().getSimpleName());
        });
        cancellableButton.addClickListener(e -> {
            Notification.show(e.getSource().getClass()+": "+e.getClass().getSimpleName());
        });
        enableDisable = new Button("Enable/Disable", e -> cancellableButton.setEnabled(!cancellableButton.isEnabled()));

        Button delay = new Button("Default delay", e -> cancellableButton.setDelay(CancellableButton.DEFAULT_DELAY_SEC));
        Button delay10 = new Button("10s delay", e -> cancellableButton.setDelay(10));
        Button delay5 = new Button("5s delay", e -> cancellableButton.setDelay(5));
        Button delay2 = new Button("2s delay", e -> cancellableButton.setDelay(2));
        Button delay0 = new Button("0s delay", e -> cancellableButton.setDelay(0));
        Button click = new Button("Click", e -> cancellableButton.clickWithDelay());
        Button cancel = new Button("Cancel click", e -> cancellableButton.cancelClick());
        cancellableButton.addClickShortcut(Key.ENTER);

        add(new HorizontalLayout(cancellableButton, enableDisable, delay, delay0, delay2, delay5, delay10, click, cancel));
    }
}
