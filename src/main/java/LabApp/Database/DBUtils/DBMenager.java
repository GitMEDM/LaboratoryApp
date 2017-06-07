package LabApp.Database.DBUtils;

import LabApp.Database.Models.Issue;
import LabApp.Database.Models.Subject;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by MEDM on 2017-06-04.
 */
public class DBMenager {

    //LOGGER
    public static final Logger LOGGER = LoggerFactory.getLogger(DBMenager.class);

    //STEROWNIK DO BAZY DANYCH H2
    //private static final String JDBC_DRIVER_H2 = "jdbc:h2:./LaboratoryDB";
    private static final String JDBC_DRIVER_SQLITE = "jdbc:sqlite:LaboratoryDB";

    //DANE LOGOWANIA DO BAZY DANYCH
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    //AKTUALNE POŁĄCZENIE DO BAZY DANYCH
    private static ConnectionSource connectionSource;

    //INICJALIZACJA BAZY DANYCH
    public static void initDatabase(){
        //METODA NARZĘDZIOWA - PODCZAS TWORZENIA APLIKACJI
        createConnectionSource();
        dropTable();
        createTable();
        closeConnectionSource();
    }

    //TWORZENIE POŁĄCZENIA
    private static void createConnectionSource(){
        try {
            //connectionSource = new JdbcConnectionSource(JDBC_DRIVER_SQLITE, USER, PASSWORD);
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_SQLITE);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    //POBIERANIE POŁĄCZENIA
    public static ConnectionSource getConnectionSource(){
        if(connectionSource == null){
            createConnectionSource();
        }
        return connectionSource;
    }

    //ZAMYKANIE POŁĄCZENIA
    public static void closeConnectionSource(){
        if(connectionSource != null){
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    //TWORZENIE TABEL
    private static void createTable(){
        try {
            TableUtils.createTableIfNotExists(connectionSource, Subject.class);
            TableUtils.createTableIfNotExists(connectionSource, Issue.class);
            //TableUtils.createTableIfNotExists(connectionSource, Code.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    //USUWANIE TABEL
    private static void dropTable(){
        try {
            TableUtils.dropTable(connectionSource, Subject.class, true);
            TableUtils.dropTable(connectionSource, Issue.class, true);
            //TableUtils.dropTable(connectionSource, Code.class, true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
