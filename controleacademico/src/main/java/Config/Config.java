/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;

/**
 *
 * @author Emanuelle Scheifer
 * 
 */
public class Config {

    public static final char ds = new DecimalFormatSymbols(Locale.getDefault(Locale.Category.FORMAT)).getDecimalSeparator();

    public static final NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

    public static final char INCLUIR = 'I';
    public static final char ALTERAR = 'A';
    public static final char EXCLUIR = 'E';

}
