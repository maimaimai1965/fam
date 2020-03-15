package ua.mai.fam.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtil {

    private DateTimeUtil() {}

    /**
     * Используется конвертация из {@link #convertUtilDateToLocalDateViaSqlDate(java.util.Date)} (
     * 
     * @param dateToConvert
     * @return
     */
    public static LocalDate convertUtilDateToLocalDate(java.util.Date dateToConvert) {
        return (dateToConvert != null) ? new java.sql.Date(dateToConvert.getTime()).toLocalDate() : null;
    }
    
    public static LocalDate convertUtilDateToLocalDateViaInstant(java.util.Date dateToConvert) {
        return (dateToConvert != null) ? dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

    public static LocalDate convertUtilDateToLocalDateViaSqlDate(java.util.Date dateToConvert) {
        return (dateToConvert != null) ? new java.sql.Date(dateToConvert.getTime()).toLocalDate() : null;
    }

    public static LocalDate convertUtilDateToLocalDateViaMillisecond(java.util.Date dateToConvert) {
        return (dateToConvert != null) ? Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

//    public static LocalDate convertToLocalDate(java.util.Date dateToConvert) {
//        return LocalDate.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
//    }


    /**
     * Используется конвертация из {@link #convertUtilDateToLocalDateTimeViaSqlTimestamp(java.util.Date dateToConvert)} (
     *
     * @param dateToConvert
     * @return
     */
    public static LocalDateTime convertUtilDateToLocalDateTime(java.util.Date dateToConvert) {
        return (dateToConvert != null) ? new java.sql.Timestamp(dateToConvert.getTime()).toLocalDateTime() : null;
    }

    public static LocalDateTime convertUtilDateToLocalDateTimeViaInstant(java.util.Date dateToConvert) {
        return (dateToConvert != null) ? dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
    }

    public static LocalDateTime convertUtilDateToLocalDateTimeViaSqlTimestamp(java.util.Date dateToConvert) {
        return (dateToConvert != null) ? new java.sql.Timestamp(dateToConvert.getTime()).toLocalDateTime() : null;
    }

    public static LocalDateTime convertUtilDateToLocalDateTimeViaMilisecond(java.util.Date dateToConvert) {
        return (dateToConvert != null) ? Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
    }
//    public static LocalDateTime convertUtilDateToLocalDateTime(java.util.Date dateToConvert) {
//        return LocalDateTime.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
//    }


    /**
     * Используется конвертация из {@link #convertLocalDateToUtilDateViaSqlDate(LocalDate dateToConvert)} (
     *
     * @param dateToConvert
     * @return
     */
    public static java.util.Date convertLocalDateToUtilDate(LocalDate dateToConvert) {
        return (dateToConvert != null) ? java.sql.Date.valueOf(dateToConvert) : null;
    }

    public static java.util.Date convertLocalDateToUtilDateViaSqlDate(LocalDate dateToConvert) {
        return (dateToConvert != null) ? java.sql.Date.valueOf(dateToConvert) : null;
    }

    public static java.util.Date convertLocalDateToUtilDateViaInstant(LocalDate dateToConvert) {
        return (dateToConvert != null) ? java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()) : null;
    }

    /**
     * Используется конвертация из {@link #convertLocalDateTimeToUtilDateViaSqlTimestamp(LocalDateTime dateToConvert)} (
     *
     * @param dateToConvert
     * @return
     */
    public static java.util.Date convertLocalDateTimeToUtilDate(LocalDateTime dateToConvert) {
        return (dateToConvert != null) ? java.sql.Timestamp.valueOf(dateToConvert) : null;
    }

    public static java.util.Date convertLocalDateTimeToUtilDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return (dateToConvert != null) ? java.sql.Timestamp.valueOf(dateToConvert) : null;
    }

    public static java.util.Date convertLocalDateTimeToUtilDateViaInstant(LocalDateTime dateToConvert) {
        return (dateToConvert != null) ? java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }

}
