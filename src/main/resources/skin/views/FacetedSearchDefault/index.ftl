    <ul id="${Document.id}" title="${Document.title}">
      <#list Document.children as doc>
        <li><a href="${basePath}/mobile/navigation/byId/${doc.id}">${doc.dublincore.title}</a></li>
      </#list>
    </ul>
