package org.vaadin.addons.specialbuttons;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import org.vaadin.addonhelpers.AbstractTest;

/**
 * Add many of these with different configurations,
 * combine with different components, for regressions
 * and also make them dynamic if needed.
 */
public class BasicSlideButtonUsageUI extends AbstractTest {

    boolean buttonActive;

    @Override
    public Component getTestComponent() {
        HorizontalLayout l = new HorizontalLayout();
        SlideButton btn = new SlideButton("Activate mic");
        btn.setWidth("400px");
        btn.setHeight("40px");

        btn.addClickListener(e -> {
            Notification.show("Click ok: "+btn.isActive());
            btn.setActive(!btn.isActive());
        });


        l.addComponents(btn);

        return l;
    }

}
