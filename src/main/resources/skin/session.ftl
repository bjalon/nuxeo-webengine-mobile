<html>
  <head>
    <title>Nuxeo DM</title>
    <meta name="viewport" content="user-scalable=no,width=device-width"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link rel="stylesheet" type="text/css" href="iui/iui.css"/>
    <link rel="stylesheet" type="text/css" href="iui/t/default/default-theme.css"/>
    <link rel="stylesheet" type="text/css" href="iui/ext-sandbox/masabi/t/default/iui_ext.css"/>
    <link rel="apple-touch-icon" href="icons/nuxeo_logo.png" />
    <link rel="apple-touch-startup-image" href="image/nuxeo_splash_screen.png" />
    <script src="iui/iui.js"></script>
    <script src="iui/ext-sandbox/masabi/iui_ext.js"></script>
    <!-- for option button improvement -->
    <script src="iui/ext-sandbox/TbBMod/TbBMod.js"></script>
  </head>
  <body>
    <div id="toolBar" class="toolbar">
      <h1 id="pageTitle"></h1>
      <a id="backButton" class="button" href="#"></a>
    </div>
    <div id="about" title="About" class="panel">
      <h2>Nuxeo DM - 5.4.0</h2>
    </div>
    <form id="login" class="panel" title="Nuxeo DM" method="post" action="${Root.path}/session/@@login" method="POST" target="_webapp">
      <fieldset>
        <div class="row">
          <label>Username</label>
          <span><input name="user_name" type="text"/></span>
        </div>
        <div class="row">
          <label>Password</label>
          <span><input name="user_password" type="password"/></span>
        </div>
      <fieldset>
    </form>
  </body>
</html>