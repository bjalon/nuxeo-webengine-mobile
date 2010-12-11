<ul title="Annotations">
  <#if annotations?size == 0>
    There is no annotation
  </#if>
  <#list annotations as annotation>
  <li class="nxDocumentItem">
    <div>
      ${annotation.creator} wrote:
    </div>
    <div>${annotation.bodyAsText}</div>
  </li>
  </#list>
</ul>
