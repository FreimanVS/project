package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(urlPatterns = {"/*"})
public class UserInfoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;

            if (req.getCookies() == null || Arrays.stream(req.getCookies()).noneMatch(
                    cookie -> cookie.getName().equals("browser-version") && cookie.getValue().equals("ok"))) {

                String userAgent = req.getHeader("User-Agent");

                if (browserIsUpToDate(userAgent))
                    req.getRequestDispatcher(req.getContextPath() + "/jsp/up-to-date.jsp").forward(request, response);
                else {
                    resp.addCookie(new Cookie("browser-version", "ok"));
                    chain.doFilter(request, response);
                }
            } else
                chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    private static boolean browserIsUpToDate(String userAgent) {
        boolean ieIsUpToDate =
                (userAgent.contains("MSIE")
                        && (Integer.parseInt(userAgent.substring(userAgent.indexOf("MSIE")).split("[;]")[0]
                        .split("[\\s]")[1].split("[.]")[0]) < 10)
                );

        boolean firefoxIsUpToDate =
                (userAgent.contains("Firefox")
                        && (Integer.parseInt(userAgent.substring(userAgent.indexOf("Firefox")).split("[\\s]")[0]
                        .replace("Firefox/", "").split("[.]")[0]) < 45));
        boolean operaIsUpToDate =
                ((userAgent.contains("OPR"))
                        && (Integer.parseInt(userAgent.substring(userAgent.indexOf("OPR")).split("[\\s]")[0]
                        .replace("OPR/", "").split("[.]")[0]) < 38))
                        || ((userAgent.contains("Opera"))
                        && (Integer.parseInt(userAgent.substring(userAgent.indexOf("Version")).split("[\\s]")[0]
                        .replace("Version/", "").split("[.]")[0]) < 38));
        boolean chromeIsUpToDate =
                (userAgent.contains("Chrome")
                        && (Integer.parseInt(userAgent.substring(userAgent.indexOf("Chrome")).split("[\\s]")[0]
                        .replace("Chrome/", "").split("[.]")[0]) < 50));
        boolean safariIsUpToDate =
                (userAgent.contains("Safari") && userAgent.contains("Version"))
                        && (Integer.parseInt(userAgent.substring(userAgent.indexOf("Version")).split("[\\s]")[0]
                        .replace("Version/", "").split("[.]")[0]) < 7);

        return operaIsUpToDate || chromeIsUpToDate || firefoxIsUpToDate || ieIsUpToDate || safariIsUpToDate;
    }
}
