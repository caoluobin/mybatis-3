package org.apache.ibatis.test.type;


import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParamTypeEg {

  private Map<String, Long> map;

  private List<String> list;

  private Class<?> type;

  private Map.Entry<String, String> entry;

  private ParamA.ParamB paramB;

  private String[] strings;

  public static void main(String[] args) {
    Field[] declaredFields = ParamTypeEg.class.getDeclaredFields();
    for (Field field : declaredFields) {
      Type genericType = field.getGenericType();
      if (genericType instanceof ParameterizedType) {
        ParameterizedType parameterizedType = (ParameterizedType) genericType;
        Type rawType = parameterizedType.getRawType();
        System.out.println(field.getName() + "==rawType==>" + rawType.getTypeName());
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        for (Type typeArgument : typeArguments) {
          System.out.println(field.getName() + "==typeArgument==>" + typeArgument.getTypeName());
        }
        Type ownerType = parameterizedType.getOwnerType();
        if (ownerType != null)
          System.out.println(field.getName() + "==ownerType==>" + ownerType.getTypeName());
      } else if (genericType instanceof GenericArrayType){

        GenericArrayType genericArrayType = (GenericArrayType)genericType;
        Type genericComponentType = genericArrayType.getGenericComponentType();
        System.out.println(field.getName()+"==genericComponentType==>"+genericComponentType.getTypeName());
      }


    }
  }


}
