package org.nuxeo.webengine.mobile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.platform.faceted.search.api.service.FacetedSearchService;
import org.nuxeo.ecm.platform.query.api.PageProvider;
import org.nuxeo.ecm.platform.query.api.PageProviderService;
import org.nuxeo.ecm.webengine.WebEngine;
import org.nuxeo.ecm.webengine.model.WebObject;
import org.nuxeo.ecm.webengine.model.impl.DefaultObject;
import org.nuxeo.runtime.api.Framework;

@WebObject(type = "search")
@Produces("text/html;charset=UTF-8")
public class Search extends DefaultObject {

    public static CoreSession session;

    protected PageProviderService service;

    protected FacetedSearchService facetedSearchService;

    @GET
    public Object doGet() throws Exception {
        return getTemplate("search/index.ftl");
    }

    @GET
    @Path("mySearches")
    public Object doGetMySearch() throws ClientException {
        FacetedSearchService facetedSearchService = getFacetedSearchService();
        List<DocumentModel> searches = facetedSearchService.getCurrentUserSavedSearches(getCoreSession());
        return getTemplate("search/my_searches.ftl").arg("searches", searches);
    }

    @GET
    @Path("sharedSearches")
    public Object doGetSharedSearch() throws ClientException {
        FacetedSearchService facetedSearchService = getFacetedSearchService();
        List<DocumentModel> searches = facetedSearchService.getOtherUsersSavedSearches(getCoreSession());
        return getTemplate("search/shared_searches.ftl").arg("searches",
                searches);
    }

    @GET
    @Path("byId/{id}/{pageIndex}")
    public Object doGetSearchById(@PathParam("id") String docId,
            @PathParam("pageIndex") int pageIndex) throws Exception {

        Map<String, Serializable> prop = new HashMap<String, Serializable>();
        prop.put("coreSession", (Serializable) getCoreSession());

        PageProvider<?> pageProvider = getPageProviderService().getPageProvider(
                "faceted_search_core_default", null, 9L, null, prop);

        DocumentModel searchCriteria = getCoreSession().getDocument(
                new IdRef(docId));
        pageProvider.setSearchDocumentModel(searchCriteria);
        // TODO : Waiting an improvement of CoreQuerySession if pageIndex > 0
        pageProvider.setCurrentPage(pageIndex);

        return getTemplate("search/search.ftl").arg("page",
                pageProvider.getCurrentPage()).arg("pageNumber",
                pageProvider.getNumberOfPages()).arg("pageIndex", pageIndex).arg(
                "searchId", docId);
    }

    @GET
    @Path("byId/{id}")
    public Object doGetSearchById(@PathParam("id") String docId)
            throws Exception {
        return doGetSearchById(docId, 0);
    }

    public CoreSession getCoreSession() {
        return WebEngine.getActiveContext().getCoreSession();
    }

    public PageProviderService getPageProviderService() throws Exception {
        if (service == null) {
            service = Framework.getService(PageProviderService.class);
        }
        return service;
    }

    protected FacetedSearchService getFacetedSearchService()
            throws ClientException {
        if (facetedSearchService == null) {
            try {
                facetedSearchService = Framework.getService(FacetedSearchService.class);
            } catch (Exception e) {
                final String errMsg = "Error connecting to FacetedSearchService. "
                        + e.getMessage();
                throw new ClientException(errMsg, e);
            }
            if (facetedSearchService == null) {
                throw new ClientException(
                        "FacetedSearchService service not bound");
            }
        }
        return facetedSearchService;
    }

}
