/*     */ package com.hive;
/*     */ 
/*     */ import com.hive.sca.NodeClassifyInfo;
/*     */ import com.hive.sca.PropertiesUtil;
/*     */ import com.hive.sca.Result;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.hadoop.hive.ql.exec.Description;
/*     */ import org.apache.hadoop.hive.ql.exec.UDF;
/*     */ 
/*     */ @Description(name="catalogdecoder", value="_FUNC_(str [,code]) - decoder a scaURL code as encoding scheme ", extended="")
/*     */ public class CatalogDecoder extends UDF
/*     */ {
/*     */   public static void main(String[] a)
/*     */   {
/*  25 */     CatalogDecoder o = new CatalogDecoder();
/*  26 */     o.evaluate("1", Integer.valueOf(3));
/*     */   }
/*     */ 
/*     */   public String evaluate(String str, Integer code) {
/*  30 */     if (str == null) {
/*  31 */       return null;
/*     */     }
/*  33 */     String s = "";
/*  34 */     String matchResultJsonStr = str;
/*     */ 
/*  36 */     if (!checkIfJson(matchResultJsonStr)) {
/*  37 */       return s;
/*     */     }
/*     */     try
/*     */     {
/*  41 */       Result result = Result.convertToResult(matchResultJsonStr);
/*  42 */       s = splitPrimaryCInfo(result, code);
/*     */     }
/*     */     catch (Exception e) {
/*  45 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  48 */     return s;
/*     */   }
/*     */ 
/*     */   private String splitPrimaryCInfo(Result rs, Integer code)
/*     */   {
/*  67 */     if (code == null) {
/*  68 */       code = Integer.valueOf(2);
/*     */     }
/*  70 */     String pCatalogTypeId = "";
/*  71 */     String pCatalogId = "";
/*  72 */     String pClassifyId = "";
/*     */ 
/*  74 */     if (rs == null) {
/*  75 */       return null;
/*     */     }
/*     */ 
/*  79 */     pCatalogTypeId = rs.getCatalogTypeId();
/*     */ 
/*  85 */     Map catalogAndClassifyMap = rs.getClassifyResult();
/*  86 */     if (catalogAndClassifyMap != null) {
/*  87 */       int catalogIdNum = catalogAndClassifyMap.size();
/*     */ 
/*  90 */       if (catalogIdNum > 0) {
/*  91 */         List pCatalogIdList = new ArrayList();
/*     */ 
/*  93 */         Set catalogIdSet = catalogAndClassifyMap.keySet();
/*  94 */         for (String catalogId : catalogIdSet)
/*     */         {
/*  96 */           String sp = PropertiesUtil.getPropertie("scaclass").getProperty(catalogId);
/*  97 */           if ("1".equalsIgnoreCase(sp)) {
/*  98 */             pCatalogIdList.add(catalogId);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 103 */         if (pCatalogIdList.size() == 0)
/*     */         {
/* 106 */           pCatalogId = "";
/*     */ 
/* 108 */           pCatalogTypeId = "";
/*     */         } else {
/* 110 */           pCatalogId = (String)pCatalogIdList.get(0);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 116 */     if (code.intValue() == 0) {
/* 117 */       return pCatalogTypeId;
/*     */     }
/*     */ 
/* 120 */     if (code.intValue() == 1) {
/* 121 */       return pCatalogId;
/*     */     }
/*     */ 
/* 126 */     if (StringUtils.isNotBlank(pCatalogId)) {
/* 127 */       List pCatalogChildren = (List)catalogAndClassifyMap.get(pCatalogId);
/* 128 */       pClassifyId = getMaxProbabilityClassifyId(pCatalogChildren, pCatalogId);
/*     */     }
/*     */ 
/* 133 */     return pClassifyId;
/*     */   }
/*     */ 
/*     */   private String getMaxProbabilityClassifyId(List<NodeClassifyInfo> classifyInfoList, String catalogId)
/*     */   {
/* 152 */     String lable = "";
/* 153 */     double probability = 0.0D;
/* 154 */     for (NodeClassifyInfo nodeClassifyInfo : classifyInfoList) {
/* 155 */       if (nodeClassifyInfo.getProperties() > probability) {
/* 156 */         lable = nodeClassifyInfo.getLable();
/* 157 */         probability = nodeClassifyInfo.getProperties(); } else {
/* 158 */         nodeClassifyInfo.getProperties();
/*     */       }
/*     */     }
/* 161 */     return lable;
/*     */   }
/*     */ 
/*     */   private boolean checkIfJson(String json)
/*     */   {
/* 180 */     if (StringUtils.isNotBlank(json)) {
/* 181 */       char first = json.charAt(0);
/* 182 */       char last = json.charAt(json.length() - 1);
/* 183 */       if (((first == '[') && (last == ']')) || ((first == '{') && (last == '}'))) {
/* 184 */         return true;
/*     */       }
/*     */     }
/* 187 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\sun\Desktop\oc-catalogdecoder-V04.jar
 * Qualified Name:     com.hive.CatalogDecoder
 * JD-Core Version:    0.6.0
 */