package scrap;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author User
 */
public class Scrap {

    public static void main(String[] args) {
        int s = 0;
        String[] link = new String[200];
        String[] jpg = new String[200];
        String[] combo = new String[400];
        try {

            URL url = new URL("http://hamrobazaar.com/c31-mobile-and-accessories-handsets");
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = " ";
            StringBuilder content = new StringBuilder();

            while ((line = reader.readLine()) != null) {

                content.append(line).append("\n");
            }

            reader.close();

            String regEx = "<div class(.*?).html\\\"> <img src='(.*?)_small(.*?)' width(.*?)</a>\\s*(.*?)</center>\\s*</td>\\s*(.*?).html\">\\s*(.*?)>\\s*(.*?)\">\\s*<font class=\"txt\"><b>(.*?)</b>(.*?)color(.*?)</font>\\s*(.*?)</tr>\\s*(.*?)cols(.*?)>\\s*(.*?)</font>\\s*(.*?)</tr>\\s*<tr>\\s*(.*?)</td>\\s*(.*?)<tr >\\s*(.*?)</td>\\s*(.*?)</td>\\s*(.*?)<b>(.*?)</b>";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                s++;
                System.out.print(s + "." + matcher.group(9) + "=======================================>");
                System.out.println(matcher.group(22));

                link[s] = matcher.group(2);
                jpg[s] = matcher.group(3);

                combo[s] = link[s] + jpg[s];

                URL u = new URL(combo[s]);

                URLConnection con = u.openConnection();

                InputStream is = con.getInputStream();
                FileOutputStream fos = new FileOutputStream("C:\\Users\\User\\Desktop\\Pictures\\" + s + ".jpg ");
                byte[] data = new byte[1024 * 5];
                int i = 0;
                while ((i = is.read(data)) != -1) {
                    fos.write(data, 0, i);
                }

            }

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}
