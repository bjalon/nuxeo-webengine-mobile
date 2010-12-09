    <div id="${Document.id}" title="Details"  class="panel">
      <h2>Dublin Core</h2>
      <fieldset>
        <div class="row">
          <label>Title</label>
          <span>${Document.dublincore.title}</span>
        </div>
        <div class="row">
          <label>Description</label>
          <span>${Document.dublincore.description}</span>
        </div>
        <div class="row">
          <label>Issued date</label>
          <span>${Document.dublincore.issued}</span>
        </div>
      </fieldset>
      <h2>Localization</h2>
      <fieldset>
        <div class="row">
          <label>Latitude</label>
          <span>${Document.gpslocation.latitude}</span>
        </div>
        <div class="row">
          <label>Longitude</label>
          <span>${Document.gpslocation.longitude}</span>
        </div>
      </fieldset>

      <form>
        <input type="hidden" name="TbBModHIDE_help" value="true" />
      </form>
    <div>
