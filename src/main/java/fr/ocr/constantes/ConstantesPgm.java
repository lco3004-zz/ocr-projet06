package fr.ocr.constantes;

/**
 * <p>
 *
 * @author laurent cordier
 * </p>
 */
 public enum ConstantesPgm {
        UNITE_DE_PERSISTANCE("nomDeLaPersistance");

        private String valeurConstante;

        ConstantesPgm(String s) {
            valeurConstante = s;
        }

        public String getValeurConstante() {
            return valeurConstante;
        }

    }
/*
 * ***************************************************************************************************************
 *  the end
 * ***************************************************************************************************************
 */