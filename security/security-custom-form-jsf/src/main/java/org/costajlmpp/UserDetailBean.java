package org.costajlmpp;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Model
public class UserDetailBean {

    @Inject
    private Principal principal;

    private String userName;

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getUserPrincipal() != null) {
            userName = request.getUserPrincipal().getName();
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPrincipalName() {
        return principal.getName();
    }
}
