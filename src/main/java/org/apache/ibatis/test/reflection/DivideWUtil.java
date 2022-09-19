package org.apache.ibatis.test.reflection;

import org.apache.ibatis.reflection.*;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.ibatis.reflection.wrapper.BeanWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

public class DivideWUtil<T> {
  private static final DefaultReflectorFactory factory = new DefaultReflectorFactory();

  public void divideWDefault(T a, Class<T> c, BigDecimal divisor) {
    for (Field declaredField : c.getDeclaredFields()) {
      if (declaredField.getGenericType() == BigDecimal.class) {
        declaredField.setAccessible(true);
        BigDecimal decimal = null;
        try {
          decimal = (BigDecimal) declaredField.get(a);
          declaredField.set(a, decimal.divide(divisor));
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  public void divideWByReflector(T a, Class<T> c, BigDecimal divisor) {
    Reflector reflector = factory.findForClass(c);
    String[] getterNames = reflector.getGetablePropertyNames();
    for (String filedName : getterNames) {
      Class<?> getterType = reflector.getGetterType(filedName);
      if (getterType == BigDecimal.class) {
        Invoker setInvoker = reflector.getSetInvoker(filedName);
        Invoker getInvoker = reflector.getGetInvoker(filedName);
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

  public void divideWByReflectorAndName(T a, Class<T> c, BigDecimal divisor, String name) {
    Reflector reflector = factory.findForClass(c);
    Class<?> getterType = reflector.getGetterType(name);
    if (getterType == BigDecimal.class) {
      Invoker setInvoker = reflector.getSetInvoker(name);
      Invoker getInvoker = reflector.getGetInvoker(name);
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

  public void divideW(T a, Class<T> c, BigDecimal divisor) {
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

  public void divideW(T a, Class<T> c, BigDecimal divisor, String name) {
    MetaClass metaClass = MetaClass.forClass(c, factory);
    Class<?> getterType = metaClass.getGetterType(name);
    if (getterType == BigDecimal.class) {
      Invoker setInvoker = metaClass.getSetInvoker(name);
      Invoker getInvoker = metaClass.getGetInvoker(name);
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

  public void divideWMetaObject(T a, Class<T> c, BigDecimal divisor) {
    MetaObject metaObject = MetaObject.forObject(a, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, factory);
    String[] getterNames = metaObject.getGetterNames();
    for (String getterName : getterNames) {
      if (metaObject.getSetterType(getterName) == BigDecimal.class) {
        BigDecimal decimal = (BigDecimal) metaObject.getValue(getterName);
        metaObject.setValue(getterName, decimal.divide(divisor));
      }
    }
  }

  public void divideWMetaObjectByName(T a, Class<T> c, BigDecimal divisor, String name) {
    MetaObject metaObject = MetaObject.forObject(a, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, factory);
    if (metaObject.getSetterType(name) == BigDecimal.class) {
      BigDecimal decimal = (BigDecimal) metaObject.getValue(name);
      metaObject.setValue(name, decimal.divide(divisor));
    }
  }

  public void divideWMetaObjectChild(Object a, Class c, BigDecimal divisor, Map<Class, List<Class>> map) {
    MetaObject metaObject = MetaObject.forObject(a, new DefaultObjectFactory(), SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, factory);
    String[] getterNames = metaObject.getGetterNames();
    List<Class> childList = new ArrayList<>();
    if (map.containsKey(c)) childList = map.get(c);
    for (String getterName : getterNames) {
      Object value = metaObject.getValue(getterName);
      Class getterType = metaObject.getGetterType(getterName);
      if (metaObject.getSetterType(getterName) == BigDecimal.class) {
        BigDecimal decimal = (BigDecimal) metaObject.getValue(getterName);
        metaObject.setValue(getterName, decimal.divide(divisor));
      } else if (childList.contains(metaObject.getSetterType(getterName))) {

        divideWMetaObjectChild(value, getterType, divisor, map);
      } else if (!isCommon(getterType)) {

      }
    }
  }

  public boolean isCommon(Class c) {
    if (Collection.class.isAssignableFrom(c) || Map.class.isAssignableFrom(c) || c == int.class || c == Integer.class) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    DivideWUtil<ReT> divideWUtil = new DivideWUtil<>();
    ReT reT = new ReT();
    Re re = new Re();
    re.setAmount(new BigDecimal(90));
    reT.setA(new BigDecimal(80));
    reT.setPhid(10l);
    reT.setStatus(1);
    reT.setName("1233");
    reT.setRe(re);

    Map<Class, List<Class>> map = new HashMap<>();
    map.put(ReT.class, Collections.singletonList(Re.class));
    divideWUtil.divideWMetaObjectChild(reT, ReT.class, new BigDecimal(10), map);
    System.out.println(reT);
  }
}
