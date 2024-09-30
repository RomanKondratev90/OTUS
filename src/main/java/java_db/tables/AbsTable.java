package java_db.tables;

import java_db.db.IDBConnect;
import java_db.db.MySQLConnect;

public abstract class AbsTable {
    protected IDBConnect dbConnector;

    public AbsTable(IDBConnect dbConnector, String name) {
        this.dbConnector = dbConnector;
        this.dbConnector.execute(String.format("CREATE TABLE IF NOT EXISTS %s (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "color VARCHAR(255), " +
                "name VARCHAR(255), " +
                "weight INT, " +
                "type VARCHAR(255), " +
                "age INT);", name));
    }
}
