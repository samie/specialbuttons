window.org_vaadin_holdbutton_HoldButton = function() {

    "use strict";

    var connector = this;
    this.timerID;
    this.pressHoldEndTime = 0; // Not animating by default

    this.getElement().className = "hold-button";
    this.getElement().innerHTML = "<div class='content'><img class='icon' /></div>";
    this.content = connector.getElement().getElementsByTagName("div")[0];
    this.icon = connector.getElement().getElementsByTagName("img")[0];

     // Handle changes from the server-side
     this.onStateChange = function() {
        var state = connector.getState();
        connector.content.style.transitionDuration = state.holdtimems +"ms";
        if (state.width){
          connector.getElement().width = state.width;
        }
        if (state.height){
          connector.getElement().height = state.height;
        }
        if (state.buttonCaption){
          connector.content.innerText = state.buttonCaption;
        }
        if (state.resources && state.resources.buttonIcon){
          connector.icon.src = connector.translateVaadinUri(state.resources.buttonIcon.uRL);
        }
      };

     this.timer = function() {
       if (connector.pressHoldEndTime > 0) {
           if (Date.now() < connector.pressHoldEndTime) {
             connector.timerID = requestAnimationFrame(connector.timer);
           } else {
             connector.pressHoldEndTime = 0;
             connector.getElement().classList.add("endstate");
             connector.afterHoldClick();
           }
       }
     }

    this.pressingDown = function(e) {
      // Start the timer
      connector.getElement().classList.add("animate");
      connector.pressHoldEndTime = Date.now() + connector.getState().holdtimems;
      requestAnimationFrame(connector.timer);
      e.preventDefault();
    }

    this.notPressingDown = function(e) {
      // Stop the timer
      connector.getElement().classList.remove("animate");
      connector.getElement().classList.remove("endstate");
      connector.pressHoldEndTime = 0;
      cancelAnimationFrame(connector.timerID);
    }

    // Listening for the mouse and touch events
    this.getElement().addEventListener("mousedown", this.pressingDown, false);
    this.getElement().addEventListener("mouseup", this.notPressingDown, false);
    this.getElement().addEventListener("mouseleave", this.notPressingDown, false);
    this.getElement().addEventListener("touchstart", this.pressingDown, false);
    this.getElement().addEventListener("touchend", this.notPressingDown, false);
    this.getElement().addEventListener("contextmenu", this.notPressingDown, false);

}
