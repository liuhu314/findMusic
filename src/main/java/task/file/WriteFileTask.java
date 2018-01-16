package task.file;

import thread.PoolType;

import java.io.*;

/**
 * Created by liuhu on 18/1/10.
 */
public class WriteFileTask implements Runnable{

    private BufferedInputStream inputStream;
    private BufferedOutputStream outputStream;
    private File file;
    private byte[] buffer;
    private static final String taskType = PoolType.filetype;

    public WriteFileTask(String path, String name,InputStream stream,int bufferSize) throws IOException {

        file = new File(path,name);

        if(file != null)
        {
            file.createNewFile();
        }

        outputStream = new BufferedOutputStream(new FileOutputStream(file));
        inputStream = new BufferedInputStream(stream);

        buffer = new byte[bufferSize];


    }
    @Override
    public void run() {

        int writeSize;

        try {
            while ((writeSize = inputStream.read(buffer) )!= -1)
            {
                outputStream.write(buffer,0,writeSize);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
