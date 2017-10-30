package org.jcosta.user.boundary;


import org.jcosta.user.entity.Role;
import org.jcosta.user.entity.User;

import javax.json.Json;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.ws.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("users")
public class UserResource {

    @PersistenceContext
    EntityManager em;

    @GET
    public List<User> all() {
        EntityGraph<User> graph = this.em.createEntityGraph(User.class);
        Subgraph<Role> itemGraph = graph.addSubgraph("roles");
        itemGraph.addAttributeNodes("product");

        Map<String, Object> hints = new HashMap<String, Object>();
        hints.put("javax.persistence.loadgraph", graph);

        //return this.em.find(User.class, orderId, hints);
        return Collections.emptyList();
    }

}
