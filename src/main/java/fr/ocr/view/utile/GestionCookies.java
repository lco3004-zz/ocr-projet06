package fr.ocr.view.utile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import static fr.ocr.view.utile.ConstantesSvt.IDDELAVOIE;
import static fr.ocr.view.utile.ConstantesSvt.IDDUSECTEUR;

public class GestionCookies {
    private final Logger logger;

    public GestionCookies() {
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    public Cookie resetThisCookie(HttpServletRequest request, String nomCookie) {
        Cookie valRet = null;
        try {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(nomCookie)) {
                    cookie.setValue(String.valueOf(-1));
                    valRet = cookie;
                }
            }
        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new RuntimeException(ex);
        }
        return valRet;
    }

    public Integer getValParamReqFromCookie(HttpServletRequest request, String nomCookie, String nomParamRequest) throws RuntimeException {

        Integer selectionRadioButton = null;
        try {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(nomCookie)) {
                    selectionRadioButton = Integer.valueOf(cookie.getValue());
                    break;
                }
            }

            if (selectionRadioButton == null) {
                logger.error("Erreur : " + this.getClass().getSimpleName() + " selectionRadio est vide ");
            }

        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new RuntimeException(ex);

        }

        return selectionRadioButton;
    }

    public Cookie getCookieByName(HttpServletRequest request, String nomCookie) {
        Cookie valRet = null;
        try {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(nomCookie)) {
                    valRet = cookie;
                }
            }
            if (valRet == null) {
                logger.error("Erreur : " + this.getClass().getSimpleName() + " aucun choix de Voie -- idVoie est vide ");
            }

        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new RuntimeException(ex);

        }
        return valRet;
    }

    public Cookie setValParamReqIntoCookie(HttpServletRequest request, String nomCookie, String nomParamRequest) throws RuntimeException {
        String selectionRadioButton;
        Cookie valRet = null;
        try {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(nomCookie)) {
                    selectionRadioButton = request.getParameter(nomParamRequest);
                    if (selectionRadioButton != null) {
                        cookie.setValue(selectionRadioButton);
                        valRet = cookie;
                    }
                }
            }

            if (valRet == null) {
                logger.error("Erreur : " + this.getClass().getSimpleName() + " aucun choix de Voie -- idVoie est vide ");
            }

        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new RuntimeException(ex);

        }

        return valRet;
    }

    public void createCookies(HttpServletRequest req, HttpServletResponse resp) throws RuntimeException {
        try {
            String sIdSecteur = "-1";
            String sIdVoie = "-1";
            boolean cookieTrouve = false;

            Cookie[] cookies = req.getCookies();
            if (req.getCookies() != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(IDDUSECTEUR) || cookie.getName().equals(IDDELAVOIE)) {
                        cookie.setValue(String.valueOf(-1));
                        cookieTrouve = true;
                        logger.debug("Hello from :" + this.getClass().getSimpleName() + "Cookie trouvé = " + cookie.getName());
                    }
                }
            }

            if (!cookieTrouve) {
                resp.addCookie(new Cookie(IDDUSECTEUR, sIdSecteur));
                resp.addCookie(new Cookie(IDDELAVOIE, sIdVoie));
                logger.debug("Hello from :" + this.getClass().getSimpleName() + " Création Cookies  ");
            }

        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new RuntimeException(ex);
        }
    }
}
