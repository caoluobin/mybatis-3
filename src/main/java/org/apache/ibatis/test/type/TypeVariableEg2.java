package org.apache.ibatis.test.type;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeVariableEg2 <K extends InputStream & Closeable> {

  private K a;

  public static void main(String[] args) {
    Field[] declaredFields = TypeVariableEg2.class.getDeclaredFields();
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
        System.out.println("getGenericDeclaration==>"+typeVariable.getGenericDeclaration());
        System.out.println("getName==>"+typeVariable.getName());
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
