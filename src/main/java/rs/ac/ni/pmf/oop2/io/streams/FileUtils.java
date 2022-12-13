package rs.ac.ni.pmf.oop2.io.streams;

import java.io.*;

public class FileUtils
{
    public static void copyFile(final String fileName, final String destination) throws IOException
    {
        try (final InputStream is = new FileInputStream(fileName);
             final OutputStream os = new FileOutputStream(destination))
        {
            int b;

            while ((b = is.read()) != -1)
            {
                os.write(b);
            }
        }
    }

    private static void createFile(final OutputStream os) throws IOException
    {
        System.out.println("Starting...");
        final long start = System.currentTimeMillis();

        for (int i = 0; i < 20000000; i++)
        {
            os.write(i);
        }

        final long end = System.currentTimeMillis();

        System.out.printf("Total time: %d\n", end - start);
    }

    public static void createBigFile(final String fileName) throws IOException
    {
        try (final FileOutputStream fos = new FileOutputStream(fileName))
        {
            createFile(fos);
        }
        finally
        {
            System.out.println("Execute finally block");
        }
    }

    public static void createBigFileBuffered(final String fileName) throws IOException
    {
        try (final BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(fileName)))
        {
            createFile(fos);
        }
        finally
        {
            System.out.println("Execute finally block");
        }
    }
}
