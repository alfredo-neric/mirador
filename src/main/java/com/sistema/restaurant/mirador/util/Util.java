package com.sistema.restaurant.mirador.util;

import static com.sistema.restaurant.mirador.util.Constants.ZERO;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import com.sistema.restaurant.mirador.annotation.IgnoredQuery;

/**
 * 
 * @author Juan Carlos Pedraza Alcala
 *
 */
@Component
public class Util {	

	/** Constante para utilizar el log de la aplicacion
	 */
	//private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);		
	
	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isFieldIgnored(final Field field) {
		boolean result = Boolean.FALSE;		
		final Annotation[] annotations = field.getAnnotations();		
		if(ArrayUtils.isNotEmpty(annotations)) {			
			for(final Annotation value: annotations) {
				if(value instanceof IgnoredQuery) {
					result = Boolean.TRUE;
					break;
				}
			}			
		}
		return result;
	}	
	
	/**
	 * 
	 * @param value
	 * @param length
	 * @return
	 */
	public static String fillZerosToRight(final String value, final int length) {
		final StringBuilder result = new StringBuilder(value);		
		for(int index = ZERO; index < length - value.length(); index++) {
			result.append(ZERO);
		}		
		return result.toString();
	}
		
	
}
