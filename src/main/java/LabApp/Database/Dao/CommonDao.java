package LabApp.Database.Dao;

import LabApp.Database.DBUtils.DBMenager;
import LabApp.Database.Models.BaseModel;
import LabApp.Utils.Exceptions.ApplicationExeptions;
import LabApp.Utils.FXMLUtils;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by MEDM on 2017-06-04.
 */
public abstract class CommonDao {

    //LOGGER
    public static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);

    //AKTUALNE POŁĄCZENIE DO BAZY DANYCH
    protected final ConnectionSource connectionSource;

    public CommonDao() {
        this.connectionSource = DBMenager.getConnectionSource();
    }

    public <T extends BaseModel, I> void creatOrUpdate(BaseModel baseModel) throws ApplicationExeptions {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationExeptions(FXMLUtils.getResourceBundle().getString("error.create.update"));
        } finally {
            this.closeDBConnection();
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws ApplicationExeptions {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationExeptions(FXMLUtils.getResourceBundle().getString("error.refresh"));
        } finally {
            this.closeDBConnection();
        }
    }

    public <T extends BaseModel, I> void delete(BaseModel baseModel) throws ApplicationExeptions {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationExeptions(FXMLUtils.getResourceBundle().getString("error.delete"));
        }  finally {
            this.closeDBConnection();
        }
    }

    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer ID) throws ApplicationExeptions {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) ID);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new ApplicationExeptions(FXMLUtils.getResourceBundle().getString("error.delete"));
        }
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer ID) throws ApplicationExeptions {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) ID);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationExeptions(FXMLUtils.getResourceBundle().getString("error.not.found"));
        }  finally {
            this.closeDBConnection();
        }
    }

    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws ApplicationExeptions {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationExeptions(FXMLUtils.getResourceBundle().getString("error.not.found.all"));
        }  finally {
            this.closeDBConnection();
        }
    }

    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) throws ApplicationExeptions {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationExeptions(FXMLUtils.getResourceBundle().getString("error.get.dao"));
        }  finally {
            this.closeDBConnection();
        }
    }

    private void closeDBConnection() throws ApplicationExeptions {
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationExeptions(FXMLUtils.getResourceBundle().getString("error.get.dao"));
        }
    }
}
