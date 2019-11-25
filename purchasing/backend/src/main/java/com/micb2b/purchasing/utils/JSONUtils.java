package com.micb2b.purchasing.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/***
 * Jackson Object to/from String
 *
 */
public class JSONUtils {
  public static String convertToJSON(Object object) {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String result;
        try {
            result = objectWriter.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result = convertToJSON(e);
        }
        return result;
    }

    public static <T> T convertToObject(Class<T> clazz, String jsonString) {
      T result = null;
        try {
          ObjectMapper mapper = new ObjectMapper();
          result = (T)mapper.readValue(jsonString, clazz);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
