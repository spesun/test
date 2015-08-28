<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">  
  <xsl:output method="xml" encoding="GB2312"/>  
  <xsl:template match="Order">  
    <PUBLIC>  
      <ORDER_NUM>  
		<xsl:value-of select="WorkOrder/OrderNum"/>  
      </ORDER_NUM>  
      <ORDER_CODE>  
		<xsl:value-of select="WorkOrder/OrderId"/>  
      </ORDER_CODE>  
      <ORDER_ID>  
		<xsl:value-of select="WorkOrder/OrderNumber"/>  
      </ORDER_ID>  
    </PUBLIC>  
    </xsl:template>
	
	<xsl:template match="/">  
        <SERVICE_ORDER_PACKAGE>  
            <xsl:apply-templates select="Order"/>  
        </SERVICE_ORDER_PACKAGE>  
    </xsl:template>  
</xsl:stylesheet>  