package com.bap.erp.commons.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberUtils {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(NumberUtils.class);

    public static void main(String[] args) {
        try {
            BigDecimal numero = new BigDecimal(150.5f);
            BigDecimal porcentaje = new BigDecimal(330L);
            BigDecimal resultado = numero.subtract(porcentaje);
            System.out.println("resultado:: "+resultado);
            System.out.println("resultado:: "+resultado.negate());
            System.out.println(" ==>  "+NumberUtils.decimalFormatBigDecimal(numero));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Float redondeaFloat(Float numero, int cantidadDecimales) throws Exception {
        try {
            BigDecimal bd = new BigDecimal(Float.toString(numero));
            bd = bd.setScale(cantidadDecimales, BigDecimal.ROUND_HALF_UP);
            return bd.floatValue();           
        } catch (Exception e) {
            throw e;
        }
    }

    public static BigDecimal redondeaBigDecimal(BigDecimal numero, int cantidadDecimales) throws Exception {
        try {
            numero = numero.setScale(cantidadDecimales, BigDecimal.ROUND_HALF_UP);
            return numero;
        } catch (Exception e) {
            throw e;
        }
    }

    public static String decimalFormatBigDecimal(BigDecimal numero, int cantidadDecimales) throws Exception {
        numero = numero.setScale(cantidadDecimales, BigDecimal.ROUND_HALF_UP);
//        log.info("Locale.getDefault()::: "+Locale.getDefault());
//        log.info("Locale.getDefault().getLanguage():::::: "+Locale.getDefault().getLanguage());
        
        Locale bLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        //log.info("bLocale::: "+bLocale.getLanguage());
        
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(bLocale);
        DecimalFormat df = new DecimalFormat("###.00",unusualSymbols); // Set your desired format here.
        df.setGroupingUsed(true);
        df.setGroupingSize(3);
        return df.format(numero);
    }
    
    public static String decimalFormatBigDecimal(BigDecimal numero) throws Exception {
        numero = numero.setScale(0, BigDecimal.ROUND_HALF_UP);
//        log.info("Locale.getDefault()::: "+Locale.getDefault());
//        log.info("Locale.getDefault().getLanguage():::::: "+Locale.getDefault().getLanguage());
        
        Locale bLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        //log.info("bLocale::: "+bLocale.getLanguage());
        
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(bLocale);
        DecimalFormat df = new DecimalFormat("###",unusualSymbols); // Set your desired format here.
        df.setGroupingUsed(true);
        df.setGroupingSize(3);
        return df.format(numero);
    }

    public static String decimalFormat(Float numero, int cantidadDecimales) throws Exception {
        log.info("numero:: " + numero);

        BigDecimal bd = new BigDecimal(Float.toString(numero));
        bd = bd.setScale(cantidadDecimales, BigDecimal.ROUND_HALF_UP);

        DecimalFormat df = new DecimalFormat("###.00"); // Set your desired format here.
        df.setGroupingUsed(true);
        df.setGroupingSize(3);
        return df.format(bd);
    }

    private static final String[] UNIDADES = {"", "UN ", "DOS ", "TRES ",
        "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ",
        "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS ",
        "DIECISIETE ", "DIECIOCHO ", "DIECINUEVE ", "VEINTE "};

    private static final String[] DECENAS = {"VENTI", "TREINTA ", "CUARENTA ",
        "CINCUENTA ", "SESENTA ", "SETENTA ", "OCHENTA ", "NOVENTA ",
        "CIEN "};

    private static final String[] CENTENAS = {"CIENTO ", "DOSCIENTOS ",
        "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ",
        "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS "};

    public static String convierteNumeroALetra(BigDecimal numero, String tipoMoneda) throws NumberFormatException {       
//        number = number.replace(",", "");
        String number = numero.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        String numeroCadena = number.replace(".", "#");
        Double numeroFormateado = Double.parseDouble(number);
        if (numeroFormateado > 9999999) {
            String[] numeroDividido = numeroCadena.split("#");
            String numeroMillones = numeroDividido[0].substring(0, (numeroDividido[0].length() - 6));            
            Double numeroMillonesformateado = Double.parseDouble(numeroMillones);
//            BigDecimal segundaParte = new BigDecimal(number.substring(numeroDividido[0].length() - 6, number.length()));
            Double segundaParte = new Double(number.substring(numeroDividido[0].length() - 6, number.length()));
            return convierteNumeroALetra(numeroMillonesformateado, "") + "MILLONES " + convierteNumeroALetra(segundaParte, tipoMoneda);
        }
        return convierteNumeroALetra(Double.parseDouble(number), tipoMoneda);
    }

    private static String convierteNumeroALetra(double doubleNumber, String tipoMoneda)
            throws NumberFormatException {

        StringBuilder converted = new StringBuilder();

        // Validamos que sea un numero legal
        if (doubleNumber > 999999999) {
            throw new NumberFormatException(
                    "El numero es mayor de 999'999.999, "
                    + "no es posible convertirlo");
        }

        if (doubleNumber < 0) {
            throw new NumberFormatException("El numero debe ser positivo");
        }

        String splitNumber[] = String.valueOf(doubleNumber).replace('.', '#')
                .split("#");

        // Descompone el trio de millones
        int millon = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0],
                8))
                + String.valueOf(getDigitAt(splitNumber[0], 7))
                + String.valueOf(getDigitAt(splitNumber[0], 6)));
        if (millon == 1) {
            converted.append("UN MILLON ");
        } else if (millon > 1) {
            converted.append(convertNumber(String.valueOf(millon))
                    + "MILLONES ");
        }

        // Descompone el trio de miles
        int miles = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0],
                5))
                + String.valueOf(getDigitAt(splitNumber[0], 4))
                + String.valueOf(getDigitAt(splitNumber[0], 3)));
        if (miles == 1) {
            converted.append("MIL ");
        } else if (miles > 1) {
            converted.append(convertNumber(String.valueOf(miles)) + "MIL ");
        }

        // Descompone el ultimo trio de unidades
        int cientos = Integer.parseInt(String.valueOf(getDigitAt(
                splitNumber[0], 2))
                + String.valueOf(getDigitAt(splitNumber[0], 1))
                + String.valueOf(getDigitAt(splitNumber[0], 0)));
        if (cientos == 1) {
            converted.append("UN ");
        }

        if (millon + miles + cientos == 0) {
//            converted.append("CERO ");
        }
        if (cientos > 1) {
            converted.append(convertNumber(String.valueOf(cientos)));
        }

//        converted.append("PESOS");
        // Descompone los centavos
        if (!tipoMoneda.equals("")) {
            int centavos = Integer.parseInt(String.valueOf(getDigitAt(
                    splitNumber[1], 2))
                    + String.valueOf(getDigitAt(splitNumber[1], 1))
                    + String.valueOf(getDigitAt(splitNumber[1], 0)));
            if (centavos == 0) {
                converted.append("00/100 " + tipoMoneda);
            } else {
                if (centavos < 10) {
                    converted.append("0" + centavos + "/100 " + tipoMoneda);
                } else {
                    converted.append("" + centavos + "/100 " + tipoMoneda);
                }
//            if (centavos == 1) {
//                converted.append(" CON UN CENTAVO");
//            } else if (centavos > 1) {
//                converted.append(" CON " + convertNumber(String.valueOf(centavos)) + "CENTAVOS");
//            }
            }
        }

        return converted.toString();
    }

    /**
     * Convierte los trios de numeros que componen las unidades, las decenas y
     * las centenas del numero.
     *
     * @param number Numero a convetir en digitos
     * @return Numero convertido en letras
     */
    public static String convertNumber(String number) {

        if (number.length() > 3) {
            throw new NumberFormatException(
                    "La longitud maxima debe ser 3 digitos");
        }

        // Caso especial con el 100
        if (number.equals("100")) {
            return "CIEN ";
        }

        StringBuilder output = new StringBuilder();
        if (getDigitAt(number, 2) != 0) {
            output.append(CENTENAS[getDigitAt(number, 2) - 1]);
        }

        int k = Integer.parseInt(String.valueOf(getDigitAt(number, 1))
                + String.valueOf(getDigitAt(number, 0)));

        if (k <= 20) {
            output.append(UNIDADES[k]);
        } else if (k > 30 && getDigitAt(number, 0) != 0) {
            output.append(DECENAS[getDigitAt(number, 1) - 2] + "Y "
                    + UNIDADES[getDigitAt(number, 0)]);
        } else {
            output.append(DECENAS[getDigitAt(number, 1) - 2]
                    + UNIDADES[getDigitAt(number, 0)]);
        }

        return output.toString();
    }

    /**
     * Retorna el digito numerico en la posicion indicada de derecha a izquierda
     *
     * @param origin Cadena en la cual se busca el digito
     * @param position Posicion de derecha a izquierda a retornar
     * @return Digito ubicado en la posicion indicada
     */
    private static int getDigitAt(String origin, int position) {
        if (origin.length() > position && position >= 0) {
            return origin.charAt(origin.length() - position - 1) - 48;
        }
        return 0;
    }

}
