[![Build Status](https://travis-ci.org/samie/special-buttons.svg?branch=master)](https://travis-ci.org/samie/holdbutton)

# Specialized buttons add-on for Vaadin 14

This addon implements some specialized buttons: 
* HoldButton - a Button that needs to be pressed for a specified time.
* SlideButton - a Button simulating "swipe to unlock" style.
* DelayedButton - a Button that becomes active only after a delay. Shows a countdown in caption.
* CancellableButton - a Button that you can cancel during given period. Shows a countdown in caption.

## Development instructions 

1. Import to your favourite IDE
2. Run the Maven goal `mvn jetty:run` http://localhost:8099
3. Code and test
  * create UI's for various use cases for your add-ons, see examples. These can also work as usage examples for your add-on users.
  * create browser level and integration tests under src/test/java/
  * Browser level tests are executed manually from IDE (JUnit case) or with Maven profile "it" (mvn verify -Pit). 
4. Test also in real world projects, e.g. create a demo project, build a snapshot release ("mvn install") and use the snapshot build in it.

## Creating releases

1. Push your changes to e.g. Github 
2. Update pom.xml to contain proper SCM coordinates (first time only)
3. Use Maven release plugin (mvn versions:set -DnewVersion=4.0.3; mvn install -Pdirectory)
4. Upload the ZIP file generated to target/checkout/target directory to https://vaadin.com/directory service (and/or optionally publish your add-on to Maven central)

