package org.apache.ibatis.reflection;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.ibatis.reflection.property.PropertyCopier;
import org.apache.ibatis.test.reflection.DivideWUtil;
import org.apache.ibatis.test.reflection.Re;
import org.apache.ibatis.test.reflection.ReT;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

public class DivideWUtilTest {
  @Test
  public void divideW(){
    DivideWUtil<ReT> divideWUtil = new DivideWUtil<>();
    ReT reT = new ReT();
    Re re = new Re();
    re.setAmount(new BigDecimal(90));
    reT.setA(new BigDecimal(80));
    reT.setPhid(10l);
    reT.setStatus(1);
    reT.setName("1233");
    reT.setRe(re);

    Map<Class,List<Class>> map = new HashMap<>();
    map.put(ReT.class, Collections.singletonList(Re.class));
    divideWUtil.divideW(reT,ReT.class,new BigDecimal(10));
    System.out.println(reT);
  }
  @Test
  public void divideWMetaObjectChild(){
    DivideWUtil<ReT> divideWUtil = new DivideWUtil<>();
    ReT reT = new ReT();
    Re re = new Re();
    re.setAmount(new BigDecimal(90));
    reT.setA(new BigDecimal(80));
    reT.setPhid(10l);
    reT.setStatus(1);
    reT.setName("1233");
    reT.setRe(re);

    Map<Class,List<Class>> map = new HashMap<>();
    map.put(ReT.class, Collections.singletonList(Re.class));
    divideWUtil.divideWMetaObjectChild(reT,ReT.class,new BigDecimal(10),map);
    System.out.println(reT);
  }

  @Test
  public void speedTest(){
    ReT reT = new ReT();
    reT.setA(new BigDecimal(80));
    reT.setPhid(10l);
    reT.setStatus(1);
    reT.setName("1233");
    DivideWUtil<ReT> divideWUtil = new DivideWUtil<>();
    long start =System.currentTimeMillis();
    BigDecimal bigDecimal = new BigDecimal(1);
    for (int i = 0; i < 10000000; i++) {
      divideWUtil.divideWByReflector(reT,ReT.class,bigDecimal);
    }
    long end =System.currentTimeMillis();
    System.out.println(end-start);
    start =System.currentTimeMillis();
    for (int i = 0; i < 10000000; i++) {
      divideWUtil.divideWByReflectorAndName(reT,ReT.class,bigDecimal,"a");
    }
    end =System.currentTimeMillis();
    System.out.println(end-start);
    start =System.currentTimeMillis();
    for (int i = 0; i < 10000000; i++) {
      divideWUtil.divideWDefault(reT,ReT.class,bigDecimal);
    }
    end =System.currentTimeMillis();
    System.out.println(end-start);
  }


}
