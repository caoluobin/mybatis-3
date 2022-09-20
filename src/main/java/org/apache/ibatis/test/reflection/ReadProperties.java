package org.apache.ibatis.test.reflection;

import org.apache.ibatis.datasource.DataSourceException;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.io.*;
import java.util.Properties;

public class ReadProperties {


  public static Properties getProperties(String filePath) {
    Properties p = new Properties();
    try {
      InputStream fileInputStream = new FileInputStream(filePath);//配置文件地址
      p.load(fileInputStream);
      fileInputStream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return p;
  }

  public static Object propertiesToObject(String filePath, Object object) {
    Properties properties = getProperties(filePath);
    MetaObject metametaObject = SystemMetaObject.forObject(object);
    for (Object key : properties.keySet()) {
      String propertyName = (String) key;
      if (metametaObject.hasSetter(propertyName)) {
        String value = (String) properties.get(propertyName);
        Object convertedValue = convertValue(metametaObject, propertyName, value);
        metametaObject.setValue(propertyName, convertedValue);
      }
    }
    return metametaObject.getOriginalObject();
  }
  private static Object convertValue(MetaObject metaDataSource, String propertyName, String value) {
    Object convertedValue = value;
    Class<?> targetType = metaDataSource.getSetterType(propertyName);
    if (targetType == Integer.class || targetType == int.class) {
      convertedValue = Integer.valueOf(value);
    } else if (targetType == Long.class || targetType == long.class) {
      convertedValue = Long.valueOf(value);
    } else if (targetType == Boolean.class || targetType == boolean.class) {
      convertedValue = Boolean.valueOf(value);
    }
    return convertedValue;
  }
  public static void main(String[] args) {
    File file = new File(".");
    String path="D:\\zysj\\JAVAPROJECT\\mybatis-3\\src\\test\\java\\test.properties";
    Object o = propertiesToObject(path, new ReT());
    System.out.println(o);
  }
}
