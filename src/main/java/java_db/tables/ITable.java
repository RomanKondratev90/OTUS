package java_db.tables;

import java.util.List;

public interface ITable {
    void create(List<String> columns);
    void delete();
}
