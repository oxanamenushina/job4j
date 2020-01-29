package ru.job4j.filedownload;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * FileDownload.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class FileDownload implements Runnable {

    /**
     * Link to the download the file.
     */
    private String link;

    /**
     * The file for output.
     */
    private String output;

    /**
     * The specified download speed.
     */
    private int speed;

    /**
     * Number of bytes read.
     */
    private int bytesRead = 0;

    /**
     * Pause in milliseconds.
     */
    private int pause = 0;

    /**
     * The amount of kilobytes downloaded in 1 second.
     */
    private int sum = 0;

    public FileDownload(String link, String output, int speed) {
        this.link = link;
        this.output = output;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream bis = new BufferedInputStream(new URL(this.link).openStream());
             FileOutputStream fos = new FileOutputStream(this.output)) {
            byte[] dataBuffer = new byte[1024];
            while ((this.bytesRead = bis.read(dataBuffer, 0, 1024)) != -1) {
                fos.write(dataBuffer, 0, this.bytesRead);
                this.sum++;
                if (this.pause > 0) {
                    try {
                        Thread.sleep(this.pause);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method returns number of bytes read.
     */
    public int getBytesRead() {
        return this.bytesRead;
    }

    /**
     * The method calculates pause.
     */
    public void calculatePause() {
        if (this.sum > this.speed) {
            this.pause = 1000 / this.sum - 1000 / this.speed;
        }
        this.sum = 0;
    }

    public static void main(String[] args) {
        FileDownload fd = new FileDownload(
                "https://skachat.site/data/t/the-doors/the-doors-people-are-strange-amerikanskie-hity-50-60-godov.mp3",
                "c:\\Download\\music\\People_are_strange.mp3", 200);
        Thread t1 = new Thread(fd);
        t1.start();
        while (fd.getBytesRead() != -1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            fd.calculatePause();
        }
    }
}