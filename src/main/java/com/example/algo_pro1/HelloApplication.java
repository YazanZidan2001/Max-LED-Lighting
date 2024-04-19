package com.example.algo_pro1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HelloApplication extends Application {
    Pane  pane2;
    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane root = new Pane();
        root.setPrefSize(1100, 1021);
        root.setStyle("-fx-background-color: lightblue;");

        Label num = new Label("The number of LED ");
        num.setLayoutX(252);
        num.setLayoutY(18);
        num.setPrefSize(204, 59);
        num.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 15));

        TextField numText = new TextField();
        numText.setLayoutX(502.0);
        numText.setLayoutY(29.0);
        numText.setPrefSize(161.0, 41.0);
        numText.setFont(Font.font(20));
        numText.setStyle("-fx-border-color : black;");

        Button generate = new Button("generate");
        generate.setLayoutX(703.0);
        generate.setLayoutY(29.0);
        generate.setPrefSize(98.0, 41.0);
        generate.setStyle("-fx-font: 20px \"Serif\"; -fx-border-color: black; -fx-background-color:tan ");


        Label order = new Label("the ordering of LED");
        order.setLayoutX(21.0);
        order.setLayoutY(89.0);
        order.setPrefSize(191.0, 41.0);
        order.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 15));

        TextField orderText = new TextField();
        orderText.setLayoutX(218.0);
        orderText.setLayoutY(85.0);
        orderText.setPrefSize(845.0, 48.0);
        orderText.setEditable(false);
        orderText.setFont(Font.font(20));
        orderText.setStyle("-fx-border-color : black;");

        Label print = new Label("the DP table");
        print.setLayoutX(21);
        print.setLayoutY(474);
        print.setPrefSize(161.0, 66.0);
        print.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 20));

        TextArea table = new TextArea();
        table.setLayoutX(14);
        table.setLayoutY(540);
        table.setPrefSize(1000, 467);
        table.setEditable(false);
        table.setStyle("-fx-border-color : black;");
        Font font = Font.font("Monospaced", 20); // Example: Arial font with size 14
        table.setFont(font);

        TextField parisText1 = new TextField();
        parisText1.setLayoutX(218.0);
        parisText1.setLayoutY(153.0);
        parisText1.setPrefSize(845.0, 48.0);
        parisText1.setEditable(false);
        parisText1.setFont(Font.font(20));
        parisText1.setStyle("-fx-border-color : black;");

        Label paris = new Label("the best paris");
        paris.setLayoutX(21.0);
        paris.setLayoutY(157.0);
        paris.setPrefSize(191.0, 41.0);
        paris.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 15));


        pane2 = new Pane();
        pane2.setLayoutX(21.0);
        pane2.setLayoutY(217);
        pane2.setPrefSize(1032, 250);

        VBox nodesContainer = new VBox();
        pane2.getChildren().add(nodesContainer);

      //  numText.setEditable(false);
        generate.setOnAction(e->{


//    /****** from file*********************/
//
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("Select File");
//
//            File selectedFile = fileChooser.showOpenDialog(primaryStage);
//            int n =0;
//            List<Integer> arrayList= null;
//            if (selectedFile != null) {
//                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
//                    // Read the first line as an integer
//                     n = Integer.parseInt(reader.readLine());
//
//                    // Read the second line and parse it into an ArrayList<Integer>
//                    String secondLine = reader.readLine();
//                     arrayList = parseArrayList(secondLine);
//
//                    // Error checking
//                    if (arrayList.size() != n) {
//                        showAlert("Error", "Number of elements in the ArrayList does not equle the number of LEDs.");
//                        System.exit(1);
//                    } else if (hasDuplicates(arrayList)) {
//                        showAlert("Error", "There is a duplicate elements.");
//                        System.exit(1);
//                    } else if (hasNegativeNumbers(arrayList)) {
//                        showAlert("Error", "There is a negative numbers.");
//                        System.exit(1);
//                    } else if (hasGreaterThanFirstLine(arrayList, n)) {
//                        showAlert("Error", "The ArrayList line contains a number greater than the number of LEDs.");
//                        System.exit(1);
//
//                    } else {
//                        // Print the results
//                        System.out.println("First Line (Integer): " + n);
//                        System.out.println("Second Line (ArrayList<Integer>): " + arrayList);
//                    }
//
//                } catch (IOException | NumberFormatException a) {
//                    a.printStackTrace();
//                }
//            }
//            numText.setText(String.valueOf(n));
//            /****** from file*********************/


            /****************** from user************************/

            if(numText.getText().equals("")){
                showAlert("Error", "enter number");

            }
            int n = Integer.parseInt(numText.getText());
            ArrayList<Integer> arrayList = generateRandomPermutation(n);
            /****************** from user************************/



            int[][] dp = new int[n + 1][n + 1];


            PairResult result = findBestPairs(n, (ArrayList<Integer>) arrayList, dp);
            orderText.setText(String.valueOf(arrayList));

            String re= "";
            for (Pair pair : result.getBestPairs()) {
                re+= pair+" ";
            }
            parisText1.setText(re);
            String dpTableString = printDPTable(dp, n, (ArrayList<Integer>) arrayList);

            table.setText(dpTableString);



            ArrayList<Integer> arrayList2 = generatePermutation(n);

            Image img = new Image("file:icon1.png");
            Image img2 = new Image("file:icon2.png");

            List<StackPane> textNodes = createImageNodes(n, arrayList2, result,img);
            List<StackPane> circleNodes = createImageNodes2(arrayList, result,img2);



            // Clear existing nodes in the VBox
            nodesContainer.getChildren().clear();

            // Create an HBox for text nodes
            HBox textHBox = new HBox(); // Adjust the spacing as needed
            textHBox.getChildren().addAll(textNodes);



            // Create an HBox for circle nodes
            HBox circleHBox = new HBox(); // Adjust the spacing as needed
            circleHBox.getChildren().addAll(circleNodes);


            // Add both HBoxes to the VBox
        //    List<Line> drawnLines = drawLines(result.getBestPairs(), textNodes, circleNodes);
            nodesContainer.getChildren().addAll(textHBox, circleHBox);



        });


        root.getChildren().addAll(num, numText, order, orderText, print, table, generate, parisText1, paris,pane2);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LED Table App");
        primaryStage.show();
    }



    private List<StackPane> createImageNodes(int numberOfWords, List<Integer> orderList, PairResult result, Image img) {
        List<StackPane> imageNodes = new ArrayList<>();

        for (int i = 0; i < orderList.size(); i++) {
            StackPane stackPane = new StackPane();

            // Create an ImageView object with the image
            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(150); // Adjust size as needed
            imageView.setFitWidth(100); // Adjust size as needed

            Text numberText = new Text(String.valueOf(orderList.get(i)));
            numberText.setStyle("-fx-font: 12px \"Serif\"; -fx-fill: black;");

            stackPane.getChildren().addAll(imageView, numberText); // Add ImageView instead of Circle

            // Check if the current LED number is within the BestPairs
            for (Pair pair : result.getBestPairs()) {
                if (pair.ledIndex == orderList.get(i)) {
                    // You might want to change the image or apply an effect to indicate selection
                    imageView.setEffect(effect()); // Example effect, modify as needed
                    break; // No need to continue checking once found
                }
            }

            imageNodes.add(stackPane);
        }

        return imageNodes;
    }





    private List<StackPane> createImageNodes2(List<Integer> orderList, PairResult result, Image img) {
        List<StackPane> imageNodes = new ArrayList<>();

        for (int i = 0; i < orderList.size(); i++) {
            StackPane stackPane = new StackPane();

            // Create an ImageView object with the image
            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(150); // Adjust size as needed
            imageView.setFitWidth(100); // Adjust size as needed

            Text numberText = new Text(String.valueOf(orderList.get(i)));
            numberText.setStyle("-fx-font: 12px \"Serif\"; -fx-fill: black;");

            stackPane.getChildren().addAll(imageView, numberText); // Add ImageView instead of Circle

            // Check if the current LED number is within the BestPairs
            for (Pair pair : result.getBestPairs()) {
                if (pair.ledIndex == orderList.get(i)) {
                    // You might want to change the image or apply an effect to indicate selection
                    imageView.setEffect(effect());// Example effect, modify as needed
                    break; // No need to continue checking once found
                }
            }

            imageNodes.add(stackPane);
        }

        return imageNodes;
    }

    private DropShadow effect() {
        DropShadow glow = new DropShadow();
        glow.setWidth(25);
        glow.setHeight(25);
        glow.setRadius(25);
        glow.setSpread(0.8);
        glow.setColor(Color.GOLD); // Neon color
        return glow;
    }




    private List<Line> drawLines(List<Pair> pairs, List<StackPane> sourceNodes, List<StackPane> ledNodes) {
        List<Line> lines = new ArrayList<>();
        for (Pair pair : pairs) {
            // Assuming pair.ledIndex and pair.sourceIndex are 1-based indices
            StackPane sourceNode = sourceNodes.get(pair.sourceIndex - 1);
            StackPane ledNode = ledNodes.get(pair.ledIndex - 1);

            // Calculate the center of sourceNode
            Bounds sourceBounds = sourceNode.localToScene(sourceNode.getBoundsInLocal());
            double startX = sourceBounds.getMinX() + sourceBounds.getWidth() / 2;
            double startY = sourceBounds.getMinY() + sourceBounds.getHeight() / 2;

            // Calculate the center of ledNode
            Bounds ledBounds = ledNode.localToScene(ledNode.getBoundsInLocal());
            double endX = ledBounds.getMinX() + ledBounds.getWidth() / 2;
            double endY = ledBounds.getMinY() + ledBounds.getHeight() / 2;

            // Create the line and style it as needed
            Line line = new Line(startX, startY, endX, endY);
            line.setStroke(Color.BLACK); // set the color of the line
            line.setStrokeWidth(2); // set the width of the line

            // Add the line to the pane
            pane2.getChildren().add(line);

            // Add the line to the list of lines
            lines.add(line);
        }
        return lines;
    }



    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private List<Integer> parseArrayList(String line) {
        List<Integer> arrayList = new ArrayList<>();
        String[] numbers = line.split("\\s+"); // Use "\\s+" as the split pattern
        for (String number : numbers) {
            if (!number.isEmpty()) { // Handle cases where there might be consecutive spaces
                int num = Integer.parseInt(number);
                arrayList.add(num);
            }
        }
        return arrayList;
    }

    private boolean hasDuplicates(List<Integer> arrayList) {
        Set<Integer> set = new HashSet<>();
        for (Integer element : arrayList) {
            if (!set.add(element)) {
                return true; // Duplicate found
            }
        }
        return false; // No duplicate found
    }

    private boolean hasNegativeNumbers(List<Integer> arrayList) {
        for (Integer element : arrayList) {
            if (element < 0) {
                return true; // Negative number found
            }
        }
        return false; // No negative number found
    }

    private boolean hasGreaterThanFirstLine(List<Integer> arrayList, int firstLine) {
        for (Integer element : arrayList) {
            if (element > firstLine) {
                return true; // Number greater than the first line found
            }
        }
        return false; // No number greater than the first line found
    }

    public static void main(String[] args) {
        launch();
    }


    static class Pair {
        int ledIndex;
        int sourceIndex;

        Pair(int ledIndex, int sourceIndex) {
            this.ledIndex = ledIndex;
            this.sourceIndex = sourceIndex;
        }

        @Override
        public String toString() {
            return "(S" + ledIndex + ",L" + sourceIndex + ")";
        }
    }

    static class PairResult {
        ArrayList<Pair> bestPairs;

        PairResult(ArrayList<Pair> bestPairs) {
            this.bestPairs = bestPairs;
        }

        ArrayList<Pair> getBestPairs() {
            return bestPairs;
        }
    }


        static ArrayList<Integer> generatePermutation(int n) {
            ArrayList<Integer> permutation = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                permutation.add(i);
            }
            return permutation;
    }

    static ArrayList<Integer> generateRandomPermutation(int n) {
        ArrayList<Integer> permutation = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            permutation.add(i);
        }
        Collections.shuffle(permutation);
        return permutation;
    }



    static PairResult findBestPairs(int n, ArrayList<Integer> ledsOrder, int[][] dp) {

        // Build the dp table using dynamic programming
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (ledsOrder.get(i - 1).equals(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Backtrack to find the selected pairs
        ArrayList<Pair> bestPairs = new ArrayList<>();
        int i = n, j = n;
        while (i > 0 && j > 0) {
            if (ledsOrder.get(i - 1).equals(j)) {
                bestPairs.add(new Pair(ledsOrder.get(i - 1), i));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        Collections.reverse(bestPairs);

        return new PairResult(bestPairs);
    }

    static String printDPTable(int[][] dp, int n, ArrayList<Integer> ledsOrder) {
        StringBuilder result = new StringBuilder();
        result.append("    ");
        for (int j = 0; j <= n; j++) {
            result.append(String.format("%-4d", j));
        }
        result.append("\n");
        for (int i = 0; i <= n; i++) {
            result.append(String.format("%-4d", i));
            for (int j = 0; j <= n; j++) {
                result.append(String.format("%-4d", dp[i][j]));
            }
            result.append("\n");
        }
        return result.toString();
    }







}
