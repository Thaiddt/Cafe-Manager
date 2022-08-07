package cafeteria.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import java.util.Locale;

public class OclockHelper extends Thread {

    private static JLabel lblOclock;
    private static final Locale vietNam = new Locale("vi","vn");
    private static SimpleDateFormat simpleDate = new SimpleDateFormat("EEEE dd-MM-yyyy HH:mm:ss", vietNam);
    private static Thread oclockThread;

    private static void createThreadOclock() {
        oclockThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    lblOclock.setText(simpleDate.format(new Date()));
                }
            }

        };
        oclockThread.start();
    }

    public static void startOclock(JLabel ocl) {
        if (ocl == null) {
            lblOclock = new JLabel();
        } else {
            lblOclock = ocl;
        }
        
        if (oclockThread == null) {
            createThreadOclock();
        }
    }
    
}
