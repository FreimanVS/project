<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/ValCurs">
        <valcurs>
        <xsl:for-each select="Valute/CharCode[text()='CNY' or text()='USD' or text()='EUR']">
            <valute>
                <charcode><xsl:value-of select="../CharCode"/></charcode>
                <value><xsl:value-of select="../Value"/></value>
            </valute>
        </xsl:for-each>
        </valcurs>
    </xsl:template>
</xsl:stylesheet>

<!--<?xml version="1.0"?>-->
<!--<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">-->
    <!--<xsl:template match="/ValCurs">-->
        <!--<xsl:for-each select="Valute/CharCode[text()='CNY' or text()='USD' or text()='EUR']">-->
            <!--<ul style="list-style-type: none;">-->
                <!--<li><xsl:value-of select="../CharCode"/> <span style="padding-left: 0.5em"><xsl:value-of select="../Value"/></span></li>-->
            <!--</ul>-->
        <!--</xsl:for-each>-->
    <!--</xsl:template>-->
<!--</xsl:stylesheet>-->
