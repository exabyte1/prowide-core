
package com.prowidesoftware.swift.model.mx.dic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.prowidesoftware.CopyableTo;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * Source code generated by prowidesoftware.com
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessApplicationHeaderV01", propOrder = {
    "charSet",
    "fr",
    "to",
    "bizMsgIdr",
    "msgDefIdr",
    "bizSvc",
    "creDt",
    "cpyDplct",
    "pssblDplct",
    "prty",
    "sgntr",
    "rltd"
})
public class BusinessApplicationHeaderV01 implements CopyableTo<BusinessApplicationHeaderV01>
{

    @XmlElement(name = "CharSet")
    protected String charSet;
    @XmlElement(name = "Fr", required = true)
    protected Party9Choice fr;
    @XmlElement(name = "To", required = true)
    protected Party9Choice to;
    @XmlElement(name = "BizMsgIdr", required = true)
    protected String bizMsgIdr;
    @XmlElement(name = "MsgDefIdr", required = true)
    protected String msgDefIdr;
    @XmlElement(name = "BizSvc")
    protected String bizSvc;
    @XmlElement(name = "CreDt", required = true)
    protected XMLGregorianCalendar creDt;
    @XmlElement(name = "CpyDplct")
    protected CopyDuplicate1Code cpyDplct;
    @XmlElement(name = "PssblDplct")
    protected Boolean pssblDplct;
    @XmlElement(name = "Prty")
    protected String prty;
    @XmlElement(name = "Sgntr")
    protected SignatureEnvelope sgntr;
    @XmlElement(name = "Rltd")
    protected BusinessApplicationHeader1 rltd;

    /**
     * Gets the value of the charSet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharSet() {
        return charSet;
    }

    /**
     * Sets the value of the charSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public BusinessApplicationHeaderV01 setCharSet(String value) {
        this.charSet = value;
        return this;
    }

    /**
     * Gets the value of the fr property.
     * 
     * @return
     *     possible object is
     *     {@link Party9Choice }
     *     
     */
    public Party9Choice getFr() {
        return fr;
    }

    /**
     * Sets the value of the fr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Party9Choice }
     *     
     */
    public BusinessApplicationHeaderV01 setFr(Party9Choice value) {
        this.fr = value;
        return this;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link Party9Choice }
     *     
     */
    public Party9Choice getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link Party9Choice }
     *     
     */
    public BusinessApplicationHeaderV01 setTo(Party9Choice value) {
        this.to = value;
        return this;
    }

    /**
     * Gets the value of the bizMsgIdr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBizMsgIdr() {
        return bizMsgIdr;
    }

    /**
     * Sets the value of the bizMsgIdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public BusinessApplicationHeaderV01 setBizMsgIdr(String value) {
        this.bizMsgIdr = value;
        return this;
    }

    /**
     * Gets the value of the msgDefIdr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgDefIdr() {
        return msgDefIdr;
    }

    /**
     * Sets the value of the msgDefIdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public BusinessApplicationHeaderV01 setMsgDefIdr(String value) {
        this.msgDefIdr = value;
        return this;
    }

    /**
     * Gets the value of the bizSvc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBizSvc() {
        return bizSvc;
    }

    /**
     * Sets the value of the bizSvc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public BusinessApplicationHeaderV01 setBizSvc(String value) {
        this.bizSvc = value;
        return this;
    }

    /**
     * Gets the value of the creDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreDt() {
        return creDt;
    }

    /**
     * Sets the value of the creDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public BusinessApplicationHeaderV01 setCreDt(XMLGregorianCalendar value) {
        this.creDt = value;
        return this;
    }

    /**
     * Gets the value of the cpyDplct property.
     * 
     * @return
     *     possible object is
     *     {@link CopyDuplicate1Code }
     *     
     */
    public CopyDuplicate1Code getCpyDplct() {
        return cpyDplct;
    }

    /**
     * Sets the value of the cpyDplct property.
     * 
     * @param value
     *     allowed object is
     *     {@link CopyDuplicate1Code }
     *     
     */
    public BusinessApplicationHeaderV01 setCpyDplct(CopyDuplicate1Code value) {
        this.cpyDplct = value;
        return this;
    }

    /**
     * Gets the value of the pssblDplct property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPssblDplct() {
        return pssblDplct;
    }

    /**
     * Sets the value of the pssblDplct property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public BusinessApplicationHeaderV01 setPssblDplct(Boolean value) {
        this.pssblDplct = value;
        return this;
    }

    /**
     * Gets the value of the prty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrty() {
        return prty;
    }

    /**
     * Sets the value of the prty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public BusinessApplicationHeaderV01 setPrty(String value) {
        this.prty = value;
        return this;
    }

    /**
     * Gets the value of the sgntr property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureEnvelope }
     *     
     */
    public SignatureEnvelope getSgntr() {
        return sgntr;
    }

    /**
     * Sets the value of the sgntr property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureEnvelope }
     *     
     */
    public BusinessApplicationHeaderV01 setSgntr(SignatureEnvelope value) {
        this.sgntr = value;
        return this;
    }

    /**
     * Gets the value of the rltd property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessApplicationHeader1 }
     *     
     */
    public BusinessApplicationHeader1 getRltd() {
        return rltd;
    }

    /**
     * Sets the value of the rltd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessApplicationHeader1 }
     *     
     */
    public BusinessApplicationHeaderV01 setRltd(BusinessApplicationHeader1 value) {
        this.rltd = value;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public final void copyTo(final BusinessApplicationHeaderV01 target) {
        target.charSet = charSet;
        Party9Choice frTarget = new Party9Choice();
        fr.copyTo(frTarget);
        target.fr = frTarget;
        Party9Choice toTarget = new Party9Choice();
        to.copyTo(toTarget);
        target.to = toTarget;
        target.bizMsgIdr = bizMsgIdr;
        target.msgDefIdr = msgDefIdr;
        target.bizSvc = bizSvc;
        // debug: XMLGregorianCalendar does not implement copyTo
        target.creDt = creDt;
        // debug: CopyDuplicate1Code does not implement copyTo
        target.cpyDplct = cpyDplct;
        // debug: Boolean does not implement copyTo
        target.pssblDplct = pssblDplct;
        target.prty = prty;
        SignatureEnvelope sgntrTarget = new SignatureEnvelope();
        sgntr.copyTo(sgntrTarget);
        target.sgntr = sgntrTarget;
        BusinessApplicationHeader1 rltdTarget = new BusinessApplicationHeader1();
        rltd.copyTo(rltdTarget);
        target.rltd = rltdTarget;
    }

}