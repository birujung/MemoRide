package com.birujung.memoride_frontend;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private Connection connection;

    private final String host = "ep-holy-dust-952851.ap-southeast-1.aws.neon.tech";
    private final String database = "memoride_oop";
    private final int port = 5432;
    private final String user = "amrita.deviayu";
    private final String pass = "gt8W1HADhdSG";
    private String url = "jdbc:postgresql://ep-holy-dust-952851.ap-southeast-1.aws.neon.tech/memoride_oop?user=amrita.deviayu&password=gt8W1HADhdSG";
    private boolean status;

    public Database()
    {
        this.url = String.format(this.url, this.host, this.port, this.database);
        connect();
        //this.disconnect();
        System.out.println("connection status:" + status);
    }

    private void connect()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    System.out.println("connected:" + status);
                }
                catch (Exception e)
                {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }

    public Connection getExtraConnection()
    {
        Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }
}

