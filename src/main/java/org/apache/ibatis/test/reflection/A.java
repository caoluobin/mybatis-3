package org.apache.ibatis.test.reflection;

import org.apache.ibatis.annotations.Param;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

public class A {



  public String getA(@Param(value = "c")String a, List b,Long c){
    return "";
  }

  public static void main(String[] args) {
    try {
      Method getA = A.class.getDeclaredMethod("getA", String.class, List.class, Long.class);
      Annotation[][] parameterAnnotations = getA.getParameterAnnotations();
      for (int index = 0; index < parameterAnnotations.length; index++) {
        for (Annotation annotation : parameterAnnotations[index]) {
          System.out.println(annotation);
        }
      }

    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
