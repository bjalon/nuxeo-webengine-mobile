  <form id="_${Document.id}_preview" class="dialog">
    <div class="popupToolBar">
      <h1 class="popupTitle">Preview</h1>
      <a class="popupButton" type="cancel">Fermer</a>
    </div>
    <div class="popupContent">
      <iframe src="${This.getPreviewURL()}" frameborder="1" width="100%" scrolling="auto" class="preview">
      </iframe>
    </div>
  <form>
