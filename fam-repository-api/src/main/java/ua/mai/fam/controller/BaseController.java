package ua.mai.fam.controller;

public interface BaseController {

    boolean isJdbcImplementation();

    Object getService();
    Object getDtoRepository();

}
