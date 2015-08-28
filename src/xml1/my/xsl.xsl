<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="message">
  <h1><xsl:apply-templates select="title"/></h1>
</xsl:template>


</xsl:stylesheet>