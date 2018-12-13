import java.applet.Applet;
import java.awt.*;

public class Test extends Applet {
    public Test() throws HeadlessException {
        super();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    public void paint(Graphics g)
    {
        g.drawLine(0, 0, 40, 60);

    }
}
