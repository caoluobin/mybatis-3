package org.apache.ibatis.test.type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

public class WildTypeEg {
  private List<? extends  Number> list;
  private List<? super String> b;
  private List<String> c;
  private Class<?> aClass;
  public static void main(String[] args) {
    for (Field declaredField : WildTypeEg.class.getDeclaredFields()) {
      ParameterizedType parameterizedType = (ParameterizedType) declaredField.getGenericType();
      Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
      Type wild=actualTypeArguments[0];
      if (wild instanceof WildcardType){
        for (Type upperBound : ((WildcardType) wild).getUpperBounds()) {
          System.out.println(declaredField.getName()+"==up==>"+upperBound.getTypeName());
        }
        for (Type lowerBound : ((WildcardType) wild).getLowerBounds()) {
          System.out.println(declaredField.getName()+"==low==>"+lowerBound.getTypeName());
        }
      }
    }
  }
}
