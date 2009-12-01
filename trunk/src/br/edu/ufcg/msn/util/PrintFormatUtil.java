package br.edu.ufcg.msn.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class PrintFormatUtil {
	
	 private static NumberFormat nf;
	 private static DecimalFormatSymbols dfs;
	 private static DecimalFormat dfe[] = new DecimalFormat[51];
	 
	 static 
	 {
	     nf = NumberFormat.getInstance(Locale.US);
	     dfs = new DecimalFormatSymbols(Locale.US);
	 }

	public static String format(int fieldwidth, int accuracy, int precision, double x){
	     if(Double.isNaN(x))
	         return s(fieldwidth, "NaN");
	     if(Double.isInfinite(x))
	         return s(fieldwidth, (new StringBuilder()).append(x >= 0.0D ? "" : "-").append("Infinite").toString());
	     if(canUseDecimalNotation(fieldwidth, accuracy, precision, x))
	     {
	         return f(fieldwidth, accuracy, x);
	     } else
	     {
	         String S = E(fieldwidth, precision - 1, x);
	         return processExp(S);
	     }
	 }
	
	 private static boolean canUseDecimalNotation(int fieldwidth, int accuracy, int precision, double x){
	     int PosEntier = 0;
	     int Neg = 0;
	     int EntierSign;
	     if(x == 0.0D)
	     {
	         EntierSign = 1;
	     } else
	     {
	         EntierSign = PosEntier = (int)Math.floor(Math.log10(Math.abs(x)) + 1.0D);
	         if(x < 0.0D)
	             Neg = 1;
	     }
	     if(EntierSign <= 0)
	         PosEntier = 1;
	     return x == 0.0D || EntierSign + accuracy >= precision && fieldwidth >= PosEntier + accuracy + Neg + 1;
	 }
	 
	 private static String processExp(String s){
	     int p = s.indexOf("E+0");
	     if(p == -1)
	         p = s.indexOf("E-0");
	     if(p != -1)
	         s = (new StringBuilder()).append(" ").append(s.substring(0, p + 2)).append(s.substring(p + 3)).toString();
	     p = s.indexOf(".E");
	     if(p != -1)
	         s = (new StringBuilder()).append(" ").append(s.substring(0, p)).append(s.substring(p + 1)).toString();
	     return s;
	 }
	 
	 public static String s(int fieldwidth, String str)
	 {
	     if(str == null)
	         return s(fieldwidth, "null");
	     int fw = Math.abs(fieldwidth);
	     if(str.length() < fw)
	     {
	         StringBuffer buf = new StringBuffer();
	         int sl = str.length();
	         for(int i = 0; i < fw - sl; i++)
	             buf.append(' ');

	         return fieldwidth < 0 ? (new StringBuilder()).append(str).append(buf.toString()).toString() : (new StringBuilder()).append(buf.toString()).append(str).toString();
	     } else
	     {
	         return str;
	     }
	 }
	 
	 public static String f(int fieldwidth, int precision, double x)
	 {
	     if(precision < 0)
	         throw new IllegalArgumentException("precision must not be negative.");
	     if(Double.isNaN(x))
	         return s(fieldwidth, "NaN");
	     if(Double.isInfinite(x))
	     {
	         return s(fieldwidth, (new StringBuilder()).append(x >= 0.0D ? "" : "-").append("Infinite").toString());
	     } else
	     {
	         nf.setGroupingUsed(false);
	         nf.setMinimumIntegerDigits(1);
	         nf.setMinimumFractionDigits(precision);
	         nf.setMaximumFractionDigits(precision);
	         return s(fieldwidth, nf.format(x));
	     }
	 }
	 
	 public static String E(int fieldwidth, int precision, double x)
	 {
	     if(precision < 0)
	         throw new IllegalArgumentException("precision must not be negative.");
	     if(Double.isNaN(x))
	         return s(fieldwidth, "NaN");
	     if(Double.isInfinite(x))
	         return s(fieldwidth, (new StringBuilder()).append(x >= 0.0D ? "" : "-").append("Infinite").toString());
	     DecimalFormat df;
	     if(precision >= dfe.length || dfe[precision] == null)
	     {
	         StringBuffer pattern = new StringBuffer("0.");
	         for(int i = 0; i < precision; i++)
	             pattern.append("0");

	         pattern.append("E00");
	         df = new DecimalFormat(pattern.toString(), dfs);
	         df.setGroupingUsed(false);
	         if(precision < dfe.length)
	             dfe[precision] = df;
	     } else
	     {
	         df = dfe[precision];
	     }
	     String res = df.format(x);
	     int exppos = res.indexOf('E');
	     if(exppos != -1 && res.charAt(exppos + 1) != '-')
	         res = (new StringBuilder()).append(res.substring(0, exppos + 1)).append("+").append(res.substring(exppos + 1)).toString();
	     return s(fieldwidth, res);
	 }
}
