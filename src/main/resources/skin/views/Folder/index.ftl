<ul id="${Document.id}" title="${Document.title}">
  <script src="${basePath}/skin/mobile/script/folder_index.js"></script>
  <script>
    function $(id) { return document.getElementsById(id); }
    addListenerForGestureOnItemInPage('${Document.id}');
    /* TODO : improve this selector */
    x$('.deleteButton').on('click', function(event) {
      var docId = this.id.substring(0, this.id.length - '_children'.length) ;
      iui.goBack();
    });
  </script>
  <#assign docs = This.getVisibleChildren()>
  <#if docs?size == 0>
    <li>No children Document found</li>
  </#if>
  <#list docs as doc>
    <li class="nxDocumentItem">
      <div class="deleteButton hiddenDeleteButton" id="${doc.id}_children">Delete</div>
      <a class="${doc.id}_nxlist removable" href="${basePath}/mobile/navigation/byId/${doc.id}">
        <#if doc.common.icon != null && doc.common.icon != "">
        <img src="${contextPath}/${doc.common.icon}" />
        </#if>
        ${doc.dublincore.title}
      </a>
    </li>

  </#list>
  <#if This.hasSubTypes()>
  <form>
    <input type="hidden" name="TbBModTEXT_help" value="+"/>
    <input type="hidden" name="TbBModHREF_help" value="${basePath}/mobile/navigation/displayNewDocumentForm/${Document.id}"/>
  </form>
  </#if>
</ul>
