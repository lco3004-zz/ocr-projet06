package fr.ocr.view.servlets.spot;

import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.model.entities.DbSpot;
import fr.ocr.view.utile.GestionCookies;
import fr.ocr.view.utile.MsgExcpStd;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.ocr.view.utile.ConstantesSvt.*;

@WebServlet(name = "Svt_AcceuilSpot", urlPatterns = {"/AcceuilSpot",
        "/AcceuilSpot/SelectionSecteur",
        "/AcceuilSpot/SelectionSpot",
        "/AcceuilSpot/SelectionVoie"})

public class Svt_AcceuilSpot extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Logger logger;
    private GestionCookies gestionCookies;
    private  List<DbSpot> dbSpots = null;


    public Svt_AcceuilSpot() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
        gestionCookies = new GestionCookies();
    }

    private CtrlMetierSpot ctrlMetierSpot;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;

        String natureRequete = req.getServletPath();

        if (natureRequete.equals("/AcceuilSpot")) {

            gestionCookies.supprimeCookies(req,resp,
                    (ArrayList<String>) Arrays.asList((new String[]{IDDUSPOT,IDDUSECTEUR, IDDELAVOIE})));
            gestionCookies.createCookies(resp,
                    (ArrayList<String>) Arrays.asList((new String[]{IDDUSPOT,IDDUSECTEUR, IDDELAVOIE})));
        }
        try {
            dbSpots = ctrlMetierSpot.listerTousSpots();
        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,req,resp);
        }
        req.setAttribute("dbSpots", dbSpots);
        super.service(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_GestionSpot");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String natureRequete = request.getServletPath();
            Cookie cookie;

            switch (natureRequete) {
                case "/AcceuilSpot":
                    break;
                case "/AcceuilSpot/SelectionSpot":
                    cookie = gestionCookies.setValParamReqIntoCookie(request, IDDUSPOT, IDSELECTIONSPOT);
                    if (cookie != null) {
                        request.setAttribute(IDSELECTIONSPOT, cookie.getValue());
                        response.addCookie(cookie);
                    }
                    gestionCookies.resetThisCookie(request, IDDUSECTEUR);
                    gestionCookies.resetThisCookie(request, IDDELAVOIE);

                    request.setAttribute("dbSpot", ctrlMetierSpot.consulterCeSpot(Integer.valueOf(cookie.getValue())));

                    break;
                case "/AcceuilSpot/SelectionSecteur":
                    cookie = gestionCookies.setValParamReqIntoCookie(request, IDDUSECTEUR, IDSELECTIONSECTEUR);
                    if (cookie != null) {
                        request.setAttribute(IDSELECTIONSECTEUR, cookie.getValue());
                        response.addCookie(cookie);
                    }
                    gestionCookies.resetThisCookie(request, IDDELAVOIE);

                    cookie = gestionCookies.getCookieByName(request, IDDUSPOT);
                    if (cookie != null) {
                        request.setAttribute(IDSELECTIONSPOT, cookie.getValue());
                    }
                    request.setAttribute("dbSpot", ctrlMetierSpot.consulterCeSpot(Integer.valueOf(cookie.getValue())));

                    break;

                case "/AcceuilSpot/SelectionVoie":

                    cookie = gestionCookies.setValParamReqIntoCookie(request, IDDELAVOIE, IDSELECTIONVOIE);
                    if (cookie != null) {
                        request.setAttribute(IDSELECTIONVOIE, cookie.getValue());
                        response.addCookie(cookie);
                    }

                    cookie = gestionCookies.getCookieByName(request, IDDUSECTEUR);
                    if (cookie != null) {
                        request.setAttribute(IDSELECTIONSECTEUR, cookie.getValue());
                    }

                    cookie = gestionCookies.getCookieByName(request, IDDUSPOT);
                    if (cookie != null) {
                        request.setAttribute(IDSELECTIONSPOT, cookie.getValue());
                    }
                    request.setAttribute("dbSpot", ctrlMetierSpot.consulterCeSpot(Integer.valueOf(cookie.getValue())));
                    break;
                default:
                    logger.error("Erreur : " + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
            }
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AcceuilSpot");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }
}
