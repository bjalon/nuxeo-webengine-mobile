package org.nuxeo.webengine.mobile.authenticator;

import static org.nuxeo.ecm.platform.ui.web.auth.NXAuthConstants.ERROR_USERNAME_MISSING;
import static org.nuxeo.ecm.platform.ui.web.auth.NXAuthConstants.LOGIN_ERROR;
import static org.nuxeo.ecm.platform.ui.web.auth.NXAuthConstants.LOGIN_FAILED;
import static org.nuxeo.ecm.platform.ui.web.auth.NXAuthConstants.LOGIN_MISSING;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.common.utils.URIUtils;
import org.nuxeo.ecm.platform.ui.web.auth.NXAuthConstants;
import org.nuxeo.ecm.platform.ui.web.auth.plugins.FormAuthenticator;

public class MobileFormAuthenticator extends FormAuthenticator {

    protected static final Log log = LogFactory.getLog(MobileFormAuthenticator.class);

    protected static final String MOBILE_HOME_PAGE = "nxstartup.faces";

    @Override
    public Boolean handleLoginPrompt(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse, String baseURL) {
        try {
            log.debug("Forward to Login Screen");
            Map<String, String> parameters = new HashMap<String, String>();
            String redirectUrl = baseURL + loginPage;
            @SuppressWarnings("unchecked")
            Enumeration<String> paramNames = httpRequest.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement();
                String value = httpRequest.getParameter(name);
                parameters.put(name, value);
            }
            String homePage = baseURL + MOBILE_HOME_PAGE;
            parameters.put(NXAuthConstants.REQUESTED_URL,
                    URLEncoder.encode(baseURL + MOBILE_HOME_PAGE, "UTF-8"));
            String loginError = (String) httpRequest.getAttribute(LOGIN_ERROR);
            if (loginError != null) {
                if (ERROR_USERNAME_MISSING.equals(loginError)) {
                    parameters.put(LOGIN_MISSING, "true");
                } else {
                    parameters.put(LOGIN_FAILED, "true");
                }
            }
            // avoid resending the password in clear !!!
            parameters.remove(passwordKey);
            if (!homePage.equals(httpRequest.getRequestURL())) {
                redirectUrl = URIUtils.addParametersToURIQuery(redirectUrl,
                        parameters);
                httpResponse.sendRedirect(redirectUrl);
            } else {
                httpResponse.sendRedirect(homePage);
            }
        } catch (IOException e) {
            log.error(e, e);
            return false;
        }
        return true;
    }

}
