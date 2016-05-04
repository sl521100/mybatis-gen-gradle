package com.sl.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GWhsEntity {
    /*<记录ID>*/
    private BigDecimal recid;

    /*<运单在库位上的件数>*/
    private Integer pcs;

    /*<运单在库位上的重量>*/
    private BigDecimal weight;

    /*<进出港标志（I--IMPORT,E-EXPORT）>*/
    private String ei;

    /*<运单在库位上的体积>*/
    private BigDecimal vol;

    /*<uld信息>*/
    private String uldNo;

    /*<仓库库位信息>*/
    private String whslocation;

    /*<运单记录主体ID>*/
    private BigDecimal mawbid;

    /*<运单前缀>*/
    private String awbpre;

    /*<运单号>*/
    private String awbno;

    /*<创建人>*/
    private String createOpe;

    /*<创建时间>*/
    private Date createTime;

    /*<更新人>*/
    private String updateOpe;

    /*<更新时间>*/
    private Date updateTime;

    /*<公司代码>*/
    private String companyCode;

    /*<备注>*/
    private String remark;

    /*<扩展字段1>*/
    private String ext1;

    /*<扩展字段2>*/
    private String ext2;

    /*<部门ID>*/
    private BigDecimal deptid;

    public BigDecimal getRecid() {
        return recid;
    }

    public void setRecid(BigDecimal recid) {
        this.recid = recid;
    }

    public Integer getPcs() {
        return pcs;
    }

    public void setPcs(Integer pcs) {
        this.pcs = pcs;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getEi() {
        return ei;
    }

    public void setEi(String ei) {
        this.ei = ei == null ? null : ei.trim();
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public String getUldNo() {
        return uldNo;
    }

    public void setUldNo(String uldNo) {
        this.uldNo = uldNo == null ? null : uldNo.trim();
    }

    public String getWhslocation() {
        return whslocation;
    }

    public void setWhslocation(String whslocation) {
        this.whslocation = whslocation == null ? null : whslocation.trim();
    }

    public BigDecimal getMawbid() {
        return mawbid;
    }

    public void setMawbid(BigDecimal mawbid) {
        this.mawbid = mawbid;
    }

    public String getAwbpre() {
        return awbpre;
    }

    public void setAwbpre(String awbpre) {
        this.awbpre = awbpre == null ? null : awbpre.trim();
    }

    public String getAwbno() {
        return awbno;
    }

    public void setAwbno(String awbno) {
        this.awbno = awbno == null ? null : awbno.trim();
    }

    public String getCreateOpe() {
        return createOpe;
    }

    public void setCreateOpe(String createOpe) {
        this.createOpe = createOpe == null ? null : createOpe.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateOpe() {
        return updateOpe;
    }

    public void setUpdateOpe(String updateOpe) {
        this.updateOpe = updateOpe == null ? null : updateOpe.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public BigDecimal getDeptid() {
        return deptid;
    }

    public void setDeptid(BigDecimal deptid) {
        this.deptid = deptid;
    }
}