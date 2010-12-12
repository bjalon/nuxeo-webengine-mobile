/*
 * (C) Copyright 2010 Nuxeo SAS (http://nuxeo.com/) and contributors.
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
 * Contributors:
 * Nuxeo - initial API and implementation
 */

package org.nuxeo.webengine.mobile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
import org.nuxeo.ecm.directory.Session;
import org.nuxeo.ecm.directory.api.DirectoryService;
import org.nuxeo.ecm.platform.relations.api.Node;
import org.nuxeo.ecm.platform.relations.api.QNameResource;
import org.nuxeo.ecm.platform.relations.api.RelationManager;
import org.nuxeo.ecm.platform.relations.api.Resource;
import org.nuxeo.ecm.platform.relations.api.ResourceAdapter;
import org.nuxeo.ecm.platform.relations.api.Statement;
import org.nuxeo.ecm.platform.relations.api.Subject;
import org.nuxeo.ecm.platform.relations.api.impl.StatementImpl;
import org.nuxeo.ecm.platform.relations.api.util.RelationConstants;
import org.nuxeo.ecm.platform.relations.web.NodeInfo;
import org.nuxeo.ecm.platform.relations.web.NodeInfoImpl;
import org.nuxeo.ecm.platform.relations.web.StatementInfo;
import org.nuxeo.ecm.platform.relations.web.StatementInfoImpl;
import org.nuxeo.ecm.webengine.WebEngine;
import org.nuxeo.ecm.webengine.model.WebObject;
import org.nuxeo.ecm.webengine.model.impl.DefaultObject;
import org.nuxeo.runtime.api.Framework;

/**
 * WebObject used to retrieve relations for a given Document.
 * <p>
 * Most of the code comes from the #{RelationActionsBean} class.
 *
 * @author <a href="mailto:troger@nuxeo.com">Thomas Roger</a>
 */
@WebObject(type = "relations")
@Produces("text/html;charset=UTF-8")
public class Relations extends DefaultObject {

    /**
     * Returns a Template listing all the outgoing and incoming relations from
     * a given document.
     * @param docId
     */
    @GET
    @Path("{docId}")
    public Object getRelations(@PathParam("docId") String docId)
            throws ClientException {
        CoreSession session = WebEngine.getActiveContext().getCoreSession();
        DocumentModel doc = session.getDocument(new IdRef(docId));
        RelationManager relationManager = getRelationManager();

        List<StatementInfo> outgoingStatementsInfo;
        Resource docResource = getDocumentResource(doc);
        if (docResource == null) {
            outgoingStatementsInfo = Collections.emptyList();
        } else {
            Statement pattern = new StatementImpl(docResource, null, null);
            List<Statement> outgoingStatements = relationManager.getStatements(
                    RelationConstants.GRAPH_NAME, pattern);
            outgoingStatementsInfo = getStatementsInfo(outgoingStatements);
        }

        List<StatementInfo> incomingStatementsInfo;
        if (docResource == null) {
            incomingStatementsInfo = Collections.emptyList();
        } else {
            Statement pattern = new StatementImpl(null, null, docResource);
            List<Statement> incomingStatements = relationManager.getStatements(
                    RelationConstants.GRAPH_NAME, pattern);
            incomingStatementsInfo = getStatementsInfo(incomingStatements);
        }

        return getTemplate("relations/document_relations.ftl").arg(
                "outgoingStatements", outgoingStatementsInfo).arg(
                "incomingStatements", incomingStatementsInfo);
    }

    protected QNameResource getDocumentResource(DocumentModel document)
            throws ClientException {
        QNameResource documentResource = null;
        if (document != null) {
            documentResource = (QNameResource) getRelationManager().getResource(
                    RelationConstants.DOCUMENT_NAMESPACE, document, null);
        }
        return documentResource;
    }

    protected List<StatementInfo> getStatementsInfo(List<Statement> statements)
            throws ClientException {
        if (statements == null) {
            return null;
        }
        List<StatementInfo> infoList = new ArrayList<StatementInfo>();
        for (Statement statement : statements) {
            Subject subject = statement.getSubject();
            NodeInfo subjectInfo = new NodeInfoImpl(subject,
                    getDocumentModel(subject), true);
            Resource predicate = statement.getPredicate();
            Node object = statement.getObject();
            NodeInfo objectInfo = new NodeInfoImpl(object,
                    getDocumentModel(object), true);
            StatementInfo info = new StatementInfoImpl(statement, subjectInfo,
                    new NodeInfoImpl(predicate), objectInfo);
            infoList.add(info);
        }
        return infoList;
    }

    protected DocumentModel getDocumentModel(Node node) throws ClientException {
        if (node.isQNameResource()) {
            QNameResource resource = (QNameResource) node;
            Map<String, Serializable> context = new HashMap<String, Serializable>();
            CoreSession session = WebEngine.getActiveContext().getCoreSession();
            context.put(ResourceAdapter.CORE_SESSION_ID_CONTEXT_KEY,
                    session.getSessionId());
            Object o = getRelationManager().getResourceRepresentation(
                    resource.getNamespace(), resource, context);
            if (o instanceof DocumentModel) {
                return (DocumentModel) o;
            }
        }
        return null;
    }

    protected RelationManager getRelationManager() throws ClientException {
        try {
            return Framework.getService(RelationManager.class);
        } catch (Exception e) {
            throw new ClientException(e);
        }
    }

    /**
     * Returns the predicate's label for a given {@code Statement}
     * @param directoryName
     * @param statementInfo
     * @return
     * @throws ClientException
     */
    public String getPredicateLabel(String directoryName, StatementInfo statementInfo) throws ClientException {
        DirectoryService directoryService = getDirectoryService();
        Session session = directoryService.open(directoryName);

        DocumentModel entry = session.getEntry(statementInfo.getPredicate().getUri());
        return (String) entry.getPropertyValue("vocabulary:label");
    }

    protected DirectoryService getDirectoryService() throws ClientException {
        try {
            return Framework.getService(DirectoryService.class);
        } catch (Exception e) {
            throw new ClientException(e);
        }
    }
}
