/*    */ package com.hive.sca;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class FileTest
/*    */ {
/*    */   public static void main(String[] a)
/*    */     throws IOException
/*    */   {
/* 14 */     File file = new File("D:/Test2.txt");
/*    */     try {
/* 16 */       BufferedReader reader = new BufferedReader(new FileReader(file));
/* 17 */       String line = reader.readLine();
/* 18 */       System.out.println(line);
/*    */     } catch (IOException e) {
/* 20 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 23 */     readaaa();
/*    */   }
/*    */ 
/*    */   public static String readaaa()
/*    */   {
/* 28 */     File file = new File("D:/Test4.txt");
/*    */     try {
/* 30 */       BufferedReader reader = new BufferedReader(new FileReader(file));
/* 31 */       String line = reader.readLine();
/* 32 */       System.out.println(line);
/* 33 */       return line;
/*    */     } catch (IOException e) {
/* 35 */       e.printStackTrace();
/*    */     }
/* 37 */     return "";
/*    */   }
/*    */ }

/* Location:           C:\Users\sun\Desktop\oc-catalogdecoder-V04.jar
 * Qualified Name:     com.hive.sca.FileTest
 * JD-Core Version:    0.6.0
 */