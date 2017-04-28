package com.sistema.restaurant.mirador.util;

import static com.sistema.restaurant.mirador.util.Constants.LIKE;
import static com.sistema.restaurant.mirador.util.Constants.PREFIX_GET;
import static com.sistema.restaurant.mirador.util.Constants.PREFIX_SET;
import static com.sistema.restaurant.mirador.util.Constants.QUALIFIER_LIKE;
import static com.sistema.restaurant.mirador.util.Constants.STRING_ZERO;
import static com.sistema.restaurant.mirador.util.Constants.ZERO;
import static com.sistema.restaurant.mirador.util.Util.isFieldIgnored;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

//import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Juan Carlos Pedraza Alcala
 *
 */
public class UtilJPA {

	/**
	 * Constante para utilizar el log de la aplicacion
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilJPA.class);

	/**
	 */
	private static final String SELECT = "SELECT ";

	/**
	 */
	private static final String FROM = " FROM ";

	/**
	 */
	private static final String WHERE = " WHERE ";

	/**
	 */
	private static final String EQUAL = "=";

	/**
	 */
	private static final String AND = " AND ";

	/**
	 */
	private static final String POINT = ".";

	/**
	 */
	private static final String IDENTIFIER_FIELD = ":";

	/**
	 */
	private static final String SPACE = " ";

	/**
	 */
	public static final String FUNCTION_LOWER = " lower(";

	/**
	 */
	public static final String CLOSING_PARENTHESIS = ") ";

	/**
	 */
	private static final String CLAUSULE_ORDER_BY = " ORDER BY ";

	public static final String EXCLUDE_LEFT_REGEX = "(cuenta)";

	/**
	 * 
	 * @param em
	 * @param entity
	 * @return
	 */
	public static List<?> executeDinamicQueryJPA(final EntityManager em, final Object entity, final String operator) {
		final List<?> result;
		final String queryString = createJPAQueryFromValuesEntity(entity, operator);
		if (queryString.isEmpty()) {
			result = null;
		} else {
			final TypedQuery<?> query = em.createQuery(queryString, entity.getClass());
			addParametersQueryJPA(entity, query);
			result = query.getResultList();
		}
		return (List<?>) result;
	}

	/**
	 * 
	 * @param em
	 * @param entity
	 * @param alias
	 * @param fieldName
	 * @return
	 */
	public static List<?> executeDinamicQueryOrderByJPA(final EntityManager em, final Object entity,
			final String fieldName, final String operator) {
		final List<?> result;
		String queryString = createJPAQueryFromValuesEntity(entity, operator);
		if (queryString.isEmpty()) {
			result = null;
		} else {
			final String alias = entity.getClass().getSimpleName().substring(0, 1).toLowerCase();
			queryString = addOrderByToQuery(queryString, alias, fieldName);
			LOGGER.info(":: Consulta OrderBy: {} n\n", queryString);
			final TypedQuery<?> query = em.createQuery(queryString, entity.getClass());
			addParametersQueryJPA(entity, query);
			result = query.getResultList();
		}
		return (List<?>) result;
	}

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static String createJPAQueryFromValuesEntity(final Object entity, final String operator) {
		Object resultMethodGet;
		final StringBuilder result = new StringBuilder();
		final String alias = entity.getClass().getSimpleName().substring(0, 1).toLowerCase();
		final Map<String, Object> fields = obtainFields(entity);
		for (final String fieldName : fields.keySet()) {
			resultMethodGet = fields.get(fieldName);

			if (result.length() == 0) {
				createFirstPartJPAQuery(result, alias, entity.getClass().getSimpleName(), fieldName, operator,
						resultMethodGet instanceof String);
			} else {
				addParametersJPAQuery(result, alias, fieldName, operator, resultMethodGet instanceof String);
			}

		}
		LOGGER.info(":: Consulta formada {}", result);
		return result.toString();
	}

	/**
	 * 
	 * @param query
	 * @param alias
	 * @param fieldName
	 * @return
	 */
	public static String addOrderByToQuery(final String query, final String alias, final String fieldName) {
		final StringBuilder result = new StringBuilder(query);
		result.append(CLAUSULE_ORDER_BY);
		result.append(alias);
		result.append(POINT);
		result.append(fieldName);
		return result.toString();
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	private static Map<String, Object> obtainFields(final Object entity) {
		final Map<String, Object> result = new HashMap<String, Object>();
		final Class<?>[] parameterTypes = null;
		final Object[] args = null;
		String fieldName;
		Object resultMethodGet;
		try {
			for (final Field field : entity.getClass().getDeclaredFields()) {
				if (BooleanUtils.negate(isFieldIgnored(field))) {
					fieldName = field.getName();
					resultMethodGet = entity.getClass()
							.getMethod(obtainMehodName(PREFIX_GET, fieldName), parameterTypes).invoke(entity, args);
					if (BooleanUtils.negate(isEmptyValueMethodGet(resultMethodGet))) {
						result.put(fieldName, resultMethodGet);
					}
				}
			}
		} catch (final Exception ignored) {
			LOGGER.error(":: Error al crear la consulta ", ignored);
		}
		return result;
	}

	/**
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String obtainMehodName(final String prefix, final String fieldName) {
		final String result;
		if (isFirstUperCase(fieldName)) {
			result = prefix
					.concat(fieldName.substring(0, 1).toUpperCase().concat(fieldName.substring(1, fieldName.length())));
		} else {
			result = prefix.concat(fieldName);
		}
		return result;
	}

	/**
	 * 
	 * @param entity
	 * @param query
	 */
	public static void addParametersQueryJPA(final Object entity, final TypedQuery<?> query) {
		final Map<String, Object> fields = obtainFields(entity);
		for (final String fieldName : fields.keySet()) {

			LOGGER.info(":: Campo: {} :: Valor: {}  :: {} \n\n", fieldName, fields.get(fieldName),
					(fields.get(fieldName) instanceof String));

			if (fields.get(fieldName) instanceof String) {
				query.setParameter(fieldName, fields.get(fieldName));
			} else {
				query.setParameter(fieldName, fields.get(fieldName));
			}

		}

	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private static boolean isFirstUperCase(final String value) {
		final boolean result;
		if (StringUtils.isAllLowerCase(value.substring(0, 2))) {
			result = Boolean.TRUE;
		} else {
			result = Boolean.FALSE;
		}
		return result;
	}

	/**
	 * 
	 * @param sql
	 * @param alias
	 * @param entityName
	 * @param fieldName
	 */
	private static void createFirstPartJPAQuery(final StringBuilder sql, final String alias, final String entityName,
			final String fieldName, final String operator, final boolean isString) {

		sql.append(SELECT);
		sql.append(alias);
		sql.append(FROM);
		sql.append(entityName);
		sql.append(SPACE);
		sql.append(alias);
		sql.append(WHERE);

		if (isString) {
			sql.append(FUNCTION_LOWER);
			sql.append(alias);
			sql.append(POINT);
			sql.append(fieldName);
			sql.append(CLOSING_PARENTHESIS);
			sql.append(LIKE);
		} else {
			sql.append(alias);
			sql.append(POINT);
			sql.append(fieldName);
			if (StringUtils.isEmpty(operator)) {
				sql.append(EQUAL);
			} else {
				sql.append(operator);
			}
		}
		if (isString) {
			sql.append(FUNCTION_LOWER);
		}
		sql.append(IDENTIFIER_FIELD);
		sql.append(fieldName);
		if (isString) {
			sql.append(CLOSING_PARENTHESIS);
		}

	}

	/**
	 * 
	 * @param sql
	 * @param alias
	 * @param fieldName
	 */
	private static void addParametersJPAQuery(final StringBuilder sql, final String alias, final String fieldName,
			final String operator, final boolean isString) {
		sql.append(AND);
		if (isString) {
			sql.append(FUNCTION_LOWER);
			sql.append(alias);
			sql.append(POINT);
			sql.append(fieldName);
			sql.append(CLOSING_PARENTHESIS);
			sql.append(LIKE);
		} else {
			sql.append(alias);
			sql.append(POINT);
			sql.append(fieldName);
			if (StringUtils.isEmpty(operator)) {
				sql.append(EQUAL);
			} else {
				sql.append(operator);
			}
		}
		if (isString) {
			sql.append(FUNCTION_LOWER);
		}
		sql.append(IDENTIFIER_FIELD);
		sql.append(fieldName);
		if (isString) {
			sql.append(CLOSING_PARENTHESIS);
		}
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmptyValueMethodGet(final Object value) {
		boolean result;
		if (value instanceof String) {
			if (StringUtils.isEmpty((String) value)) {
				result = Boolean.TRUE;
			} else {
				result = Boolean.FALSE;
			}
		} else if (value == null) {
			result = Boolean.TRUE;
		} else {
			result = Boolean.FALSE;
		}
		return result;
	}

	/**
	 * 
	 * @param entity
	 */
	public static void addQualifiersLikeFieldsString(final Object entity) {
		final Class<?>[] parameterTypes = null;
		final Object[] args = null;
		String fieldName;
		Object resultMethodGet;
		try {
			for (final Field field : entity.getClass().getDeclaredFields()) {
				if (BooleanUtils.negate(isFieldIgnored(field))) {
					fieldName = field.getName();
					resultMethodGet = entity.getClass()
							.getMethod(obtainMehodName(PREFIX_GET, fieldName), parameterTypes).invoke(entity, args);

					if (BooleanUtils.negate(isEmptyValueMethodGet(resultMethodGet))
							&& resultMethodGet instanceof String) {
						if (fieldName.matches(EXCLUDE_LEFT_REGEX)) {
							entity.getClass().getMethod(obtainMehodName(PREFIX_SET, fieldName), String.class)
									.invoke(entity, (resultMethodGet) + QUALIFIER_LIKE);
						} else {

							entity.getClass().getMethod(obtainMehodName(PREFIX_SET, fieldName), String.class)
									.invoke(entity, QUALIFIER_LIKE + (resultMethodGet) + QUALIFIER_LIKE);
						}
					}
				}
			}
		} catch (final Exception ignored) {
			LOGGER.error(":: Error al agregar el cualificador like :  ", ignored);
		}
	}

	/**
	 * 
	 * @param entity
	 */
	public static Object addRightQualifiersLikeFieldsString(final Object entity) {
		final Class<?>[] parameterTypes = null;
		final Object[] args = null;
		String fieldName;
		Object resultMethodGet;
		try {
			for (final Field field : entity.getClass().getDeclaredFields()) {
				if (BooleanUtils.negate(isFieldIgnored(field))) {
					fieldName = field.getName();
					resultMethodGet = entity.getClass()
							.getMethod(obtainMehodName(PREFIX_GET, fieldName), parameterTypes).invoke(entity, args);

					if (BooleanUtils.negate(isEmptyValueMethodGet(resultMethodGet))
							&& resultMethodGet instanceof String) {
						String toSet = (String) resultMethodGet;
						if (!StringUtils.contains((String) resultMethodGet, QUALIFIER_LIKE)) {
							toSet = (resultMethodGet) + QUALIFIER_LIKE;
						}
						// if (fieldName.matches(EXCLUDE_LEFT_REGEX)) {
						// entity.getClass().getMethod(obtainMehodName(PREFIX_SET,
						// fieldName), String.class)
						// .invoke(entity, (resultMethodGet) + QUALIFIER_LIKE);
						// } else {

						entity.getClass().getMethod(obtainMehodName(PREFIX_SET, fieldName), String.class).invoke(entity,
								toSet);
						// }
					}
				}
			}
		} catch (final Exception ignored) {
			LOGGER.error(":: Error al agregar el cualificador like :  ", ignored);
		}
		return entity;
	}

	public static Object fillPropertyStringIfNull(Object entity) {
//		try {
//			PropertyDescriptor[] desc = PropertyUtils.getPropertyDescriptors(entity);
//			for (PropertyDescriptor propertyDescriptor : desc) {
//				if (propertyDescriptor.getPropertyType().getName().equals("java.lang.String")) {
//					Object value;
//
//					value = propertyDescriptor.getReadMethod().invoke(entity, new Object[] {});
//
//					if (null == value) {
//						propertyDescriptor.getWriteMethod().invoke(entity, new Object[] { StringUtils.EMPTY });
//					}
//				}
//			}
//
//		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//			LOGGER.error(e.getMessage(), e);
//		}

		return entity;
	}

	/**
	 * 
	 * @param entity
	 */
	public static void clearQualifiersLikeFieldsString(final Object entity) {
		final Class<?>[] parameterTypes = null;
		final Object[] args = null;
		String fieldName;
		Object resultMethodGet;
		try {
			for (final Field field : entity.getClass().getDeclaredFields()) {
				if (BooleanUtils.negate(isFieldIgnored(field))) {
					fieldName = field.getName();
					resultMethodGet = entity.getClass()
							.getMethod(obtainMehodName(PREFIX_GET, fieldName), parameterTypes).invoke(entity, args);

					if (BooleanUtils.negate(isEmptyValueMethodGet(resultMethodGet))
							&& resultMethodGet instanceof String) {

						entity.getClass().getMethod(obtainMehodName(PREFIX_SET, fieldName), String.class).invoke(entity,
								StringUtils.strip(resultMethodGet.toString(), "%"));

					}
				}
			}
		} catch (final Exception ignored) {
			LOGGER.error(":: Error al agregar el cualificador like :  ", ignored);
		}
	}

	private static int countRightZeros(String son) {
		int countZeros = 0;
		String digit;
		for (int ix = son.length() - 1; ix >= 0; ix--) {
			digit = son.substring(ix, ix + 1);
			if (digit.equalsIgnoreCase(STRING_ZERO)) {
				countZeros++;
			} else {
				break;
			}
		}
		return countZeros;
	}

	public static List<String> getParentsToValidate(String son) {
		StringBuilder sbConcat = new StringBuilder();
		List<String> parentsToVal = new ArrayList<String>();
		int countRightZeros = countRightZeros(son);
		if (countRightZeros < son.length() - 1) {
			sbConcat.append(son.substring(ZERO, son.length() - countRightZeros - 1));
			for (int ix = son.length() - countRightZeros - 1; ix < son.length(); ix++) {
				sbConcat.append(STRING_ZERO);
			}
			parentsToVal.add(sbConcat.toString());
		}

		// char parentSecondLevel = son.charAt(son.length() - countRightZeros -
		// 1);
		// int asciiParent = (int) parentSecondLevel;
		// if (asciiParent != ASCII_A && asciiParent != ASCII_0 && asciiParent
		// != ASCII_1) {
		// sbConcat.replace(ZERO, sbConcat.length(), "");
		// sbConcat.append(son.substring(ZERO, son.length() - countRightZeros -
		// 1));
		// asciiParent = asciiParent - 1;
		// sbConcat.append((char) asciiParent);
		// for (int ix = son.length() - countRightZeros; ix < son.length();
		// ix++) {
		// sbConcat.append(STRING_ZERO);
		// }
		// parentsToVal.add(sbConcat.toString());
		// }

		return parentsToVal;

	}

	public static List<String> getParentsToValidateDependencia(String son) {
		StringBuilder sbConcat = new StringBuilder();
		List<String> parentsToVal = new ArrayList<String>();
		int countRightZeros = countRightZeros(son);
		if (countRightZeros < son.length() - 1) {
			sbConcat.append(son.substring(ZERO, son.length() - countRightZeros - 1));
			for (int ix = son.length() - countRightZeros - 1; ix < son.length(); ix++) {
				sbConcat.append(STRING_ZERO);
			}
			parentsToVal.add(sbConcat.toString());
		}

		return parentsToVal;

	}

	public static List<String> getParentsToValidateMuninep(String son) {
		List<String> parents = new ArrayList<String>();
		for (int i = 0; i < son.length(); i += 2) {
			parents.add(0, son.substring(0, i + 2));
		}
		return parents;
	}

	public static void main(String[] args) {

		System.out.println(getParentsToValidate("100A420000"));

	}

}
