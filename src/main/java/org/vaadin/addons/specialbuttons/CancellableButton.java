package org.vaadin.addons.specialbuttons;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;

/** A button that the user can cancel within a grace period.
 *
 */
@Tag("cancellable-button")
@JsModule("./cancellable-button.ts")
@CssImport("./cancellable-button.css")
public class CancellableButton extends AbstractButton {

    /** Default delay is 3 seconds. */
    public static final int DEFAULT_DELAY_SEC = 3;

    /**
     * Creates a new cancellable button with caption. Default delay is 3 seconds.
     *
     * @see #DEFAULT_DELAY_SEC
     * @param caption
     *            the Button caption
     */
    public CancellableButton(String caption) {
        super(caption);
        setDelay(DEFAULT_DELAY_SEC);
    }

    /**
     * Creates a new cancellable button with caption and delay delay
     *
     * @param caption
     *            the Button caption
     * @param delaySeconds
     *            Number of seconds as specified for {@link #setDelay(int)}
     */
    public CancellableButton(String caption, int delaySeconds) {
        super(caption);
        setDelay(delaySeconds);
    }

    /**
     * Creates a new cancellable button with delay and click listener.
     *
     * @param caption
     *            the Button caption.
     * @param delaySeconds
     *            Number of seconds as specified for {@link #setDelay(int)}
     * @param listener
     *            the Button click listener.
     */
    public CancellableButton(String caption, int delaySeconds,
                             ComponentEventListener<ClickEvent> listener) {
        super(caption, listener);
        setDelay(delaySeconds);
    }

    /** Internal method called from the client side.
     *
     */
    @ClientCallable
    public void click() {
        fireEvent(new ClickEvent(this, true));
    }

    /**
     * Set delay in seconds after which the button is enabled.
     *
     * @see #setDelay(int)
     */
    public int getDelay() {
        return getElement().getProperty("delay", DEFAULT_DELAY_SEC);
    }

    /**
     * Set delay in seconds after which the button is enabled.
     *
     * @param seconds
     *            Number of seconds. Zero or negative number disables the
     *            behaviour.
     * @see #setEnabled(boolean)
     */
    public void setDelay(int seconds) {
        getElement().setProperty("delay", seconds > 0 ? seconds : 0);
    }

    /**
     * Get the function mode for the delay.
     *
     *  By default user should click to cancel withing the delay. Alternatively if this is set to
     *  true, user needs to click twice within the delay.
     *
     * @see #setSecondClickConfirm(boolean)
     */
    public boolean isSecondClickConfirm() {
        return getElement().getProperty("secondClickConfirms", false);
    }

    /**
     * Set the function mode for the delay.
     *
     *  By default user should click to cancel withing the delay. Alternatively if this is set to
     *  true, user needs to click twice within the delay.
     * @param secondClickConfirms
     *            True if a second click is expected within the delay, false if second click should cancel.
     *
     * @see #isSecondClickConfirm
     */
    public void setSecondClickConfirm(boolean secondClickConfirms) {
        getElement().setProperty("secondClickConfirms", secondClickConfirms);
    }

    /**
     * Click button using the given delay timeout to cancel the click.
     *
     * @param seconds
     *            Number of seconds. Zero or negative number disables the
     *            behaviour.
     * @see #setDelay(int)
     * @see #clickWithDelay()
     *
     */
    public void clickWithDelay(int seconds) {
        setDelay(seconds);
        clickWithDelay();
    }

    /**
     * Click button using the set delay timeout to cancel the click.
     *
     * @see #setDelay(int)
     * @see #clickWithDelay(int)
     */
    public void clickWithDelay() {
        getElement().callJsFunction("clickWithDelay");
    }

    /**
     * Cancel the click within the timeout.
     * Cancels the click event, if called within the current cancel delay.
     *
     * @see #setDelay(int)
     * @see #clickWithDelay(int)
     */
    public void cancelClick() {
        getElement().callJsFunction("cancelClick");
    }

    /**
     * Restarts the cancel timer.
     * Restarts the click cancellation timer, if called within the current cancel delay.
     *
     * @see #setDelay(int)
     * @see #clickWithDelay(int)
     */
    public void restartTimer() {
        getElement().callJsFunction("restartTimer");
    }

}
