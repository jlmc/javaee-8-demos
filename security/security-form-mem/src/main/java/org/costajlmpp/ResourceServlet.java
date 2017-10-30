package org.jcosta;

import javax.annotation.security.DeclareRoles;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet")
@DeclareRoles({ "ADMIN", "VIEW", "kaz" })
@ServletSecurity(@HttpConstraint(rolesAllowed = "ADMIN"))
public class ResourceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String webName = null;
        if (request.getUserPrincipal() != null) {
            webName = request.getUserPrincipal().getName();
        }

        response.getWriter().write(
                "<html><body> This is a servlet <br><br>\n" +

                        "web username: " + webName + "<br><br>\n" +

                        "web user has role \"ADMIn\": " + request.isUserInRole("ADMIN") + "<br>\n" +
                        "web user has role \"VIEW\": " + request.isUserInRole("VIEW") + "<br>\n" +
                        "web user has role \"kaz\": " + request.isUserInRole("kaz") + "<br><br>\n" +


                        "<form method=\"POST\">" +
                        "<input type=\"hidden\" name=\"logout\" value=\"true\"  >" +
                        "<input type=\"submit\" value=\"Logout\">" +
                        "</form>" +
                        "</body></html>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("true".equals(request.getParameter("logout"))) {
            request.logout();
            request.getSession().invalidate();
        }

        doGet(request, response);
    }

}
