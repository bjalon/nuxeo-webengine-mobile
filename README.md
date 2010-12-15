About
=====

The purpose of Nuxeo WebEngime Mobile is to create a dedicated mobile application that exposes some Nuxeo DM features.

To enable it you just need to drop the jar into you Nuxeo DM.

The application is based on the [iPhone User Interface](http://code.google.com/p/iui/) (iUI).


Manual Installation
===================

Download the latest Nuxeo DM 5.4.1-SNAPSHOT from "here":http://www.nuxeo.org/static/snapshots/

Build the project with:

    $ ant build

Copy the jar `target/nuxeo-webengine-mobile-X.X.jar`, in the `bundles` direcotry of your Nuxeo DM, for instance:

    $TOMCAT/nxserver/bundles


Building and deploying on an existing Tomcat
============================================

Configure the build.properties files (starting from the build.properties.sample file to be found in the current folder), to point your Tomcat instance::

    $ cp build.properties.sample build.properties
    $ vi build.properties

You can then build and deploy Nuxeo WebEngine Mobile with:

    $ ant deploy


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
