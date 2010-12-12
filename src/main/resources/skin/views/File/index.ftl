<div id="${Document.id}" title="Details"  class="panel">
  <h2>Dublincore</h2>
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
  <h2>Main File attached</h2>
  <fieldset>
    <div class="row">
      <label>Principal file</label>
      <#if Document.file.filename != null>
      <span><a href="${basePath}/mobile/navigation/byId/${Document.id}/@file" target="_webapp">${Document.file.filename}</a></span>
      <#else>
      <span>No file attached</span>
      </#if>
    </div>
  </fieldset>
  <form>
    <input type="hidden" name="TbBModTEXT_help" value="Edit"/>
    <input type="hidden" name="TbBModHREF_help" value="${basePath}/mobile/navigation/byId/${Document.id}/@views/edit"/>
  </form>

  <ul>
    <li class="nxDocumentItem">
      <a href="${basePath}/mobile/relations/${Document.id}">
        Relations
      </a>
    </li>
    <li class="nxDocumentItem">
      <a href="${basePath}/mobile/comments/${Document.id}">
        Comments
      </a>
    </li>
    <li class="nxDocumentItem">
      <a href="${basePath}/mobile/annotations/${Document.id}">
        Annotations
      </a>
    </li>
    <#if Document.file.filename != null && This.hasPreview()>
    <li class="nxDocumentItem">
      <a href="${basePath}/mobile/navigation/byId/${Document.id}/@preview">Preview</a>
    </li>
    </#if>
  </ul>
</div>
