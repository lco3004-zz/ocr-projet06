/*
 * **********************************************************
 * Projet 06
 * Vue : "Listener"
 *      déploiement application : ouverture accès database
 *      retrait applicaiton : cloture accès database
 *
 * Le nom de chaque méthode suffit à comprendre sa fonction
 * méthodes auto générées par IDE
 * ************************************************************
 */

package fr.ocr.view.listeners;

import fr.ocr.business.BusinessMgmt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class List_ApplicationListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {


    private BusinessMgmt businessMgmt;

    private final Logger logger;

    // Public constructor is required by servlet spec
    public List_ApplicationListener() {
        super();
        logger = LogManager.getLogger(this.getClass());
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

        logger.debug("Hello from :" + this.getClass().getSimpleName());

    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        businessMgmt.closeDao();
        logger.debug("Bye from :" + this.getClass().getSimpleName());
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
