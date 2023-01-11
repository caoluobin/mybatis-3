package org.apache.ibatis.test.type;

import java.lang.reflect.*;
import java.util.List;

public class GenericArrayTypeEg<T> {
  private T[] tArray;

  private String[] sArray;

  private List<T> tList;

  private List<String>[] lists;

  public static void main(String[] args) {
    Field[] declaredFields = GenericArrayTypeEg.class.getDeclaredFields();
    for (Field declaredField : declaredFields) {
      Type genericType = declaredField.getGenericType();
      if (genericType instanceof GenericArrayType) {//泛型数组
        GenericArrayType genericArrayType=(GenericArrayType) genericType;
        Type genericComponentType = genericArrayType.getGenericComponentType();
        System.out.println(declaredField.getName()+"======>"+genericComponentType.getTypeName());
        System.out.println(declaredField.getName()+"======>"+(genericComponentType instanceof ParameterizedType));
        System.out.println(declaredField.getName()+"======>"+(genericComponentType instanceof TypeVariable<?>));
      }
    }
  }
}
