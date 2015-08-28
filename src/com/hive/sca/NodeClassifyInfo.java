/*    */ package com.hive.sca;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.io.Serializable;
/*    */ import java.text.NumberFormat;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ public class NodeClassifyInfo
/*    */   implements Comparable<NodeClassifyInfo>, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7854606457376716257L;
/*    */   private String lable;
/*    */   private double properties;
/*    */ 
/*    */   public NodeClassifyInfo()
/*    */   {
/*    */   }
/*    */ 
/*    */   public NodeClassifyInfo(String lable, double properties)
/*    */   {
/* 33 */     this.lable = lable;
/* 34 */     this.properties = properties;
/*    */   }
/*    */ 
/*    */   public NodeClassifyInfo(String lable) {
/* 38 */     this.lable = lable;
/* 39 */     this.properties = 1.0D;
/*    */   }
/*    */ 
/*    */   public String getLable() {
/* 43 */     return this.lable;
/*    */   }
/*    */ 
/*    */   public void setLable(String lable) {
/* 47 */     this.lable = lable;
/*    */   }
/*    */ 
/*    */   public double getProperties() {
/* 51 */     return this.properties;
/*    */   }
/*    */ 
/*    */   public void setProperties(double properties) {
/* 55 */     this.properties = properties;
/*    */   }
/*    */ 
/*    */   public int compareTo(NodeClassifyInfo o)
/*    */   {
/* 60 */     if (this.properties < o.properties)
/* 61 */       return 1;
/* 62 */     return -1;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 67 */     NumberFormat numFormat = NumberFormat.getPercentInstance();
/* 68 */     return this.lable + "," + numFormat.format(this.properties);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 72 */     List list = new ArrayList();
/*    */ 
/* 74 */     NodeClassifyInfo infor = new NodeClassifyInfo("w", 1.9D);
/* 75 */     list.add(infor);
/* 76 */     infor = new NodeClassifyInfo("r", 2.0D);
/* 77 */     list.add(infor);
/* 78 */     infor = new NodeClassifyInfo("t", 1.0D);
/* 79 */     list.add(infor);
/* 80 */     infor = new NodeClassifyInfo("t", 0.3D);
/* 81 */     list.add(infor);
/*    */ 
/* 83 */     System.out.println(list);
/* 84 */     Collections.sort(list);
/* 85 */     System.out.println(list);
/*    */   }
/*    */ }

/* Location:           C:\Users\sun\Desktop\oc-catalogdecoder-V04.jar
 * Qualified Name:     com.hive.sca.NodeClassifyInfo
 * JD-Core Version:    0.6.0
 */