package calculator;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.ls.LSOutput;


public class Controller {

    private String num1 = "";
    private String num2 = "";
    private String operator = "";

    // Document info
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    // Input - display
    @FXML
    private TextField display;


    // Top buttons
    @FXML
    private Button ac;

    @FXML
    private Button plus_minus;

    @FXML
    private Button percent;


    // Digit buttons
    @FXML
    private Button zero;

    @FXML
    private Button one;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button four;

    @FXML
    private Button five;

    @FXML
    private Button six;

    @FXML
    private Button seven;

    @FXML
    private Button eight;

    @FXML
    private Button nine;


    // Operator buttons
    @FXML
    private Button plus;

    @FXML
    private Button minus;

    @FXML
    private Button multiplication;

    @FXML
    private Button division;

    @FXML
    private Button coma;

    @FXML
    private Button enter;



    // On start do this
    @FXML
    void initialize() {
        System.out.println("Calculator is ready!");
    }


    // Button listeners
    @FXML
    void erase(MouseEvent event) {

        // Delete input and clear cash
        display.setText("0");
        num1 = "";
        num2 = "";
        operator = "";
        System.out.println("AC button pressed");
    }

    @FXML
    void changeSign(MouseEvent event) {
        System.out.println("+/- button pressed");

        // Get input from display
        String input = display.getText().trim();
        StringBuilder sb = new StringBuilder(input);

        if(input.startsWith("-")){
            sb.deleteCharAt(0);
        }else if(!input.equals("0")){
            sb.insert(0, '-');
        }
        display.setText(sb.toString());
        if(!num2.equals("")){
            num2 = sb.toString();
        }else{
            num1 = sb.toString();
        }
    }

    @FXML
    void getPercentage(MouseEvent event) {
        percentage();
        System.out.println("Operator: " + operator);
    }

    @FXML
    void divide(MouseEvent event) {
        if(!num1.equals("") && num2.equals("")){
            operator = "/";
        }else if(!num2.equals("")){
            calculate();
            operator = "/";
        }
        System.out.println("Operator: " + operator);
    }

    @FXML
    void multiply(MouseEvent event) {
        if(!num1.equals("") && num2.equals("")){
            operator = "*";
        }else if(!num2.equals("")){
            calculate();
            operator = "*";
        }
        System.out.println("Operator: " + operator);
    }

    @FXML
    void subtract(MouseEvent event) {
        if(!num1.equals("") && num2.equals("")){
            operator = "-";
        }else if(!num2.equals("")){
            calculate();
            operator = "-";
        }
        System.out.println("Operator: " + operator);
    }

    @FXML
    void add(MouseEvent event) {
        if(!num1.equals("") && num2.equals("")){
            operator = "+";
        }else if(!num2.equals("")){
            calculate();
            operator = "+";
        }
        System.out.println("Operator: " + operator);
    }

    @FXML
    void getResult(MouseEvent event) {
        calculate();
    }

    @FXML
    void one(MouseEvent event) {
        type('1');
    }

    @FXML
    void two(MouseEvent event) {
        type('2');
    }
    @FXML
    void three(MouseEvent event) {
        type('3');
    }
    @FXML
    void four(MouseEvent event) {
        type('4');
    }
    @FXML
    void five(MouseEvent event) {
        type('5');
    }
    @FXML
    void six(MouseEvent event) {
        type('6');
    }
    @FXML
    void seven(MouseEvent event) {
        type('7');
    }
    @FXML
    void eight(MouseEvent event) {
        type('8');
    }
    @FXML
    void nine(MouseEvent event) {
        type('9');
    }
    @FXML
    void zero(MouseEvent event) {
        type('0');
    }
    @FXML
    void coma(MouseEvent event) {
        type('.');

    }


    private String displayAsInteger(double num){
        if(num % 1 == 0){
            return String.valueOf((int)num);
        }
        return String.valueOf(num);
    }

    private void type(Character c){
        //save character to num1 if num2 and operator are empty
        if(num2.equals("") && operator.equals("")){
            if(c == '.'){
                if(num1.equals("")){
                    num1 = "0";
                }
                if(!num1.contains(".")){
                    num1 += ".";
                }
            }else{
                if(num1.equals("0") || num1.equals("error")){
                    num1 = String.valueOf(c);
                }else{
                    num1 += c;
                }
            }
            display.setText(num1);
            System.out.println(c + " is pressed and num1: " + num1);
        }
        //save character to num2 if num1 and operator are not empty
        else if(!(num1.equals("") && operator.equals(""))){
            if(c == '.'){
                if(num2.equals("")){
                    num2 = "0";
                }
                if(!num2.contains(",")){
                    num2 += ".";
                }
            }else{
                if(num2.equals("0")){
                    num2 = String.valueOf(c);
                }else{
                    num2 += c;
                }
            }
            display.setText(num2);
            System.out.println(c + " is pressed and num2: " + num2);
        }

    }

    private void calculate(){
        if(!((num1.equals("") || (num1.equals("error"))) && operator.equals("") && num2.equals(""))){
            switch(operator){
                case "+":
                    add();
                    break;
                case "-":
                    subtract();
                    break;
                case "*":
                    multiply();
                    break;
                case "/":
                    divide();
                    break;
                default:
                    break;
            }
            display.setText(num1);
            System.out.println("calculate() num1: " + num1);
            if(num1.equals("error")){
                num1 = "";
            }
        }
    }

    private void add(){
            try{
                double result = Double.parseDouble(num1) + Double.parseDouble(num2); // convert from string to double
                System.out.println(num1 + " + " + num2 + " = " + result);
                num1 = displayAsInteger(result);
                num2 = "";
                operator = "";
                System.out.println("add() result: " + num1);
            }catch (NumberFormatException e) {
                System.out.println(e);
            }
    }

    private void subtract(){
        try{
            double result = Double.parseDouble(num1) - Double.parseDouble(num2); // convert from string to double
            System.out.println(num1 + " - " + num2 + " = " + result);
            num1 = displayAsInteger(result);
            num2 = "";
            operator = "";
            System.out.println("subtract() result: " + num1);
        }catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void multiply(){
        try{
            double result = Double.parseDouble(num1) * Double.parseDouble(num2); // convert from string to double
            System.out.println(num1 + " * " + num2 + " = " + result);
            num1 = displayAsInteger(result);
            num2 = "";
            operator = "";
            System.out.println("multiply() result: " + num1);
        }catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void divide(){
        try{
            if(!displayAsInteger(Double.parseDouble(num2)).equals("0")){
                double result = Double.parseDouble(num1) / Double.parseDouble(num2); // convert from string to double
                System.out.println(num1 + " / " + num2 + " = " + result);
                num1 = displayAsInteger(result);
                num2 = "";
                operator = "";
                System.out.println("divide() result: " + num1);
            }else{
                System.out.println("division by 0");
                num1 = "error";
                num2 = "";
                operator = "";

            }

        }catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void percentage(){
        try{
            double result = 0;
            if(!(num1.equals("") || num1.equals("error")) && num2.equals("")){
                result = Double.parseDouble(num1) * 0.01;

            }else if(!((num1.equals("") || num1.equals("error")) && num2.equals(""))){
                result = (Double.parseDouble(num1) * Double.parseDouble(num2)) * 0.01;

            }
            //num1 = displayAsInteger(result);
            DecimalFormat df = new DecimalFormat("#");
            df.setMaximumFractionDigits(8);
            num1 = df.format(result);
            num2 = "";
            operator = "";
            System.out.println("percentage() result: " + num1);
            display.setText(num1);

            //todo: fix bugs when press % over again the result is incorrect comparing to real calculator
            
            //todo: change the way result is displayed (example 5.0E-4  but should be 5.0004)
        }
        catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

}
