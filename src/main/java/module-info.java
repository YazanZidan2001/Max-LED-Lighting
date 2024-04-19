module com.example.algo_pro1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.algo_pro1 to javafx.fxml;
    exports com.example.algo_pro1;
}