package org.apache.ibatis.test.reflection;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.invoker.Invoker;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class DivideWUtil<T> {
  private static final DefaultReflectorFactory factory = new DefaultReflectorFactory();

  public void divideW(T a, Class<T> c,BigDecimal divisor) {
    MetaClass metaClass = MetaClass.forClass(c, factory);
    String[] getterNames = metaClass.getGetterNames();
    for (String filedName : getterNames) {
      Class<?> getterType = metaClass.getGetterType(filedName);
      if (getterType == BigDecimal.class) {
        Invoker setInvoker = metaClass.getSetInvoker(filedName);
        Invoker getInvoker = metaClass.getGetInvoker(filedName);
        try {
          BigDecimal decimal = (BigDecimal) getInvoker.invoke(a, new Object[]{});
          setInvoker.invoke(a, new Object[]{decimal.divide(divisor)});
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  public static void main(String[] args) {
    DivideWUtil<ReT> divideWUtil = new DivideWUtil<>();
    ReT reT = new ReT();
    reT.setA(new BigDecimal(80));
    reT.setPhid(10l);
    reT.setStatus(1);
    reT.setName("1233");
    divideWUtil.divideW(reT,ReT.class,new BigDecimal(10));
    System.out.println(reT);
  }
}
