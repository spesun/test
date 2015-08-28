/*     */ package com.hive.sca;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class ResourceLoader
/*     */ {
/*     */   public static URL getResource(String rpath)
/*     */   {
/*  41 */     URL resourceAbsoluteURL = null;
/*  42 */     String relativePath = rpath;
/*     */     try {
/*  44 */       relativePath = 
/*  45 */         getStringForNum(ResourceLoader.class.getName()
/*  45 */         .split("\\.").length - 1, "../") + 
/*  46 */         relativePath;
/*  47 */       if (relativePath.indexOf("../") < 0) {
/*  48 */         return ResourceLoader.class.getResource(relativePath);
/*     */       }
/*  50 */       URL cpUrl = ResourceLoader.class.getResource("");
/*  51 */       String classPath = "";
/*  52 */       if (cpUrl == null) {
/*  53 */         classPath = ResourceLoader.class.getClassLoader()
/*  54 */           .getResource("").toString();
/*     */ 
/*  56 */         String resourceAbsolutePath = classPath + rpath;
/*  57 */         return new URL(resourceAbsolutePath);
/*     */       }
/*  59 */       classPath = cpUrl.toString();
/*     */ 
/*  61 */       if (relativePath.substring(0, 1).equals("/")) {
/*  62 */         relativePath = relativePath.substring(1);
/*     */       }
/*  64 */       String wildcardString = relativePath.substring(0, relativePath
/*  65 */         .lastIndexOf("../") + 3);
/*  66 */       relativePath = relativePath.substring(relativePath
/*  67 */         .lastIndexOf("../") + 3);
/*  68 */       int containSum = containSum(wildcardString, "../");
/*  69 */       classPath = cutLastString(classPath, "/", containSum);
/*  70 */       String resourceAbsolutePath = classPath + relativePath;
/*  71 */       resourceAbsoluteURL = new URL(resourceAbsolutePath);
/*     */     } catch (Exception e) {
/*  73 */       e.printStackTrace();
/*     */     }
/*  75 */     return resourceAbsoluteURL;
/*     */   }
/*     */ 
/*     */   public static File getResourceFile(String relativePath) {
/*     */     try {
/*  80 */       return new File(getResource(relativePath).getFile()); } catch (Exception e) {
/*     */     }
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   public static InputStream getStream(String relativePath)
/*     */   {
/*     */     try
/*     */     {
/*  95 */       return getStream(getResource(relativePath));
/*     */     } catch (Exception e) {
/*  97 */       e.printStackTrace();
/*  98 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Properties getProperties(String resource)
/*     */   {
/* 111 */     Properties properties = new Properties();
/* 112 */     InputStream in = null;
/*     */     try {
/* 114 */       in = getStream(resource);
/* 115 */       properties.load(in);
/* 116 */       Properties localProperties1 = properties;
/*     */       return localProperties1;
/*     */     } catch (Exception e) {
/* 118 */       e.printStackTrace();
/*     */       return null;
/*     */     } finally {
/*     */       try {
/* 122 */         in.close();
/*     */       } catch (Exception e) {
/* 124 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 126 */     //throw localObject;
/*     */   }
/*     */ 
/*     */   private static int containSum(String source, String dest)
/*     */   {
/* 138 */     int containSum = 0;
/* 139 */     int destLength = dest.length();
/* 140 */     while (source.indexOf(dest) >= 0) {
/* 141 */       containSum++;
/* 142 */       source = source.substring(destLength);
/*     */     }
/* 144 */     return containSum;
/*     */   }
/*     */ 
/*     */   private static InputStream getStream(URL url)
/*     */   {
/*     */     try
/*     */     {
/* 157 */       if (url != null)
/* 158 */         return url.openStream();
/*     */     } catch (IOException e) {
/* 160 */       e.printStackTrace();
/*     */     }
/* 162 */     return null;
/*     */   }
/*     */ 
/*     */   private static String cutLastString(String source, String dest, int num)
/*     */   {
/* 174 */     for (int i = 0; i < num; i++)
/* 175 */       source = source.substring(0, source.lastIndexOf(dest, source
/* 176 */         .length() - 2) + 1);
/* 177 */     return source;
/*     */   }
/*     */ 
/*     */   private static String getStringForNum(int num, String str)
/*     */   {
/* 189 */     String ret = "";
/* 190 */     for (; num > 0; num--)
/* 191 */       ret = ret + str;
/* 192 */     return ret;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 196 */     System.out.println(getResource("scaclass.properties"));
/*     */   }
/*     */ }

/* Location:           C:\Users\sun\Desktop\oc-catalogdecoder-V04.jar
 * Qualified Name:     com.hive.sca.ResourceLoader
 * JD-Core Version:    0.6.0
 */