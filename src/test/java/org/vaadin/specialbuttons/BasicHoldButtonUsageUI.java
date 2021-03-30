package org.vaadin.specialbuttons;

import com.vaadin.server.ClassResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
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
