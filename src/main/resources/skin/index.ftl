<html>
  <head>
    <title>Nuxeo DM</title>
    <meta name="viewport" content="user-scalable=no,width=device-width"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link rel="stylesheet" type="text/css" href="/nuxeo/iui/iui.css"/>
    <link rel="stylesheet" type="text/css" href="/nuxeo/iui/t/default/default-theme.css"/>
    <link rel="stylesheet" type="text/css" href="/nuxeo/iui/ext-sandbox/masabi/t/default/iui_ext.css"/>
    <link rel="apple-touch-icon" href="/nuxeo/icons/nuxeo_logo.png" />
    <link rel="apple-touch-startup-image" href="/nuxeo/image/nuxeo_splash_screen.png" />
    <link rel="stylesheet" type="text/css" href="${basePath}/skin/mobile/css/nx.css"/>
    <script src="/nuxeo/iui/iui.js"></script>
    <script src="/nuxeo/iui/ext-sandbox/masabi/iui_ext.js"></script>

    <!-- for option button improvement -->
    <script src="/nuxeo/iui/ext-sandbox/TbBMod/TbBMod.js"></script>
    <script src="/nuxeo/iui/ext-sandbox/jit-loader/jit-loader.js"></script>
    <script src="${basePath}/skin/mobile/script/nx.js"></script>
    <script src="${basePath}/skin/mobile/script/xui-more-1.0.0.min.js"></script>
    <script> basePath='${basePath}' </script>

  </head>
  <body>
    <div id="toolBar" class="toolbar">
      <h1 id="pageTitle"></h1>
      <a id="backButton" class="button" href="#"></a>
      <a id="help" class="button" href="#about">?</a>
    </div>
    <ul id="home" title="Home" selected="true">
      <li><a href="${basePath}/mobile/navigation/root">Navigation</a></li>
      <li><a href="${basePath}/mobile/search">Saved Searches</a></li>
      <li><a href="${basePath}/mobile/workflow">Tasks</a></li>
    </ul>
    <div id="about" class="panel" title="About">
      <h2>Nuxeo WebApp application</h2>
      <fieldset>
        <p class="normalText">This an example of what you can do with IUI and Webengine. Thanks to the IUI project for this easy to use javascript/css framework.</p>
      </fieldset>
      <form>
        <input type="hidden" name="TbBModHIDE_help" value="how" />
      </form>
    </div>
  </body>
</html>
