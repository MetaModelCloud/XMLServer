import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import ru.spbu.math.gladiolus.handlers.ServiceHandler;
import ru.spbu.math.gladiolus.handlers.StaticHandler;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;

public class GladiolusServer {
 
    private static final int MIN_THREADS = Runtime.getRuntime().availableProcessors();
    private static final int MAX_THREADS = MIN_THREADS * 10;
    private static final int PORT = 8069;
    private static final String SERVICE_TARGET_PREFIX = "/service";
    private static final String[] AVAILABLE_EXTENSIONS = {"html", "js", "css", "jpg", "ico", "png"};
    private static final String INDEX_FILE = "index.html";
 
        private static final Logger LOG = Logger.getLogger(GladiolusServer.class);
 
        private static GladiolusServer server;
       
    public static void main(String ... args) {
                if (args.length == 1) {
                        startServer();
                } else {
                        GladiolusFrame frame = new GladiolusFrame();
                        final String url = startServer();
                        frame.label.setText(url);
                        frame.setVisible(true);
                }
    }
 
        private static String startServer() {
                server = new GladiolusServer();
                try {
                        server.init();
                        server.start();
                        InetAddress localhost = InetAddress.getLocalHost();
                        final String url = "http://" + localhost.getHostAddress() + ":" + PORT;
                        LOG.info("Gladiolus server started at " + url);
                        return url;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return "";
        }
       
        private static void stopServer() {
                if (server != null && server.jettyServer.isRunning()) {
                        try {
                                server.stop();
                                LOG.info("Gladiolus stopped");
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
        }
 
        private Server jettyServer;
 
    public GladiolusServer() {
        jettyServer = new Server(PORT);
    }
 
    public void init() throws FileNotFoundException {
        QueuedThreadPool queuedThreadPool = new QueuedThreadPool(MAX_THREADS);
                queuedThreadPool.setName("GladiolusServerPool");
        queuedThreadPool.setMinThreads(MIN_THREADS);
 
        jettyServer.setThreadPool(queuedThreadPool);
        jettyServer.setHandler(new HandlerProvider());
        jettyServer.setStopAtShutdown(true);
 
        System.setErr(System.out);
    }
 
    public void start() throws Exception {
        jettyServer.start();
    }
 
    public void stop() throws Exception {
        jettyServer.stop();
    }
 
    public static String getServiceTargetPrefix() {
        return SERVICE_TARGET_PREFIX;
    }
 
    private static class HandlerProvider extends AbstractHandler {
 
        private ServiceHandler serviceHandler;
        private StaticHandler staticHandler;
 
        public HandlerProvider() {
            serviceHandler = new ServiceHandler();
            staticHandler = new StaticHandler(INDEX_FILE, AVAILABLE_EXTENSIONS);
        }
 
        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            if (target.startsWith(SERVICE_TARGET_PREFIX)) {
                serviceHandler.handle(target, baseRequest, request, response);
            } else {
                staticHandler.handle(target, baseRequest, request, response);
            }
        }
    }
 
   private static class GladiolusFrame extends JFrame implements ActionListener {
               
                private static final String START = "Пуск!";
                private static final String STOP = "Стоп";
                private static final String TOOL_TIP_TEXT = "Откройте этот адрес в браузере";
 
                private static final int WIDTH = 300;
                private static final int HEIGHT = 200;
 
                private final JButton startButton;
                private final JButton stopButton;
                private final JLabel label;
               
                public GladiolusFrame() throws HeadlessException {
                        super("GladiolusServer");
                        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        setResizable(false);
 
                        final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        final GraphicsDevice device = env.getDefaultScreenDevice();
                        final DisplayMode displayMode = device.getDisplayMode();
                        final int deviceWidth = displayMode.getWidth();
                        final int deviceHeight = displayMode.getHeight();
 
                        setBounds((deviceWidth - WIDTH) / 2, (deviceHeight - HEIGHT) / 2, WIDTH, HEIGHT);
 
                        startButton = new JButton(START);
                        startButton.addActionListener(this);
                        startButton.setEnabled(false);
 
                        stopButton = new JButton(STOP);
                        stopButton.addActionListener(this);
                        stopButton.setEnabled(true);
 
                        label = new JLabel();
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setToolTipText(TOOL_TIP_TEXT);
 
                        Container contentPane = getContentPane();
                        contentPane.setLayout(new GridLayout(3, 1));
 
                        Container container = new Container();
                        container.setLayout(new GridLayout(1, 4));
 
                        container.add(Box.createVerticalGlue());
                        container.add(startButton);
                        container.add(stopButton);
                        container.add(Box.createVerticalGlue());
 
                        contentPane.add(label);
                        contentPane.add(container);
                        contentPane.add(Box.createVerticalGlue());
 
                        addWindowListener(new WindowListener() {
                                @Override
                                public void windowOpened(WindowEvent e) {}
 
                                @Override
                                public void windowClosing(WindowEvent e) {}
 
                                @Override
                                public void windowClosed(WindowEvent e) {
                                        stopServer();
                                }
 
                                @Override
                                public void windowIconified(WindowEvent e) {}
 
                                @Override
                                public void windowActivated(WindowEvent e) {}
 
                                @Override
                                public void windowDeiconified(WindowEvent e) {}
 
                                @Override
                                public void windowDeactivated(WindowEvent e) {}
                        });
                }
 
                @Override
                public void actionPerformed(ActionEvent e) {
                        if (START.equals(e.getActionCommand())) {
                                startButton.setEnabled(false);
                                final String url = startServer();
                                label.setText(url);
                                stopButton.setEnabled(true);
                        } else if (STOP.equals(e.getActionCommand())) {
                                stopButton.setEnabled(false);
                                stopServer();
                                label.setText("");
                                startButton.setEnabled(true);
                        }
                }
        }
}