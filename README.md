[![Build Status](https://travis-ci.org/samie/special-buttons.svg?branch=master)](https://travis-ci.org/samie/holdbutton)

# Specialized buttons add-on for Vaadin 8

This addon implements some specialized buttons: 
* HoldButton - a Button that needs to be pressed for a specified time.
* SlideButton - a Button simulating "swipe to unlock" style.

## Development instructions 

1. Import to your favourite IDE
2. Run main method of the Server class to launch embedded web server that lists all your test UIs at http://localhost:9998
3. Code and test
  * create UI's for various use cases for your add-ons, see examples. These can also work as usage examples for your add-on users.
  * create browser level and integration tests under src/test/java/
  * Browser level tests are executed manually from IDE (JUnit case) or with Maven profile "browsertests" (mvn verify -Pbrowsertests). The browsertests profile is enabled automatically for release builds
4. Test also in real world projects, e.g. create a demo project, build a snapshot release ("mvn install") and use the snapshot build in it.

## GWT compilation

* To recompile test widgetset, issue *mvn vaadin:compile*, if you think the widgetset changes are not picked up by Vaadin plugin, do a *mvn clean package* or try with parameter *mvn vaadin:compile -Dgwt.compiler.force=true*
* To use superdevmode, issue "mvn vaadin:run-codeserver" and then just open superdevmode like with any other project

## Creating releases

1. Push your changes to e.g. Github 
2. Update pom.xml to contain proper SCM coordinates (first time only)
3. Use Maven release plugin (mvn release:prepare; mvn release:perform)
4. Upload the ZIP file generated to target/checkout/target directory to https://vaadin.com/directory service (and/or optionally publish your add-on to Maven central)

