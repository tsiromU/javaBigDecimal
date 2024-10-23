import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import org.junit.Test;
import java.math.RoundingMode;
import java.util.Random;

public class LogarithmTaylorSeriesTest {
    @Test
    public void test1(){
        BigDecimal eps = BigDecimal.valueOf(9);
        BigDecimal x = BigDecimal.valueOf(1.5);

        BigDecimal answer =  LogarithmTaylorSeries.lnTaylor(x, eps);
        BigDecimal rightAnswer = BigDecimal.valueOf(0.405465108);
        
        assertTrue(answer.compareTo(rightAnswer) == 0);
    }

    @Test
    public void test2(){
        BigDecimal eps = BigDecimal.valueOf(10);
        BigDecimal x = BigDecimal.valueOf(1.5);

        BigDecimal answer =  LogarithmTaylorSeries.lnTaylor(x, eps);
        BigDecimal rightAnswer = BigDecimal.valueOf(Math.log(x.doubleValue()));
        rightAnswer = rightAnswer.setScale(eps.intValue(), RoundingMode.DOWN);
        
        assertTrue(answer.compareTo(rightAnswer) == 0);
    }

    @Test
    public void RandomTest(){
        Random rand = new Random();
        BigDecimal eps = BigDecimal.valueOf(rand.nextInt(10) + 3);
        BigDecimal x = BigDecimal.valueOf(rand.nextDouble(2)).setScale(4, RoundingMode.DOWN);

        BigDecimal answer =  LogarithmTaylorSeries.lnTaylor(x, eps).setScale(eps.intValue(), RoundingMode.DOWN);
        BigDecimal rightAnswer = BigDecimal.valueOf(Math.log(x.doubleValue()));
        rightAnswer = rightAnswer.setScale(eps.intValue(), RoundingMode.DOWN);

        boolean a = answer.compareTo(rightAnswer) == 0;
        String errorMessage = "mistake for x:" + x + "   eps:" + eps;
        assertTrue(errorMessage, a);
    }
}
