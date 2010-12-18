<ul title="Annotations">
  <#if annotations?size == 0>
    There is no annotation
  </#if>
  <#list annotations as annotation>
  <li class="annotation">
    <div class="annotationHeader">
      ${annotation.creator} wrote:
    </div>
    <div class="annotationText">${annotation.bodyAsText}</div>
  </li>
  </#list>
</ul>
