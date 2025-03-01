package SolidPrinciples;

public class SingleResponsibilityPrinciple {

}

/**
 S - Single Responsibility Principle (SRP)
 Definition: A class should have only one reason to change,
 meaning it should have only one responsibility.
 Why?
 If a class has multiple responsibilities,
 changes to one function could impact other unrelated functionalities.
 */

//Bad Approach
class Save {
    void SaveInFile() { /* Saves in File */ }
    void saveToDB() { /* Saves in DB */ }
}


//Good Approach
class SaveToDb {
    void saveToDB() { /* Saves in DB */ }  //
}

class SaveToFile {
    void SaveInFile() { /* Saves in File */ }
}

