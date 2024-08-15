package ru.otus.objectpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sergey created on 19.09.18.
 */
class ConnectionObjectFactory implements ObjectFactory<Connection> {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionObjectFactory.class);

    @Override
    public Connection create() {
        logger.info("Create new Connection");
        return new ConnectionPostgres();
    }
}
