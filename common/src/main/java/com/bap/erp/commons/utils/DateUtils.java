package com.bap.erp.commons.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DateUtils.class);

    public static void main(String args[]) {
        try {
            Date fechaInicial = new Date("03/21/2015");
            Date fechaFinal = new Date("05/15/2017");
            log.info("fechaFinal:: " + aniosMesesDiasEntreDosFechas(fechaInicial, fechaFinal));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Date ultimoDiaDelMes(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMaximum(Calendar.DAY_OF_MONTH),
                cal.getMaximum(Calendar.HOUR_OF_DAY),
                cal.getMaximum(Calendar.MINUTE),
                cal.getMaximum(Calendar.SECOND));
        return cal.getTime();
    }

    public static Date sumaDias(Date fecha, int dias) throws Exception {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha); //configuramos la fecha que se recibe
            calendar.add(Calendar.DAY_OF_YEAR, dias);
            return calendar.getTime();

        } catch (Exception e) {
            throw e;
        }
    }

    public static int dias_entre_2_fechas(Date fechainicial, Date fechafinal) throws Exception {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechainiciostring = df.format(fechainicial);
        try {
            fechainicial = df.parse(fechainiciostring);
        } catch (Exception e) {
        }

        String fechafinalstring = df.format(fechafinal);
        try {
            fechafinal = df.parse(fechafinalstring);
        } catch (Exception e) {
            throw e;
        }
        long fechainicialms = fechainicial.getTime();
        long fechafinalms = fechafinal.getTime();
        long diferencia = fechafinalms - fechainicialms;
        double dias = Math.floor(diferencia / 86400000L);// 3600*24*1000 
        return ((int) dias);
    }

    public static int dias_entre_2_fechas_contables(Date fechainicial, Date fechafinal) throws Exception {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechainiciostring = df.format(fechainicial);
        try {
            fechainicial = df.parse(fechainiciostring);
        } catch (Exception e) {
        }

        String fechafinalstring = df.format(fechafinal);
        try {
            fechafinal = df.parse(fechafinalstring);
        } catch (Exception e) {
            throw e;
        }
        long fechainicialms = fechainicial.getTime();
        long fechafinalms = fechafinal.getTime();
        long diferencia = fechafinalms - fechainicialms;
        double dias = Math.floor(diferencia / 86400000L);// 3600*24*1000 
        return ((int) dias);
    }

    public static String aniosMesesDiasEntreDosFechas(Date fechaInicial, Date fechaFinal) throws Exception {
        try {
            String aniosMesesDias = "";
            int anioFinal = (fechaFinal.getYear() + 1900) - (fechaInicial.getYear() + 1900);
            int mesFinal = (fechaFinal.getMonth() + 1) - (fechaInicial.getMonth() + 1);
            int diaFinal = (fechaFinal.getDate()) - (fechaInicial.getDate());
            if (diaFinal < 0) {
                diaFinal = ultimoDiaDelMes(fechaFinal).getDate() + diaFinal + 1;
                mesFinal = mesFinal - 1;
            }
            if (mesFinal < 0) {
                mesFinal = mesFinal + 12;
                anioFinal = anioFinal - 1;
            }
            if (anioFinal > 9) {
                aniosMesesDias = anioFinal + "";
            } else {
                aniosMesesDias = "0" + anioFinal;
            }
            if (mesFinal > 9) {
                aniosMesesDias = aniosMesesDias + "-" + mesFinal;
            } else {
                aniosMesesDias = aniosMesesDias + "-0" + mesFinal;
            }
            if (diaFinal > 9) {
                aniosMesesDias = aniosMesesDias + "-" + diaFinal;
            } else {
                aniosMesesDias = aniosMesesDias + "-0" + diaFinal;
            }
            return aniosMesesDias;
        } catch (Exception e) {
            throw e;
        }
    }

    public static String convierteFechaALiteral(String fecha) throws Exception {
        String[] fechaDividida = fecha.split("/");
        log.info("fechaDividida::: " + Integer.parseInt(fechaDividida[1]));
        String fechaLiteral = fechaDividida[0] + " de " + devuelveMes(Integer.parseInt(fechaDividida[1])) + " de " + fechaDividida[2];
        return fechaLiteral;
    }

    public static String devuelveMes(int month) {
        String result = "";
        switch (month) {
            case 1: {
                result = "Enero";
                break;
            }
            case 2: {
                result = "Febrero";
                break;
            }
            case 3: {
                result = "Marzo";
                break;
            }
            case 4: {
                result = "Abril";
                break;
            }
            case 5: {
                result = "Mayo";
                break;
            }
            case 6: {
                result = "Junio";
                break;
            }
            case 7: {
                result = "Julio";
                break;
            }
            case 8: {
                result = "Agosto";
                break;
            }
            case 9: {
                result = "Septiembre";
                break;
            }
            case 10: {
                result = "Octubre";
                break;
            }
            case 11: {
                result = "Noviembre";
                break;
            }
            case 12: {
                result = "Diciembre";
                break;
            }
            default: {
                result = "Error";
                break;
            }
        }
        return result;
    }

    public static String dateFormat(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }

    public static String dateFormatReporte(Date date) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
            return formatter.format(date);
        }
        return "";
    }

    public static String dateFormatReporteMes(Date date) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MMM-yy");
            return formatter.format(date);
        }
        return "";
    }

    public static int diferenciaDiasReporte(Date fechaAceptacion, Date fechaFactura) throws Exception {
        try {
            if (fechaAceptacion.getMonth() == fechaFactura.getMonth()) {
                return 0;
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(fechaAceptacion);
                cal.setLenient(true);
                cal.add(Calendar.MONTH, 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                Date fecha = new Date(cal.getTimeInMillis());
                return dias_entre_2_fechas(fecha, fechaFactura) + 1;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static Date convertToDate(Long fecha) throws Exception {
        try {
            Timestamp stamp = new Timestamp(fecha);
            return new Date(stamp.getTime());
        } catch (Exception e) {
            throw e;
        }
    }

}
