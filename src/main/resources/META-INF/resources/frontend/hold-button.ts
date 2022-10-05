import { customElement, property, html, LitElement, TemplateResult } from 'lit-element';


/** Custom button element.
 * 
 */
@customElement('hold-button')
export class HoldButtonElement extends LitElement {

  @property({ attribute: true }) active: boolean = true;

  @property({ attribute: false }) holdtime: number = 1000;

  @property({ attribute: true }) icon: string | null = null;

  private timer: any = null;

  private pressHoldEndTime: number = 0;


  firstUpdated() {
    // Listening for the mouse and touch events
    this.renderRoot.addEventListener("mousedown", this.pressingDown);
    this.renderRoot.addEventListener("mouseup", this.notPressingDown);
    this.renderRoot.addEventListener("mouseleave", this.notPressingDown);
    this.renderRoot.addEventListener("touchstart", this.pressingDown);
    this.renderRoot.addEventListener("touchend", this.notPressingDown);
    this.renderRoot.addEventListener("contextmenu", this.notPressingDown);
  }

  /** Render the button.
   * 
   * Add a template to your component to define what it should render. 
   * Templates can include expressions, which are placeholders for dynamic content.
   * 
   */
  render(): TemplateResult {
    return html`<div class='content'><img class='icon' src="${this.icon}" /></div>`;
  }

  _timer() {
    if (this.holdtime > 0) {
      if (Date.now() < this.pressHoldEndTime) {
        this.timer = requestAnimationFrame(this.timer);
      } else {
        this.holdtime = 0;
        this.classList.add("endstate");
        this.$server.afterHoldClick();
      }
    }

  }

  pressingDown(event: Event): void {
    // Start the timer
    this.classList.add("animate");
    this.pressHoldEndTime = Date.now() + this.holdtime;
    requestAnimationFrame(this.timer);
    event.preventDefault();
  }

  notPressingDown(): void {
    // Stop the timer
    this.classList.remove("animate");
    this.classList.remove("endstate");
    this.pressHoldEndTime = 0;
    cancelAnimationFrame(this.timer);    
  }


}