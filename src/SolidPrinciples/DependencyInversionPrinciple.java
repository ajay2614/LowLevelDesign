package SolidPrinciples;

public class DependencyInversionPrinciple {
}

/**
 * Definition: High-level modules should not depend on low-level modules.
 * Both should depend on abstractions.
 * Why? Tight coupling makes code rigid and hard to change.
 */

class MySQLDatabse {
    void connect() { /* Connects to MySQL */ }
}
class DataServ {
    private MySQLDatabse db = new MySQLDatabse();  // Tight coupling
    void fetchData() { db.connect(); }
}

interface Database {
    void connect();
}
class MySQLDatabase implements Database {
    public void connect() { /* Connects to MySQL */ }
}
class DataService {
    private Database db;
    DataService(Database db) { this.db = db; }  // Inject dependency
    void fetchData() { db.connect(); }
}
