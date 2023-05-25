package com.mycompany.dadoclimatico.dadoclimatico.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtil {

	public static LocalDate stringToData( String data ) {
		try {
			DateTimeFormatter formatter;
			if( data.contains( "/" ) ) {
				formatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
			} else {
				formatter = DateTimeFormatter.ofPattern( "ddMMyyyy" );
			}
			return LocalDate.parse( data, formatter );
		} catch ( Exception e ) {
			System.out.println( "Data Inválida - usando data atual" );
		}
		return LocalDate.now();
	}

	public static String dataToString( LocalDate data ) {
		return data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear();
	}

}
