package org.apache.ibatis.test.reflection;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ReT {
  private BigDecimal a;
  private String name;
  private Long phid;
  private Integer status;
}
