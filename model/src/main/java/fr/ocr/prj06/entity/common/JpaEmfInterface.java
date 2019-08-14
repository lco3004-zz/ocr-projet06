package fr.ocr.prj06.entity.common;

public interface JpaEmfInterface {
    void openDao() throws Exception;

    void closeDao() throws Exception;
}
