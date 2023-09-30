package org.vaadin.addons.specialbuttons;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Test view for manual and automated testing of the component.
 *
 */
@Route("")
@Theme(Material.class)
public class ExampleView extends VerticalLayout {


    public static class Item {
        private LocalDateTime dateTime;

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }
    }

    public ExampleView() {

        // Hold button
        HoldButton holdButton = new HoldButton(null, 2000);
        holdButton.setIcon(new ClassResource(this, "mic.png"));

        HoldButton holdButton2 = new HoldButton(null, 2000);
        holdButton2.setIcon(new Icon(VaadinIcon.POWER_OFF));
        holdButton2.setWidth("200px");
        holdButton2.setHeight("200px");


        HoldButton holdButton3 = new HoldButton(null, 2000);
        holdButton3.setIcon(new ClassResource(this, "mic.png"));
        /*
        Style s = holdButton3.getStyle();
        s.set("position", "absolute");
        s.set("width","350px");
        s.set("height","350px");
        s.set("top", "530px");
        s.set("left", "10px");
        s.set("z-index", "2");
        UI.getCurrent().add(holdButton3);
         */

        holdButton.addHoldListener(e -> {
            Notification.show(e.getSource().getClass()+": "+e.getClass().getSimpleName()+". Active: "+holdButton.isActive());
            holdButton.setActive(!holdButton.isActive());
        });
        holdButton2.addHoldListener(e -> {
            Notification.show(e.getSource().getClass()+": "+e.getClass().getSimpleName()+". Active: "+holdButton2.isActive());
            holdButton2.setActive(!holdButton2.isActive());
        });

        LocalDateTimeRenderer tx = new LocalDateTimeRenderer<>(
                Item::getDateTime,
                DateTimeFormatter.ofLocalizedDateTime(
                        FormatStyle.SHORT,
                        FormatStyle.MEDIUM));

        Button start = new Button("3s delay", e -> holdButton.setHoldTime(3000));
        Button start1 = new Button("0.5s delay", e -> holdButton.setHoldTime(500));
        Button start2 = new Button("0s delay", e -> holdButton.setHoldTime(0));
        Button start3 = new Button("Default delay", e -> holdButton.setHoldTime(HoldButton.DEFAULT_HOLD_TIME_MS));
        Button enableDisable = new Button("Enable/Disable",
                new Icon(VaadinIcon.POWER_OFF),
                e -> holdButton.setEnabled(!holdButton.isEnabled()));
        holdButton.addClickShortcut(Key.KEY_H);
        Arrays.asList(enableDisable,start,start1,start2,start3).forEach(b-> b.setThemeName("tertiary"));
        add(new H2("HoldButton with image"));
        add(new HorizontalLayout(holdButton,enableDisable,start,start1,start2,start3));
        add(new H2("HoldButton with icon"));
        add(holdButton2);

        // Slide button

        SlideButton slideButton = new SlideButton("Activate mic");
        slideButton.setWidth("400px");
        slideButton.setHeight("40px");

        slideButton.addSlideListener(e -> {
            Notification.show(e.getSource().getClass()+": "+e.getClass().getSimpleName()+". Active: "+slideButton.isActive());
            slideButton.setActive(!slideButton.isActive());
        });
        enableDisable = new Button("Enable/Disable",
                new Icon(VaadinIcon.POWER_OFF),
                e -> slideButton.setEnabled(!slideButton.isEnabled()));
        add(new H2("SlideButton with text"));
        add(new HorizontalLayout(slideButton, enableDisable));
        Arrays.asList(enableDisable).forEach(b-> b.setThemeName("tertiary"));
        slideButton.addClickShortcut(Key.KEY_S);

        // Cancellable button

        CancellableButton cancellableButton = new CancellableButton("Activate mic");
        cancellableButton.setWidth("300px");
        cancellableButton.addTimeoutListener(e -> {
            Notification.show(e.getSource().getClass()+": "+e.getClass().getSimpleName());
        });
        cancellableButton.addClickListener(e -> {
            Notification.show(e.getSource().getClass()+": "+e.getClass().getSimpleName());
        });
        enableDisable = new Button("Enable/Disable",
                new Icon(VaadinIcon.POWER_OFF),
                e -> cancellableButton.setEnabled(!cancellableButton.isEnabled()));

        Button delay = new Button("Default delay", e -> cancellableButton.setDelay(CancellableButton.DEFAULT_DELAY_SEC));
        Button delay10 = new Button("10s delay", e -> cancellableButton.setDelay(10));
        Button delay5 = new Button("5s delay", e -> cancellableButton.setDelay(5));
        Button delay2 = new Button("2s delay", e -> cancellableButton.setDelay(2));
        Button delay0 = new Button("0s delay", e -> cancellableButton.setDelay(0));
        Button click = new Button("Click", e -> cancellableButton.clickWithDelay());
        Button cancel = new Button("Cancel click", e -> cancellableButton.cancelClick());
        Arrays.asList(enableDisable,delay,delay10,delay5,delay2, delay0, click, cancel).forEach(b-> b.setThemeName("tertiary"));
        cancellableButton.addClickShortcut(Key.ENTER);
        add(new H2("CancellableButton with text"));
        add(new HorizontalLayout(cancellableButton, enableDisable, delay, delay0, delay2, delay5, delay10, click, cancel));
    }
}
