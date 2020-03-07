package br.com.koradi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date utils
 *
 * @author Cláudio Margulhano
 */
public class DateUtils {

  private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * Returns today's date as java.util.Date object
   *
   * @return today's date as java.util.Date object
   */
  public static Date today() {
    return new Date();
  }

  /**
   * Returns today's date as yyyy-MM-dd format
   *
   * @return today's date as yyyy-MM-dd format
   */
  public static String todayStr() {
    return sdf.format(today());
  }
}
