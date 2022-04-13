import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LongBuild {
    public static void main(String[] args) throws InterruptedException {
        int delayMinutes = Integer.parseInt(args[0]);
        System.out.println("Delay for: " + delayMinutes + " m");
        System.out.println(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));

        int tenSecsIntervals = delayMinutes * 60 / 10;
        for (int i = 0; i < tenSecsIntervals; ++i) {
            System.out.println((i * 10) + "s/" + delayMinutes + "m");
            TimeUnit.SECONDS.sleep(10);
        }
        System.out.println("The end!");
        System.out.println(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
    }
}

//comment for commit 1234 px1 123 5 6 dasdasdas dasd789выфвdasdasdы898 dasвфывыфвd 123 fsmjkljdsaddfsdf dsfdasdasdasfasdfadssdfsfddsasfsdffsdf fsd das fsdfsd dasfddassf fsfsdf dsada dadasd gdfg dsadasd dasdasd