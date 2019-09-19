package fr.ocr.view.utile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;

public class GestionCookies {
    private final Logger logger;

    public GestionCookies() {
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    public Cookie resetThisCookie(HttpServletRequest request, String nomCookie) throws RuntimeException {
        Cookie cookie = getCookieByName(request,nomCookie);
        if (cookie != null)
            cookie.setValue(String.valueOf(-1));
        return  cookie;
    }

    public Integer getValParamReqFromCookie(HttpServletRequest request, String nomCookie) throws RuntimeException {
        Cookie cookie= getCookieByName(request,nomCookie);
        return  (cookie !=null)?Integer.valueOf(cookie.getValue()) : null;
    }

    public Cookie setValParamReqIntoCookie(HttpServletRequest request, String nomCookie, String nomParamRequest) throws RuntimeException {
        String selectionRadioButton;
        Cookie cookie= getCookieByName(request,nomCookie);
        selectionRadioButton = request.getParameter(nomParamRequest);

        if (cookie != null && selectionRadioButton != null) {
                cookie.setValue(selectionRadioButton);
        }

        return cookie;
    }

    public  void supprimeCookies(HttpServletRequest req,
                                     HttpServletResponse resp,
                                     ArrayList<String> nomDesCookies) throws RuntimeException  {
        try {
            Cookie[] cookies = req.getCookies();
            if (req.getCookies() != null) {
                for (Cookie cookie : cookies) {
                    nomDesCookies.stream().
                            filter(w-> w.equals(cookie.getName())).
                            forEach((z) -> {
                                cookie.setMaxAge(0); resp.addCookie(cookie);
                                logger.debug("Hello from :" + this.getClass().getSimpleName() + "Cookie effacé = " + cookie.getName());
                            });
                }
            }
        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new RuntimeException(ex);
        }
    }

    public void createCookies (HttpServletResponse resp,
                              ArrayList<String> nomDesCookies) throws RuntimeException {
        try {

            for (String nomCookie :nomDesCookies) {
                resp.addCookie(new Cookie(nomCookie, String.valueOf(-1)));
                logger.debug("Hello from :" + this.getClass().getSimpleName() + " Création Cookies : " + nomCookie);
            }

        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new RuntimeException(ex);
        }
    }

    public Cookie getCookieByName(HttpServletRequest request, String nomCookie) throws RuntimeException{
        Cookie valRet = null;
        try {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(nomCookie)) {
                    valRet = cookie;
                    break;
                }
            }
            if (valRet == null) {
                logger.error("Erreur : " + this.getClass().getSimpleName() + " aucun cookie");
            }

        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new RuntimeException(ex);
        }
        return valRet;
    }
}
