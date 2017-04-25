package nc.vo.af.trolleyhintpaper;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class TrolleyHintPaperHVO {
    /**
     * *审批人
     * */
    public static final String APPROVER="approver";
    /**
     * *业务类型
     * */
    public static final String BUSITYPE="busitype";
    /**
     * *制单时间
     * */
    public static final String CREATIONTIME="creationtime";
    /**
     * *制单人
     * */
    public static final String CREATOR="creator";
    /**
     * *来源单据表体主键
     * */
    public String csrcbid;
    /**
     * *来源单据主表主键
     * */
    public String csrcid;
    /**
     * *来源单据类型编码
     * */
    public String csrctype;
    /**
     * *来源单据类型
     * */
    public String csrctypeid;
    /**
     * *交易类型
     * */
    public static final String CTRANTYPEID="ctrantypeid";
    /**
     * *单据日期
     * */
    public static final String DBILLDATE="dbilldate";
    /**
     * *日期
     * */
    public UFDate ddate;
    /**
     * *自定义项1
     * */
    public String def1;
    /**
     * *自定义项10
     * */
    public String def10;
    /**
     * *自定义项11
     * */
    public String def11;
    /**
     * *自定义项12
     * */
    public String def12;
    /**
     * *自定义项13
     * */
    public String def13;
    /**
     * *自定义项14
     * */
    public String def14;
    /**
     * *自定义项15
     * */
    public String def15;
    /**
     * *自定义项16
     * */
    public String def16;
    /**
     * *自定义项17
     * */
    public String def17;
    /**
     * *自定义项18
     * */
    public String def18;
    /**
     * *自定义项19
     * */
    public String def19;
    /**
     * *自定义项2
     * */
    public String def2;
    /**
     * *自定义项20
     * */
    public String def20;
    /**
     * *自定义项3
     * */
    public String def3;
    /**
     * *自定义项4
     * */
    public String def4;
    /**
     * *自定义项5
     * */
    public String def5;
    /**
     * *自定义项6
     * */
    public String def6;
    /**
     * *自定义项7
     * */
    public String def7;
    /**
     * *自定义项8
     * */
    public String def8;
    /**
     * *自定义项9
     * */
    public String def9;
    /**
     * *单据状态
     * */
    public static final String FSTATUSFLAG="fstatusflag";
    /**
     * *最后修改时间
     * */
    public static final String MODIFIEDTIME="modifiedtime";
    /**
     * *最后修改人
     * */
    public static final String MODIFIER="modifier";
    /**
     * *打印类型
     * */
    public String papertype;
    /**
     * *单据类型编码
     * */
    public static final String PK_BILLTYPECODE="pk_billtypecode";
    /**
     * *单据类型
     * */
    public static final String PK_BILLTYPEID="pk_billtypeid";
    /**
     * *集团
     * */
    public static final String PK_GROUP="pk_group";
    /**
     * *组织
     * */
    public static final String PK_ORG="pk_org";
    /**
     * *组织版本
     * */
    public static final String PK_ORG_V="pk_org_v";
    /**
     * *主表主键
     * */
    public String pk_trolleyhintpaper_h;
    /**
     * *审批时间
     * */
    public static final String TAUDITTIME="taudittime";
    /**
     * *起飞时间
     * */
    public UFDateTime tdeparturestm;
    /**
     * *时间戳
     * */
    public UFDateTime ts;
    /**
     * *飞机号
     * */
    public String vairplanenbr;
    /**
     * *审批批语
     * */
    public static final String VAPPROVENOTE="vapprovenote";
    /**
     * *单据号
     * */
    public static final String VBILLCODE="vbillcode";
    /**
     * *航班号
     * */
    public String vfltno;
    /**
     * *配餐
     * */
    public String vfood;
    /**
     * *厨房位置
     * */
    public String vkitchen;
    /**
     * *航段
     * */
    public String vleg;
    /**
     * *机型
     * */
    public String vmodel;
    /**
     * *备注
     * */
    public static final String VNOTE="vnote";
    /**
     * *服务台
     * */
    public String vplanserver;
    /**
     * *具体位置
     * */
    public String vplanseat;
    /**
     * *交易类型编码
     * */
    public static final String VTRANTYPECODE="vtrantypecode";
    /**
     * *设备型号
     * */
    public String vtrolleymodel;
    /**
     * *设备编号
     * */
    public String vtrolleynbr;
    /**
     *  * 是否装配特殊餐
     *   */
    public UFBoolean bisspecialinstalled;
    public UFDate dbilldate;

    /**
     * * 获取单据日期
     * *
     * * @return 单据日期
     * */
    public UFDate getDbilldate () {
        return dbilldate;
         }

    /**
     * * 设置单据日期
     * *
     * * @param dbilldate 单据日期
     * */
    public void setDbilldate ( UFDate dbilldate) {
        this.dbilldate = dbilldate;
         }

    /**
     * * 获取日期
     * *
     * * @return 日期
     * */
    public UFDate getDdate () {
        return this.ddate;
         }

    /**
     * * 设置日期
     * *
     * * @param ddate 日期
     * */
    public void setDdate ( UFDate ddate) {
        this.ddate=ddate;
         }

    /**
     * * 获取自定义项1
     * *
     * * @return 自定义项1
     * */
    public String getDef1 () {
        return this.def1;
         }

    /**
     * * 设置自定义项1
     * *
     * * @param def1 自定义项1
     * */
    public void setDef1 ( String def1) {
        this.def1=def1;
         }

    /**
     * * 获取自定义项10
     * *
     * * @return 自定义项10
     * */
    public String getDef10 () {
        return this.def10;
         }

    /**
     * * 设置自定义项10
     * *
     * * @param def10 自定义项10
     * */
    public void setDef10 ( String def10) {
        this.def10=def10;
         }

    /**
     * * 获取自定义项11
     * *
     * * @return 自定义项11
     * */
    public String getDef11 () {
        return this.def11;
         }

    /**
     * * 设置自定义项11
     * *
     * * @param def11 自定义项11
     * */
    public void setDef11 ( String def11) {
        this.def11=def11;
         }

    /**
     * * 获取自定义项12
     * *
     * * @return 自定义项12
     * */
    public String getDef12 () {
        return this.def12;
         }

    /**
     * * 设置自定义项12
     * *
     * * @param def12 自定义项12
     * */
    public void setDef12 ( String def12) {
        this.def12=def12;
         }

    /**
     * * 获取自定义项13
     * *
     * * @return 自定义项13
     * */
    public String getDef13 () {
        return this.def13;
         }

    /**
     * * 设置自定义项13
     * *
     * * @param def13 自定义项13
     * */
    public void setDef13 ( String def13) {
        this.def13=def13;
         }

    /**
     * * 获取自定义项14
     * *
     * * @return 自定义项14
     * */
    public String getDef14 () {
        return this.def14;
         }

    /**
     * * 设置自定义项14
     * *
     * * @param def14 自定义项14
     * */
    public void setDef14 ( String def14) {
        this.def14=def14;
         }

    /**
     * * 获取自定义项15
     * *
     * * @return 自定义项15
     * */
    public String getDef15 () {
        return this.def15;
         }

    /**
     * * 设置自定义项15
     * *
     * * @param def15 自定义项15
     * */
    public void setDef15 ( String def15) {
        this.def15=def15;
         }

    /**
     * * 获取自定义项16
     * *
     * * @return 自定义项16
     * */
    public String getDef16 () {
        return this.def16;
         }

    /**
     * * 设置自定义项16
     * *
     * * @param def16 自定义项16
     * */
    public void setDef16 ( String def16) {
        this.def16=def16;
         }

    /**
     * * 获取自定义项17
     * *
     * * @return 自定义项17
     * */
    public String getDef17 () {
        return this.def17;
         }

    /**
     * * 设置自定义项17
     * *
     * * @param def17 自定义项17
     * */
    public void setDef17 ( String def17) {
        this.def17=def17;
         }

    /**
     * * 获取自定义项18
     * *
     * * @return 自定义项18
     * */
    public String getDef18 () {
        return this.def18;
         }

    /**
     * * 设置自定义项18
     * *
     * * @param def18 自定义项18
     * */
    public void setDef18 ( String def18) {
        this.def18=def18;
         }

    /**
     * * 获取自定义项19
     * *
     * * @return 自定义项19
     * */
    public String getDef19 () {
        return this.def19;
         }

    /**
     * * 设置自定义项19
     * *
     * * @param def19 自定义项19
     * */
    public void setDef19 ( String def19) {
        this.def19=def19;
         }

    /**
     * * 获取自定义项2
     * *
     * * @return 自定义项2
     * */
    public String getDef2 () {
        return this.def2;
         }

    /**
     * * 设置自定义项2
     * *
     * * @param def2 自定义项2
     * */
    public void setDef2 ( String def2) {
        this.def2=def2;
         }

    /**
     * * 获取自定义项20
     * *
     * * @return 自定义项20
     * */
    public String getDef20 () {
        return this.def20;
         }

    /**
     * * 设置自定义项20
     * *
     * * @param def20 自定义项20
     * */
    public void setDef20 ( String def20) {
        this.def20=def20;
         }

    /**
     * * 获取自定义项3
     * *
     * * @return 自定义项3
     * */
    public String getDef3 () {
        return this.def3;
         }

    /**
     * * 设置自定义项3
     * *
     * * @param def3 自定义项3
     * */
    public void setDef3 ( String def3) {
        this.def3=def3;
         }

    /**
     * * 获取自定义项4
     * *
     * * @return 自定义项4
     * */
    public String getDef4 () {
        return this.def4;
         }

    /**
     * * 设置自定义项4
     * *
     * * @param def4 自定义项4
     * */
    public void setDef4 ( String def4) {
        this.def4=def4;
         }

    /**
     * * 获取自定义项5
     * *
     * * @return 自定义项5
     * */
    public String getDef5 () {
        return this.def5;
         }

    /**
     * * 设置自定义项5
     * *
     * * @param def5 自定义项5
     * */
    public void setDef5 ( String def5) {
        this.def5=def5;
         }

    /**
     * * 获取自定义项6
     * *
     * * @return 自定义项6
     * */
    public String getDef6 () {
        return this.def6;
         }

    /**
     * * 设置自定义项6
     * *
     * * @param def6 自定义项6
     * */
    public void setDef6 ( String def6) {
        this.def6=def6;
         }

    /**
     * * 获取自定义项7
     * *
     * * @return 自定义项7
     * */
    public String getDef7 () {
        return this.def7;
         }

    /**
     * * 设置自定义项7
     * *
     * * @param def7 自定义项7
     * */
    public void setDef7 ( String def7) {
        this.def7=def7;
         }

    /**
     * * 获取自定义项8
     * *
     * * @return 自定义项8
     * */
    public String getDef8 () {
        return this.def8;
         }

    /**
     * * 设置自定义项8
     * *
     * * @param def8 自定义项8
     * */
    public void setDef8 ( String def8) {
        this.def8=def8;
         }

    /**
     * * 获取自定义项9
     * *
     * * @return 自定义项9
     * */
    public String getDef9 () {
        return this.def9;
         }

    /**
     * * 设置自定义项9
     * *
     * * @param def9 自定义项9
     * */
    public void setDef9 ( String def9) {
        this.def9=def9;
         }

    /**
     * * 获取单据状态
     * *
     * * @return 单据状态
     * * @see String
     * */
    public Integer getFstatusflag () {
        return fstatusflag;
         }
    public Integer fstatusflag;
    public String pk_group;
    public String pk_org;

    public String pk_org_v;
    public String vnote;

    /**
     * * 设置单据状态
     * *
     * * @param fstatusflag 单据状态
     * * @see String
     * */
    public void setFstatusflag ( Integer fstatusflag) {
        this.fstatusflag = fstatusflag;
         }


    /**
     * * 获取打印类型
     * *
     * * @return 打印类型
     * * @see String
     * */
    public String getPapertype () {
        return this.papertype;
         }

    /**
     * * 设置打印类型
     * *
     * * @param papertype 打印类型
     * * @see String
     * */
    public void setPapertype ( String papertype) {
        this.papertype=papertype;
         }

    /**
     * * 获取集团
     * *
     * * @return 集团
     * */
    public String getPk_group () {
        return pk_group;
         }

    /**
     * * 设置集团
     * *
     * * @param pk_group 集团
     * */
    public void setPk_group ( String pk_group) {
        this.pk_group = pk_group;
         }

    /**
     * * 获取组织
     * *
     * * @return 组织
     * */
    public String getPk_org () {
        return pk_org;
         }

    /**
     * * 设置组织
     * *
     * * @param pk_org 组织
     * */
    public void setPk_org ( String pk_org) {
        this.pk_org = pk_org;
         }

    /**
     * * 获取组织版本
     * *
     * * @return 组织版本
     * */
    public String getPk_org_v () {
        return pk_org_v;
         }

    /**
     * * 设置组织版本
     * *
     * * @param pk_org_v 组织版本
     * */
    public void setPk_org_v ( String pk_org_v) {
        this.pk_org_v = pk_org_v;
         }

    /**
     * * 获取主表主键
     * *
     * * @return 主表主键
     * */
    public String getPk_trolleyhintpaper_h () {
        return this.pk_trolleyhintpaper_h;
         }

    /**
     * * 设置主表主键
     * *
     * * @param pk_trolleyhintpaper_h 主表主键
     * */
    public void setPk_trolleyhintpaper_h ( String pk_trolleyhintpaper_h) {
        this.pk_trolleyhintpaper_h=pk_trolleyhintpaper_h;
         }


    /**
     * * 获取起飞时间
     * *
     * * @return 起飞时间
     * */
    public UFDateTime getTdeparturestm () {
        return this.tdeparturestm;
         }

    /**
     * * 设置起飞时间
     * *
     * * @param tdeparturestm 起飞时间
     * */
    public void setTdeparturestm ( UFDateTime tdeparturestm) {
        this.tdeparturestm=tdeparturestm;
         }

    /**
     * * 获取时间戳
     * *
     * * @return 时间戳
     * */
    public UFDateTime getTs () {
        return this.ts;
         }

    /**
     * * 设置时间戳
     * *
     * * @param ts 时间戳
     * */
    public void setTs ( UFDateTime ts) {
        this.ts=ts;
         }

    /**
     * * 获取飞机号
     * *
     * * @return 飞机号
     * */
    public String getVairplanenbr () {
        return this.vairplanenbr;
         }

    /**
     * * 设置飞机号
     * *
     * * @param vairplanenbr 飞机号
     * */
    public void setVairplanenbr ( String vairplanenbr) {
        this.vairplanenbr=vairplanenbr;
         }



    /**
     * * 获取航班号
     * *
     * * @return 航班号
     * */
    public String getVfltno () {
        return this.vfltno;
         }

    /**
     * * 设置航班号
     * *
     * * @param vfltno 航班号
     * */
    public void setVfltno ( String vfltno) {
        this.vfltno=vfltno;
         }

    /**
     * * 获取配餐
     * *
     * * @return 配餐
     * */
    public String getVfood () {
        return this.vfood;
         }

    /**
     * * 设置配餐
     * *
     * * @param vfood 配餐
     * */
    public void setVfood ( String vfood) {
        this.vfood=vfood;
         }

    /**
     * * 获取厨房位置
     * *
     * * @return 厨房位置
     * */
    public String getVkitchen () {
        return this.vkitchen;
         }

    /**
     * * 设置厨房位置
     * *
     * * @param vkitchen 厨房位置
     * */
    public void setVkitchen ( String vkitchen) {
        this.vkitchen=vkitchen;
         }

    /**
     * * 获取航段
     * *
     * * @return 航段
     * */
    public String getVleg () {
        return this.vleg;
         }

    /**
     * * 设置航段
     * *
     * * @param vleg 航段
     * */
    public void setVleg ( String vleg) {
        this.vleg=vleg;
         }

    /**
     * * 获取机型
     * *
     * * @return 机型
     * */
    public String getVmodel () {
        return this.vmodel;
         }

    /**
     * * 设置机型
     * *
     * * @param vmodel 机型
     * */
    public void setVmodel ( String vmodel) {
        this.vmodel=vmodel;
         }

    /**
     * * 获取备注
     * *
     * * @return 备注
     * */
    public String getVnote () {
        return vnote;
         }

    /**
     * * 设置备注
     * *
     * * @param vnote 备注
     * */
    public void setVnote ( String vnote) {
        this.vnote = vnote;
         }

    /**
     * * 获取服务台
     * *
     * * @return 服务台
     * */
    public String getVplanserver () {
        return this.vplanserver;
         }

    /**
     * * 设置服务台
     * *
     * * @param vplanserver 服务台
     * */
    public void setVplanserver ( String vplanserver) {
        this.vplanserver=vplanserver;
         }

    /**
     * * 获取具体位置
     * *
     * * @return 具体位置
     * */
    public String getVplanseat() {
            return vplanseat;
    }

    /**
     * * 设置具体位置
     * *
     * * @param vplanseat 具体位置
     * */
    public void setVplanseat(String vplanseat) {
            this.vplanseat = vplanseat;
    }


    /**
     * * 获取设备型号
     * *
     * * @return 设备型号
     * */
    public String getVtrolleymodel () {
        return this.vtrolleymodel;
         }

    /**
     * * 设置设备型号
     * *
     * * @param vtrolleymodel 设备型号
     * */
    public void setVtrolleymodel ( String vtrolleymodel) {
        this.vtrolleymodel=vtrolleymodel;
         }

    /**
     * * 获取设备编号
     * *
     * * @return 设备编号
     * */
    public String getVtrolleynbr () {
        return this.vtrolleynbr;
         }

    /**
     * * 设置设备编号
     * *
     * * @param vtrolleynbr 设备编号
     * */
    public void setVtrolleynbr ( String vtrolleynbr) {
        this.vtrolleynbr=vtrolleynbr;
         }


    /**
     *  * 获取是否装配特殊餐
     *   * @return 是否装配特殊餐
     *    */
      public UFBoolean getBisspecialinstalled() {
            return bisspecialinstalled;
      }
      /**
       *  * 设置是否装配特殊餐
       *   * @param bisspecialinstalled
       *    */
      public void setBisspecialinstalled(UFBoolean bisspecialinstalled) {
            this.bisspecialinstalled = bisspecialinstalled;
      }

}
