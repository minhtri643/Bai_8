    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package murach.data;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.HashMap;

import murach.business.User;
/**
 *
 * @author aothu
 */
public class UserIO {

    public static void addUser(User user, String filename) throws IOException {    
        System.out.println("Attempting to write user to file: " + filename);  // Debugging output
        File file = new File(filename);

        // Check if file exists or can be created
        if (!file.exists()) {
            System.out.println("File does not exist. Creating new file.");
            file.createNewFile();  // This will create the file if it doesn't exist
        }

        PrintWriter out = new PrintWriter(new FileWriter(file, true));
        out.println(user.getEmail() + "|" + user.getFirstName() + "|" + user.getLastName());
        out.close();

        System.out.println("User written to file: " + user.getEmail());  // Debugging output
    }
    
    // Phương thức lấy một người dùng dựa trên email
    public static User getUser(String emailList, String filename) throws IOException {
        
        File file = new File(filename);
        BufferedReader in = new BufferedReader(
                new FileReader(file));
        User user = new User();
        String line = in.readLine();
        while (line != null) {
            StringTokenizer t = new StringTokenizer(line, "|");
            String email = t.nextToken();
            if (email.equalsIgnoreCase(emailList)) {
                String firstName = t.nextToken();
                String lastName = t.nextToken();
                user.setEmail(emailList);
                user.setFirstName(firstName);
                user.setLastName(lastName);
            }
            line = in.readLine();
        }
        in.close();
        return user;
    }

    // Phương thức lấy danh sách tất cả người dùng từ tệp
    public static ArrayList<User> getUsers(String filename) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        BufferedReader in = new BufferedReader(
                new FileReader(filename));
        String line = in.readLine();
        while (line != null) {
            try {
                StringTokenizer t = new StringTokenizer(line, "|");
                String emailAddress = t.nextToken();
                String firstName = t.nextToken();
                String lastName = t.nextToken();
                User user = new User(firstName, lastName, emailAddress);
                users.add(user);
                line = in.readLine();
            } catch (NoSuchElementException e) {
                line = in.readLine();
            }
        }
        in.close();
        return users;
    }
    public static HashMap<String, User> getUsersMap(String filename) throws IOException {
         HashMap<String, User> users = new HashMap<String, User>();
        BufferedReader in = new BufferedReader(
                new FileReader(filename));
        String line = in.readLine();
        while (line != null) {
            try {
                StringTokenizer t = new StringTokenizer(line, "|");
                String emailAddress = t.nextToken();
                String firstName = t.nextToken();
                String lastName = t.nextToken();
                User user = new User(firstName, lastName, emailAddress);
                users.put(emailAddress, user);
                line = in.readLine();
            } catch (NoSuchElementException e) {
                line = in.readLine();
            }
        }
        in.close();
        return users;
    }
    
}
