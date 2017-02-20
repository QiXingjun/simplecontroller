<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <!--<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>-->
    <xsl:template match="/">
        <html>
            <title>
                <xsl:value-of select="view/header/title"/>
            </title>
            <body>
                <xsl:variable name="formName" select="view/body/form/name"></xsl:variable>
                <xsl:variable name="formAction" select="view/body/form/action"></xsl:variable>
                <xsl:variable name="formMethod" select="view/body/form/method"></xsl:variable>
                <xsl:variable name="textViewName" select="view/body/form/textView/name"></xsl:variable>
                <xsl:variable name="buttonName" select="view/body/form/buttonView/name"></xsl:variable>
                <xsl:variable name="buttonLabel" select="view/body/form/buttonView/label"></xsl:variable>
                <form name = "{$formName}" action = "{$formAction}" method = "{$formMethod}">
                    <xsl:for-each select="view/body/form/textView">
                        <td><label for="{$textViewName}"><xsl:value-of select="label"/><xsl:value-of select="value"/></label></td><br/>
                    </xsl:for-each>
                    <input type="button" name="{$buttonName}" value="{$buttonLabel}"></input>
                </form>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>