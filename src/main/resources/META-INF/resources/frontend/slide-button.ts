import { customElement, property, html, LitElement, TemplateResult } from 'lit-element';

/** Custom button element.
 * 
 */
@customElement('slide-button')
export class SlideButtonElement extends LitElement {

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
    return html`<div class='container'><div class='slider'>&#10095;</div></div>`;
  }


  firstUpdated(): void {
    this.container = this.renderRoot.querySelector("div.container")
    this.slider = this.renderRoot.querySelector("div.slider")

        // Listening for the mouse and touch events
        this.renderRoot.addEventListener("mousemove", this.drag, false);
        this.renderRoot.addEventListener("touchmove", this.drag, false);
        this.renderRoot.addEventListener("mousedown", this.dragStart, false);
        this.renderRoot.addEventListener("mouseup", this.dragEnd, false);
        this.renderRoot.addEventListener("mouseleave", this.dragEnd, false);
        this.renderRoot.addEventListener("touchstart", this.dragStart, false);
        this.renderRoot.addEventListener("touchend", this.dragEnd, false);
        this.renderRoot.addEventListener("contextmenu", this.dragEnd, false);
  }

  dragStart(event: Event): void {
    const e = event as TouchEvent;
    // Start drag
    this.dragging = true;
    if (this.slider) this.slider.style.transitionDuration = "0s";
    if (e.type === "touchstart") {
      this.initialX = e.touches[0].clientX;
    } else {
      this.initialX = e.touches[0].clientX;
    }

  };

  dragEnd(): void {
    // Stop dragging
    this.dragging = false;
    if (this.slider)  {
      this.slider.style.transition = "left cubic-bezier(0.175, 0.885, 0.32, 1.275)";
      this.slider.style.transitionDuration = "200ms";
      this.slider.style.left = "0px";
    }
  };

  drag(event: Event): void {
    const e = event as TouchEvent;
    if (this.dragging) {
      e.preventDefault();
      var currentX = (e.type === "touchmove") ?
        e.touches[0].clientX - this.initialX : e.touches[0].clientX - this.initialX;
      if (currentX < 0) currentX = 0;

      if (this.slider && this.container && currentX + this.slider.offsetWidth > this.container.offsetWidth - 16) {
        this.slider.style.transition = "left cubic-bezier(.67,-0.3,.61,.69)";
        this.slider.style.transitionDuration = "200ms";
        this.slider.style.left = "0px";
        this.dragging = false;
        setTimeout(function () { this.$server.afterSlideClick(); }, 100);
      } else {
        if (this.slider) this.slider.style.left = currentX + "px";
      }
    }
  }

}