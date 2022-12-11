package org.apache.ibatis.test.cache;

import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.impl.PerpetualCache;

public class LruCacheEg {


  public static void main(String[] args) {
    LruCache cache=new LruCache(new PerpetualCache("11"));
    for (int i = 0; i < 1026; i++) {
      cache.putObject(i,i);
    }
    System.out.println(cache.getSize());
  }
}
