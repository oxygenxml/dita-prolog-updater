package com.oxygenxml.prolog.updater.utils;

/**
 * Constant class where xpath of XML elements are defined.
 *
 */
public class ElementXPathConstants {

	/**
	 * XPath expression for Prolog element.
	 */
	public static final String PROLOG_XPATH = "/*/*[contains(@class,' topic/prolog ')]";

	/**
	 * XPath expression for topicMeta element(prolog element in DITA map).
	 */
	public static final String TOPICMETA_XPATH = "/*/*[contains(@class,' map/topicmeta ')]";
	
	/**
	 * XPath expression for topicMeta element in subject schema DITA map.
	 * EXM-40992 - Subject schema contains two element('topicmeta' and 'subjectHeadMeta') 
	 * with class's value 'map/topicmeta'. The xPath should be more restrictive. 
	 */
	public static final String TOPICMETA_SUBJECT_SCHEMA_XPATH = "/*/topicmeta[contains(@class,' map/topicmeta ')]";

	/**
	 * Xpath expression for critdates element.
	 */
	public static final String PROLOG_CRITDATES = PROLOG_XPATH + "/critdates";

	/**
	 * Xpath expression for critdates element from topicmeta.
	 */
	public static final String TOPICMETA_CRITDATES = TOPICMETA_XPATH + "/critdates";

	/**
	 * XPath for last author element from prolog.
	 */
	public static final String LAST_PROLOG_AUTHOR = PROLOG_XPATH + "/author[last()]";

	/**
	 * XPath for last author element from prolog.
	 */
	public static final String LAST_TOPICMETA_AUTHOR = TOPICMETA_XPATH + "/author[last()]";

	/**
	 * Retrieve all authors from prolog element.
	 */
	public static final String PROLOG_AUTHORS = PROLOG_XPATH + "/author";

	/**
	 * Retrieve all authors from topicMeta element.
	 */
	public static final String TOPICMETA_AUTHORS = TOPICMETA_XPATH + "/author";

	/**
	 * Returns a "created" element.
	 */
	public static final String PROLOG_CREATED_ELEMENT = PROLOG_CRITDATES + "/created";

	/**
	 * Returns a "created" element from topicmeta.
	 */
	public static final String TOPICMETA_CREATED_ELEMENT = TOPICMETA_CRITDATES + "/created";

	/**
	 * XPath for map root.
	 */
	public static final String ROOT_MAP_XPATH = "/*[1][contains(@class,' map/map ')]";

	/**
	 * XPath for bookmap root.
	 */
	public static final String ROOT_BOOKMAP_XPATH = "/*[1][contains(@class,' bookmap/bookmap ')]";

	/**
	 * XPath for subjectSchema root.
	 */
	public static final String ROOT_SUBJECT_SCHEMA_XPATH = "/*[1][contains(@class,' subjectScheme/subjectScheme ')]";
	
	/**
	 * XPath for topic root.
	 */
	public static final String ROOT_TOPIC_XPATH = "/*[1][contains(@class, ' topic/topic ')]";

	/**
	 * XPath for root topic child.
	 */
	public static final String ROOT_TOPIC_CHILD = ROOT_TOPIC_XPATH + "/*";

	/**
	 * XPath for root map child.
	 */
	public static final String ROOT_MAP_CHILD = ROOT_MAP_XPATH + "/*";

	/**
	 * Private constructor.
	 */
  private ElementXPathConstants() {
    throw new IllegalStateException("Utility class");
  }
}
