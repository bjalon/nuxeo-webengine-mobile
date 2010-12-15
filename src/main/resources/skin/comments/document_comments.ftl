<ul title="Comments">
  <#if comments?size == 0>
    There is no comment
  </#if>
  <#list comments as comment>
  <li class="comment">
    <div class="commentHeader">
      ${comment.comment.creationDate} - ${comment.comment.author} wrote:
    </div>
    <div class="commentText">${comment.comment.text}</div>
  </li>
  </#list>
</ul>
