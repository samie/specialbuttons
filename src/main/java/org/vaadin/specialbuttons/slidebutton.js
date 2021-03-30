window.org_vaadin_specialbuttons_SlideButton = function() {

    "use strict";

    var connector = this;

    this.getElement().className = "slide-button";
    this.getElement().innerHTML = "<div class='slide-button-container'><div class='slide-button-slider'>&#10095;</div></div>";
    this.container = connector.getElement().getElementsByClassName("slide-button-container")[0];
    this.slider = connector.getElement().getElementsByClassName("slide-button-slider")[0];

     // Handle changes from the server-side
     this.onStateChange = function() {
        var state = connector.getState();

        if (state.active) {
            connector.getElement().className = "slide-button";
        } else {
            connector.getElement().className = "slide-button not-active";
        }

        if (state.width){
          connector.getElement().width = state.width;
        }
        if (state.height){
          connector.getElement().height = state.height;
        }
        if (state.buttonCaption){
          connector.slider.innerText = state.buttonCaption;
        }
        if (state.resources && state.resources.buttonIcon){
          connector.icon.src = connector.translateVaadinUri(state.resources.buttonIcon.uRL);
        }
     };

    this.dragStart = function(e) {
        // Start drag
        connector.dragging = true;
        connector.getElement().className = "slide-button";
        connector.slider.style.transitionDuration = "0s";
        if (e.type === "touchstart") {
          connector.initialX = e.touches[0].clientX;
        } else {
          connector.initialX = e.clientX;
        }

    };

    this.dragEnd = function(e) {
        // Stop dragging
        connector.dragging = false;
        connector.slider.style.transition = "left cubic-bezier(0.175, 0.885, 0.32, 1.275)";
        connector.slider.style.transitionDuration = "200ms";
        connector.slider.style.left = "0px";
    };

    this.drag = function(e) {
        if (connector.dragging) {
            e.preventDefault();
            var currentX =  (e.type === "touchmove") ?
                e.touches[0].clientX - connector.initialX : e.clientX - connector.initialX;
            if(currentX < 0) currentX = 0;

            if(currentX + connector.slider.offsetWidth > connector.container.offsetWidth - 16) {
                connector.slider.style.transition = "left cubic-bezier(.67,-0.3,.61,.69)";
                connector.slider.style.transitionDuration = "200ms";
                connector.slider.style.left = "0px";
                connector.dragging = false;
                setTimeout(function(){ connector.afterSlideClick(); }, 100);
            } else {
                connector.slider.style.left = currentX + "px";
            }
        }
    };

    // Listening for the mouse and touch events
    this.getElement().addEventListener("mousemove", this.drag, false);
    this.getElement().addEventListener("touchmove", this.drag, false);
    this.getElement().addEventListener("mousedown", this.dragStart, false);
    this.getElement().addEventListener("mouseup", this.dragEnd, false);
    this.getElement().addEventListener("mouseleave", this.dragEnd, false);
    this.getElement().addEventListener("touchstart", this.dragStart, false);
    this.getElement().addEventListener("touchend", this.dragEnd, false);
    this.getElement().addEventListener("contextmenu", this.dragEnd, false);

}
