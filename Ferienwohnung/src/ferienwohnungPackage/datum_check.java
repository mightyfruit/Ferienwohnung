
/*
 * entlehnt aus dem Internet
 */

package ferienwohnungPackage;

import java.util.regex.*;

public class datum_check {

    public static boolean isCorrect( String date ) {
        String[] tokens = date.split( "\\." );
        Pattern p = Pattern.compile( "[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}" );
        Matcher m = p.matcher( date );
        if ( tokens.length != 3 || !m.matches() ) {
            return false;
        } else {
            int day = Integer.parseInt( tokens[0] );
            int month = Integer.parseInt( tokens[1] );
            int year = Integer.parseInt( tokens[2] );
            if ( month < 1 || month > 12 ) return false;
            if ( tokens[2].length() != 4 ) return false;
            return isValidDay( day, month, year );
        }
    }

    public static boolean isValidDay( int day, int month, int year ) {
        int max = 30;
        if ( month == 2 ) {
            if ( isLeapYear ( year ) )
                max = 29;
            else
                max = 28;
        } else if ( ( month < 8 && month % 2 == 1 ) || ( month > 7 && month % 2 == 0 ) ) {
            max = 31;
        }
        return ( day >= 1 && day <= max );
    }

    public static boolean isLeapYear( int year ) {
        return ( ( year % 4 == 0 || year % 400 == 0 ) && year % 100 != 0 );
    }
}	//Maximilian Wolf
