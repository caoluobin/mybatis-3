package org.apache.ibatis.test.reflection;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

public class ParameterAnnotationEg {


  private String a;

  public String getA(@Param(value = "c")String a, List b,Long c){
    return "";
  }

  public static void main(String[] args) {
    try {
      Method getA = ParameterAnnotationEg.class.getDeclaredMethod("getA", String.class, List.class, Long.class);
      Annotation[][] parameterAnnotations = getA.getParameterAnnotations();
      for (int index = 0; index < parameterAnnotations.length; index++) {
        for (Annotation annotation : parameterAnnotations[index]) {
          System.out.println(annotation);
        }
      }
//      MetaClass metaClass = MetaClass.forClass(ParameterAnnotationEg.class, new DefaultReflectorFactory());
//      String[] getterNames = metaClass.getGetterNames();
//      System.out.println(metaClass.hasSetter("a"));
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
