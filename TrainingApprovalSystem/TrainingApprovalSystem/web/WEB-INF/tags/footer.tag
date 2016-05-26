<%@include file="/WEB-INF/tags/importlib.tag" %>
<%@tag pageEncoding="UTF-8" body-content="empty" %>

<c:if test="${sessionScope['auth.user'].role == 'ADMIN'}">
    <footer class="text-left site-footer">
        <div class="footer-above">
            <div class="container">
                <p><img src="assets/images/it.png" alt="banner" /> คณะเทคโนโลยีสารสนเทศ</p> Copyright &copy; IT@12 2016
            </div>
        </div>
    </footer>
</c:if>

<c:if test="${sessionScope['auth.user'].role == 'USER'}">
    <footer class="text-left site-footer">
        <div class="footer-below">
            <div class="container">
                <p><img src="assets/images/it.png" alt="banner" /> คณะเทคโนโลยีสารสนเทศ</p> Copyright &copy; IT@12 2016
            </div>
        </div>
    </footer>
</c:if>