USE [OneHomeD]
GO

SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'PropertyUser'
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'PropertyUser') 
BEGIN 
PRINT 'Table Exists'; 
DROP TABLE PropertyUser;
END

SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Service'
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Service') 
BEGIN 
PRINT 'Table Exists'; 
DROP TABLE [Service];
END

SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Expense'
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Expense') 
BEGIN 
PRINT 'Table Exists'; 
DROP TABLE Expense;
END

SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'ExpenseCategory'
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'ExpenseCategory') 
BEGIN 
PRINT 'Table Exists'; 
DROP TABLE ExpenseCategory;
END

SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Warranty'
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Warranty') 
BEGIN 
PRINT 'Table Exists'; 
DROP TABLE Warranty;
END

SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Appliance'
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Appliance') 
BEGIN 
PRINT 'Table Exists'; 
DROP TABLE Appliance;
END

SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Vendor'
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Vendor') 
BEGIN 
PRINT 'Table Exists'; 
DROP TABLE Vendor;
END

SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Users'
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Users') 
BEGIN 
PRINT 'Table Exists'; 
DROP TABLE Users;
END

SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Properties'
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Properties') 
BEGIN 
PRINT 'Table Exists'; 
DROP TABLE Properties;
END

Create table Properties(
	ID int IDENTITY(1,1) not null PRIMARY KEY,
	Title varchar(100) null,
	Address1 varchar(100) not null,
	Address2 varchar(100) null,
	City varchar(100) not null,
	[State] varchar(2) not null,
	Zip varchar(10) not null,
	Country varchar(100) Not Null,
	IsPrimaryResidence bit not null default 'TRUE',
	Size int null,
	CurrentResident varchar(100) null,
	MoveInDate date null,
	MovedOutDate date null)

Create table Users(
	ID int IDENTITY(1,1) not null PRIMARY KEY, 
	FirstName varchar(50) not null,
	LastName varchar(50) not null,
	Email varchar(50) not null,
	UserPassword varchar(50) not null,
	CreatedOn date not null,
	CreatedBy varchar(50) not null)

CREATE TABLE Vendor(
	ID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	VendorName varchar(100) NOT NULL,
	ContactName varchar(100),
	Phone varchar(15), 
	WebURL varchar(100),
	Notes varchar(100) NOT NULL)

CREATE TABLE Appliance(
	ID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	PropertyID int NOT NULL FOREIGN KEY REFERENCES Properties(ID),
	VendorID int NOT NULL FOREIGN KEY REFERENCES Vendor(ID),
	ApplianceDescription varchar(50) NOT NULL,
	Manufacturer varchar(20),
	Model varchar(20),
	SerialNumber varchar(30),
	PurchaseDate date,
	CreatedBy varchar(50) not null,
	CreatedOn date not null,
	UpdatedBy varchar(50),
	UpdateOn date)

CREATE TABLE Warranty(
	ID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	ApplianceID int NOT NULL FOREIGN KEY REFERENCES Appliance(ID),
	WarrantyStart date NOT NULL,
	WarrantyEnd date NOT NULL,
	WarrantyContact varchar(50)) 

CREATE TABLE ExpenseCategory(
	ID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	Category varchar(30) NOT NULL)

CREATE TABLE Expense(
	ID int IDENTITY(1,1) NOT NULL Primary Key, 
	PropertyID int NOT NUll FOREIGN KEY REFERENCES Properties(ID),
	VendorID int FOREIGN KEY REFERENCES Vendor(ID),
	ExpenseCategoryID int NOT NULL FOREIGN KEY REFERENCES ExpenseCategory(ID), 
	Amount smallmoney Not NULL,
	DateofExpense date NOT NULL,
	IsRecurring bit default 'FALSE',
	Notes varchar(1000),
	CreatedOn date NULL,
	CreatedBy varchar(50) NULL)

CREATE TABLE [Service](
	ID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	PropertyID int NOT NUll FOREIGN KEY REFERENCES Properties(ID),
	ApplianceID int NULL FOREIGN KEY REFERENCES Appliance(ID),
	ServiceDescription varchar(300) NOT NULL,
	Cost smallmoney NOT NULL,
	IsFinanced bit default 'FALSE',
	ContractorName varchar(100) NOT NULL,
	ContractorPhone varchar(100) NOT NULL,
	DateT date NOT NULL,
	CreatedOn date NULL,
	CreatedBy varchar(100) NULL)

Create Table PropertyUser(
	PropertyID int not null,
	UserID int not null)



