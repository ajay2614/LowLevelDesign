package SolidPrinciples;

public class DependencyInversionPrinciple {
}

/**
 * Definition: High-level modules should not depend on low-level modules.
 * Both should depend on abstractions.
 * Why? Tight coupling makes code rigid and hard to change.
 *
 * High-level module: The part of your program that contains important business logic (e.g., a Car class).
 * Low-level module: The part that performs detailed operations (e.g., a PetrolEngine or MySQLDatabase).
 * Abstraction: An interface or abstract class that defines what a module does, not how.
 *
 * Always create an object of interface, never create object for concrete class
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
