<ul title="Relations">
  <li class="group">Outgoing relations</li>
    <#list outgoingStatements as outgoingStatement>
    <li class="relation">
      ${Context.getMessage(This.getPredicateLabel("predicates", outgoingStatement))}

      <#assign node = outgoingStatement.objectInfo />
      <#if node.documentModel>
        <#if node.documentModel.common.icon != null && node.documentModel.common.icon != "">
          <img src="${contextPath}/${node.documentModel.common.icon}" />
        </#if>
      </#if>

      <#if node.resource && !node.QNameResource>
        <img src="${contextPath}/icons/html.png" />
      </#if>
      <#if node.QNameResource && !node.documentVisible>
        <img src="${contextPath}/icons/relation_not_visible" />
      </#if>
      <#if node.literal>
        <img src="${contextPath}/icons/page_text.gif" />
      </#if>

      <#if node.QNameResource>
        <#if node.documentVisible>
          <a href="${basePath}/mobile/navigation/byId/${node.documentModel.id}">${node.title}</a>
        </#if>
        <#if !node.documentVisible>
          Referenced document not visible
        </#if>
      </#if>

      <#if node.resource && !node.QNameResource>
        <a href="${node.href}" target="_blank">${note.title}</a>
      </#if>

      <#if node.literal>
        ${node.title}
      </#if>
    </li>
    </#list>
    <#if outgoingStatements?size ==0>
      <li class="relation">No outgoing relation</li>
    </#if>

    <li class="group">Incoming relations</li>
    <#list incomingStatements as incomingStatement>
    <li class="relation">
      ${Context.getMessage(This.getPredicateLabel("inverse_predicates", incomingStatement))}

      <#assign node = incomingStatement.objectInfo />
      <#if node.documentModel>
        <#if node.documentModel.common.icon != null && node.documentModel.common.icon != "">
          <img src="${contextPath}/${node.documentModel.common.icon}" />
        </#if>
      </#if>

      <#if node.resource && !node.QNameResource>
        <img src="${contextPath}/icons/html.png" />
      </#if>
      <#if node.QNameResource && !node.documentVisible>
        <img src="${contextPath}/icons/relation_not_visible" />
      </#if>
      <#if node.literal>
        <img src="${contextPath}/icons/page_text.gif" />
      </#if>

      <#if node.QNameResource>
        <#if node.documentVisible>
          <a href="${basePath}/mobile/navigation/byId/${node.documentModel.id}">${node.title}</a>
        </#if>
        <#if !node.documentVisible>
          Referenced document not visible
        </#if>
      </#if>

      <#if node.resource && !node.QNameResource>
        <a href="${node.href}" target="_blank">${note.title}</a>
      </#if>

      <#if node.literal>
        ${node.title}
      </#if>
    </li>
    </#list>
    <#if incomingStatements?size == 0>
      <li class="relation">No incoming relation</li>
    </#if>
</ul>
