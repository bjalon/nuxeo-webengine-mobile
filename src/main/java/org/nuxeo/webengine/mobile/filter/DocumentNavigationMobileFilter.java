package org.nuxeo.webengine.mobile.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.common.utils.URIUtils;

public class DocumentNavigationMobileFilter implements Filter {

    protected static final Log log = LogFactory.getLog(DocumentNavigationMobileFilter.class);

    public static final Object IS_MOBILE_NAVIGATION_COOKIE_NAME = "isMobileNavigationActivated";

    public static String homeURLmobile = "/nuxeo/site/mobile/home";

    public static String askMobileOrStandardNavigationURL = "/nuxeo/ask-mobile-or-standard-navigation.jsp";

    public static List<String> MOBILE_USER_AGENT = Arrays.asList("(.*)Mobile(.*)Safari(.*)", "(.*)AppleWebKit(.*)Mobile(.*)");

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) {
            chain.doFilter(request, response);
            log.debug("Not a http request");
        }

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (!isMobileDevice(req)) {
            log.debug("Not Mobile device");
            chain.doFilter(request, response);
            return;
        }

        for (Cookie cookie : req.getCookies()) {
            if (IS_MOBILE_NAVIGATION_COOKIE_NAME.equals(cookie.getName())) {
                if (!"true".equals(cookie.getValue())) {
                    log.debug("User don't want mobile navigation");
                    chain.doFilter(request, response);
                    return;
                } else {
                    log.debug("User want mobile navigation");
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("initialURLRequested", req.getRequestURI());
                    String redirect = URIUtils.addParametersToURIQuery(homeURLmobile, param);
                    res.sendRedirect(redirect);
                    chain.doFilter(request, response);
                    return;
                }
            }
        }
        log.debug("Redirect to the question about mobile navigation");
        Map<String, String> param = new HashMap<String, String>();
        param.put("initialURLRequested", req.getRequestURI());
        String redirect = URIUtils.addParametersToURIQuery(askMobileOrStandardNavigationURL, param);
        res.sendRedirect(redirect);
        res.flushBuffer();

    }


    public void destroy() {
        // TODO Auto-generated method stub

    }

    protected boolean isMobileDevice(HttpServletRequest req) {
        String userAgent = req.getHeader("User-Agent");
        boolean isMobileDevice = false;
        for (String pattern : MOBILE_USER_AGENT) {
            if (userAgent.matches(pattern)) {
                isMobileDevice = true;
            }
        }
        return isMobileDevice;
    }

}
