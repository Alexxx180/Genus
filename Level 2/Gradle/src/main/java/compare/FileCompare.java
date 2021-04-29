package compare;

import dfmhph.DMP;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileCompare {

    static Logger log = LogManager.getLogger("FileCompare");

    public ArrayList<String> compare(String fName1, String fName2) throws IOException {

        final String LOG_PATTERN = "%d{yyyy-MM-dd HH:mm:ss} [%-5level] MyApp - %msg%n";
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();

        Layout layout = PatternLayout.createLayout(LOG_PATTERN, null, config, null, null, false, false, null, null);
        Appender appenderInfo = FileAppender.createAppender("Logs/Info.log", "true", "true", "File", "true",
                "false", "false", null, layout, null, "false", null, config);
        Appender appenderDebug = FileAppender.createAppender("Logs/Debug.log", "true", "true", "File2", "true",
                "false", "false", null, layout, null, "false", null, config);
        appenderInfo.start();
        appenderDebug.start();
        config.addAppender(appenderInfo);
        config.addAppender(appenderDebug);
        LoggerConfig loggerConfig = config.getLoggerConfig("compare.FileCompare");
        loggerConfig.addAppender(appenderInfo, Level.INFO, null);
        loggerConfig.addAppender(appenderDebug, Level.DEBUG, null);
        ctx.updateLoggers();

        String line1, line2;
        ArrayList<String> files = new ArrayList<String>();
        log.debug("Comparing procedure has been started.");
        try (BufferedReader br1 = new BufferedReader(new FileReader(fName1)); BufferedReader br2 = new BufferedReader(new FileReader(fName2))) {
            files.add("");
            files.add("");
            while ((line1 = br1.readLine())!=null)
                files.set(0, files.get(0)+line1+"\n");
            while ((line2 = br2.readLine())!=null)
                files.set(1, files.get(1)+line2+"\n");
            DMP dp=new DMP();

            int i=0, j=1;
            String f1 = files.get(0), f2 = files.get(1), pr, pr2;
            ArrayList<String> ops1 = new ArrayList<>();
            ArrayList<String> ops2 = new ArrayList<>();
            ArrayList<String> txt1 = new ArrayList<>();
            ArrayList<String> txt2 = new ArrayList<>();
            for (DMP.Diff df : dp.diff_main(f1, f2, true)) {
                if (df.operation.equals(DMP.Operation.EQUAL))
                    continue;
                if (df.operation.equals(DMP.Operation.DELETE)) {
                    ops1.add("Deleted");
                    txt1.add(df.text.trim());
                }
                else {
                    while (df.text.indexOf("\n")!=-1)
                    {
                        ops2.add("Updated");
                        txt2.add(df.text.substring(0,df.text.indexOf("\n")));
                        df.text = df.text.substring(df.text.indexOf("\n")+1);
                    }
                    ops2.add("Updated");
                    txt2.add(df.text.trim());
                }

            }
            while(f1.indexOf('\n')!=-1) {
                f1=f1.substring(f1.indexOf('\n')+1);
                pr=f1.substring(0, (f1.indexOf('\n')!=-1) ? f1.indexOf('\n') : f1.length());
                while (pr.contains(txt1.get(i)))
                {
                    System.out.println(ops1.get(i)+" "+j+" : "+txt1.get(i));
                    i++;
                    if (i>=txt1.size()) break;
                }
                if (i>=txt1.size()) break;
                j++;
            }
            j=1;
            i=0;
            while(f2.indexOf('\n')!=-1) {
                f2=f2.substring(f2.indexOf('\n')+1);
                pr2=f2.substring(0, (f2.indexOf('\n')!=-1) ? f2.indexOf('\n') : f2.length());
                while (pr2.contains(txt2.get(i)))
                {
                    System.out.println(ops2.get(i)+" "+j+" : "+txt2.get(i));
                    i++;
                    if (i>=txt2.size()) break;
                }
                if (i>=txt2.size()) break;
                j++;
            }
            log.debug("Success.");
        }
        catch (IOException ex) {
            log.error("Encountered problem of reading from file.\nShutdown program...");
            throw new IOException(ex);
        }
        log.debug("Comparing procedure has been ended.");
        return files;
    }
}
