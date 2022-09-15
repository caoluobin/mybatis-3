package org.apache.ibatis.test.reflection;

import lombok.Data;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;

import java.util.List;

@Data
public class ReTMeta {
  private ReT reT;
  private List<ReT> reTs;
  private String re;

  public static void main(String[] args) {
    DefaultReflectorFactory defaultReflectorFactory = new DefaultReflectorFactory();
    MetaClass retMeta = MetaClass.forClass(ReTMeta.class, defaultReflectorFactory);
    MetaClass reT1 = retMeta.metaClassForProperty("reT");
    Class<?> name = reT1.getGetterType("name");
    System.out.println(name.getName());
    String property = retMeta.findProperty("ReT.name");
    System.out.println(property);
    String[] getterNames = reT1.getGetterNames();
    for (String getterName : getterNames) {
      System.out.println(getterName);
    }
  }
}
