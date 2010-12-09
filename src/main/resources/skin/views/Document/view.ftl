    <div id="${Document.id}" title="Details"  class="panel">
      <fieldset>
        <div class="row">
          <label>dc:title</label>
          <span>${Document.dublincore.title}</span>
        </div>
        <div class="row">
          <label>dc:description</label>
          <span>${Document.dublincore.description}</span>
        </div>
        <div class="row">
          <label>Issued date</label>
          <span>${Document.dublincore.issued}</span>
        </div>
      </fieldset>
      <form>
        <input type="hidden" name="TbBModTEXT_help" value="Edit"/>
        <input type="hidden" name="TbBModHREF_help" value="${basePath}/mobile/navigation/byId/${Document.id}/@views/edit"/>
      </form>
    <div>
