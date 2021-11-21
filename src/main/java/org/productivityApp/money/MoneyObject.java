package org.productivityApp.money;

import java.time.LocalDate;
import java.util.ArrayList;

public class MoneyObject {

    private double netMoney;
    private ArrayList<MoneyInObject> moneyInSources;
    private ArrayList<MoneyOutObject> moneyOutSources;
    private double availableFunds;
    private ArrayList<Transaction> transactions;

    public MoneyObject() {

        this.netMoney = 0;
        this.moneyInSources = new ArrayList<>();
        this.moneyOutSources = new ArrayList<>();
        this.availableFunds = 0;
        this.transactions = new ArrayList<>();
    }

    public MoneyObject(double netMoney, ArrayList<MoneyInObject> moneyInSources, ArrayList<MoneyOutObject> moneyOutSources, double availableFunds, ArrayList<Transaction> transactions) {

        this.netMoney = netMoney;
        this.moneyInSources = moneyInSources;
        this.moneyOutSources = moneyOutSources;
        this.availableFunds = availableFunds;
        this.transactions = transactions;
    }

    // copy constructor
    public MoneyObject(MoneyObject moneyObject) {

        this.netMoney = moneyObject.getNetMoney();
        this.moneyInSources = new ArrayList<>(moneyObject.getMoneyInSources());
        this.moneyOutSources = new ArrayList<>(moneyObject.getMoneyOutSources());
        this.availableFunds = moneyObject.getAvailableFunds();
        this.transactions = new ArrayList<>(moneyObject.getTransactions());
    }

    // setters
    public void setNetMoney(double netMoney) { this.netMoney = netMoney; }
    public void setMoneyInSources(ArrayList<MoneyInObject> moneyInSources) { this.moneyInSources = moneyInSources; }
    public void setMoneyInSource(int index, MoneyInObject moneyInObject) { this.moneyInSources.set(index, moneyInObject); }
    public void setMoneyInSource(int index, String description, double value) {
        MoneyInObject newMoneyInObject = new MoneyInObject(description, value);
        this.moneyInSources.set(index, newMoneyInObject);
    }
    public void addMoneyInSource(MoneyInObject moneyInObject) { this.moneyInSources.add(moneyInObject); }
    public void addMoneyInSource(String description, double value) {
        MoneyInObject newObject = new MoneyInObject(description, value);
        this.moneyInSources.add(newObject);
    }
    public void setMoneyOutSources(ArrayList<MoneyOutObject> moneyOutSources) { this.moneyOutSources = moneyOutSources; }
    public void setMoneyOutSource(int index, MoneyOutObject moneyOutObject) { this.moneyOutSources.set(index, moneyOutObject); }
    public void setMoneyOutSource(int index, String description, double value) {
        MoneyOutObject newMoneyOutObject = new MoneyOutObject(description, value);
        this.moneyOutSources.set(index, newMoneyOutObject);
    }
    public void addMoneyOutSource(MoneyOutObject moneyOutObject) { this.moneyOutSources.add(moneyOutObject); }
    public void addMoneyOutSource(String description, double value) {
        MoneyOutObject newObject = new MoneyOutObject(description, value);
        this.moneyOutSources.add(newObject);
    }
    public void setAvailableFunds(double availableFunds) { this.availableFunds = availableFunds; }
    public void setTransactions(ArrayList<Transaction> transactions) { this.transactions = transactions; }
    public void setTransaction(int index, Transaction transaction) { this.transactions.set(index, transaction); }
    public void setTransaction(int index, LocalDate date, String description, double value) {
        Transaction newTransaction = new Transaction(date, description, value);
        transactions.set(index, newTransaction);
    }
    public void addTransaction(Transaction transaction) { this.transactions.add(transaction); }
    public void addTransaction(LocalDate date, String description, double value) {
        Transaction newTransaction = new Transaction(date, description, value);
        this.transactions.add(newTransaction);
    }

    // getters
    public double getNetMoney() { return this.netMoney; }
    public ArrayList<MoneyInObject> getMoneyInSources() { return this.moneyInSources; }
    public MoneyInObject getMoneyInSource(int index) { return this.moneyInSources.get(index); }
    public ArrayList<MoneyOutObject> getMoneyOutSources() { return this.moneyOutSources; }
    public MoneyOutObject getMoneyOutSource(int index) { return this.moneyOutSources.get(index); }
    public double getAvailableFunds() { return this.availableFunds; }
    public ArrayList<Transaction> getTransactions() { return this.transactions; }
    public Transaction getTransaction(int index) { return this.transactions.get(index); }

    // modifiers
    public void removeMoneyInSource(int index) { moneyInSources.remove(index); }
    public void removeMoneyInSource(MoneyInObject obj) { moneyInSources.remove(obj); }
    public void removeMoneyOutSource(int index) { moneyOutSources.remove(index); }
    public void removeMoneyOutSource(MoneyOutObject obj) { moneyOutSources.remove(obj); }
    public void removeTransaction(int index) { transactions.remove(index); }
    public void removeTransaction(Transaction transaction) { transactions.remove(transaction); }


    public class MoneyInObject {

        private String description;
        private double value;

        public MoneyInObject(String description, double value) {
            this.description = description;
            this.value = value;
        }

        // setters
        public void setDescription(String description) { this.description = description; }
        public void setValue(double value) { this.value = value; }

        // getters
        public String getDescription() { return this.description; }
        public double getValue() { return this.value; }

        @Override
        public String toString() {
            return this.description + " : + $" + Double.toString(this.value);
        }
    }

    public class MoneyOutObject {

        private String description;
        private double value;

        public MoneyOutObject(String description, double value) {
            this.description = description;
            this.value = value;
        }

        // setters
        public void setDescription(String description) { this.description = description; }
        public void setValue(double value) { this.value = value; }

        // getters
        public String getDescription() { return this.description; }
        public double getValue() { return this.value; }

        @Override
        public String toString() {
            return this.description + " : - $" + Double.toString(this.value);
        }
    }

    public class Transaction {

        private LocalDate date;
        private String description;
        private double value;

        public Transaction(LocalDate date, String description, double value) {
            this.date = date;
            this.description = description;
            this.value = value;
        }

        // setters
        public void setDate(LocalDate date) { this.date = date; }
        public void setDescription(String description) { this.description = description; }
        public void setValue(double value) { this.value = value; }

        // getters
        public LocalDate getDate() { return this.date; }
        public String getDescription() { return this.description; }
        public double getValue() { return this.value; }
    }
}
