import { customElement, property, html, LitElement, TemplateResult } from 'lit-element';

/** Custom button element.
 * 
 */
@customElement('hold-button')
export class HoldButtonElement extends LitElement {

  @property({ attribute: true, reflect: true }) active: boolean = true;

  @property({ attribute: true, reflect: true }) caption: string | null = "";

  @property({ attribute: true, reflect: true }) holdtime: number = 1000;

  @property({ attribute: true, reflect: true }) icon: string | null = null;

  private timerId: any = null;

  private pressHoldEndTimeMs: number = 0;

  private container: HTMLElement | null = null;

  firstUpdated() {

    this.container = this.renderRoot.querySelector('div');

    // Listening for the mouse and touch events
    this.addEventListener('mousedown', this.down, false);
    this.addEventListener('mouseup', this.up, false);
    this.addEventListener('mouseleave', this.up, false);
    this.addEventListener('touchstart', this.down, false);
    this.addEventListener('touchend', this.up, false);
    this.addEventListener('contextmenu', this.up, false);
  }

  /** Render the button.
   * 
   * Add a template to your component to define what it should render. 
   * Templates can include expressions, which are placeholders for dynamic content.
   * 
   */
  render(): TemplateResult {
    return html`<div part="content"><img part="icon" src="${this.icon}" /><span part="caption">${this.caption}</span</div>`;
  }


  down(event: Event): void {
    // Start the timer
    this.container!.style.setProperty("transition-duration",this.holdtime +"ms", "important");
    this.container?.setAttribute('part', 'content pressed');
    this.pressHoldEndTimeMs = Date.now() + this.holdtime;
    this.timerId = requestAnimationFrame(this._timer.bind(this));
    event.preventDefault();
  }

  up(): void {
    // Stop the timer
    this.container!.style.setProperty("transition-duration","");
    this.container?.setAttribute('part', 'content');
    this.pressHoldEndTimeMs = 0;
    cancelAnimationFrame(this.timerId);   
  }


  _timer() {
    if (this.pressHoldEndTimeMs > 0) {
      if (Date.now() < this.pressHoldEndTimeMs) {
        // Hold still
        this.timerId = requestAnimationFrame(this._timer.bind(this));
      } else {
        // Hold event
        this.pressHoldEndTimeMs = 0;
        this.container!.style.setProperty("transition-duration","");
        this.container?.setAttribute('part', 'content ready');
        this.dispatchEvent(new CustomEvent('hold-event',  {detail: {} } ));
      }
    }

  }

}