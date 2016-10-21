/*     */ package com.hive.sca;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ //import net.sf.json.JSONObject;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class Result
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7801142606643431985L;
/*     */   private String l1domain;
/*     */   private String completeDomain;
/*     */   private String siteId;
/*     */   private String channelId;
/*     */   private String isNeedKeys;
/*     */   private String isNeedContentClassify;
/*     */   private String siteName;
/*     */   private String url;
/*     */   private String key;
/*     */   private transient String maxContent;
/*     */   private int matchType;
/*     */   private String catalogTypeId;
/*     */   private String catalogTypeCode;
/*     */   private String resultStatus;
/*     */   private Map<String, List<NodeClassifyInfo>> classifyResult;
/*     */   private Map<String, String[]> eIF;
/*     */   public static final int MATCH_BY_RULE = 1;
/*     */   public static final int MATCH_BY_INSTANCE = 2;
/*     */ 
/*     */   public Result()
/*     */   {
/* 130 */     this.classifyResult = new HashMap();
/* 131 */     this.eIF = new HashMap();
/* 132 */     this.eIF.put("K1", new String[0]);
/* 133 */     this.eIF.put("K2", new String[0]);
/* 134 */     this.eIF.put("K3", new String[0]);
/*     */   }
/*     */ 
/*     */   public String getL1domain()
/*     */   {
/* 141 */     return this.l1domain;
/*     */   }
/*     */ 
/*     */   public void setL1domain(String l1domain)
/*     */   {
/* 148 */     this.l1domain = l1domain;
/*     */   }
/*     */ 
/*     */   public String getCompleteDomain()
/*     */   {
/* 155 */     return this.completeDomain;
/*     */   }
/*     */ 
/*     */   public void setCompleteDomain(String completeDomain)
/*     */   {
/* 162 */     this.completeDomain = completeDomain;
/*     */   }
/*     */ 
/*     */   public String getUrl() {
/* 166 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/* 170 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public String getSiteName() {
/* 174 */     return this.siteName;
/*     */   }
/*     */ 
/*     */   public void setSiteName(String siteName) {
/* 178 */     this.siteName = siteName;
/*     */   }
/*     */ 
/*     */   public String getMaxContent() {
/* 182 */     return this.maxContent;
/*     */   }
/*     */ 
/*     */   public void setMaxContent(String maxContent) {
/* 186 */     this.maxContent = maxContent;
/*     */   }
/*     */ 
/*     */   public String getSiteId()
/*     */   {
/* 193 */     return this.siteId;
/*     */   }
/*     */ 
/*     */   public void setSiteId(String siteId)
/*     */   {
/* 200 */     this.siteId = siteId;
/*     */   }
/*     */ 
/*     */   public String getChannelId()
/*     */   {
/* 207 */     return this.channelId;
/*     */   }
/*     */ 
/*     */   public void setChannelId(String channelId)
/*     */   {
/* 214 */     this.channelId = channelId;
/*     */   }
/*     */ 
/*     */   public void addClassifyResult(String classifyCode, NodeClassifyInfo nodeClassifyInfo)
/*     */   {
/* 224 */     List classifyInfos = (List)this.classifyResult.get(classifyCode);
/* 225 */     if (classifyInfos == null) {
/* 226 */       classifyInfos = new ArrayList();
/* 227 */       classifyInfos.add(nodeClassifyInfo);
/* 228 */       this.classifyResult.put(classifyCode, classifyInfos);
/*     */     }
/*     */     else {
/* 231 */       classifyInfos.add(nodeClassifyInfo);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean removeClassifyResultCode(String classifyCode)
/*     */   {
/* 239 */     if (this.classifyResult.containsKey(classifyCode)) {
/* 240 */       this.classifyResult.remove(classifyCode);
/* 241 */       return true;
/*     */     }
/*     */ 
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */   public void addResults(String clssifyCode, List<NodeClassifyInfo> classifyInfos)
/*     */   {
/* 254 */     if (this.classifyResult.get(clssifyCode) != null) {
/* 255 */       List classifyInfo = (List)this.classifyResult.get(clssifyCode);
/* 256 */       classifyInfo.addAll(classifyInfos);
/*     */     } else {
/* 258 */       List classifyInfo = new ArrayList();
/* 259 */       classifyInfo.addAll(classifyInfos);
/* 260 */       this.classifyResult.put(clssifyCode, classifyInfo);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getKey() {
/* 265 */     return this.key;
/*     */   }
/*     */ 
/*     */   public void setKey(String key) {
/* 269 */     this.key = key;
/*     */   }
/*     */ 
/*     */   public Map<String, List<NodeClassifyInfo>> getClassifyResult() {
/* 273 */     return this.classifyResult;
/*     */   }
/*     */ 
/*     */   public void setClassifyResult(Map<String, List<NodeClassifyInfo>> classifyResult) {
/* 277 */     this.classifyResult = classifyResult;
/*     */   }
/*     */ 
/*     */   public List<NodeClassifyInfo> getNodeClassifyInfoList(String key) {
/* 281 */     if (this.classifyResult.containsKey(key)) {
/* 282 */       return (List)this.classifyResult.get(key);
/*     */     }
/* 284 */     return null;
/*     */   }
/*     */ 
/*     */   public int getMatchType() {
/* 288 */     return this.matchType;
/*     */   }
/*     */ 
/*     */   public void setMatchType(int matchType) {
/* 292 */     this.matchType = matchType;
/*     */   }
/*     */ 
/*     */   public String getCatalogTypeId()
/*     */   {
/* 299 */     return this.catalogTypeId;
/*     */   }
/*     */ 
/*     */   public void setCatalogTypeId(String catalogTypeId)
/*     */   {
/* 306 */     this.catalogTypeId = catalogTypeId;
/*     */   }
/*     */ 
/*     */   public String getCatalogTypeCode()
/*     */   {
/* 313 */     return this.catalogTypeCode;
/*     */   }
/*     */ 
/*     */   public void setCatalogTypeCode(String catalogTypeCode)
/*     */   {
/* 320 */     this.catalogTypeCode = catalogTypeCode;
/*     */   }
/*     */ 
/*     */   public String getResultStatus()
/*     */   {
/* 327 */     return this.resultStatus;
/*     */   }
/*     */ 
/*     */   public String getIsNeedKeys()
/*     */   {
/* 334 */     return this.isNeedKeys;
/*     */   }
/*     */ 
/*     */   public void setIsNeedKeys(String isNeedKeys)
/*     */   {
/* 341 */     this.isNeedKeys = isNeedKeys;
/*     */   }
/*     */ 
/*     */   public String getIsNeedContentClassify()
/*     */   {
/* 348 */     return this.isNeedContentClassify;
/*     */   }
/*     */ 
/*     */   public void setIsNeedContentClassify(String isNeedContentClassify)
/*     */   {
/* 355 */     this.isNeedContentClassify = isNeedContentClassify;
/*     */   }
/*     */ 
/*     */   public void setResultStatus(String resultStatus)
/*     */   {
/* 362 */     this.resultStatus = resultStatus;
/*     */   }
/*     */ 
/*     */   public void seteIF(Map<String, String[]> eIF) {
/* 366 */     this.eIF = eIF;
/*     */   }
/*     */ 
/*     */   public Map<String, String[]> geteIF() {
/* 370 */     return this.eIF;
/*     */   }
/*     */ 
/*     */   public void addEIF(String key, String[] value) {
/* 374 */     this.eIF.put(key, value);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 379 */     return convertToJson();
/*     */   }
/*     */ 
/*     */   public String convertToJson()
/*     */   {
/* 389 */     return ResultForJson.toJson(this);
/*     */   }
/*     */ 
/*     */   public String convertToFlareJson()
/*     */   {
/* 396 */     return ResultForJson.toFlareJson(this);
/*     */   }
/*     */ 
/*     */   public Result mergeOtherResult(Result otherRs)
/*     */   {
/* 410 */     Map othereIF = otherRs.geteIF();
/*     */     String[] value;
/* 411 */     if ((othereIF != null) && (!othereIF.isEmpty())) {
/* 412 */       Set<String> othereIFSet = othereIF.keySet();
/* 413 */       for (String othereIFKey : othereIFSet) {
/* 414 */         if ("K1".equals(othereIFKey)) {
/* 415 */           value = (String[])this.eIF.get("K1");
/* 416 */           if ((value != null) && (value.length > 0)) {
/*     */             continue;
/*     */           }
/*     */         }
/* 420 */         this.eIF.put(othereIFKey, (String[])othereIF.get(othereIFKey));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 425 */     Map otherCR = otherRs.getClassifyResult();
/* 426 */     if ((otherCR != null) && (!otherCR.isEmpty())) {
/* 427 */       Set<String> catalogIdSet = otherCR.keySet();
/*     */ 
/* 429 */       for (String catalogId : catalogIdSet)
/*     */       {
/* 431 */         if (this.classifyResult.containsKey(catalogId)) {
/* 432 */           List<NodeClassifyInfo> thisNodeClassifyInfoList = (List)this.classifyResult.get(catalogId);
/* 433 */           List<NodeClassifyInfo> otherNodeClassifyInfoList = (List)otherCR.get(catalogId);
/*     */ 
/* 435 */           for (NodeClassifyInfo otherNodeClassifyInfo : otherNodeClassifyInfoList) {
/* 436 */             boolean has = false;
/* 437 */             String otherLable = otherNodeClassifyInfo.getLable();
/* 438 */             for (NodeClassifyInfo thisNodeClassifyInfo : thisNodeClassifyInfoList) {
/* 439 */               String thisLable = thisNodeClassifyInfo.getLable();
/* 440 */               if ((StringUtils.isNotBlank(thisLable)) && (thisLable.equals(otherLable))) {
/* 441 */                 has = true;
/* 442 */                 break;
/*     */               }
/*     */             }
/*     */ 
/* 446 */             if (!has) {
/* 447 */               thisNodeClassifyInfoList.add(otherNodeClassifyInfo);
/*     */             }
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 453 */           this.classifyResult.put(catalogId, (List)otherCR.get(catalogId));
/*     */         }
/*     */       }
/*     */     }
/* 457 */     return mergeSameCatalogIdForOneClassifyIdWithMaxProbability(this);
/*     */   }
/*     */ 
/*     */   private Result mergeSameCatalogIdForOneClassifyIdWithMaxProbability(Result rs)
/*     */   {
/* 466 */     if (rs == null) {
/* 467 */       return rs;
/*     */     }
/*     */ 
/* 470 */     for (Map.Entry entry : rs.getClassifyResult().entrySet()) {
/* 471 */       String catalogId = (String)entry.getKey();
/* 472 */       List<NodeClassifyInfo> classifyInfoList = (List)entry.getValue();
/* 473 */       String lable = "";
/* 474 */       double probability = 0.0D;
/*     */ 
/* 476 */       for (NodeClassifyInfo nodeClassifyInfo : classifyInfoList) {
/* 477 */         if (nodeClassifyInfo.getProperties() >= probability) {
/* 478 */           lable = nodeClassifyInfo.getLable();
/* 479 */           probability = nodeClassifyInfo.getProperties();
/*     */         }
/*     */       }
/*     */ 
/* 483 */       rs.getClassifyResult().put(catalogId, Arrays.asList(new NodeClassifyInfo[] { new NodeClassifyInfo(lable) }));
/*     */     }
/* 485 */     return rs;
/*     */   }
/*     */ 
/*     */   public static Result convertToResult(String json)
/*     */   {
/* 495 */     if (StringUtils.isNotBlank(json)) {
/* 496 */       return ResultForJson.fromJson(json);
/*     */     }
/* 498 */     return null;
/*     */   }
/*     */ 
/*     */   public static void main1(String[] args)
/*     */   {
/* 648 */     Result thisResult = new Result();
/* 649 */     thisResult.setChannelId("ChannelId");
/* 650 */     thisResult.setSiteId("setSiteId");
/* 651 */     thisResult.setChannelId("setChannelId");
/* 652 */     thisResult.setSiteName("baidu");
/* 653 */     thisResult.geteIF().put("K1", new String[] { "中国" });
/*     */ 
/* 655 */     thisResult.addClassifyResult("13031", new NodeClassifyInfo("1303100000001", 1.0D));
/* 656 */     thisResult.setIsNeedContentClassify("Y");
/* 657 */     thisResult.setIsNeedKeys("N");
/*     */ 
/* 659 */     Result otherResult = new Result();
/* 660 */     otherResult.addClassifyResult("12021", new NodeClassifyInfo("1202100000001", 0.3D));
/* 661 */     otherResult.addClassifyResult("12021", new NodeClassifyInfo("1202100000002", 0.2D));
/* 662 */     otherResult.addClassifyResult("12021", new NodeClassifyInfo("1202100000003", 0.5D));
/*     */ 
/* 664 */     otherResult.geteIF().put("K1", new String[] { "中国1", "钓鱼岛" });
/*     */ 
/* 666 */     thisResult.mergeOtherResult(otherResult);
/*     */ 
/* 668 */     System.out.println(thisResult.convertToJson());
/*     */   }
/*     */ 
/*     */   public static class Tester
/*     */   {
/*     */     public static void main(String[] args)
/*     */     {
/*     */     }
/*     */ 
/*     */     protected void test1()
/*     */     {
/* 564 */       Result result = new Result();
/* 565 */       result.setKey("test key");
/* 566 */       result.setMaxContent("23423423");
/* 567 */       result.setMatchType(1);
/* 568 */       result.setUrl("http://www.sina.com.cn");
/* 569 */       Map classifyResult = new HashMap();
/*     */ 
/* 571 */       String key = "0301";
/* 572 */       List classifyInfos = new ArrayList();
/* 573 */       NodeClassifyInfo nci = new NodeClassifyInfo("news");
/* 574 */       classifyInfos.add(nci);
/*     */ 
/* 576 */       nci = new NodeClassifyInfo("sport");
/* 577 */       classifyInfos.add(nci);
/*     */ 
/* 579 */       classifyResult.put(key, classifyInfos);
/*     */ 
/* 581 */       String key2 = "0302";
/* 582 */       List classifyInfos2 = new ArrayList();
/* 583 */       nci = new NodeClassifyInfo("news");
/* 584 */       classifyInfos2.add(nci);
/*     */ 
/* 586 */       nci = new NodeClassifyInfo("sport");
/* 587 */       classifyInfos2.add(nci);
/*     */ 
/* 589 */       classifyResult.put(key2, classifyInfos2);
/* 590 */       result.setClassifyResult(classifyResult);
/*     */ 
/* 592 */       //JSONObject jsonObj = JSONObject.fromObject(result);
/* 593 */       //System.out.println(jsonObj);
/*     */ 
/* 595 */       Result rs = null;//Result.convertToResult(jsonObj.toString());
/*     */ 
/* 597 */       if ((rs != null) && (rs.getClassifyResult() != null))
/*     */       {
/* 608 */         for (String catalogId : rs.getClassifyResult().keySet()) {
/* 609 */           List<NodeClassifyInfo> classifyInfoList = (List)result.getClassifyResult().get(catalogId);
/* 610 */           for (NodeClassifyInfo nodeClassifyInfo : classifyInfoList)
/* 611 */             System.out.println(catalogId + "\t" + nodeClassifyInfo.getLable());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\sun\Desktop\oc-catalogdecoder-V04.jar
 * Qualified Name:     com.hive.sca.Result
 * JD-Core Version:    0.6.0
 */