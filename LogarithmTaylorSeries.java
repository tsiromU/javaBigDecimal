import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Formatter;
import java.util.Scanner;

public class LogarithmTaylorSeries {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Formatter formatt = new Formatter();

        System.out.print("Enter the value of x: ");
        BigDecimal x = scanner.nextBigDecimal();
        
        System.out.print("Enter the value of eps (precision): ");
        BigDecimal eps = scanner.nextBigDecimal();

        BigDecimal result = lnTaylor(x, eps);
        BigDecimal resultFromMath_ln = BigDecimal.valueOf(Math.log(x.doubleValue()));
        
        System.out.println();
        //System.out.println("ln(" + x + ") = " + result.toPlainString()) + "calculated by Math.log()";
        // formatt.format("ln(" + x + ") = " + result.toPlainString() + "(taylor ln)");
        formatt.format("ln(%.10f) = %.10f (taylor ln)\n", x, result);
        formatt.format("ln(%.10f) = %.10f (library ln function)\n", x, resultFromMath_ln);
        System.out.print(formatt);
        
        scanner.close();
    }

    public static BigDecimal lnTaylor(BigDecimal x, BigDecimal eps) {
        if (x.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("x must be greater than 0");
        }
        if (x.compareTo(BigDecimal.valueOf(2)) >= 0){
            throw new IllegalArgumentException("x must be less than 2");
        }

        BigDecimal z = x.subtract(BigDecimal.ONE);
        BigDecimal term = z;  
        BigDecimal addictive = term;
        BigDecimal sum = addictive; 
        BigDecimal n = BigDecimal.ONE;  
        BigDecimal epsLimit = BigDecimal.ONE.scaleByPowerOfTen(-eps.intValue());  

        MathContext mc = new MathContext(eps.add(BigDecimal.ONE).intValue(), RoundingMode.DOWN); 
        
        while (term.abs().compareTo(epsLimit) > 0) {
            n = n.add(BigDecimal.ONE);  
            term = term.multiply(z);
            addictive = term.divide(n, mc);  
            if (n.remainder(BigDecimal.valueOf(2)).equals(BigDecimal.ZERO)) {
                sum = sum.subtract(addictive); 
            } else {
                sum = sum.add(addictive); 
            }
        }

        return sum.round(mc);  
    }
}
