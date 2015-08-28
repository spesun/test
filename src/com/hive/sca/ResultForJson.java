/*     */ package com.hive.sca;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.codehaus.jackson.JsonGenerationException;
/*     */ import org.codehaus.jackson.map.JsonMappingException;
/*     */ import org.codehaus.jackson.map.ObjectMapper;
/*     */ 
/*     */ public class ResultForJson
/*     */ {
/*     */   private String ct;
/*     */   private Map<String, List<NodeClassifyInfoForJson>> cr;
/*     */   private Map<String, String[]> eIF;
/*     */   private String si;
/*     */   private String ci;
/*     */   private String rs;
/*     */   private String nk;
/*     */   private String nc;
/*     */   private String td;
/*     */   private String cd;
/*     */   private String ctc;
/*     */   private String key;
/*  45 */   private static ObjectMapper mapper = new ObjectMapper();
/*     */ 
/*     */   public static Result fromJson(String json)
/*     */   {
/*  53 */     Result re = null;
/*     */     try {
/*  55 */       ResultForJson rfj = (ResultForJson)mapper.readValue(json, ResultForJson.class);
/*  56 */       re = new Result();
/*  57 */       re.setCatalogTypeId(rfj.getCt());
/*  58 */       re.setClassifyResult(toResultCr(rfj.getCr()));
/*  59 */       re.seteIF(rfj.geteIF());
/*  60 */       re.setSiteId(rfj.getSi());
/*  61 */       re.setChannelId(rfj.getCi());
/*  62 */       re.setResultStatus(rfj.getRs());
/*  63 */       re.setIsNeedKeys(rfj.getNk());
/*  64 */       re.setIsNeedContentClassify(rfj.getNc());
/*  65 */       re.setL1domain(rfj.getTd());
/*  66 */       re.setCompleteDomain(rfj.getCd());
/*  67 */       re.setCatalogTypeCode(rfj.getCtc());
/*  68 */       re.setKey(rfj.getKey() == null ? "" : rfj.getKey());
/*     */     } catch (Exception e) {
/*  70 */       e.printStackTrace();
/*  71 */       return null;
/*     */     }
/*  73 */     return re;
/*     */   }
/*     */ 
/*     */   public static String toJson(Result re)
/*     */   {
/*  82 */     StringBuilder sb = new StringBuilder(300);
/*  83 */     sb.append('{');
/*  84 */     sb.append("\"ct\":\"").append(convertNullToEmptyString(re.getCatalogTypeId())).append("\",");
/*  85 */     sb.append("\"cr\":").append(fromResultCr(re.getClassifyResult())).append(',');
/*  86 */     sb.append("\"eIF\":").append(fromEIF(re.geteIF())).append(',');
/*  87 */     sb.append("\"td\":\"").append(convertNullToEmptyString(re.getL1domain())).append("\",");
/*  88 */     sb.append("\"cd\":\"").append(convertNullToEmptyString(re.getCompleteDomain())).append("\",");
/*  89 */     sb.append("\"ctc\":\"").append(convertNullToEmptyString(re.getCatalogTypeCode())).append("\",");
/*  90 */     sb.append("\"si\":\"").append(convertNullToEmptyString(re.getSiteId())).append("\",");
/*  91 */     sb.append("\"ci\":\"").append(convertNullToEmptyString(re.getChannelId())).append("\",");
/*  92 */     sb.append("\"rs\":\"").append(convertNullToEmptyString(re.getResultStatus())).append("\",");
/*  93 */     sb.append("\"nk\":\"").append(convertNullToEmptyString(re.getIsNeedKeys())).append("\",");
/*  94 */     sb.append("\"nc\":\"").append(convertNullToEmptyString(re.getIsNeedContentClassify())).append("\",");
/*  95 */     sb.append("\"key\":\"").append(convertNullToEmptyString(re.getKey())).append("\"");
/*  96 */     sb.append('}');
/*  97 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String toFlareJson(Result re) {
/* 101 */     StringBuilder sb = new StringBuilder(300);
/* 102 */     sb.append('{');
/* 103 */     sb.append("\"ct\":\"").append(convertNullToEmptyString(re.getCatalogTypeId())).append("\",");
/* 104 */     sb.append("\"cr\":").append(fromResultCr(re.getClassifyResult())).append(',');
/* 105 */     sb.append("\"eIF\":").append(fromEIF(re.geteIF())).append(',');
/* 106 */     sb.append("\"td\":\"").append(convertNullToEmptyString(re.getL1domain())).append("\",");
/* 107 */     sb.append("\"cd\":\"").append(convertNullToEmptyString(re.getCompleteDomain())).append("\",");
/* 108 */     sb.append("\"ctc\":\"").append(convertNullToEmptyString(re.getCatalogTypeCode())).append("\",");
/* 109 */     sb.append("\"si\":\"").append(convertNullToEmptyString(re.getSiteId())).append("\",");
/* 110 */     sb.append("\"ci\":\"").append(convertNullToEmptyString(re.getChannelId())).append("\",");
/* 111 */     sb.append("\"rs\":\"").append(convertNullToEmptyString(re.getResultStatus())).append("\",");
/* 112 */     sb.append("\"nk\":\"").append(convertNullToEmptyString(re.getIsNeedKeys())).append("\",");
/* 113 */     sb.append("\"nc\":\"").append(convertNullToEmptyString(re.getIsNeedContentClassify())).append("\"");
/* 114 */     sb.append('}');
/* 115 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private static String convertNullToEmptyString(String str)
/*     */   {
/* 123 */     return str == null ? "" : str;
/*     */   }
/*     */ 
/*     */   private static String fromResultCr(Map<String, List<NodeClassifyInfo>> reCr)
/*     */   {
/* 132 */     StringBuilder sb = new StringBuilder(200);
/* 133 */     sb.append('{');
/* 134 */     if ((reCr != null) && (!reCr.isEmpty())) {
/* 135 */       Set keySet = reCr.keySet();
/* 136 */       Iterator iterator = keySet.iterator();
/* 137 */       boolean loopset = iterator.hasNext();
/* 138 */       while (loopset) {
/* 139 */         String key = (String)iterator.next();
/* 140 */         sb.append("\"").append(key).append("\":[");
/* 141 */         List reList = (List)reCr.get(key);
/* 142 */         if ((reList != null) && (reList.size() > 0)) {
/* 143 */           for (int i = 0; i < reList.size(); i++) {
/* 144 */             sb.append("{\"l\":\"").append(((NodeClassifyInfo)reList.get(i)).getLable()).append("\",\"p\":").append(((NodeClassifyInfo)reList.get(i)).getProperties()).append('}');
/* 145 */             if (i != reList.size() - 1) {
/* 146 */               sb.append(',');
/*     */             }
/*     */           }
/*     */         }
/* 150 */         sb.append(']');
/* 151 */         if ((loopset = iterator.hasNext()))
/* 152 */           sb.append(',');
/*     */       }
/*     */     }
/* 155 */     sb.append('}');
/* 156 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private static String fromEIF(Map<String, String[]> eIF)
/*     */   {
/* 165 */     StringBuilder sb = new StringBuilder(50);
/* 166 */     sb.append('{');
/* 167 */     if ((eIF != null) && (!eIF.isEmpty())) {
/* 168 */       Set keySet = eIF.keySet();
/* 169 */       Iterator iterator = keySet.iterator();
/* 170 */       boolean loop = iterator.hasNext();
/* 171 */       while (loop) {
/* 172 */         String key = (String)iterator.next();
/* 173 */         String[] keywords = (String[])eIF.get(key);
/* 174 */         sb.append("\"").append(key).append("\":[");
/* 175 */         if ((keywords != null) && (keywords.length > 0)) {
/* 176 */           for (int i = 0; i < keywords.length; i++) {
/* 177 */             sb.append("\"").append(keywords[i]).append("\"");
/* 178 */             if (i != keywords.length - 1) {
/* 179 */               sb.append(',');
/*     */             }
/*     */           }
/*     */         }
/* 183 */         sb.append(']');
/* 184 */         if ((loop = iterator.hasNext())) {
/* 185 */           sb.append(',');
/*     */         }
/*     */       }
/*     */     }
/* 189 */     sb.append('}');
/* 190 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String toJson2(Result re)
/*     */   {
/* 199 */     StringWriter sw = new StringWriter();
/*     */     try {
/* 201 */       ResultForJson rfj = new ResultForJson();
/* 202 */       rfj.setCt(re.getCatalogTypeId());
/* 203 */       rfj.setCr(fromResultCr2(re.getClassifyResult()));
/* 204 */       rfj.seteIF(re.geteIF());
/* 205 */       rfj.setSi(re.getSiteId());
/* 206 */       rfj.setCi(re.getChannelId());
/* 207 */       rfj.setRs(re.getResultStatus());
/* 208 */       rfj.setNk(re.getIsNeedKeys());
/* 209 */       rfj.setNc(re.getIsNeedContentClassify());
/* 210 */       rfj.setTd(re.getL1domain());
/* 211 */       rfj.setCd(re.getCompleteDomain());
/* 212 */       rfj.setCtc(re.getCatalogTypeCode());
/* 213 */       mapper.writeValue(sw, rfj);
/*     */     } catch (JsonGenerationException e) {
/* 215 */       e.printStackTrace();
/*     */     } catch (JsonMappingException e) {
/* 217 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/* 219 */       e.printStackTrace();
/*     */     }
/* 221 */     return sw.toString();
/*     */   }
/*     */ 
/*     */   private static Map<String, List<NodeClassifyInfo>> toResultCr(Map<String, List<NodeClassifyInfoForJson>> cr)
/*     */   {
/* 230 */     Map reCr = null;
/* 231 */     if ((cr != null) && (!cr.isEmpty())) {
/* 232 */       reCr = new HashMap();
/* 233 */       for (String key : cr.keySet()) {
/* 234 */         List reList = new ArrayList();
/* 235 */         List crList = (List)cr.get(key);
/* 236 */         if ((crList != null) && (crList.size() > 0)) {
/* 237 */           for (NodeClassifyInfoForJson nc : crList) {
/* 238 */             NodeClassifyInfo nci = new NodeClassifyInfo();
/* 239 */             nci.setLable(nc.getL());
/* 240 */             nci.setProperties(nc.getP());
/* 241 */             reList.add(nci);
/*     */           }
/* 243 */           reCr.put(key, reList);
/*     */         }
/*     */       }
/*     */     }
/* 247 */     return reCr;
/*     */   }
/*     */ 
/*     */   private static Map<String, List<NodeClassifyInfoForJson>> fromResultCr2(Map<String, List<NodeClassifyInfo>> reCr)
/*     */   {
/* 256 */     Map cr = null;
/* 257 */     if ((reCr != null) && (!reCr.isEmpty())) {
/* 258 */       cr = new HashMap();
/* 259 */       for (String key : reCr.keySet()) {
/* 260 */         List reList = (List)reCr.get(key);
/* 261 */         List crList = new ArrayList();
/* 262 */         if ((reList != null) && (reList.size() > 0)) {
/* 263 */           for (NodeClassifyInfo nci : reList) {
/* 264 */             NodeClassifyInfoForJson nc = new NodeClassifyInfoForJson();
/* 265 */             nc.setL(nci.getLable());
/* 266 */             nc.setP(nci.getProperties());
/* 267 */             crList.add(nc);
/*     */           }
/* 269 */           cr.put(key, crList);
/*     */         }
/*     */       }
/*     */     }
/* 273 */     return cr;
/*     */   }
/*     */ 
/*     */   public String getCt() {
/* 277 */     return this.ct;
/*     */   }
/*     */ 
/*     */   public void setCt(String ct) {
/* 281 */     this.ct = ct;
/*     */   }
/*     */ 
/*     */   public Map<String, List<NodeClassifyInfoForJson>> getCr() {
/* 285 */     return this.cr;
/*     */   }
/*     */ 
/*     */   public void setCr(Map<String, List<NodeClassifyInfoForJson>> cr) {
/* 289 */     this.cr = cr;
/*     */   }
/*     */ 
/*     */   public Map<String, String[]> geteIF() {
/* 293 */     return this.eIF;
/*     */   }
/*     */ 
/*     */   public void seteIF(Map<String, String[]> eIF) {
/* 297 */     this.eIF = eIF;
/*     */   }
/*     */ 
/*     */   public String getSi() {
/* 301 */     return this.si;
/*     */   }
/*     */ 
/*     */   public void setSi(String si) {
/* 305 */     this.si = si;
/*     */   }
/*     */ 
/*     */   public String getCi() {
/* 309 */     return this.ci;
/*     */   }
/*     */ 
/*     */   public void setCi(String ci) {
/* 313 */     this.ci = ci;
/*     */   }
/*     */ 
/*     */   public String getRs() {
/* 317 */     return this.rs;
/*     */   }
/*     */ 
/*     */   public void setRs(String rs) {
/* 321 */     this.rs = rs;
/*     */   }
/*     */ 
/*     */   public String getNk() {
/* 325 */     return this.nk;
/*     */   }
/*     */ 
/*     */   public void setNk(String nk) {
/* 329 */     this.nk = nk;
/*     */   }
/*     */ 
/*     */   public String getNc() {
/* 333 */     return this.nc;
/*     */   }
/*     */ 
/*     */   public void setNc(String nc) {
/* 337 */     this.nc = nc;
/*     */   }
/*     */ 
/*     */   public String getTd() {
/* 341 */     return this.td;
/*     */   }
/*     */ 
/*     */   public void setTd(String td) {
/* 345 */     this.td = td;
/*     */   }
/*     */ 
/*     */   public String getCd() {
/* 349 */     return this.cd;
/*     */   }
/*     */ 
/*     */   public void setCd(String cd) {
/* 353 */     this.cd = cd;
/*     */   }
/*     */ 
/*     */   public String getCtc() {
/* 357 */     return this.ctc;
/*     */   }
/*     */ 
/*     */   public void setCtc(String ctc) {
/* 361 */     this.ctc = ctc;
/*     */   }
/*     */ 
/*     */   public String getKey() {
/* 365 */     return this.key;
/*     */   }
/*     */ 
/*     */   public void setKey(String key) {
/* 369 */     this.key = key;
/*     */   }
/*     */ }

/* Location:           C:\Users\sun\Desktop\oc-catalogdecoder-V04.jar
 * Qualified Name:     com.hive.sca.ResultForJson
 * JD-Core Version:    0.6.0
 */