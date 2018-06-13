<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<script>
    $(function(){
        $("ul.pagination li.disabled a").click(function(){
            return false;
        });
    });

</script>

<nav>
    <ul class="pagination">
        <li>
            <a  href="#" aria-label="Previous" >
                <span aria-hidden="true">«</span>
            </a>
        </li>

        <li >
            <a  href="#" aria-label="Previous" >
                <span aria-hidden="true">‹</span>
            </a>
        </li>

        <c:forEach begin="1" end="${page.totalCount}" varStatus="status">
            <li>
                <a href="?start=${status.index}" class="current">${status.count}</a>
            </li>

        </c:forEach>

        <li >
            <a href="#" aria-label="Next">
                <span aria-hidden="true">›</span>
            </a>
        </li>
        <li >
            <a href="#" aria-label="Next">
                <span aria-hidden="true">»</span>
            </a>
        </li>
    </ul>
</nav>
