package org.vaadin.addons.specialbuttons;

import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.StreamResource;

/** A Stream resource that uses Java classpath.
 *
 * Offers several ways of loading a resource using classloader.
 *
 * This is implementation based on https://vaadin.com/docs/latest/application/resources/#class-resources
 */
public class ClassResource extends StreamResource {

    public ClassResource(Object obj, String resource) {
        this(obj.getClass(), resource, resource);
    }

    public ClassResource(Class clazz, String resource, String publicName) {
        super(publicName, () -> clazz.getResourceAsStream(resource));
    }

    public ClassResource(ClassLoader classLoader, String resource, String publicName) {
        super(publicName, () -> classLoader.getResourceAsStream(resource));
    }

}
