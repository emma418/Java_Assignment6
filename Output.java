import javax.swing.*;
import java.awt.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Output extends JFrame {
    JTextArea jta = new JTextArea();

    public Output() {
        setLayout(new BorderLayout());
        add(new JScrollPane(jta), BorderLayout.CENTER);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);
        this.setTitle("Concurrent Output");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        Thread thread1 = new PrintChar('b', 99, jta);
        Thread thread2 = new PrintChar('a', 99, jta);
        Thread thread3 = new PrintNum(101, jta);
        thread1.setPriority(10);
        thread1.start();
        thread2.start();
        thread3.start();


    }
    public static void main(String[] args) {
        new Output();
    }
}
class PrintChar extends Thread {
    private char charToPrint;
    private int times;
    private JTextArea output;

    public PrintChar(char c, int t, JTextArea output) {

        charToPrint = c;
        times = t;
        this.output = output;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            synchronized (output) {
                output.append(charToPrint + " ");
            }

        }
        //Thread.yield();
    }
}
class PrintNum extends Thread {
    private int lastNum;
    private JTextArea output;

    public PrintNum(int n, JTextArea output)
    {
        lastNum = n;
        this.output = output;
    }

    @Override
    public void run() {
        for (int i = 1; i < lastNum; i++) {
            synchronized (output) {
                output.append(i + " ");
            }

        }
    }

}
