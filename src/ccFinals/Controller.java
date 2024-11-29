package ccFinals;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Controller {

    @FXML
    private Stage mainWindow;

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    @FXML
    private Button WithdrawButtonWithdrawPane;

    @FXML
    private TextField amountInputDeposit;

    @FXML
    private Pane changeNamePane;

    @FXML
    private TextField amountInputWithdraw;

    @FXML
    private AnchorPane anchorWindow;

    @FXML
    private Pane balanceBox;

    @FXML
    private Text balanceDisplay;

    @FXML
    private Pane changePinPane;

    @FXML
    private Button confirmButtonChangePin;

    @FXML
    private PasswordField confirmPinInput;

    @FXML
    private Button depositButtonDepositPane;

    @FXML
    private Button depositButtonTransactionPane;

    @FXML
    private Pane depositPane;

    @FXML
    private Pane enteredPane;

    @FXML
    private Button logOutButtonTransactionPane;

    @FXML
    private Button loginLoginButton;

    @FXML
    private Pane loginPane;

    @FXML
    private TextField loginPinInput;

    @FXML
    private TextField loginUserIDInput;

    @FXML
    private Button mainLoginButton;

    @FXML
    private Pane mainPane;

    @FXML
    private Button mainRegisterButton;

    @FXML
    private Rectangle menuBox;

    @FXML
    private Line menuBoxLine;

    @FXML
    private Button nameChangeButton;

    @FXML
    private PasswordField newPinInput;

    @FXML
    private PasswordField oldPinInput;

    @FXML
    private Pane optionsPane;

    @FXML
    private Button pinChangeButton;

    @FXML
    private Button confirmButtonNameChange;

    @FXML
    private TextField newNameInput;

    @FXML
    private PasswordField pinInputNameChange;

    @FXML
    private TextField pinInputDeposit;

    @FXML
    private TextField pinInputWithdraw;

    @FXML
    private TextField registerNameInput;

    @FXML
    private Pane registerPane;

    @FXML
    private TextField registerPinInput;

    @FXML
    private Button registerRegisterButton;

    @FXML
    private TextField registerUserIDInput;

    @FXML
    private ListView<String> transactionHistoryListView;

    @FXML
    private Button returnButtonChangePin;

    @FXML
    private Button returnButtonDeposit;

    @FXML
    private Button returnButtonOptions;

    @FXML
    private Button returnButtonWithdraw;

    @FXML
    private Button settingsButtonTransactionPane;

    @FXML
    private ImageView tem;

    @FXML
    private Pane transactionPane;

    @FXML
    private Pane truePane;

    @FXML
    private Button withdrawButtonTransactionPane;

    @FXML
    private Pane withdrawPane;

    @FXML
    private Text nameText;


    //Register Button Data saving
    @FXML
    void registerRegisterButtonClick(ActionEvent event) {
        if (registrationInfoSubmission()) {
            saveUserInfo(registerNameInput.getText(), parseInt(registerPinInput.getText()), registerUserIDInput.getText());
            mainPane.setVisible(true);
            loginPane.setVisible(false);
            registerPane.setVisible(false);
        }
    }

    //Switch to mainPane
    @FXML
    void loginLoginButtonClick(ActionEvent event) {
        if ((validateLogin(loginUserIDInput.getText(), loginPinInput.getText()))) {
            truePane.setVisible(false);
            enteredPane.setVisible(true);
            balanceBox.setVisible(true);
            transactionPane.setVisible(true);
            optionsPane.setVisible(false);
            withdrawPane.setVisible(false);
            depositPane.setVisible(false);
            balanceDisplay.setText("Balance: " + Balance);
            updateTransactionHistory();


        } else {
            ; //Input Tem Dialogue Box if false
            System.out.println("Yer retarde ur smth?");
        }
    }

    //Switch to loginPane
    @FXML
    void mainLoginButtonClick(ActionEvent event) {
        mainPane.setVisible(false);
        loginPane.setVisible(true);
        registerPane.setVisible(false);
        loginPinInput.getParent().requestFocus();
    }

    //Switch to registerPane
    @FXML
    void mainRegisterButtonClick(ActionEvent event) {
        mainPane.setVisible(false);
        loginPane.setVisible(false);
        registerPane.setVisible(true);
        registerPinInput.getParent().requestFocus();
    }

    @FXML
    void WithdrawButtonWithdrawPaneClick(ActionEvent event) {
        if(validatePIN(pinInputWithdraw.getText())){
            Withdraw();
            updateTextBalance(Balance);
            balanceDisplay.setText("Balance: " + Balance);

        }
        else {
            JOptionPane.showMessageDialog(null, "Wrong PIN", "This isn't your fault.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    void depositButtonDepositPaneClick(ActionEvent event) {
        if(validatePIN(pinInputDeposit.getText())){
            Deposit();
            updateTextBalance(Balance);
            balanceDisplay.setText("Balance: " + Balance);
        }
        else {
            JOptionPane.showMessageDialog(null, "Wrong PIN", "This isn't your fault.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    void depositButtonTransactionPaneClick(ActionEvent event) {
        transactionPane.setVisible(false);
        optionsPane.setVisible(false);
        withdrawPane.setVisible(false);
        depositPane.setVisible(true);
        amountInputDeposit.getParent().requestFocus();
    }

    @FXML
    void logOutButtonTransactionPaneClick(ActionEvent event) {
        truePane.setVisible(true);
        enteredPane.setVisible(false);
        balanceBox.setVisible(false);
        Name = null;
        Balance = 0;
        mainPane.setVisible(true);
        loginPane.setVisible(false);
        registerPane.setVisible(false);
        registerPinInput.getParent().requestFocus();
        loginUserIDInput.clear();
        loginPinInput.clear();
    }

    @FXML
    void returnButtonOptionsClick(ActionEvent event) {
        transactionPane.setVisible(true);
        optionsPane.setVisible(false);
        withdrawPane.setVisible(false);
        depositPane.setVisible(false);
    }

    @FXML
    void returnButtonWithdrawClick(ActionEvent event) {
        transactionPane.setVisible(true);
        optionsPane.setVisible(false);
        withdrawPane.setVisible(false);
        depositPane.setVisible(false);
    }

    @FXML
    void returnButtonChangePinClick(ActionEvent event) {
        transactionPane.setVisible(false);
        optionsPane.setVisible(true);
        withdrawPane.setVisible(false);
        depositPane.setVisible(false);
        balanceBox.setVisible(true);
        changePinPane.setVisible(false);
        changeNamePane.setVisible(false);
    }

    @FXML
    void returnButtonNameChangeClick(ActionEvent event) {
        transactionPane.setVisible(false);
        optionsPane.setVisible(true);
        withdrawPane.setVisible(false);
        depositPane.setVisible(false);
        balanceBox.setVisible(true);
        changePinPane.setVisible(false);
        changeNamePane.setVisible(false);

    }

    @FXML
    void confirmButtonNameChangeClick(ActionEvent event) {
        if(validatePIN(pinInputNameChange.getText())){
            EditUserData("Name", newNameInput.getText());
        }
        else {
            JOptionPane.showMessageDialog(null, "Error PIN wrong", "This is your fault.", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @FXML
    void pinChangeButtonClick(ActionEvent event) {
        transactionPane.setVisible(false);
        optionsPane.setVisible(false);
        withdrawPane.setVisible(false);
        depositPane.setVisible(false);
        balanceBox.setVisible(false);
        changePinPane.setVisible(true);
        changeNamePane.setVisible(false);
        oldPinInput.getParent().requestFocus();
    }

    @FXML
    void returnButtonDepositClick(ActionEvent event) {
        transactionPane.setVisible(true);
        optionsPane.setVisible(false);
        withdrawPane.setVisible(false);
        depositPane.setVisible(false);
    }

    @FXML
    void settingsButtonTransactionPaneClick(ActionEvent event) {
        transactionPane.setVisible(false);
        optionsPane.setVisible(true);
        withdrawPane.setVisible(false);
        depositPane.setVisible(false);
    }

    @FXML
    void withdrawButtonTransactionPaneClick(ActionEvent event) {
        transactionPane.setVisible(false);
        optionsPane.setVisible(false);
        withdrawPane.setVisible(true);
        depositPane.setVisible(false);
        amountInputWithdraw.getParent().requestFocus();
    }

    @FXML
    void nameChangeButtonClick(ActionEvent event) {
        transactionPane.setVisible(false);
        optionsPane.setVisible(false);
        withdrawPane.setVisible(false);
        depositPane.setVisible(false);
        balanceBox.setVisible(false);
        changePinPane.setVisible(false);
        changeNamePane.setVisible(true);
        newNameInput.getParent().requestFocus();
    }

    @FXML
    void confirmButtonChangePinClick(ActionEvent event) {
        if(pinChangeSubmission() && newPinInput.getText().equals(confirmPinInput.getText())){
            EditUserData("PIN", newPinInput.getText());
        }
        else {
            JOptionPane.showMessageDialog(null, "New PIN does not match Confirm PIN", "This is your fault.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Initialize Data Types
    private static String Name;
    private static double Balance;
    private static String UserID;
    private static final List<String> transactionHistory = new ArrayList<>();


    //Initialize UI
    public void initialize() {
        truePane.setVisible(true);
        mainPane.setVisible(true);
        loginPane.setVisible(false);
        registerPane.setVisible(false);
        balanceBox.setVisible(false);
        enteredPane.setVisible(false);
        changePinPane.setVisible(false);
        transactionHistoryListView.setStyle("-fx-background-color: black;");
        transactionHistoryListView.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item);
                    setFont(Font.font("Comic Sans MS", FontWeight.BOLD,20));
                    setStyle("-fx-text-fill: black;");
                } else {
                    setText(null);
                }
            }
        });
    }




    //Extracting Data from text file
    private static String extractField(String line, String field) {
        String regex = field + ": (\\[.*?]|[^,]+)"; //IDK what this regex does but it works
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }




    //Saving Information from Register
    public static void saveUserInfo(String name, int PIN, String userID) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/gocep/Desktop/SceneBuilder/lib/data/userInfo.txt", true))) {
            writer.write("Name: " + name + ", PIN: " + PIN + ", UserID: " + userID + ", Balance: 0" + ", TransactionHistory: []" + "\n");
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Your information was saved successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There was some trouble saving your information, this is probably a problem on our end.", "This isn't your fault.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTransactionHistoryFile() {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/gocep/Desktop/SceneBuilder/lib/data/userInfo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String extractedUserID = extractField(line, "UserID");
                if (UserID.equals(extractedUserID)) {
                    String transactionHistoryHolder = transactionHistory.toString();
                    line = line.replaceFirst("TransactionHistory: \\[.*?]", "TransactionHistory: " + transactionHistoryHolder);
                }
                data.add(line);
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/gocep/Desktop/SceneBuilder/lib/data/userInfo.txt", false))) {
                for (String newData : data) {
                    writer.write(newData);
                    writer.newLine();
                }
            }
            updateTransactionHistory();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating transaction history in the file", "File Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    //Reading Saved Information to validate their existence in login
    public boolean validateLogin(String userID, String PIN) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/gocep/Desktop/SceneBuilder/lib/data/userInfo.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String extractedUserID = extractField(line, "UserID");
                String extractedPIN = extractField(line, "PIN");
                if (userID.equals(extractedUserID) && PIN.equals(extractedPIN)) {
                    Name = extractField(line, "Name");
                    Balance = parseDouble(extractField(line, "Balance"));
                    UserID = extractedUserID;
                    nameText.setText("heLLO, " + Name + " thi ur histor!!!");
                    transactionHistory.clear();

                    String history = extractField(line, "TransactionHistory");
                    System.out.println(extractField(line, "TransactionHistory"));
                    if(history != null && history.startsWith("[") && history.endsWith("]")) {
                        history = history.substring(1, history.length() - 1);
                        String[] historyArray = history.split(", ");
                        for(String transaction : historyArray){
                            transactionHistory.add(transaction);
                        }
                    }
                    updateTransactionHistory();
                    transactionHistoryListView.setVisible(true);
                    return true;
                }
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No users found, or file is unreachable... Please register again!", "Error Logging In", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static boolean validatePIN(String PIN) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/gocep/Desktop/SceneBuilder/lib/data/userInfo.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String extractedUserID = extractField(line, "UserID");
                String extractedPIN = extractField(line, "PIN");
                if (UserID.equals(extractedUserID) && PIN.equals(extractedPIN)) {
                    return true;
                }
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid PIN", "Error PIN", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private void updateTextBalance(double newBalance){
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/gocep/Desktop/SceneBuilder/lib/data/userInfo.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String extractedUserID = extractField(line, "UserID");
                if (UserID.equals(extractedUserID)) {
                    String extractedBalance = extractField(line, "Balance");
                    line = line.replaceFirst("Balance: " + extractedBalance, "Balance: " + newBalance);
                }
                data.add(line);
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/gocep/Desktop/SceneBuilder/lib/data/userInfo.txt", false))) {
               for (String newData : data) {
                   writer.write(newData);
                   writer.newLine();
               }
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating the balance in the file", "File Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    //Registration Validation
    private boolean registrationInfoSubmission() {
        if (registerUserIDInput.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a UserID.", "No UserID Entered.", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (registerNameInput.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your name.", "No Name Entered.", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (registerPinInput.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid PIN.", "No PIN Entered.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else {
            try {
                parseInt(registerPinInput.getText());
                if (registerPinInput.getLength() != 6) {
                    JOptionPane.showMessageDialog(null, "PIN must contain 6 characters.", "Invalid PIN", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "PIN can only consist of numbers.", "Invalid PIN", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private boolean pinChangeSubmission() {
        if (oldPinInput.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your old PIN.", "No PIN Entered.", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (newPinInput.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your name.", "No PIN Entered.", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (confirmPinInput.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid PIN.", "No PIN Entered.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else {
            try {
                parseInt(newPinInput.getText());
                if (newPinInput.getLength() != 6 || confirmPinInput.getLength() != 6) {
                    JOptionPane.showMessageDialog(null, "PIN must contain 6 characters.", "Invalid PIN", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "PIN can only consist of numbers.", "Invalid PIN", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }



    //targetData must be PIN or Name
    private static void EditUserData(String targetData, String nextData){
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/gocep/Desktop/SceneBuilder/lib/data/userInfo.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String extractedUserID = extractField(line, "UserID");
                if (UserID.equals(extractedUserID)) {
                    String extractedData = extractField(line, targetData);
                    line = line.replaceFirst(targetData + ": " + extractedData, targetData + ": "  + nextData);
                }
                data.add(line);
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/gocep/Desktop/SceneBuilder/lib/data/userInfo.txt", false))) {
               for (String newData : data) {
                   writer.write(newData);
                   writer.newLine();
               }
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating the balance in the file", "File Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    //Withdraw
    private void Withdraw(){
        try{
            int AmountInput = parseInt(amountInputWithdraw.getText());
            if(AmountInput <= Balance){
             Balance = Balance - AmountInput;
             transactionPane.setVisible(true);
             optionsPane.setVisible(false);
             withdrawPane.setVisible(false);
             depositPane.setVisible(false);
             withdrawTransactionHistory(AmountInput);
            }
            else{
                JOptionPane.showMessageDialog(null, "Insufficient Funds", "Amount Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(NumberFormatException f) {
            JOptionPane.showMessageDialog(null, "Amount should be a valid integer", "Variable Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Deposit(){
        try{
            double AmountInput = parseDouble(amountInputDeposit.getText());
            Balance += AmountInput;
            transactionPane.setVisible(true);
            optionsPane.setVisible(false);
            withdrawPane.setVisible(false);
            depositPane.setVisible(false);
            depositTransactionHistory(AmountInput);
        }
        catch(NumberFormatException f) {
            JOptionPane.showMessageDialog(null, "Amount should be a valid integer", "Variable Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void depositTransactionHistory(double amount) {
        String transaction = "+ Deposit = " + amount;
        transactionHistory.add(transaction);
        updateTransactionHistoryFile();
    }




    private void withdrawTransactionHistory(double amount) {
        String transaction = "- Withdrawn = " + amount;
        transactionHistory.add(transaction);
        updateTransactionHistoryFile();
    }




    private void updateTransactionHistory() {
        transactionHistoryListView.getItems().clear();
        transactionHistoryListView.getItems().addAll(transactionHistory);
    }


}




