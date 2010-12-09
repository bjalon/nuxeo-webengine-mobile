    <ul id="sharedSearch" title="Shared Searches">
      <#if searches?size == 0>
        <li>There is no shared search</li>
      </#if>
      <#list searches as search>
        <li><a href="${basePath}/mobile/search/byId/${search.id}/0">${search.dublincore.title}</a></li>
      </#list>
    </ul>
