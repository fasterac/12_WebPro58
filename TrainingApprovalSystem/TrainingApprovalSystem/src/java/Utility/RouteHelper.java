package utility;

import javax.servlet.http.HttpServletRequest;

public class RouteHelper {

    public static String generateURL(HttpServletRequest request, String urlPath) {
        String uri = request.getScheme() + "://" +
                request.getServerName() +
                ("http".equals(request.getScheme()) && request.getServerPort() == 80 || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? "" : ":" + request.getServerPort() ) +
                request.getContextPath();

        return uri + "/" + urlPath;
    }

}
