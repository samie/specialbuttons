package org.vaadin.addons.specialbuttons;


import org.junit.Assert;
import org.junit.Test;
import org.vaadin.addons.specialbuttons.elements.HoldButtonElement;
import org.vaadin.addons.specialbuttons.elements.SlideButtonElement;

/** Integration test for the component.
 *
 */
public class ExampleViewIT extends AbstractTestBenchIntegrationTest {

    public ExampleViewIT() {
        super();
    }

    @Test
    public void componentIsPresent()  {
        HoldButtonElement elem = $(HoldButtonElement.class).first();
        Assert.assertNotNull(elem);

        SlideButtonElement elem2 = $(SlideButtonElement.class).first();
        Assert.assertNotNull(elem2);
    }
}