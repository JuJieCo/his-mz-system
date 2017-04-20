package com.jujie.his.mz.dao;


import java.math.BigDecimal;

public class BigdecimalChang {

	// 默认除法运算精度

	private static final int DEF_DIV_SCALE = 10;

	// 这个类不能实例化

	private BigdecimalChang() {

	}

	/**
	 * 
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * 
	 * @param v2
	 *            加数
	 * 
	 * @return 两个参数的和
	 */

	public static double addByDouble(String v1, double v2) {
		BigDecimal b1;
		BigDecimal b2;
		if (v1 == "") {
			b1 = new BigDecimal("0.00");
		} else {
			b1 = new BigDecimal(v1);
		}
		b2 = new BigDecimal(v2);
		b2 = b2.setScale(2, BigDecimal.ROUND_HALF_UP);
		return b1.add(b2).doubleValue();

	}

	public static double add(String v1, String v2) {
		BigDecimal b1;
		BigDecimal b2;
		if (v1 == "") {
			b1 = new BigDecimal("0.00");
		} else {
			b1 = new BigDecimal(v1);
		}
		if (v2 == "") {
			b2 = new BigDecimal("0.00");
		} else {
			b2 = new BigDecimal(v2);
		}
		return b1.add(b2).doubleValue();

	}

	public static double addByBigDecimal(BigDecimal v1, BigDecimal v2) {

		return v1.add(v2).doubleValue();

	}

	/**
	 * 
	 * 提供精确的大小比较运算。
	 * 
	 * @param v1
	 *            被比较数
	 * 
	 * @param v2
	 *            比较数
	 * 
	 * @return 两个参数的和
	 */

	public static int equ(String v1, String v2) {

		BigDecimal b1 = new BigDecimal(v1);

		BigDecimal b2 = new BigDecimal(v2);

		return b1.compareTo(b2);

	}

	public static int equ(BigDecimal v2, BigDecimal v1) {

		return v2.compareTo(v1);

	}

	public static BigDecimal returnBigdecimal(String v1) {

		BigDecimal bd = new BigDecimal(v1);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);

		return bd;

	}

	public static BigDecimal returnBigdecimalbydouble(double v1) {

		BigDecimal bd = new BigDecimal(Double.toString(v1));
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);

		return bd;

	}

	public static BigDecimal returnBigdecimal(int scale, String v) {
		double d = BigdecimalChang.round(Double.valueOf(v), scale);
		BigDecimal bd = new BigDecimal(Double.toString(d));
		bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return bd;
	}

	/**
	 * 
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * 
	 * @param v2
	 *            减数
	 * 
	 * @return 两个参数的差
	 */

	public static double sub(String v1, String v2) {

		BigDecimal b1 = new BigDecimal(v1);

		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).doubleValue();

	}

	public static double sub(BigDecimal b1, BigDecimal b2) {

		return b1.subtract(b2).doubleValue();

	}

	/**
	 * 
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * 
	 * @param v2
	 *            乘数
	 * 
	 * @return 两个参数的积
	 */

	public static double mul(double v1, double v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));

		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.multiply(b2).doubleValue();

	}
       public static double mul(double v1, BigDecimal v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
                return b1.multiply(v2).doubleValue();

	}
	/**
	 * 
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	 * 
	 * 小数点以后 10 位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * 
	 * @param v2
	 *            除数
	 * 
	 * @return 两个参数的商
	 */

	public static double div(double v1, double v2) {

		return div(v1, v2, DEF_DIV_SCALE);

	}

	/**
	 * 
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由 scale 参数指
	 * 
	 * 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * 
	 * @param v2
	 *            除数
	 * 
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * 
	 * @return 两个参数的商
	 */

	public static double div(double v1, double v2, int scale) {

		if (scale < 0) {

			throw new IllegalArgumentException(

			"The scale must be a positive integer or zero");

		}

		BigDecimal b1 = new BigDecimal(Double.toString(v1));

		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	/**
	 * 
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * 
	 * @param scale
	 *            小数点后保留几位
	 * 
	 * @return 四舍五入后的结果
	 */

	public static double round(double v, int scale) {

		if (scale < 0) {

			throw new IllegalArgumentException(

			"The scale must be a positive integer or zero");

		}

		BigDecimal b = new BigDecimal(Double.toString(v));

		BigDecimal one = new BigDecimal("1");

		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	public static void main(String[] args) {
		System.out.println(BigdecimalChang.equ(Double.toString(1.0), "1"));
	}

}
