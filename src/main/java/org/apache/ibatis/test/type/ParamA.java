package org.apache.ibatis.test.type;

public class ParamA implements Param<String>{
  @Override
  public void sya(String a) {
    System.out.println(a);
  }
  class ParamB{
    private String a;
  }
}
