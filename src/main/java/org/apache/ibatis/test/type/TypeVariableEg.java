package org.apache.ibatis.test.type;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeVariableEg <K> {

  private K a;

  public static void main(String[] args) {
    Field[] declaredFields = TypeVariableEg.class.getDeclaredFields();
    for (Field field : declaredFields) {
      Type genericType = field.getGenericType();
      if (genericType instanceof TypeVariable<?>) {
        TypeVariable typeVariable= (TypeVariable) genericType;
        Type[] bounds = typeVariable.getBounds();
        if (bounds!=null && bounds.length>0){
          for (Type bound : bounds) {
            System.out.println(field.getName()+"==bound===>"+bound.getTypeName());
          }
        }
        AnnotatedType[] annotatedBounds = typeVariable.getAnnotatedBounds();
        if (annotatedBounds!=null && annotatedBounds.length>0){
          for (AnnotatedType annotatedBound : annotatedBounds) {
            System.out.println(annotatedBound.getType().getTypeName());
          }
        }
      }
    }
  }


}
