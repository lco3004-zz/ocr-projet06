package fr.ocr.prj06.constantes;

public enum MessageDeBase {
    HTML_DEBUT ("<html> <body> <br>"),
    CONTENT_TYPE("text/html;charset=utf-8"),
    BR("<br>"),
    PDEBUT("<p>"),
    PFIN("</p>"),
    HTML_FIN("<br></body></html>"),
    CODESESSION_LOGIN ("login"),
    IDENTIFIANT_TRAVAIL("idTravail");

    private String messageDeBase;

    MessageDeBase(String s) {
        messageDeBase = s;
    }

    public String getValeur() {
        return messageDeBase;
    }
}
