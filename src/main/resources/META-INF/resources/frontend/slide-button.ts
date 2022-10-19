import { customElement, html, property, LitElement, TemplateResult } from 'lit-element';

/** Custom button element.
 * 
 */
@customElement('slide-button')
export class SlideButtonElement extends LitElement {

  @property({ attribute: true, reflect: true }) active: boolean = true;

  @property({ attribute: true, reflect: true }) caption: string | null = "";

  private dragging: boolean = false;

  private container: HTMLElement | null = null;

  private slider: HTMLElement | null = null;

  private initialX: number = 0;

  /** Render the button.
   * 
   * Add a template to your component to define what it should render. 
   * Templates can include expressions, which are placeholders for dynamic content.
   * 
   */
  render(): TemplateResult {
    return html`<div part='container'><div part='slider'>${this.caption} &#10095;</div></div>`;
  }


  firstUpdated(): void {
    this.container = this.renderRoot.querySelector('div[part="container"]')
    this.slider = this.renderRoot.querySelector('div[part="slider"]')

        // Listening for the mouse and touch events
        this.addEventListener('mousemove', this.drag, false);
        this.addEventListener('touchmove', this.drag, false);
        this.addEventListener('mousedown', this.dragStart, false);
        this.addEventListener('mouseup', this.dragEnd, false);
        this.addEventListener('mouseleave', this.dragEnd, false);
        this.addEventListener('touchstart', this.dragStart, false);
        this.addEventListener('touchend', this.dragEnd, false);
        this.addEventListener('contextmenu', this.dragEnd, false);
  }

  dragStart(event: Event): void {
    const e = event as MouseEvent;
    // Start drag
    this.dragging = true;
    this.slider!.style.transitionDuration = "0s";
    if (e.type === "touchstart") {
      this.initialX = e.clientX;
    } else {
      this.initialX = e.clientX;
    }

  };

  dragEnd(): void {
    // Stop dragging
    this.dragging = false;
    this.slider!.style.transition = "left cubic-bezier(0.175, 0.885, 0.32, 1.275)";
    this.slider!.style.transitionDuration = "200ms";
    this.slider!.style.left = "0px";
  };

  drag(event: Event): void {
    const e = event as MouseEvent;
    if (this.dragging) {
      e.preventDefault();
      var currentX = (e.type === "touchmove") ?
        e.clientX - this.initialX : e.clientX - this.initialX;
      if (currentX < 0) currentX = 0;

      if (currentX + this.slider!.offsetWidth > this.container!.offsetWidth - 16) {
        this.slider!.style.transition = "left cubic-bezier(.67,-0.3,.61,.69)";
        this.slider!.style.transitionDuration = "200ms";
        this.slider!.style.left = "0px";
        this.dragging = false;
        setTimeout(() => { 
          this.dispatchEvent(new CustomEvent('slide-event', {detail: {} } ));
        }, 100);
      } else {
        this.slider!.style.setProperty("left", currentX + "px", "important");
      }
    }
  }

}