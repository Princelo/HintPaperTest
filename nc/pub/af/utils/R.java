package nc.pub.af.utils;

/**
 * 资源类 配置常量等信息
 *
 * @author thx
 */

final public class R {

    /**
     * 金额计算相关等字段
     *
     * @author thx
     */
    final public static class N {
        /**
         * 物料主键
         */
        final public static String pk_material = "pk_material";

        /**
         * 物料版本主键
         */
        final public static String pk_material_v = "pk_material_v";

        // =================================================================

        /**
         * 表头 价税合计
         */
        final public static String ntotalorigmny = "ntotalorigmny";

        /**
         * 表头 总数量
         */
        final public static String ntotalnum = "ntotalnum";

        // =================================================================

        /**
         * 税率
         */
        final public static String ntaxrate = "ntaxrate";

        /**
         * 税额
         */
        final public static String ntax = "ntax";

        /**
         * 本位币种
         */
        final public static String ccurrencyid = "ccurrencyid";

        /**
         * 汇率
         */
        final public static String rate = "rate";

        /**
         * 主单位
         */
        final public static String cunitid = "cunitid";

        /**
         * 辅单位
         */
        final public static String castunitid = "castunitid";

        /**
         * 主辅换算率
         */
        final public static String vchangerate = "vchangerate";

        /**
         * 主原币价税合计
         */
        final public static String norigtaxmny = "norigtaxmny";

        /**
         * 主原币无税合计
         */
        final public static String norigmny = "norigmny";

        /**
         * 主本币价税合计
         */
        final public static String ntaxmny = "ntaxmny";

        /**
         * 主本币无税合计
         */
        final public static String nmny = "nmny";

        /**
         * 报价单位
         */
        final public static String cqtunitid = "cqtunitid";

        /**
         * 报价单位数量
         */
        final public static String nqtunitnum = "nqtunitnum";

        // =================================================================
        /**
         * 主原币币种
         */
        final public static String corigcurrencyid = "corigcurrencyid";

        /**
         * 主原币含税单价
         */
        final public static String norigtaxprice = "norigtaxprice";

        /**
         * 主原币无税单价
         */
        final public static String norigprice = "norigprice";

        /**
         * 主本币含税单价
         */
        final public static String ntaxprice = "ntaxprice";

        /**
         * 主本币无税单价
         */
        final public static String nprice = "nprice";

        /**
         * 主数量
         */
        final public static String nnum = "nnum";

        // =================================================================

        /**
         * 辅原币含税单价
         */
        final public static String nqtorigtaxprice = "nqtorigtaxprice";

        /**
         * 辅原币无税单价
         */
        final public static String nqtorigprice = "nqtorigprice";

        /**
         * 辅本币含税单价
         */
        final public static String nqttaxprice = "nqttaxprice";

        /**
         * 辅本币无税单价
         */
        final public static String nqtprice = "nqtprice";

        /**
         * 辅数量
         */
        final public static String nastnum = "nastnum";

    }

    /**
     * 约定字段，例如启用字段
     *
     * @author thx
     */
    final public static class FIELD {
        /**
         * 启用状态字段
         */
        final public static String ENABLESTATE = "enablestate";

    }

    final public static class CODERULE {
        /**
         * 烤炉起始编码
         */
        final public static String JK = "JK";
        /**
         * 机上用品起始编码
         */
        final public static String J = "J";
        /**
         * 餐车起始编码
         */
        final public static String JC = "JC";
    }

    /**
     * 参数设置字段
     *
     * @author thx
     */
    final public static class INIT {
        /**
         * 参数设置，AF01 使用期间汇率/日汇率，true = 期间汇率，false=日汇率
         */
        final public static String AF01 = "AF01";

        /**
         * 参数设置 AF02 指定 客户基本信息是否南航自定义项是哪一个字段
         */
        final public static String AF02 = "AF02";

        /**
         * 全局参数 AF03 航食系统接口URL
         */
        final public static String AF03 = "AF03";

        /**
         * 全局参数 AF04 国外航空公司  客户基本分类编码<BR/>多个时逗号隔开:01,02
         */
        final public static String AF04 = "AF04";




        /**
         * 集团参数 AFHC04 产成品入库单表体 计划分批号字段
         */
        final public static String AFHC04 = "AFHC04";

        /**
         * 集团参数 AFHC03 产成品入库单表体 生产时间字段
         */
        final public static String AFHC03 = "AFHC03";

        /**
         * 组织参数AFORG01 餐具计划审核通过，不处理，推保存态,推审批态
         */
        final public static String AFORG01 = "AFORG01";

        /**
         * 组织参数 AFHE001 发餐明细审核通过，不推,自由,签字 状态的其它出库
         */
        final public static String AFHE001 = "AFHE001";


        /**
         * 组织参数 AFHA11 排产明细 审核通过,不推,推保存态,推提交态,推审批态
         */
        final public static String AFHA11 = "AFHA11";

        /**
         * 组织参数 AFHA02 计划属性T开始时间的
         */
        final public static String AFHA02 = "AFHA02";



    }

    final public static class VALUE {
        /**
         * 全局组织主键
         */
        final public static String GLOBLE = "GLOBLE00000000000000";


    }

    /**
     * 数据权限 业务实体管理 资源编码
     *
     * @author thx
     */
    final public static class POWERDATA {

        /**
         * 飞机档案  资源编码
         */
        final public static String AIRPLANE = "airplane";

    }

}
