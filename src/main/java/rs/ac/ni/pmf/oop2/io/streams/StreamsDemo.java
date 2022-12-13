package rs.ac.ni.pmf.oop2.io.streams;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class StreamsDemo
{

    public static void main(String[] args) throws IOException
    {
//        FileUtils.createBigFile("numbers.dat");
//        FileUtils.createBigFileBuffered("numbers_buffered.dat");
        new FileDownloader().download("https://nasport.pmf.ni.ac.rs/materijali/2755/l01.pdf", "downloads/l01.pdf");
    }

    public static void main3(String[] args) throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("brojevi.txt")))
        {
            String text = "";

            String line;
            while ((line = reader.readLine()) != null)
            {
                text += line;
            }

            System.out.println(text);
        }
    }

    public static void main2(String[] args) throws IOException
    {
        try (final OutputStream fos = new FileOutputStream("brojevi.dat"))
        {
            for (int i = 256; i <= 266; i++)
            {
                fos.write(i);
            }
        }
    }

    public static void main1(String[] args)
    {
        try
        {
            FileUtils.copyFile("L03.pdf", "copy_of_L03.pdf");
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("General I/O error: " + e.getMessage());
        }
    }
}
