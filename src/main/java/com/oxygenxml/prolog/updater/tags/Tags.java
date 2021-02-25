package com.oxygenxml.prolog.updater.tags;

/**
 * Tags used for translation.
 * 
 * @author cosmin_duna
 *
 */
public class Tags {

	/**
	 * Private constructor.
	 */
	private Tags() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * en: Author
	 */
	public static final String AUTHOR = "prolog.updater.author.name";

	/**
	 * en: Date format
	 */
	public static final String DATE_FORMAT = "prolog.updater.date.format";

	/**
	 * en: DITA topic
	 */
	public static final String DITA_TOPIC = "prolog.updater.dita.topic";

	/**
	 * en: DITA map
	 */
	public static final String DITA_MAP = "prolog.updater.dita.map";

	/**
	 * en: Enable automatic prolog update on save
	 */
	public static final String ENABLE_UPDATE_ON_SAVE = "prolog.updater.enable.on.save";

	/**
	 * en: Set creator name
	 */
	public static final String SET_CREATOR = "prolog.updater.set.creator";

	/**
	 * en: Set created date
	 */
	public static final String SET_CREATED_DATE = "prolog.updater.set.created.date";

	/**
	 * en: Update contributor names
	 */
	public static final String UPDATE_CONTRIBUTOR = "prolog.updater.update.contributor";

	/**
	 * en: Update revised dates
	 */
	public static final String UPDATE_REVISED_DATES = "prolog.updater.update.revised.dates";

	/**
	 * en: The prolog wasn't updated.
	 */
	public static final String ERROR_MESSAGE = "prolog.updater.error.message";

	/**
	 * en: Limit the number of revised dates to
	 */
  public static final String LIMIT_REVISED_DATES_TO = "prolog.updater.limit.revised.dates.to";
  
  /**
   * Value of type attribute for creator author.
   */
  public static final String VALUE_OF_TYPE_FOR_CREATOR = "value.of.type.attribute.for.creator";
  
  /**
   * Value of type attribute for contributor author
   */
  public static final String VALUE_OF_TYPE_FOR_CONTRIBUTOR = "value.of.type.attribute.for.contributor";

}
