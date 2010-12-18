About
=====

The purpose of Nuxeo WebEngime Mobile is to create a dedicated mobile application that exposes some Nuxeo DM features.

To enable it you just need to drop the jar into you Nuxeo DM.

The application is based on the [iPhone User Interface](http://code.google.com/p/iui/) (iUI).


Building and deploying
======================

Using ant 
---------

Download the latest Nuxeo DM 5.4.1-SNAPSHOT Tomcat distribution from [here](http://www.nuxeo.org/static/snapshots/).

Configure the build.properties files (starting from the build.properties.sample file to be found in the current folder), to point your Tomcat instance::

    $ cp build.properties.sample build.properties
    $ vi build.properties

You can then build and deploy Nuxeo WebEngine Mobile with:

    $ ant deploy

Start your server with:
  
    $ $TOMCAT/bin/nuxeoctl start
    
Then you can access the mobile application, with a mobile browser, at the following URL:

    http://localhost:8080/nuxeo/site/mobile

If you want to test it with a non-mobile browser, you can point your browser to:

    http://localhost:8080/nuxeo/login-mobile.jsp

Each time you do a new deployment, you need to restart your server, for that use:

    $ $TOMCAT/bin/nuxeoctl restart|start|stop


Manual deployment
-----------------

You can also do a manual build using `maven` (or `ant`) and deployment.

Build the project with:

    $ ant build

or

    $ mvn clean install

Copy the generated jar `target/nuxeo-webengine-mobile-X.X.jar`, in the `bundles` directory of your Nuxeo DM, for instance:

    $TOMCAT/nxserver/bundles

Start your server with:

    $ $TOMCAT/bin/nuxeoctl start


For developers
==============

To avoid redeploying the whole project for each change when working with web files, there is a `ant` target "`web`" that copies all the web files to the specified Tomcat:

    $ ant web                                                                    
    Buildfile: /Users/nuxeo/coding/nuxeo/nuxeo-webengine-mobile/build.xml

    web:
         [copy] Copying 36 files to /opt/tomcat/nxserver/web/root.war/modules/org.nuxeo.webengine.mobile
         [copy] Copying 142 files to /opt/tomcat/nxserver/nuxeo.war

    BUILD SUCCESSFUL
    Total time: 0 seconds

You don't need to stop and restart your server when using that target.

Build the marketplace package
=============================

To build the marketplace package associated to the Nuxeo WebEngine Mobile application, use:

    $ ant marketplace

A nuxeo-webengine-mobile-X.X.X.zip file will be generated in the `target/` folder. You can then upload it into your Nuxeo DM through the Admin Center (in the local packages tab of the Update Center).
