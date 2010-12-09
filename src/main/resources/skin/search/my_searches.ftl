    <ul id="mysearch" title="My Searches">
      <#if searches?size == 0>
        <li>You have no saved search</li>
      </#if>
      <#list searches as search>
        <li><a href="${basePath}/mobile/search/byId/${search.id}/0">${search.dublincore.title}</a></li>
      </#list>
    </ul>
