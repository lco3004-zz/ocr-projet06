package fr.ocr.prj06.listeners;

import fr.ocr.prj06.business.BusinessMgmt;
import fr.ocr.prj06.entity.common.UserProfile;
import fr.ocr.prj06.entity.stub.DbUser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class ApplicationListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    public BusinessMgmt getBusinessMgmt() {
        return businessMgmt;
    }

    private BusinessMgmt businessMgmt;
    // Public constructor is required by servlet spec
    public ApplicationListener() throws Exception {
        businessMgmt = new BusinessMgmt();
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        businessMgmt.openDAO();
        DbUser[] dbUsers = new DbUser[2];
        BusinessMgmt businessMgmt = null;
        try {
            businessMgmt = new BusinessMgmt();
            dbUsers[0] = businessMgmt.ajouterGrimpeur("laurentPgm", "laurent@laurent.pgm", "mdpPgm", UserProfile.GRIMPEUR);

            dbUsers[1] = businessMgmt.ajouterGrimpeur("CordierPgm", "cordiert@laurent.pgm", "mdpPgm", UserProfile.GRIMPEUR);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        businessMgmt.closeDao();

    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
