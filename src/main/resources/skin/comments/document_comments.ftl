<ul title="Comments">
  <#if comments?size == 0>
    There is no comment
  </#if>
  <#list comments as comment>
  <li class="nxDocumentItem">
    <div>
      ${comment.comment.creationDate} - ${comment.comment.author} wrote:
    </div>
    <div>${comment.comment.text}</div>
  </li>
  </#list>
</ul>
