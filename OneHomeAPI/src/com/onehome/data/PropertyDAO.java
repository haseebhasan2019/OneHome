package com.onehome.data;

import com.onehome.model.Appliance;
import com.onehome.model.Property;
import com.onehome.model.Service;
import com.onehome.model.Users;

import java.sql.*;
import java.util.*;

public class PropertyDAO {
    Connection connection;
    Statement statement;
    String url = "jdbc:sqlserver://T450;databaseName=OneHomeDB;user=sa;password=adpadp10";

    public void establishConnection() {
        try {
            // Establish Connection
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to Microsoft SQL Server");
        } catch (SQLException e) {
            System.out.println("There was an error connecting to Microsoft SQL Server");
            e.printStackTrace();
        }
    }

    // crud - Create(save) Read(get) Update(edit) Delete(deletes a row in table)

    public void saveProperty(Property p) throws SQLException {
        // SQL Query
        String sql = "INSERT INTO Property ([Title],[Address1],[Address2],[City],[State],[Zip],[Country],[IsPrimaryResidence],[Size],[CurrentResident],[MoveInDate],[MovedOutDate],[CreatedBy]) VALUES (";

        sql += "'" + p.getTitle() + "', ";
        sql += "'" + p.getAddress1() + "', ";

        if (p.getAddress2() == null || p.getAddress2().equals("null"))
            sql += "null, ";
        else
            sql += "'" + p.getAddress2() + "', ";

        sql += "'" + p.getCity() + "', ";
        sql += "'" + p.getState() + "', ";
        sql += "'" + p.getZip() + "', ";
        sql += "'" + p.getCountry() + "', ";
        sql += "'" + p.isPrimaryResidence() + "', ";

        if (p.getSize() == null || p.getSize().equals("null"))
            sql += "null, ";
        else
            sql += p.getSize() + ", ";

        if (p.getCurrentResident() == null || p.getCurrentResident().equals("null"))
            sql += "null, ";
        else
            sql += "'" + p.getCurrentResident() + "', ";

        if (p.getMovedInDate() == null || p.getMovedInDate().equals("null"))
            sql += "null, ";
        else
            sql += "'" + p.getMovedInDate() + "', ";

        if (p.getMovedOutDate() == null || p.getMovedOutDate().equals("null"))
            sql += "null, ";
        else
            sql += "'" + p.getMovedOutDate() + "', ";

        if (p.getCreatedBy() == null || p.getCreatedBy().equals("null"))
            sql += "null)";
        else
            sql += "'" + p.getCreatedBy() + "')";

        System.out.println("Executing: " + sql);
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public Property getLatestProperty() throws SQLException {
        Property p = new Property();
        // Create the statement object to connect
        statement = connection.createStatement();
        // Execute the statement and store query data
        String sql = "SELECT * FROM Property WHERE ID = (SELECT max(id) FROM Property)";
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            p.setId(result.getInt(DbConstants.PROPERTY_ID));
            p.setTitle(result.getString(DbConstants.PROPERTY_TITLE));
            p.setAddress1(result.getString(DbConstants.PROPERTY_ADDRESS1));
            p.setAddress2(result.getString(DbConstants.PROPERTY_ADDRESS2));
            p.setCity(result.getString(DbConstants.PROPERTY_CITY));
            p.setState(result.getString(DbConstants.PROPERTY_STATE));
            p.setZip(result.getString(DbConstants.PROPERTY_ZIP));
            p.setCountry(result.getString(DbConstants.PROPERTY_COUNTRY));
            p.setPrimaryResidence(result.getBoolean(DbConstants.PROPERTY_PRIMARYRESIDENCE));
            p.setSize(result.getString(DbConstants.PROPERTY_SIZE));
            p.setCurrentResident(result.getString(DbConstants.PROPERTY_CURRENTRESIDENT));
            p.setMovedInDate(result.getString(DbConstants.PROPERTY_MOVEINDATE));
            p.setMovedOutDate(result.getString(DbConstants.PROPERTY_MOVEOUTDATE));
            p.setCreatedOn(result.getString(DbConstants.PROPERTY_CREATEDON));
            p.setCreatedBy(result.getString(DbConstants.PROPERTY_CREATEDBY));
            p.setUpdatedOn(result.getString(DbConstants.PROPERTY_UPDATEDON));
            p.setUpdatedBy(result.getString(DbConstants.PROPERTY_UPDATEDBY));
        }
        return p;
    }

    public void savePropertyUser(int id) throws SQLException {
        Property p = getLatestProperty();
        String sql = "INSERT INTO PropertyUser ([PropertyID],[UserID]) VALUES (";
        sql += "" + p.getId() + ", "; 
        sql += "" + id + ")"; 
        System.out.println("Executing: " + sql);
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
    public ArrayList<Property> getAllProperties() throws SQLException
    {
        ArrayList<Property> properties = new ArrayList<>();
        statement = connection.createStatement();
        String sql = "SELECT * FROM Property";
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Property p = new Property();
            p.setId(result.getInt(DbConstants.PROPERTY_ID));
            p.setTitle(result.getString(DbConstants.PROPERTY_TITLE));
            p.setAddress1(result.getString(DbConstants.PROPERTY_ADDRESS1));
            p.setAddress2(result.getString(DbConstants.PROPERTY_ADDRESS2));
            p.setCity(result.getString(DbConstants.PROPERTY_CITY));
            p.setState(result.getString(DbConstants.PROPERTY_STATE));
            p.setZip(result.getString(DbConstants.PROPERTY_ZIP));
            p.setCountry(result.getString(DbConstants.PROPERTY_COUNTRY));
            p.setPrimaryResidence(result.getBoolean(DbConstants.PROPERTY_PRIMARYRESIDENCE));
            p.setSize(result.getString(DbConstants.PROPERTY_SIZE));
            p.setCurrentResident(result.getString(DbConstants.PROPERTY_CURRENTRESIDENT));
            p.setMovedInDate(result.getString(DbConstants.PROPERTY_MOVEINDATE));
            p.setMovedOutDate(result.getString(DbConstants.PROPERTY_MOVEOUTDATE));
            p.setCreatedOn(result.getString(DbConstants.PROPERTY_CREATEDON));
            p.setCreatedBy(result.getString(DbConstants.PROPERTY_CREATEDBY));
            p.setUpdatedOn(result.getString(DbConstants.PROPERTY_UPDATEDON));
            p.setUpdatedBy(result.getString(DbConstants.PROPERTY_UPDATEDBY));
            properties.add(p);
        }
        return properties;
    }

    public ArrayList<Appliance> getPropertyAppliances(int id) throws SQLException
    {
        ArrayList<Appliance> appliances = new ArrayList<>();
        statement = connection.createStatement();
        String sql = "SELECT A.*  From Appliance A JOIN Property P ON P.ID = A.PropertyID WHERE A.PropertyID = " + id;
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Appliance a = new Appliance();
            a.setId(result.getInt(DbConstants.APPLIANCE_ID));
            a.setPropertyId(result.getString(DbConstants.APPLIANCE_PROPERTYID));
            a.setVendorId(result.getString(DbConstants.APPLIANCE_VENDORID));
            a.setApplianceDescription(result.getString(DbConstants.APPLIANCE_DESCRIPTION));
            a.setManufacturer(result.getString(DbConstants.APPLIANCE_MANUFACTURER));
            a.setModel(result.getString(DbConstants.APPLIANCE_MODEL));
            a.setSerialNumber(result.getString(DbConstants.APPLIANCE_SERIALNUMBER));
            a.setPurchaseDate(result.getString(DbConstants.APPLIANCE_PURCHASEDATE));
            a.setCreatedOn(result.getString(DbConstants.APPLIANCE_CREATEDON));
            a.setCreatedBy(result.getString(DbConstants.APPLIANCE_CREATEDBY));
            a.setUpdatedOn(result.getString(DbConstants.APPLIANCE_UPDATEDON));
            a.setUpdatedBy(result.getString(DbConstants.APPLIANCE_UPDATEDBY));
            appliances.add(a);
        }
        return appliances;
    }
    
    public ArrayList<Service> getPropertyServices(int id) throws SQLException
    {
        ArrayList<Service> services = new ArrayList<>();
        statement = connection.createStatement();
        String sql = "SELECT S.* From Service S JOIN Property P ON  P.ID = S.PropertyID WHERE S.PropertyID = " + id;
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Service s = new Service();
            s.setId(result.getInt(DbConstants.SERVICE_ID));
            s.setPropertyId(result.getString(DbConstants.SERVICE_PROPERTYID));
            s.setApplianceId(result.getString(DbConstants.SERVICE_APPLIANCEID));
            s.setServiceDescription(result.getString(DbConstants.SERVICE_SERVICEDESCRIPTION));
            s.setCost(result.getString(DbConstants.SERVICE_COST));
            s.setFinanced(result.getBoolean(DbConstants.SERVICE_ISFINANCED));
            s.setContractorName(result.getString(DbConstants.SERVICE_CONTRACTORNAME));
            s.setContractorPhone(result.getString(DbConstants.SERVICE_CONTRACTORPHONE));
            s.setDateT(result.getString(DbConstants.SERVICE_DATET));
            s.setCreatedOn(result.getString(DbConstants.SERVICE_CREATEDON));
            s.setCreatedBy(result.getString(DbConstants.SERVICE_CREATEDBY));
            services.add(s);
        }
        return services;
    }

    public Property getPropertyById(int id) throws SQLException
    {
        Property p = new Property();
        //Create the statement object to connect
        statement = connection.createStatement();
        //Execute the statement and store query data
        String sql = "SELECT * FROM Property WHERE ID = " + id;
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            p.setId(result.getInt(DbConstants.PROPERTY_ID));
            p.setTitle(result.getString(DbConstants.PROPERTY_TITLE));
            p.setAddress1(result.getString(DbConstants.PROPERTY_ADDRESS1));
            p.setAddress2(result.getString(DbConstants.PROPERTY_ADDRESS2));
            p.setCity(result.getString(DbConstants.PROPERTY_CITY));
            p.setState(result.getString(DbConstants.PROPERTY_STATE));
            p.setZip(result.getString(DbConstants.PROPERTY_ZIP));
            p.setCountry(result.getString(DbConstants.PROPERTY_COUNTRY));
            p.setPrimaryResidence(result.getBoolean(DbConstants.PROPERTY_PRIMARYRESIDENCE));
            p.setSize(result.getString(DbConstants.PROPERTY_SIZE));
            p.setCurrentResident(result.getString(DbConstants.PROPERTY_CURRENTRESIDENT));
            p.setMovedInDate(result.getString(DbConstants.PROPERTY_MOVEINDATE));
            p.setMovedOutDate(result.getString(DbConstants.PROPERTY_MOVEOUTDATE));
            p.setCreatedOn(result.getString(DbConstants.PROPERTY_CREATEDON));
            p.setCreatedBy(result.getString(DbConstants.PROPERTY_CREATEDBY));
            p.setUpdatedOn(result.getString(DbConstants.PROPERTY_UPDATEDON));
            p.setUpdatedBy(result.getString(DbConstants.PROPERTY_UPDATEDBY));
        } 
        return p;
    }

    public void UpdateProperty(Property oldRow) throws SQLException
    {
        statement = connection.createStatement();
        String sql = "Update Property SET ";
        sql += "Title = '" + oldRow.getTitle() + "',";
        sql += "Address1 = '" + oldRow.getAddress1() + "',";
        if (oldRow.getAddress2() == null)
            sql += "Address2 = null, ";
        else
            sql += "Address2 = '" + oldRow.getAddress2() + "',";
        sql += "City = '" + oldRow.getCity() + "',";
        sql += "State = '" + oldRow.getState() + "',";
        sql += "Zip = '" + oldRow.getZip() + "',";
        sql += "Country = '" + oldRow.getCountry() + "',";
        if (oldRow.isPrimaryResidence())
            sql += "isPrimaryResidence = 1,";
        else
            sql += "isPrimaryResidence = 0,";
        if (oldRow.getSize() == null)
            sql += "Size = null, ";
        else
            sql += "Size = '" + oldRow.getSize() + "',";
        if (oldRow.getCurrentResident() == null)
            sql += "CurrentResident = null, ";
        else
            sql += "CurrentResident = '" + oldRow.getCurrentResident() + "',";
        if (oldRow.getMovedInDate() == null)
            sql += "MoveInDate = null, ";
        else
            sql += "MoveInDate = '" + oldRow.getMovedInDate() + "',";
        if (oldRow.getMovedOutDate() == null)
            sql += "MovedOutDate = null, ";
        else
            sql += "MovedOutDate = '" + oldRow.getMovedOutDate() + "',";
        if (oldRow.getCreatedBy() == null)
            sql += "CreatedBy = null, ";
        else
            sql += "CreatedBy = '" + oldRow.getCreatedBy() + "',";
        if (oldRow.getUpdatedBy() == null)
            sql += "UpdatedBy = null, ";
        else
            sql += "UpdatedBy = '" + oldRow.getUpdatedBy() + "',";
        
        sql += "UpdatedOn = getDate() WHERE Id = " + oldRow.getId();
        
        System.out.println("Executing: " + sql);
        statement.executeUpdate(sql);
    }

    public void deleteProperty(int id) throws SQLException 
    {
        String sql = "DELETE FROM Property WHERE ID = " + id;
        System.out.println(sql);
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public ArrayList<Users> getUsers(int id) throws SQLException
    {
        ArrayList<Users> users = new ArrayList<>();
        statement = connection.createStatement();
        String sql = "SELECT U.* From Users U JOIN Property P ON P.ID = U.PropertyID WHERE U.PropertyID = " + id;
        System.out.println("Executing: " + sql);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Users u = new Users();
            u.setId(result.getInt(DbConstants.USERS_ID));
            u.setFirstName(result.getString(DbConstants.USERS_FIRSTNAME));
            u.setLastName(result.getString(DbConstants.USERS_LASTNAME));
            u.setEmail(result.getString(DbConstants.USERS_EMAIL));
            u.setUserPassword(result.getString(DbConstants.USERS_USERPASSWORD));
            u.setCreatedOn(result.getString(DbConstants.USERS_CREATEDON));
            u.setCreatedBy(result.getString(DbConstants.USERS_CREATEDBY));
            users.add(u);
        }
        return users;
    }
    

}