    <#if pageIndex == 0>
    <ul id="search_result" title="Result">
    </#if>
      <#if page?size == 0>
        <li>No Document returned by this saved search</li>
      </#if>
      <#list page as doc>
        <li><a class="${doc.id}_nxlist" href="${basePath}/mobile/navigation/byId/${doc.id}">${doc.dublincore.title}</a></li>
      </#list>
      <#if pageIndex < pageNumber -1 >
        <li><a href="${basePath}/mobile/search/byId/${searchId}/${pageIndex + 1}" target="_replace">Next Documents...</a></li>
      </#if>
    <#if pageIndex == 0>
    </ul>
    </#if>
