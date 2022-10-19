import { customElement, property, html, LitElement, TemplateResult } from 'lit-element';

/** Custom button element.
 * 
 */
@customElement('cancellable-button')
export class CancellableButtonElement extends LitElement {

  @property({ attribute: true, reflect: true }) caption: string | null = "";

  @property({ attribute: true, reflect: true }) icon: string | null = null;

  @property({ attribute: true, reflect: true }) delay: number = 3;

  @property({ attribute: true, reflect: true }) currentTime: number = 0;

  @property({ attribute: true, reflect: true }) secondClickConfirms: boolean = false;

  private timerId: any = null;

  firstUpdated() {
  }

  /** Render the button.
   * 
   * Add a template to your component to define what it should render. 
   * Templates can include expressions, which are placeholders for dynamic content.
   * 
   */
  render(): TemplateResult {
    return html`<vaadin-button icon="${this.icon}" @click="${ () => this._buttonClick()}">${this.caption}${this.timerId ? this._getTimeText():""}</vaadin-button>`;
  }

  _checkTimer() {
    // If we have a timer running
    if (this.currentTime > 0 && this.timerId) {
      //  and the timer has expired
      if (Date.now() > this.currentTime + this.delay*1000) {
        this._clearTimer();

          // No cancel click within grace period, click 
          // Or if second click was supposed to confirm, do nothing         
          if (!this.secondClickConfirms) {
            this.dispatchEvent(new CustomEvent('timeout-event',  {detail: { confirm: this.secondClickConfirms } } ));         
        }
      }

      // Not expired yet, always update if timer is running
      this.requestUpdate();
    }
  }

  _getTimeText() {
    return this.currentTime > 0 ? " ("+ Math.round(((this.currentTime+this.delay*1000)-Date.now()) / 1000) +")" : "";
  }

  _startTimer(): void {
    this.currentTime = Date.now();
    this.timerId = setInterval(this._checkTimer.bind(this), 1000);
    this.requestUpdate();
  }


  _clearTimer(): void {
    clearInterval(this.timerId);
    this.currentTime = 0;
    this.timerId = null;
    this.requestUpdate();
  }

  _buttonClick(): void {
    if (this.secondClickConfirms || this.delay <= 0) {
      this.dispatchEvent(new CustomEvent('timeout-event',  {detail: { confirm: this.secondClickConfirms } } ));
    } else if (this.timerId) {
      this.cancelClick();
    } else if (!this.timerId) {
      this._startTimer();
    }
  }
  public restartTimer(): void {
    this._clearTimer();
    this._startTimer();
  }

  public cancelClick(): void {
    this._clearTimer();
    this.requestUpdate();
  }

  public clickWithDelay(): void {
    this._startTimer();
  }

}