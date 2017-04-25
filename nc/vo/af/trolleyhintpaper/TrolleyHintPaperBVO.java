package nc.vo.af.trolleyhintpaper;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class TrolleyHintPaperBVO {
    /**
     * *行号
     * */
    public String crowno;
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
     * *A/B面
     * */
    public String hpart;
    /**
     * *子表主键
     * */
    public String pk_trolleyhintpaper_b;
    /**
     * *上层单据主键
     * */
    public String pk_trolleyhintpaper_h;
    /**
     *  * 配餐主键
     *   */
    public String pk_defood;
    /**
     *  * 配餐
     *   */
    public String vdefood;
    /**
     *  * 物料主键
     *   */
    public String pk_material;
    /**
     * *时间戳
     * */
    public UFDateTime ts;
    /**
     * *自定义项1
     * */
    public static final String VBDEF1="vbdef1";
    /**
     * *自定义项10
     * */
    public static final String VBDEF10="vbdef10";
    /**
     * *自定义项11
     * */
    public static final String VBDEF11="vbdef11";
    /**
     * *自定义项12
     * */
    public static final String VBDEF12="vbdef12";
    /**
     * *自定义项13
     * */
    public static final String VBDEF13="vbdef13";
    /**
     * *自定义项14
     * */
    public static final String VBDEF14="vbdef14";
    /**
     * *自定义项15
     * */
    public static final String VBDEF15="vbdef15";
    /**
     * *自定义项16
     * */
    public static final String VBDEF16="vbdef16";
    /**
     * *自定义项17
     * */
    public static final String VBDEF17="vbdef17";
    /**
     * *自定义项18
     * */
    public static final String VBDEF18="vbdef18";
    /**
     * *自定义项19
     * */
    public static final String VBDEF19="vbdef19";
    /**
     * *自定义项2
     * */
    public static final String VBDEF2="vbdef2";
    /**
     * *自定义项20
     * */
    public static final String VBDEF20="vbdef20";
    /**
     * *自定义项21
     * */
    public static final String VBDEF21="vbdef21";
    /**
     * *自定义项22
     * */
    public static final String VBDEF22="vbdef22";
    /**
     * *自定义项23
     * */
    public static final String VBDEF23="vbdef23";
    /**
     * *自定义项24
     * */
    public static final String VBDEF24="vbdef24";
    /**
     * *自定义项25
     * */
    public static final String VBDEF25="vbdef25";
    /**
     * *自定义项26
     * */
    public static final String VBDEF26="vbdef26";
    /**
     * *自定义项27
     * */
    public static final String VBDEF27="vbdef27";
    /**
     * *自定义项28
     * */
    public static final String VBDEF28="vbdef28";
    /**
     * *自定义项29
     * */
    public static final String VBDEF29="vbdef29";
    /**
     * *自定义项3
     * */
    public static final String VBDEF3="vbdef3";
    /**
     * *自定义项30
     * */
    public static final String VBDEF30="vbdef30";
    /**
     * *自定义项31
     * */
    public static final String VBDEF31="vbdef31";
    /**
     * *自定义项32
     * */
    public static final String VBDEF32="vbdef32";
    /**
     * *自定义项33
     * */
    public static final String VBDEF33="vbdef33";
    /**
     * *自定义项34
     * */
    public static final String VBDEF34="vbdef34";
    /**
     * *自定义项35
     * */
    public static final String VBDEF35="vbdef35";
    /**
     * *自定义项36
     * */
    public static final String VBDEF36="vbdef36";
    /**
     * *自定义项37
     * */
    public static final String VBDEF37="vbdef37";
    /**
     * *自定义项38
     * */
    public static final String VBDEF38="vbdef38";
    /**
     * *自定义项39
     * */
    public static final String VBDEF39="vbdef39";
    /**
     * *自定义项4
     * */
    public static final String VBDEF4="vbdef4";
    /**
     * *自定义项40
     * */
    public static final String VBDEF40="vbdef40";
    /**
     * *自定义项5
     * */
    public static final String VBDEF5="vbdef5";
    /**
     * *自定义项6
     * */
    public static final String VBDEF6="vbdef6";
    /**
     * *自定义项7
     * */
    public static final String VBDEF7="vbdef7";
    /**
     * *自定义项8
     * */
    public static final String VBDEF8="vbdef8";
    /**
     * *自定义项9
     * */
    public static final String VBDEF9="vbdef9";
    /**
     * *备注
     * */
    public static final String VBNOTE="vbnote";
    public String vbnote;
    /**
     * *打印项
     * */
    public String vitem;
    /**
     * *上/下层
     * */
    public String vpart;
    /**
     * *数量
     * */
    public String vquantity;
    /**
     * * 获取行号
     * *
     * * @return 行号
     * */
    public String getCrowno () {
        return this.crowno;
         }

    /**
     * * 设置行号
     * *
     * * @param crowno 行号
     * */
    public void setCrowno ( String crowno) {
        this.crowno=crowno;
         }

    /**
     * * 获取来源单据表体主键
     * *
     * * @return 来源单据表体主键
     * */
    public String getCsrcbid () {
        return this.csrcbid;
         }

    /**
     * * 设置来源单据表体主键
     * *
     * * @param csrcbid 来源单据表体主键
     * */
    public void setCsrcbid ( String csrcbid) {
        this.csrcbid=csrcbid;
         }

    /**
     * * 获取来源单据主表主键
     * *
     * * @return 来源单据主表主键
     * */
    public String getCsrcid () {
        return this.csrcid;
         }

    /**
     * * 设置来源单据主表主键
     * *
     * * @param csrcid 来源单据主表主键
     * */
    public void setCsrcid ( String csrcid) {
        this.csrcid=csrcid;
         }

    /**
     * * 获取来源单据类型编码
     * *
     * * @return 来源单据类型编码
     * */
    public String getCsrctype () {
        return this.csrctype;
         }

    /**
     * * 设置来源单据类型编码
     * *
     * * @param csrctype 来源单据类型编码
     * */
    public void setCsrctype ( String csrctype) {
        this.csrctype=csrctype;
         }

    /**
     * * 获取来源单据类型
     * *
     * * @return 来源单据类型
     * */
    public String getCsrctypeid () {
        return this.csrctypeid;
         }

    /**
     * * 设置来源单据类型
     * *
     * * @param csrctypeid 来源单据类型
     * */
    public void setCsrctypeid ( String csrctypeid) {
        this.csrctypeid=csrctypeid;
         }

    /**
     * * 获取A/B面
     * *
     * * @return A/B面
     * * @see String
     * */
    public String getHpart () {
        return this.hpart;
         }

    /**
     * * 设置A/B面
     * *
     * * @param hpart A/B面
     * * @see String
     * */
    public void setHpart ( String hpart) {
        this.hpart=hpart;
         }

    /**
     * * 获取子表主键
     * *
     * * @return 子表主键
     * */
    public String getPk_trolleyhintpaper_b () {
        return this.pk_trolleyhintpaper_b;
         }

    /**
     * * 设置子表主键
     * *
     * * @param pk_trolleyhintpaper_b 子表主键
     * */
    public void setPk_trolleyhintpaper_b ( String pk_trolleyhintpaper_b) {
        this.pk_trolleyhintpaper_b=pk_trolleyhintpaper_b;
         }

    /**
     * * 获取上层单据主键
     * *
     * * @return 上层单据主键
     * */
    public String getPk_trolleyhintpaper_h () {
        return this.pk_trolleyhintpaper_h;
         }

    /**
     * * 设置上层单据主键
     * *
     * * @param pk_trolleyhintpaper_h 上层单据主键
     * */
    public void setPk_trolleyhintpaper_h ( String pk_trolleyhintpaper_h) {
        this.pk_trolleyhintpaper_h=pk_trolleyhintpaper_h;
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
     * * 获取备注
     * *
     * * @return 备注
     * */
    public String getVbnote () {
        return this.vbnote;
    }

    /**
     * * 设置备注
     * *
     * * @param vbnote 备注
     * */
    public void setVbnote ( String vbnote) {
        this.vbnote = vbnote;
    }

    /**
     * * 获取打印项
     * *
     * * @return 打印项
     * */
    public String getVitem () {
        return this.vitem;
         }

    /**
     * * 设置打印项
     * *
     * * @param vitem 打印项
     * */
    public void setVitem ( String vitem) {
        this.vitem=vitem;
         }

    /**
     * * 获取上/下层
     * *
     * * @return 上/下层
     * * @see String
     * */
    public String getVpart () {
        return this.vpart;
         }

    /**
     * * 设置上/下层
     * *
     * * @param vpart 上/下层
     * * @see String
     * */
    public void setVpart ( String vpart) {
        this.vpart=vpart;
         }

    /**
     * * 获取数量
     * *
     * * @return 数量
     * */
    public String getVquantity () {
        return this.vquantity;
         }

    /**
     * * 设置数量
     * *
     * * @param vquantity 数量
     * */
    public void setVquantity ( String vquantity) {
        this.vquantity=vquantity;
         }

    /**
     * * 获取配餐主键
     * *
     * * @return 配餐主键
     * */
    public String getPk_defood () {
        return this.pk_defood;
         }

    /**
     * * 设置配餐主键
     * *
     * * @param pk_defood 配餐主键
     * */
    public void setPk_defood ( String pk_defood) {
        this.pk_defood=pk_defood;
         }

    /**
     * * 获取配餐
     * *
     * * @return 配餐
     * */
    public String getVdefood () {
        return this.vdefood;
         }

    /**
     * * 设置配餐
     * *
     * * @param vdefood 配餐
     * */
    public void setVdefood ( String vdefood) {
        this.vdefood=vdefood;
         }

    /**
     * * 获取物料主键
     * *
     * * @return 物料主键
     * */
    public String getPk_material () {
        return this.pk_material;
         }

    /**
     * * 设置物料主键
     * *
     * * @param pk_material 物料主键
     * */
    public void setPk_material ( String pk_material) {
        this.pk_material=pk_material;
    }

}
