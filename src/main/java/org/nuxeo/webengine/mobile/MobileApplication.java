/*
 * (C) Copyright 2010 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Benjamin JALON
 */

package org.nuxeo.webengine.mobile;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.webengine.WebException;
import org.nuxeo.ecm.webengine.model.WebObject;
import org.nuxeo.ecm.webengine.model.exceptions.WebSecurityException;
import org.nuxeo.ecm.webengine.model.impl.ModuleRoot;

import com.sun.jersey.api.NotFoundException;

/**
 *
 * @author <a href="mailto:bjalon@nuxeo.com">Benjamin JALON</a>
 */
@Path("/mobile")
@WebObject(type = "MobileApplication")
@Produces("text/html;charset=UTF-8")
public class MobileApplication extends ModuleRoot {

    @GET
    @Path("home")
    public Object doGoHomePage(
            @QueryParam("initialURLRequested") String initialURLRequested)
            throws Exception {
        if (isUrlRequestDocument(initialURLRequested)) {
            return doGetMobileURL(initialURLRequested);
        }
        return getTemplate("index.ftl");
    }

    protected Object doGetMobileURL(String initialURLRequested) {
        MobileDocument doc = null;

        if (initialURLRequested.contains("/nxpath/")) {
            int index_start = initialURLRequested.indexOf("nxpath");
            String urlPath = initialURLRequested.substring(index_start
                    + "nxpath/default".length());
            int index_end = urlPath.indexOf("@");
            urlPath = urlPath.substring(0, index_end);
            doc = new MobileDocument(getContext(), urlPath);
        }

        if (initialURLRequested.contains("/nxdoc/")) {
            int index_start = initialURLRequested.indexOf("nxdoc/");
            String id = initialURLRequested.substring(index_start
                    + "nxdoc/default/".length());
            int index_end = id.indexOf("/");
            id = id.substring(0, index_end);
            doc = new MobileDocument(getContext(), new IdRef(id));
        }

        if (doc != null) {
            return doc.getView("index_with_body");
        }
        return getTemplate("index.ftl");
    }

    protected boolean isUrlRequestDocument(String initialURLRequested) {
        if (initialURLRequested == null) {
            return false;
        }

        if (initialURLRequested.contains("nxpath")) {
            return true;
        }

        if (initialURLRequested.contains("nxdoc")) {
            return true;
        }
        return false;
    }

    @POST
    @Path("home")
    public Object doGoHomePagePost(
            @QueryParam("initialURLRequested") String initialURLRequested)
            throws Exception {
        if (isUrlRequestDocument(initialURLRequested)) {
            return doGetMobileURL(initialURLRequested);
        }
        return getTemplate("index.ftl");
    }

    @Path("navigation")
    public Object doGetDocumentWindow() throws Exception {
        return newObject("navigation");
    }

    @Path("search")
    public Object doGetSearchWindow() throws Exception {
        return newObject("search");
    }

    @Path("workflow")
    public Object doGetWorkflowWindow() throws Exception {
        return newObject("workflow");
    }

    @Path("comments")
    public Object doGetComments() throws Exception {
        return newObject("comments");
    }

    @Path("annotations")
    public Object doGetAnnotations() throws Exception {
        return newObject("annotations");
    }

    @Path("relations")
    public Object doGetRelations() throws Exception {
        return newObject("relations");
    }

    @Path("@@login")
    @GET
    public Object doLogin() throws Exception {
        return getTemplate("session");
    }

    @Override
    public Object handleError(WebApplicationException e) {
        if (e instanceof WebSecurityException) {
            return Response.status(401).entity(
                    getTemplate("session.ftl").arg("redirect_url",
                            ctx.getUrlPath())).type("text/html").build();
        } else if (e instanceof NotFoundException) {
            return Response.status(404).entity(getTemplate("not_found.ftl")).type(
                    "text/html").build();
        } else if (e instanceof WebException) {
            return Response.status(500).entity(
                    getTemplate("error.ftl").arg("stacktrace",
                            ((WebException) e).getStackTraceString())).type(
                    "text/html").build();
        } else {

            return super.handleError(e);
        }
    }

}
