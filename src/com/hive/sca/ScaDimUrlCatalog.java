/*    */ package com.hive.sca;
/*    */ 
/*    */ public class ScaDimUrlCatalog
/*    */   implements BaseModule
/*    */ {
/*    */   private String catalogId;
/*    */   private String catalogName;
/*    */   private String catalogCode;
/*    */   private int catalogTypeId;
/*    */   private int active;
/*    */   private Integer primary;
/*    */ 
/*    */   public String toCacheKey()
/*    */   {
/* 45 */     return "ScaDimUrlCatalog[catalogId=" + this.catalogId + ",catalogName=" + this.catalogName + ",catalogCode=" + this.catalogCode + 
/* 46 */       ",catalogTypeId=" + this.catalogTypeId + ",active=" + this.active + ",primary=" + this.primary + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\sun\Desktop\oc-catalogdecoder-V04.jar
 * Qualified Name:     com.hive.sca.ScaDimUrlCatalog
 * JD-Core Version:    0.6.0
 */