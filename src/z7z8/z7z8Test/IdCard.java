package z7z8.z7z8Test;
/**
 * 
 * @author cash
 * @date 2017年11月2日 下午5:51:30
 * @decription
 * 
 *  公民身份号码是特征组合码,由十七位数字本体码和一位校验码组成。

	排列顺序从左至右依次为：六位数字地址码,八位数字出生日期码,三位数字顺序码和一位校验码。
	
	1、地址码 
	表示编码对象常住户口所在县(市、旗、区)的行政区域划分代码,按GB/T2260的规定执行。
	
	2、出生日期码 
	表示编码对象出生的年、月、日,按GB/T7408的规定执行,年、月、日代码之间不用分隔符。
	
	3、顺序码 
	表示在同一地址码所标识的区域范围内,对同年、同月、同日出生的人编定的顺序号,顺序码的奇数分配给男性,偶数分配给女性。
	
	4、校验码计算步骤
	
	(1)十七位数字本体码加权求和公式 
	S = Sum(Ai * Wi), i = 0, ... , 16 ,先对前17位数字的权求和
	Ai:表示第i位置上的身份证号码数字值(0~9) 
	Wi:7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 （表示第i位置上的加权因子）
	
	(2)计算模 
	Y = mod(S, 11)
	
	(3)根据模,查找得到对应的校验码
	Y: 0 1 2 3 4 5 6 7 8 9 10 
	校验码: 1 0 X 9 8 7 6 5 4 3 2
 */
public class IdCard {

	int[] weight = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 }; // 十七位数字本体码权重
	char[] validate = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' }; // mod11,对应校验码字符值

	public char getValidateCode(String id17) {
		int sum = 0;
		int mode = 0;
		for (int i = 0; i < id17.length(); i++) {
			sum = sum + Integer.parseInt(String.valueOf(id17.charAt(i)))
					* weight[i];
		}
		mode = sum % 11;
		return validate[mode];
	}

	public static void main(String[] args) {
		IdCard idCard = new IdCard();
		System.out.println("该身份证验证码："
				+ idCard.getValidateCode("14230219700101101")); // 该身份证校验码：3
	}

}
