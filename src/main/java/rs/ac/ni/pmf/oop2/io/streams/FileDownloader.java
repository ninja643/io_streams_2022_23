package rs.ac.ni.pmf.oop2.io.streams;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloader
{
    private static final int BUFFER_SIZE = 8 * 1024;

    private int getFileSize(final String address) throws IOException
    {
        final URL url = new URL(address);
        final URLConnection connection = url.openConnection();

        if (connection instanceof HttpURLConnection)
        {
            ((HttpURLConnection) connection).setRequestMethod("HEAD");
        }

        connection.connect();

        return connection.getContentLength();
    }

    public void download(final String address, final String outputFileName) throws IOException
    {
        final int fileSize = getFileSize(address);
        System.out.println(fileSize);

        int downloadedBytes = 0;
        byte[] buffer = new byte[BUFFER_SIZE];

        final URL url = new URL(address);
        System.out.println("Downloading...");
        try (final InputStream inputStream = url.openStream();
             final OutputStream outputStream = new FileOutputStream(outputFileName);
             final OutputStream tempStream = new FileOutputStream("temp.dat"))
        {
//            inputStream.mark(1000000);

            int totalBytesRead = 0;
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer, 0, bytesRead);

                totalBytesRead += bytesRead;
                System.out.printf("(%d / %d)\n", totalBytesRead, fileSize);
            }

//            if (inputStream.markSupported())
//            {
//                inputStream.reset();
//                while ((b = inputStream.read()) != -1)
//                {
//                    tempStream.write(b);
//                }
//            }

        }
        System.out.println("Download finished");
    }
}
