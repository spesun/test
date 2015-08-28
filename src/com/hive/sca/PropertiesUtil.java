/*    */ package com.hive.sca;
/*    */ 
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class PropertiesUtil
/*    */ {
/*    */   private static final String path = "";
/*    */   private static final String postfix = ".properties";
/*    */ 
/*    */   public static Properties getPropertie(String key)
/*    */   {
/* 40 */     return ResourceLoader.getProperties(toResource(key));
/*    */   }
/*    */ 
/*    */   public static String toResource(String resource)
/*    */   {
/* 53 */     return resource + ".properties";
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ 
/*    */   public static String toProperties(String key, String value)
/*    */   {
/* 70 */     return key + "=" + value + "\n";
/*    */   }
/*    */ }

/* Location:           C:\Users\sun\Desktop\oc-catalogdecoder-V04.jar
 * Qualified Name:     com.hive.sca.PropertiesUtil
 * JD-Core Version:    0.6.0
 */